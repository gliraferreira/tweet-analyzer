package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.gabriellira.tweetsentimentanalyzer.domain.NaturalLanguageDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.SentimentStatus
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet

class TweetsViewModel(
        private val twitterDomain: TwitterDomain,
        private val naturalLanguageDomain: NaturalLanguageDomain
) : ViewModel() {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    private val _tweets = MutableLiveData<Pair<List<Tweet>?, Status>>()
    val tweets: LiveData<Pair<List<Tweet>?, Status>>
        get() = _tweets

    private val _analyzedTweet = MutableLiveData<Pair<Tweet?, Status>>()
    val analyzedTweet: LiveData<Pair<Tweet?, Status>>
        get() = _analyzedTweet

    fun loadTweets(userName: String) {
        _tweets.value = Pair(null, Status.LOADING)
        twitterDomain.loadTweetsList(
                userName,
                success = { _tweets.value = Pair(it, Status.SUCCESS) },
                error = { _tweets.value = Pair(null, Status.ERROR) }
        )
    }

    fun analyzeTweet(tweet: Tweet) {
        _analyzedTweet.value = Pair(tweet.copy(sentimentStatus = SentimentStatus.ANALYZING), Status.LOADING)
        naturalLanguageDomain.analyzeTweet(
                tweet,
                onSuccess = { _analyzedTweet.value = Pair(it.copy(sentimentStatus = SentimentStatus.ANALYZED), Status.SUCCESS) },
                onError = { _analyzedTweet.value = Pair(null, Status.ERROR) }
        )
    }

}