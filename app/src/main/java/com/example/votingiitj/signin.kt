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

class signin : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        firebaseAuth= FirebaseAuth.getInstance()
        var token = getSharedPreferences("email", Context.MODE_PRIVATE)
//        if(token.getString("email","")!=""){
//            startActivity(Intent(this,Main_Page::class.java))
//            finish()
//        }
        val butt=findViewById<Button>(R.id.ini)
        butt.setOnClickListener({
            val prog=findViewById<ProgressBar>(R.id.progressBar2)
            prog.visibility=View.VISIBLE
            butt.setBackgroundResource(R.drawable.newbuttback)
            val email=findViewById<EditText>(R.id.rollsignin).text.toString()
            val pass=findViewById<EditText>(R.id.passsignin).text.toString()
            firebaseAuth.signInWithEmailAndPassword(email+"@iitj.ac.in",pass).addOnCompleteListener({
                if(it.isSuccessful){
                    Toast.makeText(this,"Chal gya bhai", Toast.LENGTH_SHORT).show()
                    var editor=token.edit()
                    editor.putString("email",email)
                    editor.commit()
                    startActivity(Intent(this,Main_Page::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this,"Lafda hai Bhai", Toast.LENGTH_SHORT).show()
                    prog.visibility=View.GONE
                    butt.setBackgroundResource(R.drawable.butt)
                }
            })
        })


    }
    fun gotoup(view: View) {
        startActivity(Intent(this,signup::class.java))
        finish()
    }

}

