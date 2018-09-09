package br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.interceptors

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class RequestAccessTokenInterceptor(
        private val consumerKey: String,
        private val secretKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authorization = Credentials.basic(consumerKey, secretKey)

        val requestWithCredentials = chain
                .request()
                .newBuilder()
                .addHeader("Authorization", authorization)
                .build()

        return chain.proceed(requestWithCredentials)
    }
}