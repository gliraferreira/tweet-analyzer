package br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter

import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.StatusResponse
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.UserResponse
import io.reactivex.Observable

interface TwitterDataSource {
    fun loadTweets(userName: String): Observable<List<StatusResponse>>
    fun searchUser(userName: String): Observable<UserResponse>
}