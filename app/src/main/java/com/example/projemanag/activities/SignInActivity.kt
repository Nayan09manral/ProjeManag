package com.example.projemanag.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projemanag.R
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity() {

//    private var binding : ActivitySignInBinding? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
//        binding = ActivitySignInBinding.inflate(layoutInflater)
//        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance();


//        setSupportActionBar(binding?.toolbarSignInActivity)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val Btnsignin = findViewById<Button>(R.id.bt_signIn)
        Btnsignin.setOnClickListener {
            signInRegisteredUser()
        }



//        binding?.btSignIn?.setOnClickListener {
//            signInRegisteredUser()
//        }

    }

    private fun signInRegisteredUser(){
        val etemail = findViewById<EditText>(R.id.et_email_signin)
        val etpassword = findViewById<EditText>(R.id.et_password_signin)
        val email: String = etemail.text.toString().trim{it <= ' ' }
        val password: String = etpassword.text.toString().trim{it <= ' ' }

//        val email: String = binding?.etEmailSignin?.text.toString().trim{ it <= ' '}
//        val password: String = binding?.etPasswordSignin?.text.toString().trim { it <= ' ' }

        if (validateFrom(email,password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Sign in","createUserWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sign in","createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }

        }

    }

    private fun validateFrom(email: String, password: String): Boolean {
        return when {

            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter a email")
                false
            }

            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter a password")
                false
            }

            else -> {
                true
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
