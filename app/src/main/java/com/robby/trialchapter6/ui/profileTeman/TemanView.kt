package com.robby.trialchapter6.ui.profileTeman

interface TemanView {
    fun onSuccessTeman(msg:String)
    fun onFailedTeman(msg:String)
    fun nameEmail(username:String,email:String)
}