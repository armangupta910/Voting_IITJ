package com.example.votingiitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Waiting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting)
        Handler().postDelayed({
            startActivity(Intent(this,welcome::class.java))
            finish();
        },2500)
    }
}