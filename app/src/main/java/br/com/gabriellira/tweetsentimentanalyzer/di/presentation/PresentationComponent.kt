package br.com.gabriellira.tweetsentimentanalyzer.di.presentation

import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppModule
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeActivity
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeViewModelFactory
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsActivity
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsViewModelFactory
import dagger.Component

@Component(modules = arrayOf(
        PresentationModule::class,
        AppModule::class
))
interface PresentationComponent {

    fun inject(homeActivity: HomeActivity)

    fun inject(tweetsActivity: TweetsActivity)

    fun inject(homeViewModelFactory: HomeViewModelFactory)

    fun inject(tweetsViewModelFactory: TweetsViewModelFactory)
}
