package br.com.gabriellira.tweetsentimentanalyzer

import android.app.Application
import br.com.gabriellira.tweetsentimentanalyzer.di.AppComponent
import br.com.gabriellira.tweetsentimentanalyzer.di.DaggerAppComponent

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

    companion object {
        lateinit var instance: App private set
    }
}