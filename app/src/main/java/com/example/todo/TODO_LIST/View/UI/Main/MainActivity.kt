package com.example.todo.TODO_LIST.View.UI.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todo.R
import com.example.todo.TODO_LIST.View.UI.Fragments.HeaderFragment
import com.example.todo.TODO_LIST.View_Model.ItemViewModel
import com.example.todo.TODO_LIST.View.UI.Fragments.List_Fragment
import com.example.todo.TODO_LIST.View.UI.Fragments.NoteAddFragment
import kotlinx.android.synthetic.main.fragment_header.*
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity : AppCompatActivity()  {

    lateinit var viewModel : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){

            val header = HeaderFragment()
            supportFragmentManager.beginTransaction().replace(R.id.FragmentHeaderContainer, header).commit()

            val listFragment = List_Fragment()
            supportFragmentManager.beginTransaction().replace(R.id.FragmentContainer, listFragment).commit()

        }

    }

}
