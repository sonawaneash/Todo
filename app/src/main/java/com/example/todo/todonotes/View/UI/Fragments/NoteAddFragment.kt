package com.example.todo.todonotes.View.UI.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.ViewModelProvider
import com.example.todo.R

import com.example.todo.TODO_LIST.Model.Note
import com.example.todo.todonotes.Model.db.entity.Item
import com.example.todo.todonotes.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.fragment_note_add.*
import kotlinx.android.synthetic.main.headerfragment.*


class  NoteAddFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Header.setText("Add Note")
        saveNote.setOnClickListener{
            submitData()
            hideKeyboard()
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
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

