package br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities.StatusResponse
import java.text.SimpleDateFormat
import java.util.*

class TweetMapper {

    private val StatusResponseDateFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"

    fun statusResponseToTweet(status: StatusResponse): Tweet {
        with(status) {
            val createdAtDate = SimpleDateFormat(StatusResponseDateFormat, Locale.ENGLISH).parse(created_at)
            return Tweet(
                    id,
                    text,
                    createdAtDate
            )
        }
    }
}