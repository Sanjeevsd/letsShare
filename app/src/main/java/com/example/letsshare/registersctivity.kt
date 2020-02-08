package com.example.letsshare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*

class registersctivity:AppCompatActivity() {
    val reg=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        register_reg.setOnClickListener {
            checkValidity()

        }

    }
    private fun checkValidity() {
        val name = name_reg.text.toString()
        val email = email_reg.text.toString()
        val phone = number_reg.text.toString()
        val password = password_reg.text.toString()
        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please Enter valid Data", Toast.LENGTH_LONG).show()
            return
        }
        else (
                performRegister(name,email,phone,password)
                )
    }
    private fun performRegister(name:String,email:String,phone:String,password:String)
    {
        reg.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                //else if successful
                Toast.makeText(this,"Successfully Registered",Toast.LENGTH_SHORT).show()
                saveusertodatabase(name,email,phone,password)

            }
            .addOnFailureListener {
                if(it.message=="The email address is badly formatted.")
                {
                    Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show()
                }
                else if(it.message=="The given password is invalid. [ Password should be at least 6 characters ]")
                {Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun saveusertodatabase(name:String,email:String,phone:String,password:String){

        val sid=FirebaseAuth.getInstance().uid?:""
        val ref= FirebaseDatabase.getInstance().getReference("/user/$sid")
        val user= Users(sid,name,email,phone)
        ref.setValue(user)
            .addOnCanceledListener {
                Log.d("register","saved users database:")
            }
        login_activ()
    }
    private fun login_activ()
    {
        Log.d("register","logging")
        val intent = Intent(this,loginactivity ::class.java)
        startActivity(intent)
        finish()
    }
}

