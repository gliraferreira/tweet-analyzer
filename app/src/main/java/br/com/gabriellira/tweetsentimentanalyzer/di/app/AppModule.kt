package br.com.gabriellira.tweetsentimentanalyzer.di.app

import android.app.Application
import br.com.gabriellira.tweetsentimentanalyzer.App
import br.com.gabriellira.tweetsentimentanalyzer.domain.TwitterDomain
import br.com.gabriellira.tweetsentimentanalyzer.data.database.HawkTwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.data.database.TwitterSettings
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.NaturalLanguageDataSource
import br.com.gabriellira.tweetsentimentanalyzer.data.network.google.NaturalLanguageRepository
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.TwitterDataSource
import br.com.gabriellira.tweetsentimentanalyzer.data.network.twitter.TwitterRepository
import br.com.gabriellira.tweetsentimentanalyzer.domain.NaturalLanguageDomain
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideApplication(): Application {
        return app
    }

    @Provides
    fun provideTwitterDomain(twitterDataSource: TwitterDataSource): TwitterDomain {
        return TwitterDomain(twitterDataSource)
    }

    @Provides
    fun provideNaturalLanguageDomain(dataSource: NaturalLanguageDataSource): NaturalLanguageDomain {
        return NaturalLanguageDomain(dataSource)
    }

    @Provides
    fun provideTwitterSettings(): TwitterSettings {
        return HawkTwitterSettings(app.applicationContext)
    }

    @Provides
    fun provideTwitterDataSource(settings: TwitterSettings): TwitterDataSource {
        return TwitterRepository(settings)
    }

    @Provides
    fun provideNaturalLanguageDataSource(): NaturalLanguageDataSource {
        return NaturalLanguageRepository()
    }
}