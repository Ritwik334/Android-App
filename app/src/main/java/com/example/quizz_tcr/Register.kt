package com.example.quizz_tcr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {

    lateinit var btnRegister : Button
    lateinit var etName1 : EditText
    lateinit var etPhone : EditText
    lateinit var etPass : EditText
    lateinit var etCnfPass : EditText
    lateinit var etEmail : EditText
    lateinit var tvLogin : TextView

    internal var db : DatabaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister = findViewById(R.id.btnRegister)
        etCnfPass = findViewById(R.id.etCnfPass)
        etEmail = findViewById(R.id.etEmail)
        etName1 = findViewById(R.id.etName1)
        etPass = findViewById(R.id.etPass)
        etPhone = findViewById(R.id.etPhone)
        tvLogin = findViewById(R.id.tvLogin)

        btnRegister.setOnClickListener {

            var username:String = etName1.text.toString()
            var password:String = etPass.text.toString()
            var cnfpass:String = etCnfPass.text.toString()
            var phone:String = etPhone.text.toString()
            var email:String = etEmail.text.toString()



            if(username.isEmpty() || password.isEmpty() || cnfpass.isEmpty() ||
                phone.isEmpty() || email.isEmpty())
            {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }
            else if(password != cnfpass) {
                Toast.makeText(this, "Password varies", Toast.LENGTH_SHORT).show()
            }
            else{

                db.addUser(username,email,password)

                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    var intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("username",username)
                    intent.putExtra("email",email)
                    intent.putExtra("phone",phone)
                    intent.putExtra("password",password)
                    startActivity(intent)
                },2000)
            }
        }
        tvLogin.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java);
            startActivity(intent)
        }
    }
}