package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val addnote =findViewById<Button>(R.id.addNote)

        addnote.setOnClickListener{
            val intent = Intent(this,Addnote::class.java)
            startActivity(intent)
        }

    }



}