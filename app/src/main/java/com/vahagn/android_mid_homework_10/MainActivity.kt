package com.vahagn.android_mid_homework_10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.vahagn.android_mid_homework_10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }

    fun ticTacToe(view: View) {
        val intent = Intent(this, Ex3::class.java)
        startActivity(intent)
    }

    fun playerNames(view: View) {
        supportFragmentManager.beginTransaction().replace(R.id.namesContainer, PlayerNamesEdit()).commit()
    }

    fun namesEdited() {
        supportFragmentManager.beginTransaction().replace(R.id.namesContainer, PlayerNames()).commit()
    }

    fun dice(view: View) {
        val intent = Intent(this, Ex2::class.java)
        startActivity(intent)
    }

    public data class Player(var name: String, var score: Int)

    companion object {
        public val players = listOf (
            Player("Player 1", 0),
            Player("Player 2", 0)
        )
    }
}
