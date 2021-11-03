package com.robby.trialchapter6.ui.splashScreen

import com.robby.trialchapter6.data.local.SharedPref

class SplashScreenPresenterImp(private val view: SplashScreenView): SplashScreenPresenter{
    override fun checkIsLogin() {
        if(SharedPref.isLogin == true)
            view.onLogged()
        else
            view.unLogged()
    }
}