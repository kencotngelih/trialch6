package com.robby.trialchapter6.ui.playGameCpu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.robby.trialchapter6.R
import com.robby.trialchapter6.ui.home.HomeActivity

import kotlinx.android.synthetic.main.activity_play_game_cpu.*


class PlayGameCpuActivity : AppCompatActivity(), PlayGameCpuPresenter {
    private val imgLogo = "https://i.ibb.co/HC5ZPgD/splash-screen1.png"
    private var dataPlayer1 = ""
    private val layoutImage: LinearLayout by lazy { findViewById(R.id.activity_play_game_cpu) }
    private val resetFun by lazy {
        findViewById<ImageView>(R.id.imageBattle)
    }
    private val intentDialog by lazy { Intent(this, HomeActivity::class.java) }
    private var enemyNya: ImageButton? = null
    private val backgroundAll by lazy {
        mutableListOf(
            findViewById<FrameLayout>(R.id.backgroundBatu), findViewById(R.id.backgroundScissors),
            findViewById(R.id.backgroundPaper), findViewById(R.id.backgroundBatuComp),
            findViewById(R.id.backgroundScissorsComp), findViewById(R.id.backgroundPaperComp),
        )
    }
    private val buttonAll by lazy {
        mutableListOf(
            findViewById(R.id.batuPlayer), findViewById(R.id.scissorsPlayer),
            findViewById(R.id.paperPlayer), findViewById(R.id.batuComp), findViewById(R.id.scissorsComp),
            findViewById(R.id.paperComp), findViewById<ImageButton>(R.id.but_home)
        )
    }
    private val textName by lazy {
        mutableListOf(
            findViewById<TextView>(R.id.player1),
            findViewById(R.id.player2)
        )
    }
    private val controller = PlayGameCpuPresenterImp(this)
    private val randDuration = 1000L
    private val animRand = 200L
    private var randNum = 0
    private var name = mutableListOf<String>()
    private var namePlay: String? = controller.getUsername()

    override fun onCreate(savedInstanceState: Bundle?) {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game_cpu)
        Glide.with(this)
            .load(imgLogo).error(R.drawable.ic_logo_image)
            .into(logoImageGame)
        reset()
        name.add(namePlay!!)
        textName[0]?.text = name[0]
        textName[1]?.text = getString(R.string.computer)
        val butNya = mutableListOf(
            buttonAll[0], buttonAll[1], buttonAll[2],
        )
        butNya.forEachIndexed { _, imageButton ->
            imageButton.setOnClickListener {
                when (it) {
                    buttonAll[0] -> {
                        if (backgroundAll[0].visibility != View.VISIBLE) {
                            buttonAll[0].startAnimation(animation)
                            backgroundAll[0].visibility = View.VISIBLE

                        }
                        dataPlayer1 = "batu"
                    }
                    buttonAll[1] -> {
                        if (backgroundAll[1].visibility != View.VISIBLE) {
                            buttonAll[1].startAnimation(animation)
                            backgroundAll[1].visibility = View.VISIBLE
                        }
                        dataPlayer1 = "gunting"
                    }
                    else -> {
                        if (backgroundAll[2].visibility != View.VISIBLE) {
                            buttonAll[2].startAnimation(animation)
                            backgroundAll[2].visibility = View.VISIBLE
                        }
                        dataPlayer1 = "kertas"
                    }
                }
                Toast.makeText(this, "$namePlay Memilih $dataPlayer1", Toast.LENGTH_SHORT).show()
                Log.i("MainGameComputer", "$namePlay memilih $dataPlayer1")
                lockButton()
                animationRandLoop()
                resetFun.animate().alpha(0f).scaleX(0.5f).scaleY(0.5f).setDuration(500).start()
                buttonAll[6].animate().alpha(0f).scaleX(0.5f).scaleY(0.5f).setDuration(500).start()
            }
        }
        buttonAll[6].setOnClickListener {
            buttonAll[6].startAnimation(animation)
            onBackPressed()
            Log.i("MainGameComputer", "playernya klik keluar")
        }
    }

    //Animasi Random Pada Suitnya
    private fun animationRandLoop() {
        mutableListOf(backgroundAll[3], backgroundAll[4], backgroundAll[5])
            .forEachIndexed { _, i ->
                Handler(Looper.getMainLooper()).postDelayed({
                    if (i.visibility == View.VISIBLE) {
                        i.visibility = View.INVISIBLE
                        Log.i("MainGameComputer", "Hilang Sejenak Background nya Computer")
                    }
                }, animRand)
            }
        Handler(Looper.getMainLooper()).postDelayed({
            if (randNum <= 35) {
                controller.compRand()
                Log.i("MainGameComputer", "Perulangan Computer #${randNum}")
            } else {
                dataModel()
                randNum = 0
            }
        }, animRand)
    }

    //Pemrosesan Data
    private fun dataModel() {
        val dataMauPlayer = GamePlay(dataPlayer1, "", "vsCPU")
        Log.i("MainGameComputer", "Proses Suit Computer vs Pemain")
        controller.setDataPlayer(dataMauPlayer)
        controller.chooseEnemy()
        controller.compareData()
    }

    //Button di lock ketika di proses
    private fun lockButton() {
        buttonAll[0].isEnabled = false
        buttonAll[1].isEnabled = false
        buttonAll[2].isEnabled = false
        Log.i("MainGameComputer", "Maaf tidak bisa diklik sedang proses bermain")
    }

    //unlock button semua
    private fun unlockButton() {
        if (!buttonAll[0].isEnabled && !buttonAll[1].isEnabled && !buttonAll[2].isEnabled) {
            buttonAll[0].isEnabled = true
            Log.i("MainGameComputer", "Anda memilih batu")
            buttonAll[1].isEnabled = true
            Log.i("MainGameComputer", "Anda memilih gunting")
            buttonAll[2].isEnabled = true
            Log.i("MainGameComputer", "Anda memilih kertas")
        }
    }

    //Reset semua
    private fun reset() {
        mutableListOf(
            backgroundAll[0], backgroundAll[1], backgroundAll[2], backgroundAll[3], backgroundAll[4], backgroundAll[5],
        ).forEachIndexed { _, i ->
            if (i.visibility == View.VISIBLE) {
                i.visibility = View.INVISIBLE
                Log.i("MainGameComputer", "Background tidak aktif")
            }
        }
        resetFun.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(randDuration).start()
        buttonAll[6].animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(randDuration).start()
        dataPlayer1 = ""
        unlockButton()
    }

    //Animasi Random dari Computer
    override fun randAnim(animResult: String) {
        when (animResult) {
            "batu" -> {
                backgroundAll[3].visibility = View.VISIBLE
                Log.i("MainGameComputer", "CPU memilih batu")
            }
            "gunting" -> {
                backgroundAll[4].visibility = View.VISIBLE
                Log.i("MainGameComputer", "CPU memilih gunting")
            }
            "kertas" -> {
                backgroundAll[5].visibility = View.VISIBLE
                Log.i("MainGameComputer", "CPU memilih kertas")
            }
        }
        randNum++
        animationRandLoop()
    }

    //Hasil dari Randomnya computer
    override fun resultEnemy(resultEnemy: String) {
        when (resultEnemy) {
            "batu" -> {
                enemyNya = buttonAll[3]
                backgroundAll[3].visibility = View.VISIBLE
            }
            "gunting" -> {
                enemyNya = buttonAll[4]
                backgroundAll[4].visibility = View.VISIBLE
            }
            "kertas" -> {
                enemyNya = buttonAll[5]
                backgroundAll[5].visibility = View.VISIBLE
            }
        }
        Toast.makeText(this, "CPU Memilih $resultEnemy", Toast.LENGTH_SHORT).show()
    }

    //Menampilkan hasil dari kalah atau menang
    override fun resultWinner(resultNya: String) {
        var winner = ""
        when (resultNya) {
            "P1Win" -> {
                winner = "$namePlay\nMENANG!"
            }
            "P2Win" -> {
                winner = "CPU\nMENANG!"
            }
            "Seri" -> {
                winner = "SERI!"
            }
        }
        Log.i("MainGameComputer", "Hasil: $winner")
        Handler(Looper.getMainLooper()).postDelayed({
            val view = LayoutInflater.from(this).inflate(R.layout.activity_dialog, null, false)
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setView(view)
            val dialogD1 = dialogBuilder.create()
            dialogD1.setCancelable(false)
            val winnerInfo by lazy { view.findViewById<TextView>(R.id.winner) }
            val playAgain by lazy { view.findViewById<Button>(R.id.play_again) }
            val backMenu by lazy { view.findViewById<Button>(R.id.back_menu) }
            val exitGame by lazy { view.findViewById<Button>(R.id.quit) }
            winnerInfo.text = winner
            playAgain.setOnClickListener {
                reset()
                dialogD1.dismiss()
            }
            backMenu.setOnClickListener {
                intentDialog.putExtra("dataName", name[0])
                startActivity(intentDialog)
                finish()

            }
            exitGame.setOnClickListener{
                finish()
            }
            if (!isFinishing) {
                dialogD1.show()
            }
        }, 2 * randDuration
        )
    }

    override fun onBackPressed() {
        val snackComp = Snackbar.make(layoutImage, "Apakah ingin keluar?", Snackbar.LENGTH_SHORT)
        snackComp.setAction("Keluar") {
            snackComp.dismiss()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }.show()
        Log.i("MainGameComputer", "Keluar")
    }
}
