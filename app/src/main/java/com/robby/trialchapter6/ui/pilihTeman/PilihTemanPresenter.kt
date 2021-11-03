package com.robby.trialchapter6.ui.pilihTeman

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface PilihTemanPresenter {
    fun showList(recyclerView: RecyclerView, context: Context)
    fun destroyDB()
}