package com.example.projemanag.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.TextView
import com.example.projemanag.R
import com.example.projemanag.firebase.FirestoreClass

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000
    private val handler = Handler(Looper.getMainLooper())
    private val currentUserID = FirestoreClass().getCurrentUserId()

    override fun onStart() {
        super.onStart()
        println("currentUserID :: $currentUserID")
        handler.postDelayed({
            if (currentUserID.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this,IntroActivity::class.java))
            }
            finish() // Close the splash activity to prevent going back to it on back press
        }, SPLASH_TIME_OUT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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