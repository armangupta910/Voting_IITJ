package com.example.votingiitj

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata

class all_candidates : AppCompatActivity() {
//    private var storageref=Firebase.storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_candidates)
        val db=Firebase.firestore
//        storageref= FirebaseStorage.getInstance()
        var uidlist : MutableList<String> = mutableListOf()
        var hashi : HashMap<String,Map<String,String>>  = hashMapOf()
        val progi:ProgressBar = findViewById(R.id.progressBar3)
//        storageref.getReference("Profile_Images/hello").downloadUrl.addOnSuccessListener {
//
//                Toast.makeText(this,"Image aa gyi",Toast.LENGTH_SHORT).show()
//                urimap["bdM5FCzBf0MxqmSHREqfXXsuTDo2"]=it
//
//
//        }
        db.collection("Constestants").get().addOnSuccessListener {result->
            Toast.makeText(this@all_candidates,"Data Retrieved",Toast.LENGTH_SHORT).show()
            progi.visibility= View.GONE
            for(document in result){
                uidlist.add(document.id)
                hashi[document.id.toString()]= document.data as Map<String, String>
            }
            Toast.makeText(this,"Full data",Toast.LENGTH_SHORT).show()
            val x=findViewById<RecyclerView>(R.id.recyclerviewid)
            val y=recycleadapter(this,hashi,uidlist)
            x.layoutManager=LinearLayoutManager(this@all_candidates)
            x.adapter=y
            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()



//            Toast.makeText(this@all_candidates,"Final Data Retrieved",Toast.LENGTH_SHORT).show()
        }







    }
}