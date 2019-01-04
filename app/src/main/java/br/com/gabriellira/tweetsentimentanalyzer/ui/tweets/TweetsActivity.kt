package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppModule
import br.com.gabriellira.tweetsentimentanalyzer.di.presentation.DaggerPresentationComponent
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.SentimentStatus
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User
import br.com.gabriellira.tweetsentimentanalyzer.ui.utils.argument
import br.com.gabriellira.tweetsentimentanalyzer.ui.utils.showToast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tweets.*
import kotlinx.android.synthetic.main.loading_view.loadingHolder
import javax.inject.Inject

class TweetsActivity : AppCompatActivity() {

    @Inject
    lateinit var tweetsViewModelFactory: TweetsViewModelFactory

    private lateinit var adapter: TweetsAdapter

    private lateinit var viewModel: TweetsViewModel

    private val userExtra by argument<User>(USER_EXTRA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)

        DaggerPresentationComponent
                .builder()
                .appModule(AppModule(App.instance))
                .build().inject(this)

        setupToolbar()
        setupTweetsAdapter()

        viewModel = ViewModelProviders.of(this, this.tweetsViewModelFactory).get(TweetsViewModel::class.java)
        observeTweets()
        observeAnalyzedTweet()
        loadUserInfo()
        viewModel.loadTweets(userExtra.userName)
    }

    private fun loadUserInfo() {
        profile_name.setTypeface(profile_name.typeface, Typeface.BOLD)
        profile_name.text = userExtra.name
        with(userExtra) {
            Glide
                    .with(this@TweetsActivity)
                    .load(profilePictureUrl)
                    .into(profile_photo)
            Glide
                    .with(this@TweetsActivity)
                    .load(bannerUrl)
                    .into(profile_header_photo)
        }
    }

    private fun observeAnalyzedTweet() {
        viewModel.analyzedTweet.observe(this, Observer {
            when (it?.second) {
                TweetsViewModel.Status.LOADING -> it.first?.let { tweet -> adapter.replaceItem(tweet) }
                TweetsViewModel.Status.SUCCESS -> it.first?.let { tweet -> adapter.replaceItem(tweet) }
                TweetsViewModel.Status.ERROR -> displayTweetAnalyzedError()
            }
        })
    }

    private fun observeTweets() {
        viewModel.tweets.observe(this, Observer {
            when (it?.second) {
                TweetsViewModel.Status.LOADING -> displayLoadingUI()
                TweetsViewModel.Status.SUCCESS -> {
                    hideLoadingUI()
                    it.first?.let { tweets -> loadTweets(tweets) }
                }
                TweetsViewModel.Status.ERROR -> {
                    hideLoadingUI()
                    displayEmptyListUI()
                }
            }
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = " "

        app_bar_layout?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val maxScroll = app_bar_layout?.totalScrollRange ?: 0
            val maxScrollOffset = maxScroll * SCROLL_MIN_OFFSET_PERCENTAGE
            val relativeOffset = Math.max(0f, Math.abs(verticalOffset) - (maxScroll - maxScrollOffset))
            val percentage = relativeOffset / maxScrollOffset

            val alpha = if (percentage > 0) 0f else 1f
            val alphaPercentage = (percentage * 255).toInt()

            profile_photo.alpha = alpha
            profile_name.alpha = alpha
            collapsing_toolbar?.background?.alpha = alphaPercentage
            if (alpha == 0f) {
                setListPaddingTop(RECYCLER_VIEW_PADDING_TOP - relativeOffset.toInt())
                collapsing_toolbar?.title = userExtra.name
            } else {
                setListPaddingTop(RECYCLER_VIEW_PADDING_TOP)
                collapsing_toolbar?.title = " "
            }
        })
    }

    private fun setListPaddingTop(top: Int) {
        with(tweets_recyclerview) {
            setPadding(
                    paddingLeft,
                    top,
                    paddingRight,
                    paddingBottom
            )
        }
    }

    private fun setupTweetsAdapter() {
        adapter = TweetsAdapter { viewModel.analyzeTweet(it)}
        tweets_recyclerview.layoutManager = LinearLayoutManager(baseContext)
        tweets_recyclerview.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun loadTweets(tweets: List<Tweet>) {
        adapter.setTweets(tweets)
    }

    fun displayEmptyListUI() {
        tweets_label.text = getString(R.string.empty_tweets_error)
        tweets_label.visibility = View.VISIBLE
        tweets_recyclerview.visibility = View.GONE
        loadingHolder.visibility = View.GONE
    }

    fun displayTweetAnalyzedError() {
        showToast(getString(R.string.tweet_analyze_error))
    }

    fun displayLoadingUI() {
        tweets_recyclerview.visibility = View.GONE
        tweets_label.visibility = View.GONE
        loadingHolder.visibility = View.VISIBLE
    }

    fun hideLoadingUI() {
        displayList()
    }

    private fun displayList() {
        tweets_recyclerview.visibility = View.VISIBLE
        tweets_label.visibility = View.GONE
        loadingHolder.visibility = View.GONE
    }

    companion object {
        const val USER_EXTRA = "user"
        private const val SCROLL_MIN_OFFSET_PERCENTAGE = 0.4F
        private const val RECYCLER_VIEW_PADDING_TOP = 150
    }
}
