package br.com.gabriellira.tweetsentimentanalyzer.domain.entities

import java.util.*

data class Tweet(
        val id: Long,
        val text: String,
        val creationDate: Date,
        val sentiment: Sentiment = Sentiment.UNKNOWN)