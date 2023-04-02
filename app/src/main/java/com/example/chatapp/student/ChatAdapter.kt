package com.example.chatapp.student

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.ChatData
import com.example.chatapp.R

class ChatAdapter(
    private val contex: Context,
    private val chatItems: MutableList<ChatData>,
    private val listener: (ChatData) -> Unit,
) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(contex).inflate(R.layout.chatrow, parent, false))

    override fun getItemCount(): Int = chatItems.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: ChatData = chatItems[position]
        holder.name.text = data.name
        holder.status.text = data.status
        holder.image.setImageResource(data.image)
        holder.time.text = data.time
        holder.unreadmess.text = data.unreadmess
        holder.mydata = data


        // holder.binditem(chatItems[position], listener)
    }

    class ViewHolder(itemview: View, var mydata: ChatData? = null) :
        RecyclerView.ViewHolder(itemview) {
        init {
            itemview.setOnClickListener {
                Toast.makeText(itemview.context, mydata?.name, Toast.LENGTH_LONG).show()
                var my_intent=Intent(itemview.context, StudentChatActivity::class.java)
                my_intent.putExtra("senderphoto",mydata?.image)
                itemview.context.startActivity(my_intent)


            }

        }


        val name = itemview.findViewById<TextView>(R.id.UserName)
        val status = itemview.findViewById<TextView>(R.id.textmess)
        val image = itemview.findViewById<ImageView>(R.id.senderePhoto)
        val time = itemview.findViewById<TextView>(R.id.timesend)
        val unreadmess = itemview.findViewById<TextView>(R.id.numunreadmess)


        /* fun binditem(items: ChatData, listener: (ChatData) -> Unit) {
             name.text = items.name
             status.text = items.status
             time.text = items.time
             unreadmess.text = items.unreadmess
             Glide.with(itemView.context).load(items.image).into(image)


         }*/
    }

}