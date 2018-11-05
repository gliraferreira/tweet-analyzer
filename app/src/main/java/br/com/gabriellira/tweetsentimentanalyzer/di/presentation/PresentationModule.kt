package br.com.gabriellira.tweetsentimentanalyzer.di.presentation

import br.com.gabriellira.tweetsentimentanalyzer.domain.NaturalLanguageDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeViewModelFactory
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsContract
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideTweetsPresenter(twitterDomain: TwitterDomain, naturalLanguageDomain: NaturalLanguageDomain): TweetsContract.Presenter {
        return TweetsPresenter(twitterDomain, naturalLanguageDomain)
    }

    @Provides
    fun provideHomeViewModelFactory(twitterDomain: TwitterDomain): HomeViewModelFactory {
        return HomeViewModelFactory(twitterDomain)
    }
}