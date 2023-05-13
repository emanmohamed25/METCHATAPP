package com.example.chatapp.student.student_chat_receive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.squareup.picasso.Picasso

class BubbleAdapter(
    private val contex: Context,
    private val BubbleItem: MutableList<MessageX>,
) : RecyclerView.Adapter<BubbleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(contex).inflate(R.layout.container_receive_messages, parent, false)
        )

    override fun getItemCount(): Int = BubbleItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = BubbleItem[position]
       // val url = data.file.toString()

        if (data.file == "" && data.content != "") {
            holder.mess.text = data.content
            holder.timemess.text = data.created_at
            holder.mess.setVisibility(View.VISIBLE)
            holder.recimage.setVisibility(View.GONE)


        } else if (data.file != "" && data.content == "") {
           // Picasso.get().load(url).into(holder.recimage)
           // Glide.with(holder.itemView).load(url).override(600,800).into(holder.recimage)
            holder.timemess.text = data.created_at
            holder.recimage.setVisibility(View.VISIBLE)
            holder.mess.setVisibility(View.GONE)
        } else {
           // Picasso.get().load(url).into(holder.recimage)
            //Glide.with(holder.itemView.context).load(url).into(holder.recimage)
            holder.mess.text = data.content
            holder.timemess.text = data.created_at
            holder.mess.setVisibility(View.VISIBLE)
            holder.recimage.setVisibility(View.VISIBLE)


        }
        //holder.mess.text = data.content


    }

    fun addDataItem(modelLocal: MessageX) {
        BubbleItem.add(modelLocal)

    }

    class ViewHolder(itemview: View) :
        RecyclerView.ViewHolder(itemview) {
        val mess = itemview.findViewById<TextView>(R.id.bubble_message)
        val timemess = itemview.findViewById<TextView>(R.id.bubble_time_message)
        val recimage = itemview.findViewById<ImageView>(R.id.imagemessage)


    }


}