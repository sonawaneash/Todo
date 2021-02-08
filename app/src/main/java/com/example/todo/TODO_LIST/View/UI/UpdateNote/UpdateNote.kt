package com.example.todo.TODO_LIST.View.UI.UpdateNote

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
import com.example.todo.TODO_LIST.Model.Item
import com.example.todo.TODO_LIST.UI.Adapter
import com.example.todo.TODO_LIST.View.UI.Main.MainActivity

class UpdateNote : AppCompatActivity() {
    lateinit var viewModel: ItemViewModel
    lateinit var updateTitle:EditText
    lateinit var updateContent:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        val adapter = Adapter(MainActivity(), ArrayList())
        this.setTitle("Update ToDo Note")
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
                list -> list?.let{
            adapter.updateList(it)      //update only when list not null
        }

        })


        updateTitle =findViewById(R.id.updateTitle)
        updateContent=findViewById(R.id.updateContent)


        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        val title1 = intent.getStringExtra("title").toString()
        val content1 = intent.getStringExtra("content")
        val id = intent.getStringExtra("id")

        updateTitle.setText(title1)
        updateContent.setText(content1)

        btnUpdate.setOnClickListener{
            updatesubmit()
        }


    }

   fun updatesubmit() {
       var uTitle =updateTitle.text.toString()
       var uContent=updateContent.text.toString()
       val id = intent.getIntExtra("id",-1)
       val builder = AlertDialog.Builder(this)

       if (uTitle.isEmpty()) {
           builder.setTitle("Update Note Title Cannot be Empty")
           builder.setPositiveButton("OK"){dialogInterface, which ->
               Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
           }
           val alertDialog: AlertDialog = builder.create()
           alertDialog.show()
           //Toast.makeText(this, "Title Cannot be Empty", Toast.LENGTH_LONG).show()
       }else{
           viewModel.updateItem(id,uTitle,uContent)
           builder.setTitle("Note Updated Successfully")
           builder.setPositiveButton("OK"){dialogInterface, which ->
               Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
           }
           val alertDialog: AlertDialog = builder.create()
           alertDialog.show()
          // Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_LONG).show()
       }


    }


}


/*
        val adapter = Adapter(MainActivity(), ArrayList())
        viewModel =ViewModelProvider(this).get(ItemViewModel::class.java)



        //viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
                list -> list?.let{
            adapter.updateList(it)      //update only when list not null
        }

        })
*/