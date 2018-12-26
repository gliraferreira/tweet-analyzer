package br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities

data class UserResponse (
        val id: Long,
        val name: String,
        val screen_name: String,
        val profile_banner_url: String,
        val profile_image_url_https: String
)