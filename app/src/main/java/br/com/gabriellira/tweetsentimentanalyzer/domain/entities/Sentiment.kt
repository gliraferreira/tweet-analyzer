package br.com.gabriellira.tweetsentimentanalyzer.domain.entities

enum class Sentiment {
    SAD, NEUTRAL, HAPPY, UNKNOWN, LOADING
}

enum class SentimentStatus {
    NOT_ANALYZED, ANALYZING, ANALYZED
}