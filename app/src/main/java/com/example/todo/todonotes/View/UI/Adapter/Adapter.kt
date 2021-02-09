package com.example.todo.todonotes.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.todonotes.Model.db.entity.Item
import com.example.todo.todonotes.View.UI.Fragments.List_Fragment

//Adapter class will adapt our data so that it can be displayed in a list
class Adapter(private val listener: List_Fragment, private val allitems: ArrayList<Item>): RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    override fun onCreateViewHolder(p: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(LayoutInflater.from(p.context).inflate(R.layout.list_item, p, false) ) //inflate() turns layout file to view obj

        viewHolder.btnDelete.setOnClickListener {
            listener.onItemDeleted(allitems[viewHolder.adapterPosition])
        }


        return  viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentitem = allitems[position]
        holder.tvItemName.text = currentitem.title //title and content from Item data class created
        holder.tvItemData.text = currentitem.content

        holder.itemView.setOnClickListener{
            listener.onItemUpdated(currentitem)
        }
    }

    override fun getItemCount(): Int {
        return allitems.size
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { //ViewHolder will hold the view and helps in recycling. It represents single row in list
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName) //= itemView.tvItemName
        val tvItemData: TextView = itemView.findViewById(R.id.tvItemData)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    fun updateList(newList: List<Item>){ //whle observimg data needs to be updated with live data
        allitems.clear()
        allitems.addAll(newList)

        notifyDataSetChanged() //update automatically
    }

}

//to handle clicks we create interface
interface ItemClickAdapter{
    fun onItemDeleted(item: Item)
    fun onItemUpdated(item: Item)
}