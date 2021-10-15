package com.example.quizz_tcr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Result : AppCompatActivity() {

    lateinit var tv_name : TextView
    lateinit var tv_score : TextView
    lateinit var btn_finish : Button
    lateinit var tv_congratulations : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        tv_name = findViewById(R.id.tv_name)
        tv_score = findViewById(R.id.tv_score)
        btn_finish = findViewById(R.id.btn_finish)
        tv_congratulations = findViewById(R.id.tv_congratulations)

        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text = username

        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        if (correctAnswer < 3) {
            tv_congratulations.setText("Oops Better Luck Next Time!!")
        } else if (correctAnswer >= 3 && correctAnswer <= 5) {
            tv_congratulations.setText("Pay attention")
        } else if (correctAnswer >5 && correctAnswer <= 7) {
            tv_congratulations.setText("Try Hard Next Time")
        } else if (correctAnswer >7 && correctAnswer <= 8) {
            tv_congratulations.setText("Good Job")
        } else if (correctAnswer>8 && correctAnswer <= 9) {
            tv_congratulations.setText("Very Good")
        }
        else if (correctAnswer == 10) {
            tv_congratulations.setText("Excellent")
        }
        else
        {

        }

        tv_score.text = "Your Score is $correctAnswer out of $totalQuestion"

        btn_finish.setOnClickListener{
            startActivity(Intent(this,Quiz::class.java))
            finish()
        }
    }
}