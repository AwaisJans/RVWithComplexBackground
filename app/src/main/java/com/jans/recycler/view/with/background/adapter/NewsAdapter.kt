package com.jans.recycler.view.with.background.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.activities.OneBGImageRVScreen
import com.jans.recycler.view.with.background.activities.TwoImagesBGScreen
import com.jans.recycler.view.with.background.model.BGPositionModelClass

class NewsAdapter(private val list: BGPositionModelClass?) :
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
        val item = list!![position]
        val title = item.title

        holder.titleName.text = title


        holder.itemView.setOnClickListener{

            if (item.isTwoImageBGScreen){
                val nextScreenIntent = Intent(context,TwoImagesBGScreen::class.java)
                nextScreenIntent.putExtra("ARRAY_LIST_NEWS",item)
                context.startActivity(nextScreenIntent)
            } else{
                val nextScreenIntent = Intent(context,OneBGImageRVScreen::class.java)
                nextScreenIntent.putExtra("ARRAY_LIST_NEWS",item)
                context.startActivity(nextScreenIntent)
            }

        }

    }

    class JSONViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleName: TextView = itemView.findViewById(R.id.titleName)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}
