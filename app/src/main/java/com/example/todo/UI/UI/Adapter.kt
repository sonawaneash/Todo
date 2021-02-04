package com.example.todo.UI.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R

//Adapter class will adapt our data so that it can be displayed in a list
class Adapter(val todoList: List<ListItem>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(p: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(p.context).inflate(R.layout.list_item, p, false) //inflate() turns layout file to view obj

        return  ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem: ListItem = todoList[position]
        holder.tvItemName.text = currentitem.itemName //itemName from Item data class created
        holder.tvItemData.text = currentitem.itemData
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { //ViewHolder will hold the view and helps in recycling. It represents single row in list
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName) //= itemView.tvItemName
        val tvItemData: TextView = itemView.findViewById(R.id.tvItemData)
    }

}

