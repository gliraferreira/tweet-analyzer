package br.com.gabriellira.tweetsentimentanalyzer.entities

data class User(
        val id: Long,
        val userName: String,
        val name: String,
        val bannerUrl: String,
        val profilePictureUrl: String)