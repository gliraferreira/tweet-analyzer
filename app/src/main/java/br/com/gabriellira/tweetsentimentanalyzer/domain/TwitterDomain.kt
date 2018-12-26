package br.com.gabriellira.tweetsentimentanalyzer.domain

import android.annotation.SuppressLint
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.TwitterDataSource
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter.TweetMapper
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter.UserMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class TwitterDomain (
        private val dataSource: TwitterDataSource,
        private val userMapper: UserMapper = UserMapper(),
        private val tweetMapper: TweetMapper = TweetMapper(userMapper)
) {

    enum class LoadUserError {
        USER_NOT_FOUND, GENERIC_ERROR
    }

    enum class LoadTweetsError {
        EMPTY_LIST, GENERIC_ERROR
    }

    @SuppressLint("CheckResult")
    fun loadTweetsList(userName: String, success: (data: List<Tweet>) -> Unit, error: (result: LoadTweetsError) -> Unit) {
        dataSource
                .loadTweets(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it) }
                .map { tweetMapper.statusResponseToTweet(it) }
                .toList()
                .toObservable()
                .subscribe(
                        { tweets ->
                            if (tweets.isEmpty()) {
                                error(LoadTweetsError.EMPTY_LIST)
                            } else {
                                success(tweets)
                            }
                        }, { errorResult ->
                            if (errorResult is HttpException) {
                                when (errorResult.code()) {
                                    404 -> error(LoadTweetsError.EMPTY_LIST)
                                    else -> error(LoadTweetsError.GENERIC_ERROR)
                                }
                            } else {
                                error(LoadTweetsError.GENERIC_ERROR)
                            }
                        }
                )
    }

    @SuppressLint("CheckResult")
    fun loadUserInfo(userName: String, success: (result: User) -> Unit, error: (result: LoadUserError) -> Unit) {
        dataSource
                .searchUser(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { userMapper.mapUserFromResponse(it) }
                .flatMap { Observable.just(it) }
                .subscribe(
                        { user -> user?.let { success(it) } },
                        { errorResult ->
                            if (errorResult is HttpException) {
                                when (errorResult.code()) {
                                    404 -> error(LoadUserError.USER_NOT_FOUND)
                                    else -> error(LoadUserError.GENERIC_ERROR)
                                }
                            } else {
                                error(LoadUserError.GENERIC_ERROR)
                            }
                        }
                )
    }
}