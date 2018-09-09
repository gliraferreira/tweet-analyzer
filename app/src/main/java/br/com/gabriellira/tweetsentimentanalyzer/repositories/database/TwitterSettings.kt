package br.com.gabriellira.tweetsentimentanalyzer.repositories.database

import com.orhanobut.hawk.Hawk

interface TwitterSettings {
    fun saveAccessToken(token: String)
    fun hasAccessToken(): Boolean
    fun getAccessToken(): String
}