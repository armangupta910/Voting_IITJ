package com.example.votingiitj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class category1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category1)

        val db= Firebase.firestore
//        storageref= FirebaseStorage.getInstance()
        var uidlist1 : MutableList<String> = mutableListOf()
        var hashi1 : HashMap<String,Map<String,String>>  = hashMapOf()
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
                if(document.data.get("Category")=="Category 1") {
//                    Toast.makeText(this,"Data 1", Toast.LENGTH_SHORT).show()
                    uidlist1.add(document.id)
                    hashi1[document.id.toString()] = document.data as Map<String, String>
                }
            }
            val x1=findViewById<RecyclerView>(R.id.recyclerviewidcat1)
            val y1=cat1RA(this,hashi1,uidlist1,1)
            x1.layoutManager=LinearLayoutManager(this)
            x1.adapter=y1
//            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
            }

    }
}