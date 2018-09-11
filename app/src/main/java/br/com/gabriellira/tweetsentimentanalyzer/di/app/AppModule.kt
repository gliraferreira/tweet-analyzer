package br.com.gabriellira.tweetsentimentanalyzer.di.app

import android.app.Application
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.data.database.HawkTwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.data.database.TwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.TwitterDataSource
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.TwitterRepository
import dagger.Module
import dagger.Provides

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