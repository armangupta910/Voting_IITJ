package com.example.votingiitj

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.common.base.MoreObjects.ToStringHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class Profile : AppCompatActivity() {
    var image: Uri? =null
    private var storageref= Firebase.storage
    var x=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var token = getSharedPreferences("email", Context.MODE_PRIVATE)
        val imagi=findViewById<CircleImageView>(R.id.profile_photo)
        val name=findViewById<EditText>(R.id.profile_name)
        val roll=findViewById<EditText>(R.id.profile_roll)
        val branch=findViewById<EditText>(R.id.profile_branch)
        val yop=findViewById<EditText>(R.id.profile_py)
        val update=findViewById<Button>(R.id.profile_update)
        val save=findViewById<Button>(R.id.profile_save)
        val progi=findViewById<ProgressBar>(R.id.profile_progress_bar)
        val xyz= FirebaseAuth.getInstance().getCurrentUser()?.getUid()
        Firebase.firestore.collection("users").document(xyz.toString()).collection("profile").document("profile").get().addOnSuccessListener {
            Toast.makeText(this,"Details Extracted",Toast.LENGTH_SHORT).show()
            name.setText(it.data?.get("Name").toString())
            branch.setText(it.data?.get("Branch").toString())
            yop.setText(it.data?.get("YOP").toString())
            roll.setText(it.data?.get("Roll Number").toString())
            if(it.data?.get("URL")==""){
                imagi.setBackgroundResource(R.drawable.person)
                x=1
            }
            else{
                Glide.with(this).load(it.data?.get("URL")).into(imagi)
            }
        }

        val y=registerForActivityResult(
            ActivityResultContracts.GetContent(), {
                imagi.setImageURI(it)
                if (it != null) {
                    image=it
                }
            }
        )
        imagi.setOnClickListener {
            y.launch("image/*")
        }



        update.setOnClickListener {
            name.setBackgroundResource(R.drawable.newinput)
            branch.setBackgroundResource(R.drawable.newinput)
            yop.setBackgroundResource(R.drawable.newinput)
            name.isEnabled=true
            branch.isEnabled=true
            yop.isEnabled=true
            update.visibility=View.GONE
            save.visibility=View.VISIBLE
        }
        storageref= FirebaseStorage.getInstance()
        val db=Firebase.firestore

        save.setOnClickListener {
            if(image==null && x==1){
                Toast.makeText(this,"Image not Selected",Toast.LENGTH_SHORT).show()
            }
            if(x==1 && image!=null){
                Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
//            save.visibility=View.GONE
                progi.visibility=View.VISIBLE

                storageref.getReference("Profile_Images").child(xyz.toString()).putFile(image!!).addOnSuccessListener {
                    it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                        val data= hashMapOf(
                            "Name" to name.text.toString(),
                            "Roll Number" to roll.text.toString(),
                            "Branch" to branch.text.toString(),
                            "YOP" to yop.text.toString(),
                            "URL" to it.toString()
                        )
                        db.collection("users").document(xyz.toString()).collection("profile").document("profile").set(data).addOnSuccessListener {
                            Toast.makeText(this,"Profile Updated",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,Main_Page::class.java))
                            finish()
                        }
                    }
                }

            }
            if(x==0 && image==null){
                progi.visibility=View.VISIBLE
                val data= hashMapOf(
                    "Name" to name.text.toString(),
                    "Roll Number" to roll.text.toString(),
                    "Branch" to branch.text.toString(),
                    "YOP" to yop.text.toString()
                )
                db.collection("users").document(xyz.toString()).collection("profile").document("profile").update("Name",name.text.toString())
                db.collection("users").document(xyz.toString()).collection("profile").document("profile").update("Roll Number",roll.text.toString())
                db.collection("users").document(xyz.toString()).collection("profile").document("profile").update("Branch",branch.text.toString())
                db.collection("users").document(xyz.toString()).collection("profile").document("profile").update("YOP",yop.text.toString())
                Toast.makeText(this,"Profile Updated",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Main_Page::class.java))
                finish();
            }
            if(x==0 && image!=null){
                Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
//            save.visibility=View.GONE
                progi.visibility=View.VISIBLE

                storageref.getReference("Profile_Images").child(xyz.toString()).putFile(image!!).addOnSuccessListener {
                    it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                        val data= hashMapOf(
                            "Name" to name.text.toString(),
                            "Roll Number" to roll.text.toString(),
                            "Branch" to branch.text.toString(),
                            "YOP" to yop.text.toString(),
                            "URL" to it.toString()
                        )
                        db.collection("users").document(xyz.toString()).collection("profile").document("profile").set(data).addOnSuccessListener {
                            Toast.makeText(this,"Profile Updated",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,Main_Page::class.java))
                            finish()
                        }
                    }
                }
            }
        }


    }


}