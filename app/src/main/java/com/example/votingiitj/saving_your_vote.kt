package com.example.votingiitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class saving_your_vote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savingyourvote)
        Handler().postDelayed({
            startActivity(Intent(this,Main_Page::class.java))
        },1500)
        finish()

    }
}