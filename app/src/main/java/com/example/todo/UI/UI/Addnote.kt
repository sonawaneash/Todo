
package com.example.todo.UI.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

class Addnote : AppCompatActivity() {

    lateinit var viewModel: ItemViewModel
    lateinit var addNotetitle: EditText
    lateinit var addNotecontent: EditText
    lateinit var saveNote: Button

   // val updateNote=findViewById<Button>(R.id.updateNote)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)

        addNotetitle =findViewById<EditText>(R.id.addNotetitle)
        addNotecontent=findViewById<EditText>(R.id.addNotecontent)
        saveNote = findViewById<Button>(R.id.saveNote)

        saveNote.setOnClickListener{
            submitData()
        }

        this.setTitle("Create ToDo")

        //val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        //recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(MainActivity(), ArrayList())
        //recyclerView.adapter = adapter

       viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
                list -> list?.let{
            adapter.updateList(it)      //update only when list not null
        }

        })


   /*
        updateNote.setOnClickListener{
           val intent= Intent(this, UpdateNote::class.java)
            startActivity(intent)
        }

*/


    }





    fun submitData() {
        val itemText = addNotetitle.text.toString()
        val itemContent = addNotecontent.text.toString()
        if (itemText.isNotEmpty()) {
            viewModel.insertItem((Item(itemText, itemContent)))
            Toast.makeText(this, "item inserted", Toast.LENGTH_LONG).show()
        }

    }

}





