package com.olivoreo.fiznote.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olivoreo.fiznote.R

class Adapter(listMain:ArrayList<String>) : RecyclerView.Adapter<Adapter.Holder>() {

    var listArray = listMain

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTv:TextView = itemView.findViewById(R.id.title_tv)
        fun setData(title:String){
            titleTv.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.rc_item,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(listArray[position])
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    fun updateAdapter(listItems:List<String>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
}