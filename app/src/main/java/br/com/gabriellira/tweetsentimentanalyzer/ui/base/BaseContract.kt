package br.com.gabriellira.tweetsentimentanalyzer.ui.base

interface BaseContract {
    interface View {
        fun displayLoadingUI()
        fun hideLoadingUI()
    }

    interface Presenter<in T> {
        fun attach(view: T)
    }
}