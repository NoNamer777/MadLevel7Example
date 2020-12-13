package com.nonamer777.madlevel7example.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nonamer777.madlevel7example.repository.QuizRepository
import com.nonamer777.madlevel7example.repository.exception.QuizRetrievalError
import com.nonamer777.madlevel7example.repository.exception.QuizSaveError
import kotlinx.coroutines.launch
import java.lang.Exception

class QuizViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "FIRESTORE"

    private val quizRepo = QuizRepository()

    val quiz = quizRepo.quiz

    val createSuccess = quizRepo.createSuccess

    private val _error = MutableLiveData<String>()

    val error: LiveData<String> get() = _error

    fun getQuiz() {
        viewModelScope.launch {
            try { quizRepo.getQuiz() } catch (exception: QuizRetrievalError) {
                logAndSetErrorMessage(exception, "Something went wrong while retrieving the quiz")
            }
        }
    }

    fun createQuiz(question: String, answer: String) {
        val quiz = Quiz(question, answer)

        viewModelScope.launch {
            try { quizRepo.createQuiz(quiz) } catch (exception: QuizSaveError) {
                logAndSetErrorMessage(exception, "Something went wrong while saving the quiz")
            }
        }
    }

    fun isAnswerCorrect(answer: String): Boolean =
        quiz.value?.answer.equals(answer, ignoreCase = true)

    private fun logAndSetErrorMessage(exception: Exception, message: String) {
        Log.e(TAG, exception.message ?: message)

        _error.value = message
    }
}
