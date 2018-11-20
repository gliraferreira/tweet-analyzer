package br.com.gabriellira.tweetsentimentanalyzer.di.presentation

import br.com.gabriellira.tweetsentimentanalyzer.domain.NaturalLanguageDomain
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeViewModelFactory
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideHomeViewModelFactory(twitterDomain: TwitterDomain): HomeViewModelFactory {
        return HomeViewModelFactory(twitterDomain)
    }

    @Provides
    fun provideTweetsViewModelFactory(twitterDomain: TwitterDomain, naturalLanguageDomain: NaturalLanguageDomain): TweetsViewModelFactory {
        return TweetsViewModelFactory(twitterDomain, naturalLanguageDomain)
    }
}