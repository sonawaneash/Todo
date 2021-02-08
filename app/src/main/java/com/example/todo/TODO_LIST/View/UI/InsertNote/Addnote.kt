
package com.example.todo.TODO_LIST.View.UI.InsertNote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todo.R
import com.example.todo.TODO_LIST.View_Model.ItemViewModel
import com.example.todo.TODO_LIST.UI.Adapter
import com.example.todo.TODO_LIST.Model.Item
import com.example.todo.TODO_LIST.View.UI.Main.MainActivity

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

       viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
           ItemViewModel::class.java)
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





