// Working on adding this ... until that i submitted the project
package com.example.quizz_tcr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class category : AppCompatActivity() {

    lateinit var tv_gk : Button
    lateinit var tv_maths : Button
    lateinit var tv_history : Button
    lateinit var tv_english : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        tv_gk = findViewById(R.id.tv_gk)
        tv_maths = findViewById(R.id.tv_maths)
        tv_history = findViewById(R.id.tv_history)
        tv_english = findViewById(R.id.tv_english)

        tv_gk.setOnClickListener{
            val intent = Intent(this,QuizQustionActivity::class.java)
            startActivity(intent)
            finish()
        }
        tv_english.setOnClickListener{
            val intent = Intent(this,category::class.java)
            startActivity(intent)
            finish()

        }
        tv_maths.setOnClickListener{
            val intent = Intent(this,category::class.java)
            startActivity(intent)
            finish()

        }
        tv_history.setOnClickListener{
            val intent = Intent(this,category::class.java)
            startActivity(intent)
            finish()
        }


    }
}