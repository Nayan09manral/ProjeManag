package com.example.projemanag.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.projemanag.R
import com.example.projemanag.databinding.DialogProgressBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

open class BaseActivity : AppCompatActivity() {


    private var doubleBackToExitPressedOnce = false

    private lateinit var mProgressDialog: Dialog
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

    }

    fun showProgressDialog(text:String) {
        val dialogBinding: DialogProgressBinding = DialogProgressBinding.inflate(layoutInflater)
        if (!::mProgressDialog.isInitialized) {
            mProgressDialog = Dialog(this)
            mProgressDialog.setContentView(dialogBinding.root)
        }

        dialogBinding.tvProgressText.text = text
        mProgressDialog.show()
        }


    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }

    fun getcurrentUserID(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun doubleBacktoExit(){
        if(doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this,resources.getString(R.string.please_click_back_again_to_exit),
            Toast.LENGTH_SHORT).show()

        Handler().postDelayed({doubleBackToExitPressedOnce = false},2000)
    }

    fun showErrorSnackBar(message:String){
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
            message,Snackbar.LENGTH_LONG)
        val snackBarView = snackbar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this,
            R.color.snackbar_error_color))
        snackbar.show()
    }

}