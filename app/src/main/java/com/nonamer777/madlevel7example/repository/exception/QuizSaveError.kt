package com.nonamer777.madlevel7example.repository.exception

import java.lang.Exception

data class QuizSaveError(override val message: String, override val cause: Throwable):
    Exception(message, cause)
