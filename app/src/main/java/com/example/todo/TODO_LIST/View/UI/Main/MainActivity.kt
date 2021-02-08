package com.example.todo.TODO_LIST.View.UI.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.TODO_LIST.View_Model.ItemViewModel
import com.example.todo.TODO_LIST.Model.Item
import com.example.todo.TODO_LIST.UI.Adapter
import com.example.todo.TODO_LIST.UI.ItemClickAdapter
import com.example.todo.TODO_LIST.View.UI.Fragments.List_Fragment
import com.example.todo.TODO_LIST.View.UI.Fragments.NoteAddFragment
import com.example.todo.TODO_LIST.View.UI.Fragments.NoteUpdateFragment
import com.example.todo.TODO_LIST.View.UI.InsertNote.Addnote
import com.example.todo.TODO_LIST.View.UI.UpdateNote.Updatenote
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity : AppCompatActivity(), ItemClickAdapter  {

    lateinit var viewModel : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val listFragment = List_Fragment()
            //val bundle = bundleOf(Pair("key", "value"))
            //listFragment.arguments = bundle

            supportFragmentManager.beginTransaction().replace(R.id.FragmentContainer, listFragment).commit()

        }

        btnAdd.setOnClickListener{
            val addFragment = NoteAddFragment()
            val bundle = bundleOf(Pair("key", "value"))
            addFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.FragmentContainer, addFragment).commit()

            Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
        }

/*
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this, ArrayList())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ItemViewModel::class.java)
        viewModel.allItems.observe(this, Observer {    // list can be nulluble
            list -> list?.let{
                adapter.updateList(it)      //update only when list not null
            }

        })

        //floating button
        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, Addnote::class.java)
            startActivity(intent)
        }
        */

    }


    //activity will talk only to view/model
    // delete fn
    override fun onItemDeleted(item: Item) { // interface defined in adapter
        viewModel.deleteItem(item)
        Toast.makeText(this,"Note Deleted Successfully", Toast.LENGTH_LONG).show()
    }

    override fun onItemUpdated(item: Item) {

        val intent = Intent(this, Updatenote::class.java)
        val id = item.id
        val title=item.title
        val content= item.content
        intent.putExtra("id", id)
        intent.putExtra("title", title.toString())
        intent.putExtra("content", content.toString())

        startActivity(intent)
        Toast.makeText(this,"$id Clicked", Toast.LENGTH_LONG).show()
    }


}
