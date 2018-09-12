package br.com.gabriellira.tweetsentimentanalyzer.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository {

    protected fun getRetrofit(baseUrl: String, interceptor: Interceptor? = null): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(getOkHttpClient(interceptor))
        }.build()
    }


    private fun getOkHttpClient(interceptor: Interceptor?): OkHttpClient {
        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY

        var builder = OkHttpClient.Builder()

        if (interceptor != null) {
            builder.addInterceptor(interceptor)
        }

        return builder.addInterceptor(log).build()
    }

}