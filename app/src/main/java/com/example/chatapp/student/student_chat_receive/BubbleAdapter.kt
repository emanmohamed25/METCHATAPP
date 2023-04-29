package com.example.chatapp.student.student_chat_receive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R

class BubbleAdapter(
    private val contex: Context,
    private val BubbleItem: List<Message>,
) : RecyclerView.Adapter<BubbleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(contex).inflate(R.layout.container_receive_messages,parent,false))

    override fun getItemCount(): Int = BubbleItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  data  =BubbleItem[position]
        holder.mess.text= data.content
        holder.timemess.text=data.created_at

    }
    class ViewHolder(itemview: View ) :
        RecyclerView.ViewHolder(itemview) {
            val mess=itemview.findViewById<TextView>(R.id.bubble_message)
            val timemess=itemview.findViewById<TextView>(R.id.bubble_time_message)


        }



}