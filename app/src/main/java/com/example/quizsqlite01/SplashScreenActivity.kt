package com.example.quizsqlite01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
// Splashscreen adalah tampilan kertika membuka suatu aplikasi lalu menuju suatu activity lainnya
    lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({

            startActivity(Intent(this@SplashScreenActivity,depan::class.java))
            finish()

        }, 5000)  // Menunjukkan waktu splashscreen untuk menuju ke halaman setelahnya yaitu selama 5 detik
    }
}
