package br.com.gabriellira.tweetsentimentanalyzer.ui.base

interface BaseContract {
    interface View {
        fun displayLoadingUI()
        fun hideLoadingUI()
        fun resetLayout();
    }

    interface Presenter<in T> {
        fun attach(view: T)
    }
}