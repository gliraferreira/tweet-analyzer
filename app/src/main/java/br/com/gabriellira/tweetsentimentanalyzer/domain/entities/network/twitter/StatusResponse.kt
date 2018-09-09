package br.com.gabriellira.tweetsentimentanalyzer.domain.entities.network.twitter

data class StatusResponse (
        val created_at: String,
        val id: Long,
        val text: String
)