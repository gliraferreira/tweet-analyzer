package br.com.gabriellira.tweetsentimentanalyzer

import android.app.Application
import br.com.gabriellira.tweetsentimentanalyzer.di.app.AppComponent
import br.com.gabriellira.tweetsentimentanalyzer.di.app.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    fun setup() {
        component = DaggerAppComponent.builder().build()
        component.inject(this)
    }

    fun provideAppComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}