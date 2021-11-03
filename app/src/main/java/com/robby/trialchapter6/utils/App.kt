package com.robby.trialchapter6.utils

import android.app.Application
import android.content.Context
import com.robby.trialchapter6.data.database.TemanDatabase
import java.lang.ref.WeakReference

class App: Application() {
    companion object{
        lateinit  var context : WeakReference<Context>
        var mDB: TemanDatabase?=null
    }

    override fun onCreate() {
        super.onCreate()
        context =WeakReference(applicationContext)
    }
}