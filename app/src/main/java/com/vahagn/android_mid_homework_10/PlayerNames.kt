package com.vahagn.android_mid_homework_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.vahagn.android_mid_homework_10.databinding.FragmentPlayerNamesBinding

class PlayerNames : Fragment() {
    private var _binding: FragmentPlayerNamesBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentPlayerNamesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tikTakToe.setOnClickListener(tikTakToe)
        binding.dice.setOnClickListener(dice)
        binding.playerNames.setOnClickListener(playerNames)
    }

    object playerNames : View.OnClickListener {
        override fun onClick(v: View?) {
            Navigation.findNavController(v!!).navigate(R.id.goToEdit)
        }

    }

    object dice : View.OnClickListener {
        override fun onClick(v: View?) {
            Navigation.findNavController(v!!).navigate(R.id.goToEx2)
        }

    }

    object tikTakToe : View.OnClickListener {
        override fun onClick(v: View?) {
            Navigation.findNavController(v!!).navigate(R.id.goToEx3)
        }

    }

    override fun onResume() {
        super.onResume()

        binding.p1Name.text = MainActivity.players[0].name
        binding.p2Name.text = MainActivity.players[1].name
        binding.p1Score.text = MainActivity.players[0].score.toString()
        binding.p2Score.text = MainActivity.players[1].score.toString()
    }
}