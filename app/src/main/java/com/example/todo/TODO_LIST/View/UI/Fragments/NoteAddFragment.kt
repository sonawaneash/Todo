package com.example.todo.TODO_LIST.View.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todo.R
import com.example.todo.TODO_LIST.Model.Item
import com.example.todo.TODO_LIST.UI.Adapter
import com.example.todo.TODO_LIST.View.UI.Main.MainActivity
import com.example.todo.TODO_LIST.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.activity_addnote.*

class NoteAddFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        saveNote.setOnClickListener{
           // submitData()
        }
       //this.setTitle("Create ToDo Note")
        val adapter = Adapter(List_Fragment(), ArrayList())

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
                list -> list?.let{
            adapter.updateList(it)      //update only when list not null
        }

        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_add, container, false)
    }


    fun submitData() {
        val itemText = addNotetitle.text.toString()
        val itemContent = addNotecontent.text.toString()
        val builder = AlertDialog.Builder(getActivity())
        if (itemText.isEmpty()) {

            builder.setTitle("Title Cannot be Empty")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                Toast.makeText(getActivity(),"clicked yes", Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
            // Toast.makeText(this, "Title Cannot be Empty", Toast.LENGTH_LONG).show()
        }else{
            viewModel.insertItem((Item(itemText, itemContent)))
            builder.setTitle("Note Created Successfully")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                makeText(getActivity(),"clicked yes", Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
            // Toast.makeText(this, "Note Created Successfully", Toast.LENGTH_LONG).show()
        }

    }

}