package br.com.gabriellira.tweetsentimentanalyzer.domain.callbacks

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User

interface LoadUserCallback {
    fun onUserLoaded(user: User)
    fun onUserLoadingFailed(t: Throwable)
}