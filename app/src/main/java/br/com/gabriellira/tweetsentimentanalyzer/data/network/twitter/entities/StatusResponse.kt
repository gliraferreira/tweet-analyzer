package br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities

data class StatusResponse (
        val created_at: String,
        val id: Long,
        val full_text: String
)