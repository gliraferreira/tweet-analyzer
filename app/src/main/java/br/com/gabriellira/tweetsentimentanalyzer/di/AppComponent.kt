package br.com.gabriellira.tweetsentimentanalyzer.di

import br.com.gabriellira.tweetsentimentanalyzer.App
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)
}