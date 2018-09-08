package br.com.gabriellira.tweetsentimentanalyzer.di

import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeActivity
import dagger.Component

@Component(modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}