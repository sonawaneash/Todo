package com.example.todo.TODO_LIST.View.UI.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.R
import com.example.todo.todonotes.View.UI.Fragments.ListFragment
import com.example.todo.todonotes.View_Model.ItemViewModel

class MainActivity : AppCompatActivity()  {

    lateinit var viewModel : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){

            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.FragmentContainer, listFragment).commit()

        }

    }

}
