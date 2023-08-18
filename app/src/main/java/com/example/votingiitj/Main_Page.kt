package com.example.votingiitj

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

public class vote: Fragment(R.layout.vote){

}

public class candidate: Fragment(R.layout.candi){

}

class Main_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        var token=getSharedPreferences("email", Context.MODE_PRIVATE)
        supportFragmentManager.beginTransaction().replace(R.id.frame,candidate()).commit()
        var a:Boolean = true
        val db=Firebase.firestore
        db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).collection("profile").document("profile").get().addOnSuccessListener {
            if(it.data?.get("Name")=="" || it.data?.get("Roll Number")=="" || it.data?.get("Branch")=="" || it.data?.get("YOP")=="" || it.data?.get("URL")==""){
                a=false
            }
            else{
                a=true
            }
        }

        val x=findViewById<BottomNavigationView>(R.id.bottomnav)
        x.setOnItemSelectedListener{
            when (it.itemId){
                R.id.newreg -> {
                    if(a==true){
                        startActivity(Intent(this,Registration::class.java))
                    }
                    else{
                        Toast.makeText(this,"Please Complete your Profile to Register",Toast.LENGTH_SHORT).show()
                    }
//                    supportFragmentManager.beginTransaction().replace(R.id.frame,profile()).commit()
                    true
                }
                R.id.voting -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.frame,vote()).commit()
                    startActivity(Intent(this,voting_activity_category::class.java))
                    true
                }
                R.id.cand -> {
                    startActivity(Intent(this,all_candidates::class.java))
                    true
                }
                R.id.profile -> {
                    startActivity(Intent(this,Profile::class.java))
                    true
                }

                else -> {Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                    false
                };
            }
        }

    }
    fun signout(view: View) {
        var token=getSharedPreferences("email", Context.MODE_PRIVATE)
        var editor=token.edit()
        editor.putString("email","")
        editor.commit()
        startActivity(Intent(this,Waiting::class.java))
        finish()
    }
    fun getuid(view: View) {
        val demo = FirebaseAuth.getInstance().getCurrentUser()?.getUid();
        Toast.makeText(this,""+demo,Toast.LENGTH_SHORT).show()
    }
    fun gotocat1(view:View){
        startActivity(Intent(this,category1::class.java))
    }
    fun gotocat2(view:View){
        startActivity(Intent(this,category2::class.java))
    }
    fun gotocat3(view:View){
        startActivity(Intent(this,category3::class.java))
    }

}