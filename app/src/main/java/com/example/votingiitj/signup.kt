package com.example.votingiitj

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class signup : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val db=Firebase.firestore
        firebaseAuth= FirebaseAuth.getInstance()
        val butt=findViewById<Button>(R.id.upi)
        var token = getSharedPreferences("email", Context.MODE_PRIVATE)
        butt.setOnClickListener{
            val prog=findViewById<ProgressBar>(R.id.progressBar)
            prog.visibility=View.VISIBLE
            butt.setBackgroundResource(R.drawable.newbuttback)
            val email=findViewById<EditText>(R.id.rollsignup).text.toString()
            val pass=findViewById<EditText>(R.id.passsignup).text.toString()
            val comfpass=findViewById<EditText>(R.id.confpasssignup).text.toString()
            if(email=="" || pass=="" || comfpass==""){
                prog.visibility=View.GONE
                butt.setBackgroundResource(R.drawable.butt)
                Toast.makeText(this,"Fields are Empty",Toast.LENGTH_SHORT).show()
            }
            else{
                if(pass==comfpass){
                    firebaseAuth.createUserWithEmailAndPassword(email+"@iitj.ac.in",pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            var editor=token.edit()
                            editor.putString("email",email)
                            editor.commit()
                            Toast.makeText(this,"Chal gya bhai",Toast.LENGTH_SHORT).show()
                            val data= hashMapOf("uid" to FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString(),
                                                "Category 1" to "0",
                                "Category 2" to "0",
                                "Category 3" to "0")
                            db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).set(data).addOnCompleteListener {
                                if(it.isSuccessful){
                                    startActivity(Intent(this,Main_Page::class.java))
                                    finish()
                                }
                                else{
                                    Toast.makeText(this,"Default data could not be Set",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else{
                            Toast.makeText(this,"Account already exists",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,signin::class.java))
                            finish()
                        }
                    }
                }
                else{
                    prog.visibility=View.GONE
                    butt.setBackgroundResource(R.drawable.butt)
                    Toast.makeText(this,"Passwords donot Match",Toast.LENGTH_SHORT).show()
                }
            }

        }
        }

    fun gotoin(view: View) {
        startActivity(Intent(this, signin::class.java))
        finish()
    }
}