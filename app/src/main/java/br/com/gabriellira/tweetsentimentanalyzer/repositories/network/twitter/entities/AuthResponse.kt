package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities

data class AuthResponse (
        val token_type: String,
        val access_token: String)