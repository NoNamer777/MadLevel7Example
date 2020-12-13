package com.nonamer777.madlevel7example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nonamer777.madlevel7example.databinding.FragmentQuizBinding
import com.nonamer777.madlevel7example.model.QuizViewModel


/**
 * A simple [Fragment] subclass where a User can participate in the quiz.
 */
class QuizFragment: Fragment() {

    private lateinit var binding: FragmentQuizBinding

    private val quizViewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeQuiz()
    }

    private fun observeQuiz() {
        quizViewModel.quiz.observe(viewLifecycleOwner, {
            val quiz = it

            binding.labelQuizQuestion.text = quiz.question

            binding.btnSendAnswer.setOnClickListener {
                if (quizViewModel.isAnswerCorrect(binding.inputQuizAnswer.text.toString())) {
                    Toast
                        .makeText(context, "You've got the correct answer", Toast.LENGTH_LONG)
                        .show()

                    findNavController().popBackStack()
                }

                else {
                    Toast
                        .makeText(context, "You've got the wrong answer", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}
