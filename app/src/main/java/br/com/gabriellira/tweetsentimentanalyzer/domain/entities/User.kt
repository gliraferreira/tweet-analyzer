package br.com.gabriellira.tweetsentimentanalyzer.domain.entities

import java.io.Serializable

data class User(
        val id: Long,
        val userName: String,
        val name: String,
        val bannerUrl: String,
        val profilePictureUrl: String
) : Serializable