package com.example.todo.UI.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.UI.ItemViewModel

class Addnote : AppCompatActivity() {

    lateinit var viewModel: ItemViewModel

    val updateNote=findViewById<Button>(R.id.updateNote)
    val title=findViewById<EditText>(R.id.title)

    val content=findViewById<EditText>(R.id.content)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)

        this.setTitle("Create ToDo")

        val recycler_view = findViewById(R.id.recycler_view) as RecyclerView
        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this, this)
        recycler_view.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })




        updateNote.setOnClickListener{
           val intent= Intent(this, UpdateNote::class.java)
            startActivity(intent)
        }




    }




    //Adds menu into acionbar if present
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main,menu)

        return true

    }




}


