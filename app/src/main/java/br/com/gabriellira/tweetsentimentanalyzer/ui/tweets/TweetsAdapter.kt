package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Sentiment
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.SentimentStatus
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.ui.utils.toDisplayFormat
import kotlinx.android.synthetic.main.tweet_item.view.*

class TweetsAdapter(
        private val tweetListner: (Tweet) -> Unit
) : RecyclerView.Adapter<TweetsAdapter.TweetViewHolder>() {

    private val tweets: MutableList<Tweet> = ArrayList()

    fun setTweets(tweetList: List<Tweet>) {
        tweets.clear()
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }

    fun replaceItem(tweet: Tweet) {
        val index = tweets.indexOfFirst { it.id == tweet.id }
        tweets.set(index, tweet)
        notifyItemChanged(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.tweet_item, parent, false)
        return TweetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.bind(tweet, tweetListner)
    }

    class TweetViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(tweet: Tweet, tweetListner: (Tweet) -> Unit) {
            with (itemView) {
                tweet_item_text.text = tweet.text
                tweet_item_created_date.text = tweet.creationDate.toDisplayFormat()

                when(tweet.sentimentStatus) {
                    SentimentStatus.ANALYZING -> displayLoading()
                    SentimentStatus.NOT_ANALYZED -> {
                        tweet_item_btn_analyze.setOnClickListener {
                            tweetListner(tweet)
                        }
                        displayButton()
                    }
                    SentimentStatus.ANALYZED -> {
                        val color = ContextCompat.getColor(itemView.context, getColorFromSentiment(tweet.sentiment))

                        val background = tweet_item_container_sentiment.background as GradientDrawable
                        background.setColor(color)

                        tweet_item_img_sentiment.setImageResource(getIconFromSentiment(tweet.sentiment))
                        tweet_item_tv_sentiment.setText(getNameFromSentiment(tweet.sentiment))

                        displaySentiment()
                    }
                }
            }
        }

        private fun View.displayLoading() {
            tweet_item_btn_analyze.visibility = View.GONE
            tweet_item_container_sentiment.visibility = View.GONE
            tweet_item_container_sentiment_loading.visibility = View.VISIBLE
        }

        private fun View.displaySentiment() {
            tweet_item_btn_analyze.visibility = View.GONE
            tweet_item_container_sentiment_loading.visibility = View.GONE
            tweet_item_container_sentiment.visibility = View.VISIBLE
        }

        private fun View.displayButton() {
            tweet_item_btn_analyze.visibility = View.VISIBLE
            tweet_item_container_sentiment.visibility = View.GONE
            tweet_item_container_sentiment_loading.visibility = View.GONE
        }

        private fun getColorFromSentiment(sentiment: Sentiment): Int {
            return when(sentiment) {
                Sentiment.HAPPY -> R.color.happy_color
                Sentiment.SAD -> R.color.sad_color
                else -> R.color.neutral_color
            }
        }

        private fun getIconFromSentiment(sentiment: Sentiment): Int {
            return when(sentiment) {
                Sentiment.HAPPY -> R.drawable.happy_face
                Sentiment.SAD -> R.drawable.sad_face
                else -> R.drawable.neutral_face
            }
        }

        private fun getNameFromSentiment(sentiment: Sentiment): Int {
            return when(sentiment) {
                Sentiment.HAPPY -> R.string.happy_sentiment
                Sentiment.SAD -> R.string.sad_sentiment
                else -> R.string.neutral_sentiment
            }
        }
    }
}