package br.com.gabriellira.tweetsentimentanalyzer.domain

import android.util.Log
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.exceptions.twitter.TwitterGenericException
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.exceptions.twitter.TwitterUserNotFoundException
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter.TweetMapper
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter.UserMapper
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.TwitterDataSource
import io.reactivex.Observable
import retrofit2.HttpException

class TwitterDomain (
        private val dataSource: TwitterDataSource,
        private val userMapper: UserMapper = UserMapper(),
        private val tweetMapper: TweetMapper = TweetMapper()
) {

    fun getTweets(userName: String): Observable<List<Tweet>> {
        return dataSource
                .loadTweets(userName)BuildConfig.TwitterSecretKey
                .flatMap {
                    Log.i("Tweets", it.toString())
                    Observable.fromIterable(it)
                }
                .map {
                    Log.i("Tweets", it.toString())
                    tweetMapper.statusResponseToTweet(it)
                }
                .toList()
                .toObservable()
                .onErrorResumeNext { error: Throwable ->
                    if (error is HttpException) {
                        when (error.code()) {
                            404 -> Observable.error(TwitterUserNotFoundException())
                            else -> Observable.error(TwitterGenericException())
                        }
                    } else {
                        Observable.error(error)
                    }
                }
    }
}