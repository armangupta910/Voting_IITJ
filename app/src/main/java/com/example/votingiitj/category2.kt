package com.example.votingiitj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class category2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category2)
        val db= Firebase.firestore
//        storageref= FirebaseStorage.getInstance()
        var uidlist : MutableList<String> = mutableListOf()
        var hashi : HashMap<String,Map<String,String>>  = hashMapOf()
//        storageref.getReference("Profile_Images/hello").downloadUrl.addOnSuccessListener {
//
//                Toast.makeText(this,"Image aa gyi",Toast.LENGTH_SHORT).show()
//                urimap["bdM5FCzBf0MxqmSHREqfXXsuTDo2"]=it
//
//
//        }
        db.collection("Constestants").get().addOnSuccessListener {result->
            Toast.makeText(this,"Data Retrieved", Toast.LENGTH_SHORT).show()
            for(document in result){
                if(document.data.get("Category")=="Category 2"){
                    uidlist.add(document.id)
                    hashi[document.id.toString()]= document.data as Map<String, String>
                }
            }
            val x=findViewById<RecyclerView>(R.id.recyclerviewidcat2)
            val y=cat1RA(this,hashi,uidlist,2)
            x.layoutManager= LinearLayoutManager(this)
            x.adapter=y
        }

    }
}