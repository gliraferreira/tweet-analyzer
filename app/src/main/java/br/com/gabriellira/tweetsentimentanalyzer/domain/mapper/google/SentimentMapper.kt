package br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.google

import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities.DocumentSentiment
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Sentiment

class SentimentMapper {

    fun mapDocumentToSentiment(document: DocumentSentiment): Sentiment {
        return when {
            document.score < -0.25f -> Sentiment.SAD
            document.score > 0.25f -> Sentiment.HAPPY
            else -> Sentiment.NEUTRAL
        }
    }
}