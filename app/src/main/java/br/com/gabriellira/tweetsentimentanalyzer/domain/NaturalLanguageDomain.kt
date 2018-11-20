package br.com.gabriellira.tweetsentimentanalyzer.domain

import android.annotation.SuppressLint
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.NaturalLanguageDataSource
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.google.SentimentMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaturalLanguageDomain(
        private val dataSource: NaturalLanguageDataSource,
        private val sentimentMapper: SentimentMapper = SentimentMapper()
) {

    enum class AnalyzeError {
        NETWORK_ERROR, GENERIC_ERROR
    }

    @SuppressLint("CheckResult")
    fun analyzeTweet(tweet: Tweet, onSuccess: (tweet: Tweet) -> Unit, onError: (error: AnalyzeError) -> Unit) {
        dataSource
                .analyzeText(tweet.text)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { sentimentMapper.mapDocumentToSentiment(it.documentSentiment) }
                .flatMap { Observable.just(it) }
                .subscribe(
                        { onSuccess(tweet.copy(sentiment = it)) },
                        { onError(AnalyzeError.GENERIC_ERROR) }
                )

    }
}