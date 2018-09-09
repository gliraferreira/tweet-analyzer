package br.com.gabriellira.tweetsentimentanalyzer.domain.entities.network.twitter

data class UsersResponse (
        val id: Long,
        val name: String,
        val screen_name: String,
        val profile_background_image_url: String,
        val profile_image_url: String
)