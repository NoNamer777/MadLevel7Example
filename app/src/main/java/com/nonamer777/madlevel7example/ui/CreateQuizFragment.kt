package com.nonamer777.madlevel7example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nonamer777.madlevel7example.databinding.FragmentCreateQuizBinding

/**
 * A [Fragment] subclass that enables a User to create a question-answer pair for the quiz.
 */
class CreateQuizFragment: Fragment() {

    private lateinit var binding: FragmentCreateQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQuizBinding.inflate(layoutInflater)

        return binding.root
    }
}
