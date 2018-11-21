package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppModule
import br.com.gabriellira.tweetsentimentanalyzer.di.presentation.DaggerPresentationComponent
import br.com.gabriellira.tweetsentimentanalyzer.domain.entities.User
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsActivity
import br.com.gabriellira.tweetsentimentanalyzer.ui.utils.launchActivity
import kotlinx.android.synthetic.main.home_content.home_btn_search_user
import kotlinx.android.synthetic.main.home_content.home_error_label
import kotlinx.android.synthetic.main.home_content.home_et_username
import kotlinx.android.synthetic.main.home_content.home_label
import kotlinx.android.synthetic.main.loading_view.loadingHolder
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), TextView.OnEditorActionListener {

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

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        home_btn_search_user.setOnClickListener { onSearchButtonClicked() }

        home_et_username.setOnEditorActionListener(this)

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
        loadingHolder.visibility = View.VISIBLE
        home_btn_search_user.isEnabled = false
        home_et_username.isEnabled = false
    }

    private fun hideLoadingUI() {
        resetLayout()
    }

    private fun resetLayout() {
        home_label.visibility = View.VISIBLE
        loadingHolder.visibility = View.GONE
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

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onSearchButtonClicked()
        }
        return true
    }
}
