package com.example.quizz_tcr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView

class splash : AppCompatActivity() {

    lateinit var videoview : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        videoview = findViewById(R.id.videoview)

        var  mediacontroller = MediaController(this)
        mediacontroller.setAnchorView(videoview)

        var uri : Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.quiz1)

        videoview.setVideoURI(uri)
        videoview.start()

        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}