package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppModule
import br.com.gabriellira.tweetsentimentanalyzer.di.presentation.DaggerPresentationComponent
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsActivity
import br.com.gabriellira.tweetsentimentanalyzer.ui.utils.launchActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeVMFactory: HomeViewModelFactory

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DaggerPresentationComponent
                .builder()
                .appModule(AppModule(App.instance))
                .build().inject(this)

        home_btn_search_user.setOnClickListener { onSearchButtonClicked() }

        viewModel = ViewModelProviders.of(this, this.homeVMFactory).get(HomeViewModel::class.java)
        viewModel.getUser().observe(this, Observer {
            when (it?.second) {
                HomeViewModel.Status.LOADING -> displayLoadingUI()
                HomeViewModel.Status.SUCCESS -> {
                    hideLoadingUI()
                    it.first?.let { user ->  displayTweetsList(user) }
                }
                HomeViewModel.Status.ERROR -> {
                    hideLoadingUI()
                    onSearchResultError()
                }
            }
        })
    }

    private fun onSearchButtonClicked() {
        val userName = home_et_username.text.toString()
        viewModel.searchUser(userName)
    }

    private fun displayTweetsList(user: User) {
        launchActivity<TweetsActivity> {
            putExtra(TweetsActivity.USER_EXTRA, user)
        }
    }

    private fun onSearchResultError() {
        displayErrorMessage(getString(R.string.something_went_wrong))
    }

    private fun displayLoadingUI() {
        resetLayout()
        home_label.visibility = View.GONE
        home_progress_bar.visibility = View.VISIBLE
        home_btn_search_user.isEnabled = false
        home_et_username.isEnabled = false
    }

    private fun hideLoadingUI() {
        resetLayout()
    }

    private fun resetLayout() {
        home_label.visibility = View.VISIBLE
        home_progress_bar.visibility = View.GONE
        home_error_label.visibility = View.GONE
        home_btn_search_user.isEnabled = true
        home_et_username.isEnabled = true
    }

    private fun displayUserNameRequiredError() {
        displayErrorMessage(getString(R.string.user_name_required_error))
    }

    private fun displayUserNotFoundError() {
        displayErrorMessage(getString(R.string.user_not_found_error))
    }

    private fun displayErrorMessage(message: String) {
        resetLayout()
        home_label.visibility = View.GONE
        home_error_label.text = message
        home_error_label.visibility = View.VISIBLE
    }
}
