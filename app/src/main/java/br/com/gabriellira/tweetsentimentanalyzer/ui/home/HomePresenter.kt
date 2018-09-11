package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.exceptions.twitter.TwitterUserNotFoundException
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User
import br.com.gabriellira.tweetsentimentanalyzer.domain.LoadUserCallback

class HomePresenter(
        private val twitterDomain: TwitterDomain
) : HomeContract.Presenter, LoadUserCallback {

    private lateinit var view: HomeContract.View

    override fun searchUser(userName: String) {
        view.displayLoadingUI()
        if (userName.isEmpty()) {
            view.hideLoadingUI()
            view.displayUserNameRequiredError()
        } else {
            twitterDomain.loadUser(userName, this)
        }
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
        view.resetLayout()
    }

    override fun onUserLoaded(user: User) {
        view.hideLoadingUI()
        view.displayTweetsList(user)
    }

    override fun onUserLoadingFailed(error: Throwable) {
        view.hideLoadingUI()
        when(error) {
            is TwitterUserNotFoundException -> view.displayUserNotFoundError()
            else -> view.onSearchResultError()
        }
    }
}