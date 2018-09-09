package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities

data class StatusResponse (
        val created_at: String,
        val id: Long,
        val text: String
)