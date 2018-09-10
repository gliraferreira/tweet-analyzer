package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppModule
import br.com.gabriellira.tweetsentimentanalyzer.di.presentation.DaggerPresentationComponent
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.model.User
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {
    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DaggerPresentationComponent
                .builder()
                .appModule(AppModule(App.instance))
                .build().inject(this)
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
        home_btn_search_user.isEnabled = false
        home_et_username.isEnabled = false
    }

    override fun hideLoadingUI() {
        resetLayout()
    }

    override fun resetLayout() {
        home_label.visibility = View.VISIBLE
        home_progress_bar.visibility = View.GONE
        home_error_label.visibility = View.GONE
        home_btn_search_user.isEnabled = true
        home_et_username.isEnabled = true
    }

    override fun displayUserNameRequiredError() {
    }

    override fun displayUserNotFoundError() {
    }
}
