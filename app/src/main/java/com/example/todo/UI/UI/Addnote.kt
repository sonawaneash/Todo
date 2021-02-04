package com.example.todo.UI.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import com.example.todo.R

class Addnote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)

        this.setTitle("Create ToDo")

        val updateNote=findViewById<Button>(R.id.updateNote)

        updateNote.setOnClickListener{
            val intent=Intent(this, UpdateNote::class.java)
            startActivity(intent)
        }


    }



    //Adds menu into acionbar if present
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main,menu)
        return true

    }

}