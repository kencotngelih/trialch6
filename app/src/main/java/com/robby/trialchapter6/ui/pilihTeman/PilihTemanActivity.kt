package com.robby.trialchapter6.ui.pilihTeman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robby.trialchapter6.R
import android.content.Intent
import com.robby.trialchapter6.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_pilih_teman.*
import kotlinx.android.synthetic.main.activity_profile_teman.*
import kotlinx.android.synthetic.main.activity_profile_teman.recyclerView

class PilihTemanActivity : AppCompatActivity() {
    private var presenter: PilihTemanPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_teman)
        presenter = PilihTemanPresenterImp()
        btHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        showLawan()

    }

    private fun showLawan(){
        presenter?.showList(recyclerView, this@PilihTemanActivity)
    }

    override fun onResume() {
        super.onResume()
        showLawan()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyDB()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}