package com.example.projemanag

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this, IntroActivity::class.java)
            startActivity(mainIntent)
            finish() // Close the splash activity to prevent going back to it on back press
        }, SPLASH_TIME_OUT)

        //hide status bar and set app to fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val tv_app_name: TextView = findViewById(R.id.tv_app_name)

        val typeFace: Typeface = Typeface.createFromAsset(assets, "Perfectly Vintages Font by Keithzo.otf")

        tv_app_name.typeface = typeFace

    }
}