package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.di.DaggerHomeComponent
import br.com.gabriellira.tweetsentimentanalyzer.entities.User
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DaggerHomeComponent.builder().build().inject(this)
        presenter.attach(this)
    }

    override fun displayTweetsList(user: User) {
    }

    override fun onSearchResultError() {
    }

    override fun displayLoadingUI() {
    }

    override fun hideLoadingUI() {
    }
}
