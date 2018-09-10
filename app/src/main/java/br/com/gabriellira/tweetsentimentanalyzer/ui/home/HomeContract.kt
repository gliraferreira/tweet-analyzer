package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User
import br.com.gabriellira.tweetsentimentanalyzer.ui.base.BaseContract

class HomeContract {
    interface View : BaseContract.View {
        fun displayTweetsList(user: User)
        fun onSearchResultError()
        fun displayUserNameRequiredError()
        fun displayUserNotFoundError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun searchUser(userName: String)
    }
}