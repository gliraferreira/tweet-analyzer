package br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities

data class AnalyzeSentimentRequest(
        val document: Document,
        val encodingType: String = "UTF8")