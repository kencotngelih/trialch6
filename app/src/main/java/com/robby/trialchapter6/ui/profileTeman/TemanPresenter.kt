package com.robby.trialchapter6.ui.profileTeman

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.robby.trialchapter6.data.database.Teman

interface TemanPresenter {
    fun playerName()
    fun addTeman(name: String, email: String)
    fun listTeman(recyclerView: RecyclerView, context: Context)
    fun deleteTeman(list:List<Teman>, position: Int)
    fun editTeman(list:List<Teman>, position: Int)

    fun destroyDB()
}