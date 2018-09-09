package br.com.gabriellira.tweetsentimentanalyzer.di.presentation

import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppModule
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeActivity
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomePresenter
import dagger.Component

@Component(modules = arrayOf(
        PresentationModule::class,
        AppModule::class
))
interface PresentationComponent {

    fun inject(homeActivity: HomeActivity)

    fun inject(homePresenter: HomePresenter)
}