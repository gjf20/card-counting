package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var ccPracticeButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ccPracticeButton = findViewById(R.id.ccPracticeButton)
        ccPracticeButton.setOnClickListener { navigateToPractice() }
    }

    private fun navigateToPractice() {
        val intent = Intent(this, ccPractice::class.java)
        intent.action = Intent.ACTION_VIEW
        startActivity(intent)
    }
}