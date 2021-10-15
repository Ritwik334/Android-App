package com.example.quizz_tcr

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var btnLogin : Button
    lateinit var etName : EditText
    lateinit var etPass : EditText
    lateinit var radioGroup : RadioGroup
    lateinit var radioButton: RadioButton
    lateinit var radioButton2: RadioButton
    lateinit var imageView3: ImageView
    lateinit var tvRegister : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btnLogin)
        etName = findViewById(R.id.etName)
        etPass = findViewById(R.id.etPass)
        radioGroup = findViewById(R.id.radioGroup)
        radioButton = findViewById(R.id.radioButton)
        imageView3 = findViewById(R.id.imageView3)
        radioButton2 = findViewById(R.id.radioButton2)
        tvRegister = findViewById(R.id.tvRegister)

//        if(radioButton.isChecked)
//            imageView3.setImageResource(R.drawable.male)
//        if(radioButton2.isChecked)
//            imageView3.setImageResource(R.drawable.female)

        var helper =  DatabaseHandler(applicationContext)
        var db = helper.readableDatabase

        btnLogin.setOnClickListener {

            var username:String = etName.text.toString()

            var password:String = etPass.text.toString()

            if(username.isEmpty() && password.isEmpty())
            {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }

            else{
                var args = listOf<String>(username,password).toTypedArray()
                var rs = db.rawQuery("SELECT * FROM USER WHERE USER_NAME=? AND USER_PASSWORD=?",args)
                if(rs.moveToNext()){
                    Toast.makeText(applicationContext,"Welcome Accessed",Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,Quiz::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                    Toast.makeText(applicationContext,"Invalid Credential",Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {

            val intent =Intent(this,Register::class.java)
            startActivity(intent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id1 = item.itemId
        if(id1 == R.id.policy)
        {
            val intent = Intent(this,privacy_policy::class.java)
            startActivity(intent)
            finish()
            return true
        }

        val id2 = item.itemId
        if(id2 == R.id.feedback)
        {
            val intent = Intent(this,feedback::class.java)
            startActivity(intent)
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        val alertdialog = AlertDialog.Builder(this)
        alertdialog.setTitle("Alert")
            .setMessage("Do You want to close the application ?")
            .setCancelable(true)

            .setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> finish() })
            .setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int ->
            })

        alertdialog.show()
    }
}