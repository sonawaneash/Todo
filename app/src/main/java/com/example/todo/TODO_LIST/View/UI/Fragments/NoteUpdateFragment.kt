package com.example.todo.TODO_LIST.View.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todo.R
import com.example.todo.TODO_LIST.UI.Adapter
import com.example.todo.TODO_LIST.View.UI.Main.MainActivity
import com.example.todo.TODO_LIST.View_Model.ItemViewModel


class NoteUpdateFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = Adapter(List_Fragment(), ArrayList())

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
                list -> list?.let{
            adapter.updateList(it)      //update only when list not null
        }

        })



        val title1 = intent.getStringExtra("title").toString()
        val content1 = intent.getStringExtra("content")
        val id = intent.getStringExtra("id")

        updateTitle.setText(title1)
        updateContent.setText(content1)

        btnUpdate.setOnClickListener{
            updatesubmit()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_update, container, false)
    }


    fun updatesubmit() {
        var uTitle =updateTitle.text.toString()
        var uContent=updateContent.text.toString()
        val id = intent.getIntExtra("id",-1)
        val builder = AlertDialog.Builder(this)

        if (uTitle.isEmpty()) {
            builder.setTitle("Update Note Title Cannot be Empty")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes", Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
            //Toast.makeText(this, "Title Cannot be Empty", Toast.LENGTH_LONG).show()
        }else{
            viewModel.updateItem(id,uTitle,uContent)
            builder.setTitle("Note Updated Successfully")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes", Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
            // Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_LONG).show()
        }


    }

}
