package br.com.gabriellira.tweetsentimentanalyzer.di

import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeContract
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule {
    @Provides
    fun providePresenter(): HomeContract.Presenter {
        return HomePresenter()
    }
}