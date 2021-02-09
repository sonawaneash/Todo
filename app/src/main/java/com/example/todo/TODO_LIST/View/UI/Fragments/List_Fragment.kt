package com.example.todo.TODO_LIST.View.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.TODO_LIST.Model.Note
import com.example.todo.TODO_LIST.UI.Adapter
import com.example.todo.TODO_LIST.UI.ItemClickAdapter
import com.example.todo.TODO_LIST.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.fragment_header.*
import kotlinx.android.synthetic.main.fragment_list.*


class List_Fragment : Fragment(), ItemClickAdapter {

    lateinit var viewModel : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = Adapter(this, ArrayList())
        recyclerView?.adapter = adapter

        viewModel = ViewModelProvider(this).get(
            ItemViewModel::class.java
        )
        viewModel.allItems.observe(viewLifecycleOwner, Observer {    // list can be nulluble
                list ->
            list?.let {
                var noteList = Note.convertItemListToNoteList(ArrayList(it))
                adapter.updateList(noteList)      //update only when list not null
            }
        })

        btnAdd.setOnClickListener{
            val addFragment = NoteAddFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.FragmentContainer, addFragment)?.addToBackStack(null)?.commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onItemDeleted(note: Note) {
        viewModel.deleteItem(note)
        Toast.makeText(getActivity(), getString(R.string.delete_success_msg), Toast.LENGTH_LONG).show()

    }

    override fun onItemUpdated(note: Note) {

        val updateFragment = NoteUpdateFragment()
        val bundle = bundleOf(Pair("id", note.id),Pair("title", note.title), Pair("content", note.content))
        updateFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.FragmentContainer, updateFragment)?.addToBackStack(null)?.commit()
    }


}