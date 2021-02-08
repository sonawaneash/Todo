package com.example.todo.TODO_LIST.View.UI.Fragments

import android.content.Intent
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
import com.example.todo.TODO_LIST.View.UI.InsertNote.Addnote
import com.example.todo.TODO_LIST.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*


class List_Fragment : Fragment(), ItemClickAdapter {


    lateinit var viewModel : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(getActivity())
        val adapter = Adapter(this, ArrayList())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(
                activity
            )
        ).get(
            ItemViewModel::class.java
        )
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
                list ->
            list?.let {
                adapter.updateList(it)      //update only when list not null
            }

        })

        //floating button

        btnAdd.setOnClickListener {
            val intent = Intent(getActivity(), Addnote::class.java)
            startActivity(intent)
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
        Toast.makeText(getActivity(), "Note Deleted", Toast.LENGTH_LONG).show()
        //Toast.makeText(this,"Note Deleted Successfully", Toast.LENGTH_LONG).show()

    }

    override fun onItemUpdated(item: Item) {
      /*  val intent = Intent(getActivity(), NoteUpdateFragment::class.java)
        val id = item.id
        val title=item.title
        val content= item.content
        intent.putExtra("id", id)
        intent.putExtra("title", title.toString())
        intent.putExtra("content", content.toString())
        startActivity(intent)
       */
        val updateFragment = NoteUpdateFragment()
        val bundle = bundleOf(Pair("key", "value"))
        updateFragment.arguments = bundle
        support
        supportFragmentManager.beginTransaction().replace(R.id.FragmentContainer, updateFragment).commit()

        Toast.makeText(getActivity(), "$id Clicked", Toast.LENGTH_LONG).show()
    }


}