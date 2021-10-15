package com.example.quizz_tcr

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Quiz : AppCompatActivity() {

    lateinit var btn_start : Button
    lateinit var et_name : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        btn_start = findViewById(R.id.btn_start)
        et_name = findViewById(R.id.et_name)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btn_start.setOnClickListener {
            var text = et_name.text.toString()

            if(text.equals("")){
                Toast.makeText(this,"Enter Name", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this,QuizQustionActivity::class.java)
                intent.putExtra(Constants.USER_NAME,et_name.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {

        val intent = Intent(this,MainActivity::class.java)

        val alertdialog = AlertDialog.Builder(this)
        alertdialog.setTitle("Alert")
            .setMessage("Do You want to close the quiz portal ?")
            .setCancelable(false)

            .setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> startActivity(intent) })
            .setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int ->
            })

        alertdialog.show()
    }

}