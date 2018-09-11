package br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter

import br.com.gabriellira.tweetsentimentanalyzer.domain.STATUS_RESPONSE_DATE_FORMAT
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.StatusResponse
import java.text.SimpleDateFormat
import java.util.*

class TweetMapper {

    fun statusResponseToTweet(status: StatusResponse): Tweet {
        with(status) {
            val createdAtDate = SimpleDateFormat(STATUS_RESPONSE_DATE_FORMAT, Locale.ENGLISH).parse(created_at)
            return Tweet(
                    id,
                    text,
                    createdAtDate
            )
        }
    }
}