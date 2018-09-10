package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import br.com.gabriellira.tweetsentimentanalyzer.domain.LoadTweetsCallback
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class HomePresenter(private val twitterDomain: TwitterDomain) : HomeContract.Presenter, LoadTweetsCallback {
    private lateinit var view: HomeContract.View

    override fun searchUser(userName: String) {
        view.displayLoadingUI()
        if (userName.isEmpty()) {
            view.hideLoadingUI()
            view.displayUserNameRequiredError()
        } else {
            twitterDomain.getTweets(userName, this)
        }
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
        view.resetLayout()
    }

    override fun onTweetsLoaded(tweets: List<Tweet>) {
        view.hideLoadingUI()
    }

    override fun onTweetsLoadingFailed(error: Throwable) {
        view.hideLoadingUI()
        view.onSearchResultError()
    }
}