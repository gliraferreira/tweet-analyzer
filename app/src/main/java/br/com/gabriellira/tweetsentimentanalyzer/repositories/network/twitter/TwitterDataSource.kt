package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter

import br.com.gabriellira.tweetsentimentanalyzer.domain.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.User
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities.AuthResponse
import io.reactivex.Observable

interface TwitterDataSource {
    fun loadTweets(userName: String): Observable<List<Tweet>>
    fun searchUser(userName: String): Observable<User>
    fun requestAuth(): Observable<AuthResponse>
}