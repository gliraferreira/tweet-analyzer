package br.com.gabriellira.tweetsentimentanalyzer.domain.callbacks

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet

interface LoadTweetsCallback {
    fun onTweetsLoaded(tweets: List<Tweet>)
    fun onTweetsLoadingFailed(error: Throwable)
}