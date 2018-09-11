package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppModule
import br.com.gabriellira.tweetsentimentanalyzer.di.presentation.DaggerPresentationComponent
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User
import br.com.gabriellira.tweetsentimentanalyzer.ui.utils.argument
import kotlinx.android.synthetic.main.activity_tweets.*
import javax.inject.Inject

class TweetsActivity : AppCompatActivity(), TweetsContract.View {
    @Inject
    lateinit var presenter: TweetsContract.Presenter

    private lateinit var adapter: TweetsAdapter

    private val userExtra by argument<User>(USER_EXTRA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)

        DaggerPresentationComponent
                .builder()
                .appModule(AppModule(App.instance))
                .build().inject(this)

        setupTweetsAdapter()
        presenter.attach(this)
    }

    private fun setupTweetsAdapter() {
        adapter = TweetsAdapter { presenter.analyzeTweet(it) }
        tweets_recyclerview.layoutManager = LinearLayoutManager(baseContext)
        tweets_recyclerview.adapter = adapter
    }

    override fun getUser(): User {
        return userExtra
    }

    override fun loadTweets(tweets: List<Tweet>) {
        adapter.setTweets(tweets)
    }

    override fun displayEmptyListUI() {
    }

    override fun displayTweetAnalyzedSuccess(tweet: Tweet) {
    }

    override fun displayTweetAnalyzedError() {
    }

    override fun displayLoadingUI() {
    }

    override fun hideLoadingUI() {
    }

    override fun resetLayout() {
    }

    companion object {
        const val USER_EXTRA = "user"
    }
}
