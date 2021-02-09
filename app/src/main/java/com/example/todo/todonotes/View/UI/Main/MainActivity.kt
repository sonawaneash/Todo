package com.example.todo.todonotes.View.UI.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.R
import com.example.todo.todonotes.View_Model.ItemViewModel
import com.example.todo.todonotes.View.UI.Fragments.ListFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(savedInstanceState == null){


            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.FragmentContainer, listFragment).commit()

        }

    }

}
