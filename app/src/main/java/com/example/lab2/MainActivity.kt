package com.example.lab2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d("MyTag", "Вывод сообщения в консоли")
        Log.e("MyTag", "Вывод сообщения в консоли")
        Log.w("MyTag", "Вывод сообщения в консоли")


    }
}