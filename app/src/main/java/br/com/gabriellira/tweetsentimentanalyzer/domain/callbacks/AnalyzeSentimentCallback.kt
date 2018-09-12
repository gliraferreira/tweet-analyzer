package br.com.gabriellira.tweetsentimentanalyzer.domain.callbacks

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet

interface AnalyzeSentimentCallback {
    fun onSentimentAnalyzed(tweet: Tweet)
    fun onErrorAnalysingSentiment(t: Throwable)
}