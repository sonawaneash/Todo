package com.example.todo.UI.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.UI.ItemViewModel
import com.example.todo.UI.db.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ItemClickAdapter {

    lateinit var viewModel : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this, ArrayList())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
            list -> list?.let{
                adapter.updateList(it)      //update only when list not null
            }

        })



        //floating button
        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, Addnote::class.java)
            startActivity(intent)
        }



    }

    //activity will talk only to view/model
    // delete fn
    override fun onItemDeleted(item: Item) { // interface defined in adapter
        viewModel.deleteItem(item)
        Toast.makeText(this,"item deleted", Toast.LENGTH_LONG).show()
    }

    override fun onItemUpdated(item: Item) {
       //val btnUpdate = findViewById<Button>(R.id.btnUpdate)
       // btnUpdate.setOnClickListener {}
        val intent = Intent(this, UpdateNote::class.java)
        startActivity(intent)
        val id = item.id
        //intent.putExtra("title", i.toString())
        Toast.makeText(this,"${id} Clicked", Toast.LENGTH_LONG).show()
    }


}
