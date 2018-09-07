package br.com.gabriellira.tweetsentimentanalyzer.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.gabriellira.tweetsentimentanalyzer.R
import br.com.gabriellira.tweetsentimentanalyzer.entities.User

class HomeActivity : AppCompatActivity(), HomeContract.View {
    override fun displayTweetsList(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchResultError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayLoadingUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
