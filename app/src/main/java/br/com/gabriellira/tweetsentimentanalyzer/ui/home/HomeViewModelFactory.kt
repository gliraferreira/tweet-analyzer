package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
        private val twitterDomain: TwitterDomain
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(twitterDomain) as T
        } else {
            throw IllegalArgumentException("HomeViewModel not found")
        }
    }
}