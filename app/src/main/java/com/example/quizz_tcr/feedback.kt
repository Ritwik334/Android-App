package com.example.quizz_tcr

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener

class feedback : AppCompatActivity() {

    lateinit var ratingBar : RatingBar
    lateinit var btn_feed : Button
    lateinit var textView2 : TextView
    lateinit var et_multi : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        ratingBar = findViewById(R.id.ratingBar)
        btn_feed = findViewById(R.id.btn_feed)
        textView2 = findViewById(R.id.textView2)
        et_multi = findViewById(R.id.et_multi)

        ratingBar.setOnRatingBarChangeListener(OnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (rating == 0f) {
                textView2.setText("Very Dissatisfied")
            } else if (rating == 1f) {
                textView2.setText("Dissatisfied")
            } else if (rating == 2f || rating == 3f) {
                textView2.setText("OK")
            } else if (rating == 4f) {
                textView2.setText("Satisfied")
            } else if (rating == 5f) {
                textView2.setText("Very Satisfied")
            } else {
            }
        })

        btn_feed.setOnClickListener{
            val msg= ratingBar.rating.toString()

            if(ratingBar.rating <= 0){
                Toast.makeText(this,"Not Rated Yet",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Feedback Submitted" + " "+ msg, Toast.LENGTH_LONG).show()
                Handler().postDelayed({
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },2000)
            }
        }
    }

    override fun onBackPressed() {

        val intent = Intent(this,MainActivity::class.java)

        val alertdialog = AlertDialog.Builder(this)
        alertdialog.setTitle("Go Back")
            .setCancelable(false)

            .setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> startActivity(intent) })
            .setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int ->
            })

        alertdialog.show()
    }
}