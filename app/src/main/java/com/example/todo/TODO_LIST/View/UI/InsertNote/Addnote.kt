
package com.example.todo.TODO_LIST.View.UI.InsertNote

import android.app.AlertDialog
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)

        addNotetitle =findViewById<EditText>(R.id.addNotetitle)
        addNotecontent=findViewById<EditText>(R.id.addNotecontent)
        saveNote = findViewById<Button>(R.id.saveNote)

        saveNote.setOnClickListener{
            submitData()
        }
        this.setTitle("Create ToDo Note")
        val adapter = Adapter(MainActivity(), ArrayList())

       viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
           ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
                list -> list?.let{
            adapter.updateList(it)      //update only when list not null
        }

        })
    }

    fun submitData() {
        val itemText = addNotetitle.text.toString()
        val itemContent = addNotecontent.text.toString()
        val builder = AlertDialog.Builder(this)
        if (itemText.isEmpty()) {

            builder.setTitle("Title Cannot be Empty")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
           // Toast.makeText(this, "Title Cannot be Empty", Toast.LENGTH_LONG).show()
        }else{
            viewModel.insertItem((Item(itemText, itemContent)))
            builder.setTitle("Note Created Successfully")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
           // Toast.makeText(this, "Note Created Successfully", Toast.LENGTH_LONG).show()
        }

    }

}





