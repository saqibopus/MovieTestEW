package com.saqib.movietestew.helper

import com.saqib.movietestew.BuildConfig


object Logs {
    const val TAG = "----**"
    fun p(string: String) {
        if (BuildConfig.DEBUG) {
            println("$TAG $string")
        }
    }
}