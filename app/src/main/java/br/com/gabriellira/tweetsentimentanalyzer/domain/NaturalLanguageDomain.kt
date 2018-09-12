package br.com.gabriellira.tweetsentimentanalyzer.domain

import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.NaturalLanguageDataSource
import br.com.gabriellira.tweetsentimentanalyzer.domain.callbacks.AnalyzeSentimentCallback
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Sentiment
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.google.SentimentMapper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NaturalLanguageDomain(
        private val dataSource: NaturalLanguageDataSource,
        private val sentimentMapper: SentimentMapper = SentimentMapper()
) {
    fun analyzeTweet(tweet: Tweet, callback: AnalyzeSentimentCallback) {
        dataSource
                .analyzeText(tweet.text)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { sentimentMapper.mapDocumentToSentiment(it.documentSentiment) }
                .flatMap { Observable.just(it) }
                .subscribe(object: Observer<Sentiment> {
                    override fun onComplete() = Unit

                    override fun onSubscribe(d: Disposable) = Unit

                    override fun onNext(analyzedSentiment: Sentiment) {
                        with (tweet) {
                            val analyzedTweet = Tweet(
                                    id,
                                    text,
                                    creationDate,
                                    analyzedSentiment
                            )

                            callback.onSentimentAnalyzed(analyzedTweet)
                        }
                    }

                    override fun onError(error: Throwable) = callback.onErrorAnalysingSentiment(error)
                })

    }
}