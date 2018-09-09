package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.api

import br.com.gabriellira.tweetsentimentanalyzer.domain.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.User
import io.reactivex.Observable
import retrofit2.http.*

interface TwitterAPI {
    @GET("/1.1/statuses/user_timeline.json")
    fun getTweets(@Query("screen_name") screenName: String): Observable<List<Tweet>>

    @GET("/1.1/users/show.json")
    fun getTwitterUser(@Query("screen_name") screenName: String): Observable<User>
}