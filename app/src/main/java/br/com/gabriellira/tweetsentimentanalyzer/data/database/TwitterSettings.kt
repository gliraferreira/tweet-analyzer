package br.com.gabriellira.tweetsentimentanalyzer.data.database

interface TwitterSettings {
    fun saveAccessToken(token: String)
    fun hasAccessToken(): Boolean
    fun getAccessToken(): String
}