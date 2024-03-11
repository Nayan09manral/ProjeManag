package com.example.projemanag.activities

import android.content.Intent
import android.os.Bundle
import com.example.projemanag.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    private var binding: ActivityIntroBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.signin?.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)

        }

        binding?.signup?.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


    }
}