package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.Tweet
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class HomePresenter(private val twitterDomain: TwitterDomain) : HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    override fun searchUser(userName: String) {
        view.displayLoadingUI()
        twitterDomain.getTweets(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Tweet>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Tweet>) {
                        view.hideLoadingUI()
                    }

                    override fun onError(e: Throwable) {
                        view.hideLoadingUI()
                    }
                })
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
        view.resetLayout()
    }
}