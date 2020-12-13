package com.nonamer777.madlevel7example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nonamer777.madlevel7example.R
import com.nonamer777.madlevel7example.databinding.FragmentHomeBinding

/**
 * A [Fragment] subclass that serves as the home screen of the application.
 */
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainActivity.actionBar?.setDisplayShowHomeEnabled(false)
        MainActivity.actionBar?.setDisplayHomeAsUpEnabled(false)

        binding.btnStartQuiz.isEnabled = false

        binding.btnCreateQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createQuizFragment)
        }

        binding.btnStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
        }
    }
}
