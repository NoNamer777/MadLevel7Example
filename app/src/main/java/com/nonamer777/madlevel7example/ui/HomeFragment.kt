package com.nonamer777.madlevel7example.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nonamer777.madlevel7example.R
import com.nonamer777.madlevel7example.databinding.FragmentHomeBinding
import com.nonamer777.madlevel7example.model.QuizViewModel

/**
 * A [Fragment] subclass that serves as the home screen of the application.
 */
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val quizViewModel: QuizViewModel by activityViewModels()

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

        quizViewModel.getQuiz()

        quizViewModel.quiz.observe(viewLifecycleOwner, {
            binding.btnStartQuiz.isEnabled = it.question.isNotBlank() && it.question != "null" ||
                it.answer.isNotBlank() && it.answer != "null"
        })

        binding.btnCreateQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createQuizFragment)
        }

        binding.btnStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
        }
    }
}
