package br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User

interface LoadUserCallback {
    fun onUserLoaded(user: User)
    fun onUserLoadingFailed(t: Throwable)
}