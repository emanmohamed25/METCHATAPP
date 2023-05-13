package com.example.chatapp.doctor.newchat.admin.createnewchate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Stuff
import kotlinx.android.synthetic.main.item_list_of_student.view.*

class StuffAdapter(
    var stuffS: List<Stuff>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<StuffAdapter.StuffViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class StuffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StuffViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_of_student, parent, false)
        return StuffViewHolder(view)
    }

    override fun onBindViewHolder(holder: StuffViewHolder, position: Int) {
        holder.itemView.apply {
            tvName.text = stuffS[position].nameStuff
            btnCheck.setBackgroundResource(stuffS[position].imgIsChecked)
        }
    }

    override fun getItemCount(): Int {

        return stuffS.size
    }
}