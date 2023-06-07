package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.student.student_chat_receive.BubbleAdapter
import com.example.chatapp.student.student_chat_receive.MessageX

class Starred_Message_Adapter(
    private val contex: Context,
    private val starBubbleItem: MutableList<DataXX>,
) : RecyclerView.Adapter<Starred_Message_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(contex).inflate(R.layout.container_starred_message, parent, false)
        )

    override fun getItemCount(): Int = starBubbleItem.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = starBubbleItem[position]
        holder.sender.text = data.user_name
        holder.starmessage.text = data.content
        holder.startime.text = data.created_at
    }

    class ViewHolder(itemview: View) :
        RecyclerView.ViewHolder(itemview) {
        val sender = itemview.findViewById<TextView>(R.id.UserName)
        val starmessage = itemview.findViewById<TextView>(R.id.bubble_message)
        val startime = itemview.findViewById<TextView>(R.id.bubble_time_message)


    }


}