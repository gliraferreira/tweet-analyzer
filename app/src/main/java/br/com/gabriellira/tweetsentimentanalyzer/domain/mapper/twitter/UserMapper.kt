package br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.UsersResponse

class UserMapper {
    fun mapUserFromResponse(response: UsersResponse): User {
        with(response) {
            return User(
                    id,
                    userName = screen_name,
                    name = name,
                    bannerUrl = profile_background_image_url,
                    profilePictureUrl = profile_image_url
            )
        }
    }
}