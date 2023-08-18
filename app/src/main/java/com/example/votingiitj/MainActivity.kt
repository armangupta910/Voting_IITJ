package com.example.votingiitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x=findViewById<Button>(R.id.button)
        x.setOnClickListener({
            val y=findViewById<ProgressBar>(R.id.prog)
            x.visibility=View.GONE
            y.visibility=View.VISIBLE
        })
    }


}