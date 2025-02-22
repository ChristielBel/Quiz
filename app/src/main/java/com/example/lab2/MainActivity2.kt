package com.example.lab2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_ANSWER_SHOWN = "com.example.lab2.answer_is_true"
const val EXTRA_ANSWER_IS_TRUE = "com.example.lab2.answer_shown"

class MainActivity2 : AppCompatActivity() {

    private var answerIsTrue = false
    private lateinit var showAnswerButton: Button

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, MainActivity2::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        answerIsTrue = intent?.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false) ?: false
        setContentView(R.layout.activity_main2)

        val answerTextView = findViewById<TextView>(R.id.tvAnswer)
        answerTextView.visibility = View.INVISIBLE
        val answerText = when {
            answerIsTrue -> R.string.true_text
            else -> R.string.false_text
        }
        answerTextView.setText(answerText)
//        answerTextView.text = answerText.toString()

        showAnswerButton = findViewById(R.id.btnShow)
        showAnswerButton.setOnClickListener {
            answerTextView.visibility = View.VISIBLE
            showAnswerButton.visibility = View.GONE

            val data = Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, true)
            }
            setResult(Activity.RESULT_OK, data)
        }
    }
}