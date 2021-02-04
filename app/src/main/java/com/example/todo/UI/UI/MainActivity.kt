package com.example.todo.UI.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.UI.ItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ItemViewModel::class.java)

        viewModel.allItems.observe(this, Observer{

        })







        val recycler_view = findViewById(R.id.recycler_view) as RecyclerView

        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this, this)
        recycler_view.adapter = adapter

        overr

        //floating button
        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, Addnote :: class.java)
            startActivity(intent)
        }


    }


    private fun generateList(): List<ListItem>{
        val list = ArrayList<ListItem>()

        for (i in 1..10) {
            val item = ListItem("todo Task $i", "Data items")
            list.add(item)
        }
        return list
    }

}