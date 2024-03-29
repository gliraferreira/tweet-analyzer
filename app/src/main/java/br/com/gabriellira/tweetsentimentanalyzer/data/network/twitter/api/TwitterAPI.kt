package br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.api

import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.StatusResponse
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.UserResponse
import io.reactivex.Observable
import retrofit2.http.*

interface TwitterAPI {
    @GET("/1.1/statuses/user_timeline.json")
    fun getTweets(
            @Query("screen_name") screenName: String,
            @Query("include_rts") includeRts: Boolean = false,
            @Query("exclude_replies") excludeReplies: Boolean = true,
            @Query("tweet_mode") tweet_mode: String = "extended",
            @Query("trim_user") trimUser: String = "t"
    ): Observable<List<StatusResponse>>

    @GET("/1.1/users/show.json")
    fun getTwitterUser(@Query("screen_name") screenName: String): Observable<UserResponse>
}