package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter

import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities.StatusResponse
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities.UsersResponse
import io.reactivex.Observable

interface TwitterDataSource {
    fun loadTweets(userName: String): Observable<List<StatusResponse>>
    fun searchUser(userName: String): Observable<UsersResponse>
}