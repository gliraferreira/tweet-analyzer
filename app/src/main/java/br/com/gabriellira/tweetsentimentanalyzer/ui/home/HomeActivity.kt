package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.di.DaggerHomeComponent
import br.com.gabriellira.tweetsentimentanalyzer.entities.User
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {
    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DaggerHomeComponent.builder().build().inject(this)
        presenter.attach(this)

        home_btn_search_user.setOnClickListener { onSearchButtonClicked() }
    }

    private fun onSearchButtonClicked() {
        val userName = home_et_username.text.toString()
        presenter.searchUser(userName)
    }

    override fun displayTweetsList(user: User) {
    }

    override fun onSearchResultError() {
    }

    override fun displayLoadingUI() {
        resetLayout()
        home_label.visibility = View.GONE
        home_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoadingUI() {
        resetLayout()
    }

    override fun resetLayout() {
        home_label.visibility = View.VISIBLE
        home_progress_bar.visibility = View.GONE
        home_error_label.visibility = View.GONE
    }
}
