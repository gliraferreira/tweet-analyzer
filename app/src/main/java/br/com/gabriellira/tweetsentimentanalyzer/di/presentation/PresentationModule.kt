package br.com.gabriellira.tweetsentimentanalyzer.di.presentation

import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.TwitterDataSource
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeContract
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {
    @Provides
    fun providePresenter(twitterDomain: TwitterDomain): HomeContract.Presenter {
        return HomePresenter(twitterDomain)
    }
}