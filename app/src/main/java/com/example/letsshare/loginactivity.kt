package com.example.letsshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.or
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class loginactivity : AppCompatActivity() {
    var auth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auth= FirebaseAuth.getInstance()
        if (auth.getCurrentUser() != null){
            val hintent=Intent(this,MainActivity::class.java)
            startActivity(hintent)
            finish()
        }
        button_login.setOnClickListener {
            login()
        }
        signup_login.setOnClickListener {
            val ints=Intent(this,registersctivity::class.java)
            startActivity(ints)
            finish()
        }
    }
    fun login(){
        val email=username_login.text.toString()
        val upassword=password_login.text.toString()
        if (email.isEmpty() || upassword.isEmpty()) {
            Toast.makeText(this,"Please Enter valid Username/Password", Toast.LENGTH_LONG).show()
        }
        else {
            auth.signInWithEmailAndPassword(email,upassword)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d("login","succesfully logged in succesfully")
                    val hactivityIntent = Intent(this@loginactivity, MainActivity::class.java)
                    startActivity(hactivityIntent)
                    finish()
                }
                .addOnFailureListener {
                    Log.d("login","failed to login:${it.message}")
                    if(it.message=="There is no user record corresponding to this identifier. The user may have been deleted.")
                    {
                        Toast.makeText(this,"Invalid Email",Toast.LENGTH_LONG).show()
                    }
                    else if(it.message=="The password is invalid or the user does not have a password.")
                    {
                        Toast.makeText(this,"Invalid Password",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this,"Unknown Network Error",Toast.LENGTH_LONG).show()
                    }
        }

    }
    }
}
