package com.example.votingiitj

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import org.w3c.dom.Text

class Registration : AppCompatActivity() {
    private var imagi:Uri?=null
    private var storageref=Firebase.storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
//        val x:Spinner =findViewById(R.id.branch)
//        val z:Spinner =findViewById(R.id.year)
//        val branches= arrayOf("Select your Branch","CSE","EE","BB","MECH","CH","CY","PY","CI")
//        val year=arrayOf("Select your Passing Year","2024","2025")
//        val adap=ArrayAdapter(this@Registration,android.R.layout.simple_spinner_dropdown_item,branches)
//        val adap1=ArrayAdapter(this@Registration,android.R.layout.simple_spinner_dropdown_item,year)
//        var branchh:String ="None"
//        var yearr:String ="None"
//        z.adapter=adap1
//        x.adapter=adap
//        x.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                branchh = branches[p2].toString()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//
//        z.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                yearr = year[p2].toString()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }

        val image=findViewById<ImageView>(R.id.profileim)
        //Browsing Image down
//        val y=registerForActivityResult(
//            ActivityResultContracts.GetContent(), {
//                image.setImageURI(it)
//                imagi=it
//            }
//        )
//        image.setOnClickListener {
//            y.launch("image/*")
//        }
        //Browsing Image Up
        var db=Firebase.firestore
        storageref= FirebaseStorage.getInstance()
        val xyz=FirebaseAuth.getInstance().getCurrentUser()?.getUid()
        var imageURL:String=""
        db.collection("users").document(xyz.toString()).collection("profile").document("profile").get().addOnSuccessListener {
            Glide.with(this).load(it.data?.get("URL")).circleCrop().into(image)
            findViewById<EditText>(R.id.name).setText(it.data?.get("Name").toString())
            findViewById<EditText>(R.id.roll).setText(it.data?.get("Roll Number").toString())
            findViewById<EditText>(R.id.branch).setText(it.data?.get("Branch").toString())
            findViewById<EditText>(R.id.yop).setText(it.data?.get("YOP").toString())
            findViewById<EditText>(R.id.name).isEnabled=false
            findViewById<EditText>(R.id.roll).isEnabled=false
            findViewById<EditText>(R.id.branch).isEnabled=false
            findViewById<EditText>(R.id.yop).isEnabled=false
            imageURL=it.data?.get("URL").toString()

        }


        val butt1=findViewById<Button>(R.id.submit)
        findViewById<Button>(R.id.submit).setOnClickListener{
            butt1.visibility=View.GONE
            findViewById<ProgressBar>(R.id.progressBar4).visibility=View.VISIBLE

                        val name1=findViewById<EditText>(R.id.name).text.toString()
                        val roll=findViewById<EditText>(R.id.roll).text.toString()
                        val category=findViewById<EditText>(R.id.category).text.toString()
                        val sop=findViewById<EditText>(R.id.sop).text.toString()
                        val remarks=findViewById<EditText>(R.id.remarks).text.toString()
                        val branch=findViewById<EditText>(R.id.branch).text.toString()
                        val yop=findViewById<EditText>(R.id.yop).text.toString()
                        val data= hashMapOf(
                            "Name" to name1,
                            "Roll Number" to roll,
                            "Branch" to branch,
                            "Year of Passing" to yop,
                            "Category" to category,
                            "SOP" to sop,
                            "Remarks" to remarks,
                            "Image URL" to imageURL
                        )
                        db.collection("Constestants").document(xyz.toString()).set(data).addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(this,"Registration Successfull",Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this,Main_Page::class.java))
                            }
                            else{
                                Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                            }
                        }






        }

    }
}