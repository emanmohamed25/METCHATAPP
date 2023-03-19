package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.chat_item.view.*

class ChatAdapter(private val contex: Context, private val chatItems: List<ChatData>, private val listener:(ChatData)->Unit)
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
        val image =view.findViewById<ImageView>(R.id.senderePhoto)
        val time = view.findViewById<TextView>(R.id.timesend)
        val unreadmess=view.findViewById<TextView>(R.id.numunreadmess)
        fun binditem(items: ChatData, listener:(ChatData)->Unit ){
            name.text=items.name
            status.text=items.status
            time.text=items.time
            unreadmess.text=items.unreadmess
            Glide.with(itemView.context).load(items.image).into(image)
            itemView.setOnClickListener{
                listener(items)
                var my_in=Intent(itemView.context,StudentChatActivity::class.java)
                itemView.context.startActivity(my_in)

            }


        }
    }

}