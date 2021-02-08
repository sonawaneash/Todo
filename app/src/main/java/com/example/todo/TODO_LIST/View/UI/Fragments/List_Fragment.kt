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
import com.example.todo.TODO_LIST.Model.Item
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
                adapter.updateList(it)      //update only when list not null
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

    override fun onItemDeleted(item: Item) {
        viewModel.deleteItem(item)
        Toast.makeText(getActivity(), "Successfully Deleted", Toast.LENGTH_LONG).show()

    }

    override fun onItemUpdated(item: Item) {

        val updateFragment = NoteUpdateFragment()
        val bundle = bundleOf(Pair("id", item.id),Pair("title", item.title), Pair("content", item.content))
        updateFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.FragmentContainer, updateFragment)?.addToBackStack(null)?.commit()
        Toast.makeText(context, "$id Clicked", Toast.LENGTH_LONG).show()
    }


}