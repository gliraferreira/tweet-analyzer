package br.com.gabriellira.tweetsentimentanalyzer.domain

import java.util.*

data class Tweet(
        val id: Long,
        val text: String,
        val creationTimestamp: Date,
        val sentiment: Sentiment = Sentiment.NotAnalyzed)