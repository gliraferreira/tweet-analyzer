package br.com.gabriellira.tweetsentimentanalyzer.data.network.google.entities

data class Document(
        val content: String,
        val type: String = "PLAIN_TEXT")
