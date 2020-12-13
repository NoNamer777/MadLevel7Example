package com.nonamer777.madlevel7example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nonamer777.madlevel7example.R
import com.nonamer777.madlevel7example.databinding.FragmentCreateQuizBinding
import com.nonamer777.madlevel7example.model.QuizViewModel

/**
 * A [Fragment] subclass that enables a User to create a question-answer pair for the quiz.
 */
class CreateQuizFragment: Fragment() {

    private lateinit var binding: FragmentCreateQuizBinding

    private val quizViewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQuizBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainActivity.actionBar?.setDisplayShowHomeEnabled(true)
        MainActivity.actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnCreateQuestion.setOnClickListener {
            quizViewModel.createQuiz(
                binding.inputQuestion.text.toString(),
                binding.inputAnswer.text.toString()
            )

            observeQuizCreation()
        }
    }

    private fun observeQuizCreation() {
        quizViewModel.createSuccess.observe(viewLifecycleOwner, {
            Toast
                .makeText(activity, R.string.notification_quiz_creation_success, Toast.LENGTH_LONG)
                .show()

            findNavController().popBackStack()
        })

        quizViewModel.error.observe(viewLifecycleOwner, {
            Toast
                .makeText(activity, it, Toast.LENGTH_LONG)
                .show()
        })
    }
}
