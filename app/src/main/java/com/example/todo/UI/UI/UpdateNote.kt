package com.example.todo.UI.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import com.example.todo.R

class UpdateNote : AppCompatActivity() {

//    lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        this.setTitle("Update ToDo Note")

        var updateTitle =findViewById<EditText>(R.id.updateTitle)
        var updateContent=findViewById<EditText>(R.id.updateContent)

  //      btnUpdate = findViewById<Button>(R.id.btnUpdate)
  //      btnUpdate.setOnClickListener{}

        val title1 = intent.getStringExtra("title")
        updateTitle.setText(title1)

    }


}