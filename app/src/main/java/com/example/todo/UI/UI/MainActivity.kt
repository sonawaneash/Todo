package com.example.todo.UI.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val examplelist = generateList()

        var recycler_view = findViewById(R.id.recycler_view) as RecyclerView

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = Adapter(examplelist)

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