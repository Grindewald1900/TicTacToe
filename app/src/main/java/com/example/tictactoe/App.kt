package com.example.tictactoe

import android.app.Application
import android.content.Context

class App :Application(){
    companion object{
        var  mContext:Application? = null
        fun getContext(): Context {
            return mContext!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}