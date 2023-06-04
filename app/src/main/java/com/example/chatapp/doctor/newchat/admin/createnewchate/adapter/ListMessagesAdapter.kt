package com.example.chatapp.doctor.newchat.admin.createnewchate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Message
import kotlinx.android.synthetic.main.item_message_list.view.*
import kotlinx.android.synthetic.main.item_section.view.*

class ListMessagesAdapter(
   val messages: List<Message>, val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ListMessagesAdapter.MessamgViewHolder>() {

    inner class MessamgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            println("chicked inner")

            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessamgViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_message_list, parent, false)
        return MessamgViewHolder(view)
    }



    override fun onBindViewHolder(holder: MessamgViewHolder, position: Int) {
        holder.itemView.apply {
            tvContent.text =messages[position].content!!
            tvTime.text=messages[position].time!!
        }
    }

    override fun getItemCount(): Int {
return messages.size
    }


}