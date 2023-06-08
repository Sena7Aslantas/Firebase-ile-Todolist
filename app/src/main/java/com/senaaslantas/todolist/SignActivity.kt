package com.senaaslantas.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senaaslantas.todolist.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth=Firebase.auth
        val currentUser=auth.currentUser
        if(currentUser!=null){
            val intent= Intent(this@SignActivity,FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun signin(view: View){

        val email=binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()
        if(email.equals("")|| password.equals("")){
            Toast.makeText(this,"Enter email and password!", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent= Intent(this@SignActivity,FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@SignActivity,it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }




    }
    fun signup(view: View){

        val email=binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()
        if(email.equals("")|| password.equals("")){
            Toast.makeText(this,"Enter email and password!",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent= Intent(this@SignActivity,FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@SignActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }

    }
