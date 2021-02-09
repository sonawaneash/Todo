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
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_note_update.*


class NoteUpdateFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(
            ItemViewModel::class.java)

        updateTitle.setText(arguments?.getString("title", ""))
        updateContent.setText(arguments?.getString("content", ""))

        btnUpdate.setOnClickListener{
            updatesubmit()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_update, container, false)
    }


    fun updatesubmit() {
        val uTitle = arguments?.getString("title", "")
        val uContent=arguments?.getString("content", "")
        val id = arguments?.getInt("id", -1)

        val builder = AlertDialog.Builder(context)
        var updateTitle = updateTitle.text.toString()
        var updateContent = updateContent.text.toString()


        if (updateTitle.isNullOrEmpty() || id == -1) {
            builder.setTitle(getString(R.string.empty_error_msg))
            builder.setPositiveButton("OK"){dialogInterface, which ->
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
        else{
            if( updateTitle == uTitle)
                Toast.makeText(activity,getString(R.string.update_error_msg), Toast.LENGTH_LONG).show()
            else {
                viewModel.updateItem(id!!, updateTitle, updateContent)
                builder.setTitle(getString(R.string.update_success_msg))
                builder.setPositiveButton("OK") { dialogInterface, which ->
                    Toast.makeText(getActivity(), getString(R.string.update_success_msg), Toast.LENGTH_LONG).show()
                    activity?.supportFragmentManager?.popBackStack()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }


    }

}