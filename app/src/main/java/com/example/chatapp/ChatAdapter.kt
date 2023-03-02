package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ChatAdapter(private val contex: Context, private val chatItems: List<ChatItem>, private val listener:(ChatItem)->Unit)
    : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(contex).inflate(R.layout.chat_item,parent,false))

    override fun getItemCount(): Int =chatItems.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.binditem(chatItems[position], listener )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name=view.findViewById<TextView>(R.id.UserName)
        val status =view.findViewById<TextView>(R.id.textmess)
        val image =view.findViewById<ImageView>(R.id.ProfilePhoto)
        val time = view.findViewById<TextView>(R.id.timesend)
        fun binditem(items: ChatItem, listener:(ChatItem)->Unit ){
            name.text=items.name
            status.text=items.status
            time.text=items.time
            Glide.with(itemView.context).load(items.image).into(image)
            itemView.setOnClickListener{
                listener(items)

            }


        }
    }

}