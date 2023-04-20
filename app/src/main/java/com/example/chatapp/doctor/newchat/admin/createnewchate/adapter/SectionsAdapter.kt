package com.example.chatapp.doctor.newchat.admin.createnewchate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Sections
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Students
import kotlinx.android.synthetic.main.item_list_of_student.view.*

class SectionsAdapter(
    var sections: List<Sections>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<SectionsAdapter.SectionViewholder>() {
    inner class SectionViewholder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_section, parent, false)
        return SectionViewholder(view)
    }

    override fun onBindViewHolder(holder: SectionViewholder, position: Int) {
        holder.itemView.apply {
            tvStudentName.text = sections[position].nameSection
            btnCheck.setBackgroundResource(sections[position].imgIsChecked)
        }

    }

    override fun getItemCount(): Int {
        return sections.size
    }


}