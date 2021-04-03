package com.vahagn.android_mid_homework_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PlayerNames : Fragment() {

    lateinit var root: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_names, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        root = view
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()

        root.findViewById<TextView>(R.id.p1Name).text = MainActivity.players[0].name
        root.findViewById<TextView>(R.id.p2Name).text = MainActivity.players[1].name
        root.findViewById<TextView>(R.id.p1Score).text = MainActivity.players[0].score.toString()
        root.findViewById<TextView>(R.id.p2Score).text = MainActivity.players[1].score.toString()
    }
}