package com.robby.trialchapter6.ui.login

import com.robby.trialchapter6.utils.App.Companion.mDB
import com.robby.trialchapter6.utils.App.Companion.context
import com.robby.trialchapter6.data.local.SharedPref
import com.robby.trialchapter6.data.database.TemanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenterImp(private val view: LoginView) : LoginPresenter {


    override fun login(username: String, password: String) {
        mDB = context.get()?.let { TemanDatabase.getInstance(it) }

        GlobalScope.launch(Dispatchers.IO) {
            val pemain = mDB?.pemainDao()?.getPemainByUsername(username)

            launch(Dispatchers.Main) {
                if (pemain == null) {
                    view.onError("Username belum terdaftar")
                } else {
                    val passwordDB = pemain.password
                    if (password != passwordDB)
                        view.onError("Password salah")
                    else{
                        SharedPref.id = pemain.id
                        SharedPref.username = pemain.username
                        SharedPref.isLogin = true
                        view.onSuccess()
                    }
                }
            }
        }

    }
}