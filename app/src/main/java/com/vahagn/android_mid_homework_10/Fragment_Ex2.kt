package com.vahagn.android_mid_homework_10

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.vahagn.android_mid_homework_10.databinding.FragmentEx2Binding

class Fragment_Ex2 : Fragment() {
    private val rollIngFrequency = 200L
    private val throwFrequency = 400L

    private val diceIds = listOf(
        R.drawable.ic_dice_1, R.drawable.ic_dice_2,
        R.drawable.ic_dice_3, R.drawable.ic_dice_4,
        R.drawable.ic_dice_5, R.drawable.ic_dice_6
    )

    private val rollHandler = Handler(Looper.getMainLooper())
    private val throwHandler = Handler(Looper.getMainLooper())

    private var _binding: FragmentEx2Binding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEx2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.p1Name.text = MainActivity.players[0].name
        binding.p2Name.text = MainActivity.players[1].name

        binding.rollButton.setOnClickListener {roll()}
        binding.resetButton.setOnClickListener {reset()}
        binding.throwButton.setOnClickListener {throwDice()}

    }

    fun roll() {
        binding.rollButton.isEnabled = false
        binding.throwButton.isEnabled = true

        rollImages()
    }

    private fun rollImages() {
        binding.dicesImage.visibility = View.INVISIBLE
        binding.rollingDice.visibility = View.VISIBLE

        rollHandler.post(runChangeDice)
    }

    private val runChangeDice = object: Runnable {
        override fun run() {
            binding.dice1.setImageDrawable(ContextCompat.getDrawable(requireContext(), diceIds.shuffled().first()))
            binding.dice2.setImageDrawable(ContextCompat.getDrawable(requireContext(), diceIds.shuffled().first()))
            rollHandler.postDelayed(this, rollIngFrequency)
        }
    }

    fun throwDice() {
        binding.throwButton.isEnabled = false

        throwImages()
    }

    private fun throwImages() {
        rollHandler.removeCallbacksAndMessages(null)

        runThrowDice.countDown = 2
        throwHandler.post(runThrowDice)
    }

    private val runThrowDice = object: Runnable {
        var countDown: Int = 2

        override fun run() {
            val p1DrawableId = diceIds.shuffled().first()
            val p2DrawableId = diceIds.shuffled().first()

            binding.dice1.setImageDrawable(ContextCompat.getDrawable(requireContext(), p1DrawableId))
            binding.dice2.setImageDrawable(ContextCompat.getDrawable(requireContext(), p2DrawableId))

            if(countDown-- > 0) throwHandler.postDelayed(this, throwFrequency)
            else {
                binding.resetButton.isEnabled = true
                when {
                    diceIds.indexOf(p1DrawableId) > diceIds.indexOf(p2DrawableId) -> {
                        Toast.makeText(requireContext(),"${MainActivity.players[0].name} win!", Toast.LENGTH_SHORT).show()
                        MainActivity.players[0].score++
                    }
                    diceIds.indexOf(p1DrawableId) < diceIds.indexOf(p2DrawableId) -> {
                        Toast.makeText(requireContext(),"${MainActivity.players[1].name} win!", Toast.LENGTH_SHORT).show()
                        MainActivity.players[1].score++
                    } else -> Toast.makeText(requireContext(),"Draw.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun reset() {
        binding.resetButton.isEnabled = false
        binding.rollButton.isEnabled = true

        resetImages()
    }

    private fun resetImages() {
        binding.rollingDice.visibility = View.INVISIBLE
        binding.dicesImage.visibility = View.VISIBLE
    }
}