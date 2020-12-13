package com.nonamer777.madlevel7example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.nonamer777.madlevel7example.model.Quiz
import com.nonamer777.madlevel7example.repository.exception.QuizRetrievalError
import com.nonamer777.madlevel7example.repository.exception.QuizSaveError
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout

class QuizRepository {

    private var firestore = FirebaseFirestore.getInstance()

    private var quizDocument = firestore
        .collection("quizzes")
        .document("quiz")

    private val _quiz = MutableLiveData<Quiz>()

    val quiz: LiveData<Quiz> get() = _quiz

    private val _createSuccess = MutableLiveData<Boolean>()

    val createSuccess: LiveData<Boolean> get() = _createSuccess

    suspend fun getQuiz() {
        try {
            withTimeout(5_000) {
                val data = quizDocument.get().await()

                val question = data.getString("question").toString()
                val answer = data.getString("answer").toString()

                _quiz.value = Quiz(question, answer)
            }

        } catch (exception: Exception) {
            throw QuizRetrievalError("Retrieval-firebase-task has failed")
        }
    }

    suspend fun createQuiz(quiz: Quiz) {
        try {
            withTimeout(5_000) {
                quizDocument.set(quiz).await()

                _createSuccess.value = true
            }

        } catch (exception: Exception) {
            throw QuizSaveError(exception.message.toString(), exception)
        }
    }
}
