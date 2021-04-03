package com.vahagn.android_mid_homework_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    data class Player(var name: String, var score: Int)

    companion object {
        val players = listOf (
            Player("Player 1", 0),
            Player("Player 2", 0)
        )
    }
}
