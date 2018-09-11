package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import br.com.gabriellira.tweetsentimentanalyzer.domain.LoadTweetsCallback
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet

class TweetsPresenter(
        private val domain: TwitterDomain
) : TweetsContract.Presenter, LoadTweetsCallback {
    private lateinit var view: TweetsContract.View

    override fun analyzeTweet(tweet: Tweet) {

    }

    override fun attach(view: TweetsContract.View) {
        this.view = view
        domain.loadTweets(this.view.getUser().userName, this)
    }

    override fun onTweetsLoaded(tweets: List<Tweet>) {
        view.loadTweets(tweets)
    }

    override fun onTweetsLoadingFailed(error: Throwable) {

    }
}