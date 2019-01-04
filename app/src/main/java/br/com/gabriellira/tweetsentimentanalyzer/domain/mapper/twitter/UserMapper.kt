package br.com.gabriellira.tweetsentimentanalyzer.domain.mapper.twitter

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.entities.UserResponse

class UserMapper {
    fun mapUserFromResponse(response: UserResponse) : User {
        with(response) {
            return User(
                    id,
                    userName = screen_name,
                    name = name,
                    bannerUrl = profile_banner_url,
                    profilePictureUrl = profile_image_url_https
            )
        }
    }

    fun replaceImageSize(user: User) : User {
        with(user) {
            return User(
                    id,
                    userName,
                    name,
                    bannerUrl,
                    profilePictureUrl.replace("_normal", "")
            )
        }
    }
}