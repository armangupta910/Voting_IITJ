package com.example.votingiitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class full_profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_profile)
        val x=intent.getStringExtra("uid")
        val db=Firebase.firestore

        db.collection("Constestants").get().addOnSuccessListener {document->
            for(i in document){
                if(i.id==x.toString()){
                    findViewById<CardView>(R.id.card).visibility=View.VISIBLE
                    findViewById<ImageView>(R.id.putprof).visibility=View.VISIBLE
                    findViewById<TextView>(R.id.name).visibility=View.VISIBLE
                    findViewById<TextView>(R.id.roll).visibility=View.VISIBLE
                    findViewById<TextView>(R.id.branch).visibility=View.VISIBLE
                    findViewById<TextView>(R.id.year).visibility=View.VISIBLE
                    findViewById<TextView>(R.id.category).visibility=View.VISIBLE
                    findViewById<TextView>(R.id.sop).visibility=View.VISIBLE
                    findViewById<TextView>(R.id.remarks).visibility=View.VISIBLE
                    findViewById<ProgressBar>(R.id.progressBar6).visibility=View.GONE

                    findViewById<TextView>(R.id.name).text="Name - "+i.data.get("Name").toString()
                    findViewById<TextView>(R.id.roll).text="Roll Number - "+i.data.get("Roll Number").toString()
                    findViewById<TextView>(R.id.branch).text="Branch - "+i.data.get("Branch").toString()
                    findViewById<TextView>(R.id.year).text="Year of Passing - "+i.data.get("Year of Passing").toString()
                    findViewById<TextView>(R.id.category).text="Category - "+i.data.get("Category").toString()
                    findViewById<TextView>(R.id.sop).text="SOP - "+i.data.get("SOP").toString()
                    findViewById<TextView>(R.id.remarks).text="Remarks - "+i.data.get("Remarks").toString()
                    Glide.with(this).load(i.data.get("Image URL")).circleCrop().into(findViewById(R.id.putprof))
                }
            }
        }

    }
}