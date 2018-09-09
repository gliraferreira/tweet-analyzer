package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.api

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.network.twitter.StatusResponse
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.network.twitter.UsersResponse
import io.reactivex.Observable
import retrofit2.http.*

interface TwitterAPI {
    @GET("/1.1/statuses/user_timeline.json")
    fun getTweets(@Query("screen_name") screenName: String): Observable<List<StatusResponse>>

    @GET("/1.1/users/show.json")
    fun getTwitterUser(@Query("screen_name") screenName: String): Observable<UsersResponse>
}