package com.example.keranscoffeeandspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    //Deklarasi variabel timer splash screennya
    private val SPLASH_TIME_OUT: Long = 5000 //delay 5 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Aktifkan progress bar/loading bar dan menjalankan main screen setelah timer splash screen habis
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}