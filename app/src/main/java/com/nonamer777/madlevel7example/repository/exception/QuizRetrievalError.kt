package com.nonamer777.madlevel7example.repository.exception

import java.lang.Exception

data class QuizRetrievalError(override val message: String): Exception(message)
