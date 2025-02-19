package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_ANSWER_SHOWN = ""
const val EXTRA_ANSWER_IS_TRUE = ""

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}