package com.vahagn.android_mid_homework_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class PlayerNamesEdit : Fragment() {
    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_names_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<EditText>(R.id.p1NameEdit).setText(MainActivity.players[0].name)
        view.findViewById<EditText>(R.id.p2NameEdit).setText(MainActivity.players[1].name)
        view.findViewById<Button>(R.id.okButton).setOnClickListener { ok() }
        view.findViewById<Button>(R.id.cancelButton).setOnClickListener { cancel() }

        root = view
    }

    fun ok() {
        MainActivity.players[0].name = root.findViewById<EditText>(R.id.p1NameEdit).text.toString()
        MainActivity.players[1].name = root.findViewById<EditText>(R.id.p2NameEdit).text.toString()
        (activity as MainActivity).namesEdited()
    }

    fun cancel() {
        (activity as MainActivity).namesEdited()
    }
}