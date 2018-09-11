package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet

class TweetsActivity : AppCompatActivity(), TweetsContract.View {
    override fun loadTweets(tweets: List<Tweet>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayEmptyListUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayTweetAnalyzedSuccess(tweet: Tweet) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayTweetAnalyzedError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayLoadingUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resetLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)
    }
}
