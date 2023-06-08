package com.senaaslantas.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.senaaslantas.todolist.adapter.FeedRecyclerAdapter
import com.senaaslantas.todolist.databinding.ActivityFeedBinding
import com.senaaslantas.todolist.model.Post

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db:FirebaseFirestore
    private lateinit var postArrayList: ArrayList<Post>
    private lateinit var feedAdapter:FeedRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth=Firebase.auth
        db=Firebase.firestore
        postArrayList=ArrayList<Post>()
        getData()
        binding.recayclerview.layoutManager=LinearLayoutManager(this)
        feedAdapter=FeedRecyclerAdapter(postArrayList)
        binding.recayclerview.adapter=feedAdapter

    }
    private fun getData(){
        db.collection("Posts").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()

            }else{
                if(value!=null){

                        val documents=value.documents
                    postArrayList.clear()
                        for(document in documents){
                            val date=document.get("date")as String

                            val note=document.get("note")as String
                            val time=document.get("time")as String

                            val post=Post(note, date, time)
                            postArrayList.add(post)

                        }
                        feedAdapter.notifyDataSetChanged()

                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.todo_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_note) {
            val intent = Intent(this@FeedActivity, UploadActivity::class.java)
            startActivity(intent)
        } else if (item.itemId ==R.id.signout) {
            //Logout

            auth.signOut()
            val intent = Intent(applicationContext,SignActivity::class.java)
            startActivity(intent)
            finish()

        }

        return super.onOptionsItemSelected(item)

}
}


