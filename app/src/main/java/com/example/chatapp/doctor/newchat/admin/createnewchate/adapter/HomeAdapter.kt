package com.example.chatapp.doctor.newchat.admin.createnewchate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Chat
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Sections
import kotlinx.android.synthetic.main.item_list_chats.view.*
import kotlinx.android.synthetic.main.item_section.view.*

class HomeAdapter(
    var chats: List<Chat>,
    val listener: OnItemClickListener
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder> (){
    inner class HomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
    val view =LayoutInflater.from(parent.context)
        .inflate(R.layout.item_list_chats,parent,false)
        return HomeViewHolder(view)
    }
//    holder.itemView.apply {
//        tvSection.text = sections[position].nameSection!!
//        btnSectionCheck.setBackgroundResource(sections[position].imgIsChecked)
//    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
    holder.itemView.apply {
        ivProfile.setImageResource(chats[position].image)
        tvNameChat.text=chats[position].Name
        tvMessage.text=chats[position].message
        tvTime.text=chats[position].time
    }
    }

    override fun getItemCount(): Int {
    return  chats.size}

}