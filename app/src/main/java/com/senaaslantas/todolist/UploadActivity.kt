package com.senaaslantas.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.senaaslantas.todolist.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUploadBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUploadBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth=Firebase.auth
        firestore=Firebase.firestore

    }
    fun save(view: View){
        val postMap = hashMapOf<String,Any>()
        postMap.put("note",binding.noteText.text.toString())
        postMap.put("time",binding.timeText.text.toString())
        postMap.put("date",binding.dateText.text.toString())


        firestore.collection( "Posts").add(postMap).addOnCompleteListener{task ->

            if (task.isComplete && task.isSuccessful) {
                //back
                finish()

            }

        }.addOnFailureListener{exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }


    }

}

