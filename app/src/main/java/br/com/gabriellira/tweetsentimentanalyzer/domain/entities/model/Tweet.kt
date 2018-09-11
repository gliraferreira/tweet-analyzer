package br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model

import java.util.*

data class Tweet(
        val id: Long,
        val text: String,
        val creationDate: Date,
        val sentiment: Sentiment = Sentiment.Unknown)