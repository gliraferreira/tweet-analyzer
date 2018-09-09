package br.com.gabriellira.tweetsentimentanalyzer.di.app

import android.app.Application
import android.content.Context
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.repositories.database.HawkTwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.repositories.database.TwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.TwitterDataSource
import br.com.gabriellira.tweetsentimentanalyzer.repositories.network.twitter.TwitterRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideApplication(): Application = app;

    @Provides
    fun provideTwitterDomain(twitterDataSource: TwitterDataSource): TwitterDomain {
        return TwitterDomain(twitterDataSource)
    }

    @Provides
    fun provideTwitterSettings(): TwitterSettings {
        return HawkTwitterSettings(app.applicationContext)
    }

    @Provides
    fun provideTwitterDataSource(settings: TwitterSettings): TwitterDataSource {
        return TwitterRepository(settings)
    }
}