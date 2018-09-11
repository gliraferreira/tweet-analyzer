package br.com.gabriellira.tweetsentimentanalyzer.di.presentation

import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomeContract
import br.com.gabriellira.tweetsentimentanalyzer.ui.home.HomePresenter
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsContract
import br.com.gabriellira.tweetsentimentanalyzer.ui.tweets.TweetsPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideHomePresenter(twitterDomain: TwitterDomain): HomeContract.Presenter {
        return HomePresenter(twitterDomain)
    }

    @Provides
    fun provideTweetsPresenter(twitterDomain: TwitterDomain): TweetsContract.Presenter {
        return TweetsPresenter(twitterDomain)
    }
}