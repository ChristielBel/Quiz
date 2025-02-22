package com.example.lab2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val LOG_TAG = "com.example.lab2_MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: MainActivityViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "MainActivity создан")

        setContentView(R.layout.activity_main)

        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        nextButton = findViewById(R.id.ibNext)
        prevButton = findViewById(R.id.ibPrev)
        questionTextView = findViewById(R.id.tvQuestion)

        updateQuestion()

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    quizViewModel.isCheater =
                        data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
                }
            }

        findViewById<Button>(R.id.btnCheat).setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, quizViewModel.currentQuestionAnswer)
            resultLauncher.launch(intent)
        }

    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val isCheat = quizViewModel.isCheater
        val messageResId = when {
            isCheat -> R.string.cheater_text
            userAnswer == correctAnswer -> R.string.true_text
            else -> R.string.false_text
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
}