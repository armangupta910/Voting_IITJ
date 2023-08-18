package com.example.votingiitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class voting_activity_category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voting_category)
        val db=Firebase.firestore
        db.collection("users").get().addOnSuccessListener {
            for(i in it){
                if(i.id== FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()){
                    if(i.data.get("Category 1")!="0"){
                        findViewById<Button>(R.id.gotocat1).visibility= View.GONE
                        findViewById<ImageView>(R.id.img1).visibility=View.VISIBLE
                    }
                    if(i.data.get("Category 2")!="0"){
                        findViewById<Button>(R.id.gotocat2).visibility= View.GONE
                        findViewById<ImageView>(R.id.img2).visibility=View.VISIBLE
                    }
                    if(i.data.get("Category 3")!="0"){
                        findViewById<Button>(R.id.gotocat3).visibility= View.GONE
                        findViewById<ImageView>(R.id.img3).visibility=View.VISIBLE
                    }
                }
            }
        }
        findViewById<Button>(R.id.gotocat1).setOnClickListener {
            startActivity(Intent(this,category1::class.java))
        }
        findViewById<Button>(R.id.gotocat2).setOnClickListener {
            startActivity(Intent(this,category2::class.java))
        }
        findViewById<Button>(R.id.gotocat3).setOnClickListener {
            startActivity(Intent(this,category3::class.java))
        }
    }
}