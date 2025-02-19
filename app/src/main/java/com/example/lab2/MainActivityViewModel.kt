package com.example.lab2

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question1, false),
        Question(R.string.question2, true),
        Question(R.string.question3, false),
        Question(R.string.question4, false),
        Question(R.string.question5, true)
    )

    private var currentIndex = 0
    var isCheater = false

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        isCheater = false
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        isCheater = false
        currentIndex = (currentIndex - 1) % questionBank.size
    }
}