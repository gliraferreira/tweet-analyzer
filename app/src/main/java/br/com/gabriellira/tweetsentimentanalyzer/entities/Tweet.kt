package br.com.gabriellira.tweetsentimentanalyzer.entities

import java.util.*

data class Tweet(
        val id: Long,
        val text: String,
        val creationTimestamp: Date,
        val sentiment: Sentiment = Sentiment.NotAnalyzed)