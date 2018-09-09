package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter

import br.com.gabriellira.tweetsentimentanalyzer.BuildConfig
import br.com.gabriellira.tweetsentimentanalyzer.domain.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.User
import br.com.gabriellira.tweetsentimentanalyzer.repositories.database.TwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.BaseService
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.api.AuthAPI
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.api.TwitterAPI
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities.AuthResponse
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.interceptors.CredentialsInterceptor
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.interceptors.RequestAccessTokenInterceptor
import io.reactivex.Observable
import okhttp3.Interceptor

class TwitterRepository(private val settings: TwitterSettings) : BaseService(), TwitterDataSource {

    private val twitterApi: TwitterAPI =
            getRetrofit(BuildConfig.TwitterApiBaseURL, CredentialsInterceptor(settings)).create(TwitterAPI::class.java)

    private val authApi: AuthAPI =
            getRetrofit(BuildConfig.TwitterApiBaseURL,
                    RequestAccessTokenInterceptor(BuildConfig.TwitterSecretKey, BuildConfig.TwitterConsumerKey))
                    .create(AuthAPI::class.java)

    override fun loadTweets(userName: String): Observable<List<Tweet>> {
        return twitterApi
                .getTweets(userName)
    }

    override fun searchUser(userName: String): Observable<User> = twitterApi.getTwitterUser(userName)

    override fun requestAuth(): Observable<AuthResponse> = authApi.requestAuth()
}