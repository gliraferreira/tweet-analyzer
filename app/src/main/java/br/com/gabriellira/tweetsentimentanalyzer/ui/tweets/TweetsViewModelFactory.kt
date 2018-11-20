package br.com.gabriellira.tweetsentimentanalyzer.ui.tweets

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.gabriellira.tweetsentimentanalyzer.domain.NaturalLanguageDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain

class TweetsViewModelFactory(
        private val twitterDomain: TwitterDomain,
        private val naturalLanguageDomain: NaturalLanguageDomain
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TweetsViewModel::class.java)) {
            TweetsViewModel(twitterDomain, naturalLanguageDomain) as T
        } else {
            throw IllegalArgumentException("TweetsViewModel not found")
        }
    }
}