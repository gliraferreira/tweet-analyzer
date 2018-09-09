package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.network.twitter.StatusResponse
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.network.twitter.UsersResponse
import io.reactivex.Observable

interface TwitterDataSource {
    fun loadTweets(userName: String): Observable<List<StatusResponse>>
    fun searchUser(userName: String): Observable<UsersResponse>
}