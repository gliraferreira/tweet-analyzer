package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import br.com.gabriellira.tweetsentimentanalyzer.domain.NaturalLanguageDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.callbacks.LoadTweetsCallback
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.callbacks.AnalyzeSentimentCallback
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User

class TweetsPresenter(
        private val twitterDomain: TwitterDomain,
        private val naturalLanguageDomain: NaturalLanguageDomain
) : TweetsContract.Presenter, LoadTweetsCallback, AnalyzeSentimentCallback {

    private lateinit var view: TweetsContract.View

    private lateinit var user: User;

    private lateinit var tweets: List<Tweet>

    override fun analyzeTweet(tweet: Tweet) {
        naturalLanguageDomain.analyzeTweet(tweet, this)
    }

    override fun attach(view: TweetsContract.View) {
        this.view = view
        this.view.displayLoadingUI()
        twitterDomain.loadTweets(user.userName, this)
    }

    override fun setUser(user: User) {
        this.user = user
    }

    override fun onTweetsLoaded(tweets: List<Tweet>) {
        this.tweets = tweets
        if (this.tweets.isEmpty()) {
            view.displayEmptyListUI()
        } else {
            view.loadTweets(this.tweets)
        }
        view.hideLoadingUI()
    }

    override fun onTweetsLoadingFailed(error: Throwable) {
        view.hideLoadingUI()
        view.displayEmptyListUI()
    }

    override fun onSentimentAnalyzed(tweet: Tweet) {
        var tmpTweets = tweets.toMutableList()
        val index = tmpTweets.indexOfFirst { it.id == tweet.id }
        tmpTweets[index] = tweet

        tweets = tmpTweets.toList()
        view.loadTweets(tweets)
    }

    override fun onErrorAnalysingSentiment(t: Throwable) {
        view.displayTweetAnalyzedError()
        view.loadTweets(tweets)
    }
}