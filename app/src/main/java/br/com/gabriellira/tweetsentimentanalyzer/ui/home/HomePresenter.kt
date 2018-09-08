package br.com.gabriellira.tweetsentimentanalyzer.ui.home

class HomePresenter : HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    override fun searchUser(userName: String) {
        view.displayLoadingUI()
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
        view.resetLayout()
    }
}