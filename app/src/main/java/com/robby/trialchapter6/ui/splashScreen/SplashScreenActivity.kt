package com.robby.trialchapter6.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.robby.trialchapter6.R
import com.robby.trialchapter6.ui.home.HomeActivity
import com.robby.trialchapter6.ui.landingPage.LandingPageActivity

class SplashScreenActivity : AppCompatActivity(), SplashScreenView {

    private var presenter: SplashScreenPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val ivTitle by lazy {this.findViewById<ImageView>(R.id.ivTitle)}

        presenter = SplashScreenPresenterImp(this)

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(ivTitle)
        presenter?.checkIsLogin()
    }

    override fun onLogged() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    override fun unLogged() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}