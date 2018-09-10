package br.com.gabriellira.tweetsentimentanalyzer.domain

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet

interface LoadTweetsCallback {
    fun onTweetsLoaded(tweets: List<Tweet>)
    fun onTweetsLoadingFailed(error: Throwable)
}