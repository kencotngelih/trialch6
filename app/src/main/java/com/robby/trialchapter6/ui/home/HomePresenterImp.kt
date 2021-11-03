package com.robby.trialchapter6.ui.home

import com.robby.trialchapter6.data.local.SharedPref

class HomePresenterImp(private val view: HomeView) : HomePresenter{

    override fun showUsername() {
        val username = SharedPref.username

        if (username != null) {
            view.onSuccess(username)
        }
    }

    override fun logout() {
        SharedPref.isLogin=false
    }

}