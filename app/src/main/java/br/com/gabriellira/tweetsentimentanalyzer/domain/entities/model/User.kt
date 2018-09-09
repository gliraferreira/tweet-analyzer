package br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model

data class User(
        val id: Long,
        val userName: String,
        val name: String,
        val bannerUrl: String,
        val profilePictureUrl: String)