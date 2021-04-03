package com.vahagn.android_mid_homework_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.Navigation
import com.vahagn.android_mid_homework_10.databinding.FragmentPlayerNamesEditBinding

class PlayerNamesEdit : Fragment() {
    private var _binding: FragmentPlayerNamesEditBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerNamesEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.p1NameEdit.setText(MainActivity.players[0].name)
        binding.p2NameEdit.setText(MainActivity.players[1].name)

        binding.okButton.setOnClickListener { ok() }
        binding.cancelButton.setOnClickListener { cancel() }
    }

    fun ok() {
        MainActivity.players[0].name = binding.root.findViewById<EditText>(R.id.p1NameEdit).text.toString()
        MainActivity.players[1].name = binding.root.findViewById<EditText>(R.id.p2NameEdit).text.toString()
        Navigation.findNavController(binding.root).popBackStack()
    }

    fun cancel() {
        Navigation.findNavController(binding.root).popBackStack()
    }
}