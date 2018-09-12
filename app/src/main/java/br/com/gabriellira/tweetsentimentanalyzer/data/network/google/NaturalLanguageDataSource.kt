package br.com.gabriellira.tweetsentimentanalyzer.data.network.google

import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities.AnalyzeSentimentResponse
import io.reactivex.Observable

interface NaturalLanguageDataSource {
    fun analyzeText(text: String): Observable<AnalyzeSentimentResponse>
}