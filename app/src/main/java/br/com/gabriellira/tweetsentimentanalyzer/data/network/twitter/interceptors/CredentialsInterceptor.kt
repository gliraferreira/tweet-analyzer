package br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.interceptors

import br.com.gabriellira.tweetsentimentanalyzer.data.database.TwitterSettings
import okhttp3.Interceptor
import okhttp3.Response

class CredentialsInterceptor (
        private val settings: TwitterSettings) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (settings.hasAccessToken()) {
            val twitterAccessToken = settings.getAccessToken()

            val modifiedRequest = originalRequest
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $twitterAccessToken")
                    .build()

            return chain.proceed(modifiedRequest)
        }

        return chain.proceed(originalRequest)
    }
}