package com.example.quizz_tcr

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class privacy_policy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
    }

    override fun onBackPressed() {

        val intent = Intent(this,MainActivity::class.java)

        val alertdialog = AlertDialog.Builder(this)
        alertdialog.setTitle("Go Back to Dashboard")
            .setCancelable(true)

            .setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> startActivity(intent) })
            .setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int ->
            })

        alertdialog.show()
    }
}