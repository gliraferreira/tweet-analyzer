package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User
import br.com.gabriellira.tweetsentimentanalyzer.ui.base.BaseContract

class TweetsContract {
    interface View : BaseContract.View {
        fun loadTweets(tweets: List<Tweet>)
        fun displayEmptyListUI()
        fun displayTweetAnalyzedSuccess(tweet: Tweet)
        fun displayTweetAnalyzedError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun analyzeTweet(tweet: Tweet)
    }
}