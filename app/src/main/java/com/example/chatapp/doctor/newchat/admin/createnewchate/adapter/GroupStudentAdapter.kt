package com.example.chatapp.doctor.newchat.admin.createnewchate.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Students
import kotlinx.android.synthetic.main.item_list_of_student.view.*

class GroupStudentAdapter(
    var students: List<Students>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<GroupStudentAdapter.GroupStudentViewHOlder>() {

    inner class GroupStudentViewHOlder(itemView: View) : RecyclerView.ViewHolder(itemView),
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

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupStudentViewHOlder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_of_student, parent, false)
        return GroupStudentViewHOlder(view)
    }

    override fun onBindViewHolder(holder: GroupStudentViewHOlder, position: Int) {
        holder.itemView.apply {
            tvStudentName.text = students[position].nameStudent
            btnCheck.setBackgroundResource(students[position].imgIsChecked)
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }
}