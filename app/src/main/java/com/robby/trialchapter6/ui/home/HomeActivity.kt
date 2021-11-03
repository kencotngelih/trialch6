package com.robby.trialchapter6.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.robby.trialchapter6.R
import com.robby.trialchapter6.ui.login.LoginActivity
import com.robby.trialchapter6.ui.pilihTeman.PilihTemanActivity
import com.robby.trialchapter6.ui.playGameCpu.PlayGameCpuActivity
import com.robby.trialchapter6.ui.playGameTeman.PlayGameTemanActivity
import com.robby.trialchapter6.ui.profileTeman.ProfileTemanActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeView {
    private lateinit var presenter: HomePresenter
    private val username= mutableListOf<String>()
    val parent: ConstraintLayout by lazy { findViewById(R.id.main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter = HomePresenterImp(this)
        presenter.showUsername()

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(ivLogo)


        ivMenu1.setOnClickListener {
            startActivity(Intent(this, PilihTemanActivity::class.java))
            finish()
        }
        ivMenu2.setOnClickListener{
            startActivity(Intent(this, PlayGameCpuActivity::class.java))
            finish()
        }
        ivMenu3.setOnClickListener{
            startActivity(Intent(this, ProfileTemanActivity::class.java))
            finish()
        }

        btLogout.setOnClickListener {
            presenter.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onSuccess(msg: String) {
        val snackbar = Snackbar.make(parent,"Selamat datang $msg", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup"){
            snackbar.dismiss()
        }.show()
        username.add(msg)
        tvPlayer.text=msg
        tvPlayer2.text=msg
    }

    override fun onBackPressed() {
        finish()
    }
}