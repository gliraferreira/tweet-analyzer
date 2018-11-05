package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User

class HomeViewModel(
        private val twitterDomain: TwitterDomain
) : ViewModel() {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    private val user = MutableLiveData<Pair<User?, Status>>()

    fun getUser() = user as LiveData<Pair<User?, Status>>

    fun searchUser(userName: String) {
        user.value = Pair(null, Status.LOADING)
        twitterDomain.loadUserInfo(userName, {
            user.value = Pair(it, Status.SUCCESS)
        }, {
            user.value = Pair(null, Status.ERROR)
        })
    }
}
