package com.example.chatapp.student

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.ChatData
import com.example.chatapp.R

class BubbleAdapter (
    private val contex: Context,
    private val BubbleItem: MutableList<bubble_chat_data>,
    private val listener: (ChatData) -> Unit,
) : RecyclerView.Adapter<BubbleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(contex).inflate(R.layout.container_receive_messages,parent,false))

    override fun getItemCount(): Int = BubbleItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  data : bubble_chat_data =BubbleItem[position]
        holder.mess.text= data.message
        holder.timemess.text=data.timemessage

    }
    class ViewHolder(itemview: View ) :
        RecyclerView.ViewHolder(itemview) {
            val mess=itemview.findViewById<TextView>(R.id.bubble_message)
            val timemess=itemview.findViewById<TextView>(R.id.bubble_time_message)


        }



}