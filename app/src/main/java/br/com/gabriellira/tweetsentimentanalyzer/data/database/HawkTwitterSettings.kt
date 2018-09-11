package br.com.gabriellira.tweetsentimentanalyzer.data.database

import android.content.Context
import com.orhanobut.hawk.Hawk

class HawkTwitterSettings(context: Context) : TwitterSettings{

    private val access_token_key = "access_token"

    init {
        Hawk.init(context).build()
    }

    override fun saveAccessToken(token: String) {
        Hawk.put(access_token_key, token)
    }

    override fun hasAccessToken() : Boolean = Hawk.contains(access_token_key)

    override fun getAccessToken() : String = Hawk.get(access_token_key)
}