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
import com.example.todo.TODO_LIST.Model.Note
import com.example.todo.TODO_LIST.UI.Adapter
import com.example.todo.TODO_LIST.View.UI.Main.MainActivity
import com.example.todo.TODO_LIST.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.fragment_header.*
import kotlinx.android.synthetic.main.fragment_note_add.*


class NoteAddFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveNote.setOnClickListener{
            submitData()
        }

        viewModel = ViewModelProvider(this).get(
            ItemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_add, container, false)

    }


    fun submitData() {
        val itemText = addNotetitle.text.toString()
        val itemContent = addNotecontent.text.toString()
        val builder = AlertDialog.Builder(activity)
        if (itemText.isEmpty()) {

            builder.setTitle(getString(R.string.empty_error_msg))
            builder.setPositiveButton("OK"){dialogInterface, which -> }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
        else{
            viewModel.insertItem((Note(itemText, itemContent)))
            builder.setTitle(getString(R.string.add_success_msg))
            builder.setPositiveButton("OK"){dialogInterface, which ->
                makeText(getActivity(),getString(R.string.add_success_msg), Toast.LENGTH_LONG).show()
                activity?.supportFragmentManager?.popBackStack()
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }

    }

}