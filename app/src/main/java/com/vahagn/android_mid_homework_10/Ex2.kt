package com.vahagn.android_mid_homework_10

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat

class Ex2 : AppCompatActivity() {
    private val rollIngFrequency = 200L
    private val throwFrequency = 400L

    private val diceIds = listOf(
        R.drawable.ic_dice_1, R.drawable.ic_dice_2,
        R.drawable.ic_dice_3, R.drawable.ic_dice_4,
        R.drawable.ic_dice_5, R.drawable.ic_dice_6
    )

    private val rollHandler = Handler(Looper.getMainLooper())
    private val throwHandler = Handler(Looper.getMainLooper())

    private lateinit var rollButton: Button
    private lateinit var throwButton: Button
    private lateinit var resetButton: Button
    private lateinit var diceImage: ImageView
    private lateinit var rollingDice: Group
    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex2)

        findViewById<TextView>(R.id.p1Name).text = MainActivity.players[0].name
        findViewById<TextView>(R.id.p2Name).text = MainActivity.players[1].name

        rollButton = findViewById(R.id.rollButton)
        throwButton = findViewById(R.id.throwButton)
        resetButton = findViewById(R.id.resetButton)
        diceImage = findViewById(R.id.dicesImage)
        rollingDice = findViewById(R.id.rollingDice)
        dice1 = findViewById(R.id.dice1)
        dice2 = findViewById(R.id.dice2)
    }

    fun roll(view: View) {
        rollButton.isEnabled = false
        throwButton.isEnabled = true

        rollImages()
    }

    private fun rollImages() {
        diceImage.visibility = View.INVISIBLE
        rollingDice.visibility = View.VISIBLE

        rollHandler.post(runChangeDice)
    }

    private val runChangeDice = object: Runnable {
        override fun run() {
            dice1.setImageDrawable(ContextCompat.getDrawable(this@Ex2, diceIds.shuffled().first()))
            dice2.setImageDrawable(ContextCompat.getDrawable(this@Ex2, diceIds.shuffled().first()))
            rollHandler.postDelayed(this, rollIngFrequency)
        }
    }

    fun throwDice(view: View) {
        throwButton.isEnabled = false

        throwImages()
    }

    private fun throwImages() {
        rollHandler.removeCallbacksAndMessages(null);

        runThrowDice.countDown = 2
        throwHandler.post(runThrowDice)
    }

    private val runThrowDice = object: Runnable {
        var countDown: Int = 2

        override fun run() {
            val p1DrawableId = diceIds.shuffled().first()
            val p2DrawableId = diceIds.shuffled().first()

            dice1.setImageDrawable(ContextCompat.getDrawable(this@Ex2, p1DrawableId))
            dice2.setImageDrawable(ContextCompat.getDrawable(this@Ex2, p2DrawableId))

            if(countDown-- > 0) throwHandler.postDelayed(this, throwFrequency)
            else {
                resetButton.isEnabled = true
                when {
                    diceIds.indexOf(p1DrawableId) > diceIds.indexOf(p2DrawableId) -> {
                        Toast.makeText(this@Ex2,"${MainActivity.players[0].name} win!", Toast.LENGTH_SHORT).show()
                        MainActivity.players[0].score++
                    }
                    diceIds.indexOf(p1DrawableId) < diceIds.indexOf(p2DrawableId) -> {
                        Toast.makeText(this@Ex2,"${MainActivity.players[1].name} win!", Toast.LENGTH_SHORT).show()
                        MainActivity.players[1].score++
                    } else -> Toast.makeText(this@Ex2,"Draw.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun reset(view: View) {
        resetButton.isEnabled = false
        rollButton.isEnabled = true

        resetImages()
    }

    private fun resetImages() {
        rollingDice.visibility = View.INVISIBLE
        diceImage.visibility = View.VISIBLE
    }
}