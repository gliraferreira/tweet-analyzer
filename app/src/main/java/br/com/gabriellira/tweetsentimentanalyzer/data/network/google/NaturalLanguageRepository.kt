package br.com.gabriellira.tweetsentimentanalyzer.data.network.google

import br.com.gabriellira.tweetsentimentanalyzer.BuildConfig
import br.com.gabriellira.tweetsentimentanalyzer.data.network.BaseRepository
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.api.NaturalLanguageAPI
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities.AnalyzeSentimentRequest
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities.AnalyzeSentimentResponse
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities.Document
import io.reactivex.Observable

class NaturalLanguageRepository : BaseRepository(), NaturalLanguageDataSource {

    private val api: NaturalLanguageAPI =
            getRetrofit(BuildConfig.GoogleLanguageApiURL)
                    .create(NaturalLanguageAPI::class.java)

    override fun analyzeText(text: String): Observable<AnalyzeSentimentResponse> {

        val document = Document(text)
        val request = AnalyzeSentimentRequest(document)

        return api.analyzeText(request, BuildConfig.GoogleApiKey)
    }

}