package com.example.votingiitj

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        var token = getSharedPreferences("email", Context.MODE_PRIVATE)
        if(token.getString("email","")!=""){
            startActivity(Intent(this,Main_Page::class.java))
            finish()
        }
        findViewById<Button>(R.id.signin).setOnClickListener({
            startActivity(Intent(this,signin::class.java))
            finish()
        })

        findViewById<Button>(R.id.signup).setOnClickListener({
            startActivity(Intent(this,signup::class.java))
            finish()
        })
    }
}