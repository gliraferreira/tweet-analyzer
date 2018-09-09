package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.api

import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.entities.AuthResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun requestAuth(@Field("grant_type") grantType: String = "client_credentials"): Observable<AuthResponse>
}