package com.motion.recyclerviewdiffutils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MyListAdapter(val dataSource: MutableList<String>) :
    RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var my_text: TextView

        init {
            my_text = itemView.findViewById(R.id.textView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.my_text.text=dataSource.get(position)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    fun insertData(newList:List<String>){
        val diffUtilCallback=MyDiffUtilCallback(dataSource,newList)
        val diffResult:DiffUtil.DiffResult=DiffUtil.calculateDiff(diffUtilCallback)

        //Add new item to existing list
        dataSource.addAll(newList)
        diffResult.dispatchUpdatesTo(this)

    }

    fun updateData(newList:List<String>){
        val diffUtilCallback=MyDiffUtilCallback(dataSource,newList)
        val diffResult:DiffUtil.DiffResult=DiffUtil.calculateDiff(diffUtilCallback)

        //clear old data
        dataSource.clear()
        dataSource.addAll(newList)
        diffResult.dispatchUpdatesTo(this)

    }
}