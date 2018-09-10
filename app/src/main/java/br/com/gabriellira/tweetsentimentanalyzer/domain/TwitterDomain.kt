package br.com.gabriellira.tweetsentimentanalyzer.domain

import android.util.Log
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.exceptions.twitter.TweetsNotFoundException
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.exceptions.twitter.TwitterGenericException
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.exceptions.twitter.TwitterUserNotFoundException
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter.LoadUserCallback
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter.TweetMapper
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter.UserMapper
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.TwitterDataSource
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class TwitterDomain (
        private val dataSource: TwitterDataSource,
        private val userMapper: UserMapper = UserMapper(),
        private val tweetMapper: TweetMapper = TweetMapper()
) {

    fun loadTweets(userName: String, callback: LoadTweetsCallback) {
        dataSource
                .loadTweets(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it) }
                .map { tweetMapper.statusResponseToTweet(it) }
                .toList()
                .toObservable()
                .subscribe(object : Observer<List<Tweet>> {
                    override fun onComplete() {
                        //Not implemented
                    }

                    override fun onSubscribe(d: Disposable) {
                        //Not implemented
                    }

                    override fun onNext(tweets: List<Tweet>) {
                        if (tweets.isEmpty()) {
                            callback.onTweetsLoadingFailed(TweetsNotFoundException())
                        } else {
                            callback.onTweetsLoaded(tweets)
                        }
                    }

                    override fun onError(error: Throwable) {
                        if (error is HttpException) {
                            when (error.code()) {
                                404 -> callback.onTweetsLoadingFailed(TweetsNotFoundException())
                                else -> callback.onTweetsLoadingFailed(TwitterGenericException())
                            }
                        } else {
                            callback.onTweetsLoadingFailed(error)
                        }
                    }
                })
    }

    fun loadUser(userName: String, callback: LoadUserCallback) {
        dataSource
                .searchUser(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { userMapper.mapUserFromResponse(it) }
                .flatMap { Observable.just(it) }
                .subscribe(object : Observer<User> {
                    override fun onComplete() {
                        //Not implemented
                    }

                    override fun onSubscribe(d: Disposable) {
                        //Not implemented
                    }

                    override fun onNext(user: User) {
                        callback.onUserLoaded(user)
                    }

                    override fun onError(error: Throwable) {
                        if (error is HttpException) {
                            when (error.code()) {
                                404 -> callback.onUserLoadingFailed(TwitterUserNotFoundException())
                                else -> callback.onUserLoadingFailed(TwitterGenericException())
                            }
                        } else {
                            callback.onUserLoadingFailed(error)
                        }
                    }
                })
    }
}