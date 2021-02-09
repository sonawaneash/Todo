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
import androidx.lifecycle.ViewModelProvider
import com.example.todo.R
import com.example.todo.todonotes.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.fragment_note_update.*
import kotlinx.android.synthetic.main.headerfragment.*


class NoteUpdateFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Header.setText("Update Note")

        viewModel = ViewModelProvider(this).get(
            ItemViewModel::class.java)


        updateTitle.setText(arguments?.getString("title", ""))
        updateContent.setText(arguments?.getString("content", ""))


        btnUpdate.setOnClickListener{
            updatesubmit()
            hideKeyboard()
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
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}