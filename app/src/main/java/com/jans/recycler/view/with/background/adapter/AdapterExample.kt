package com.jans.recycler.view.with.background.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import java.io.Serializable

class AdapterExample(private val list: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        holder = JSONViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as JSONViewHolder
        val context = holder.itemView.context
        val item = list[position]
        val title = item

        holder.titleName.text = title



    }

    class JSONViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleName: TextView = itemView.findViewById(R.id.titleName)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
