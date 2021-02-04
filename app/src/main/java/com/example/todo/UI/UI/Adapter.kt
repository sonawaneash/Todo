package com.example.todo.UI.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.UI.db.Item

//Adapter class will adapt our data so that it can be displayed in a list
class Adapter( private val context: Context, private val listener: ItemClickAdapter): RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    private val todoList: ArrayList<ListItem>()

    override fun onCreateViewHolder(p: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p, false) ) //inflate() turns layout file to view obj
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(todoList[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentitem = todoList[position]
        holder.tvItemName.text = currentitem.itemName //itemName from Item data class created
        holder.tvItemData.text = currentitem.itemData
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { //ViewHolder will hold the view and helps in recycling. It represents single row in list
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName) //= itemView.tvItemName
        val tvItemData: TextView = itemView.findViewById(R.id.tvItemData)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    fun updateList(newList: List<Item>){
        todoList.clear()
        todoList.addAll(newList)

        notifyDataSetChanged()
    }

}

//to handle clicks we create interface
interface ItemClickAdapter{
    fun onItemClicked(item: ListItem)
}