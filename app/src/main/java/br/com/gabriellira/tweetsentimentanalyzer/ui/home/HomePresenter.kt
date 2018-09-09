package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import javax.inject.Inject

class HomePresenter(private val twitterDomain: TwitterDomain) : HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    override fun searchUser(userName: String) {
        view.displayLoadingUI()
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
        view.resetLayout()
    }
}