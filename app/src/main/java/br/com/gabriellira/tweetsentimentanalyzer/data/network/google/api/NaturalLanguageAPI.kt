package br.com.gabriellira.tweetsentimentanalyzer.data.network.google.api

import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities.AnalyzeSentimentRequest
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities.AnalyzeSentimentResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NaturalLanguageAPI {
    @POST("/v1/documents:analyzeSentiment")
    fun analyzeText(
            @Body request: AnalyzeSentimentRequest,
            @Query("key") key: String
    ): Observable<AnalyzeSentimentResponse>
}