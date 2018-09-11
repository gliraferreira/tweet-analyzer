package br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter

import br.com.gabriellira.tweetsentimentanalyzer.BuildConfig
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.exceptions.twitter.TwitterAuthException
import br.com.gabriellira.tweetsentimentanalyzer.data.database.TwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.data.network.BaseRepository
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.api.AuthAPI
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.api.TwitterAPI
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.StatusResponse
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.UsersResponse
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.interceptors.CredentialsInterceptor
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.interceptors.RequestAccessTokenInterceptor
import io.reactivex.Observable

class TwitterRepository(private val settings: TwitterSettings) : BaseRepository(), TwitterDataSource {

    private val twitterApi: TwitterAPI =
            getRetrofit(BuildConfig.TwitterApiBaseURL, CredentialsInterceptor(settings)).create(TwitterAPI::class.java)

    private val authApi: AuthAPI =
            getRetrofit(BuildConfig.TwitterApiBaseURL,
                    RequestAccessTokenInterceptor(BuildConfig.TwitterConsumerKey, BuildConfig.TwitterSecretKey))
                    .create(AuthAPI::class.java)

    override fun loadTweets(userName: String): Observable<List<StatusResponse>> {
        return getAccessToken().flatMap {
            twitterApi.getTweets(userName)
        }
    }

    override fun searchUser(userName: String): Observable<UsersResponse> {
        return getAccessToken().flatMap { twitterApi.getTwitterUser(userName) }
    }

    private fun getAccessToken(): Observable<String> {
        if (settings.hasAccessToken()) {
            return Observable.just(settings.getAccessToken())
        }

        return authApi
                .getAccessToken()
                .flatMap {
                    if (it.token_type == "bearer") {
                        Observable.just(it.access_token)
                    } else {
                        Observable.error(TwitterAuthException())
                    }
                }
                .doOnNext { settings.saveAccessToken(it) }
                .onErrorResumeNext(Observable.error(TwitterAuthException()))
    }
}