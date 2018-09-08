package br.com.gabriellira.tweetsentimentanalyzer.di

import android.app.Application
import br.com.gabriellira.tweetsentimentanalyzer.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app;
}