package com.example.projemanag.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignUpActivity : BaseActivity() {

    private var binding : ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarSignUpActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.btnSignin?.setOnClickListener {
            registerUser()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private  fun registerUser(){
        val name: String = binding?.etnName?.text.toString().trim { it <= ' '}

        val email: String = binding?.etEmail?.text.toString().trim { it <= ' '}

        val password: String = binding?.etPassword?.text.toString().trim { it <= ' ' }

        if(validateFrom(name, email, password)){
           showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val registeredEmail = firebaseUser.email!!
                        Toast.makeText(
                            this,
                            "$name you have" +
                                    "succesfully regigstered the email address $registeredEmail",
                            Toast.LENGTH_LONG
                        ).show()
                        FirebaseAuth.getInstance().signOut()
                    } else {
                        Toast.makeText(
                            this, task.exception!!.message, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }


    private fun validateFrom(name: String, email: String, password: String):Boolean{
        return when{
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter a name")
                false
            }
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter a email")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a password")
                false
            }else ->{
                true
            }
        }
    }

    // vnurgfugvbgit
    //okdk
    //ckmcvmv

}