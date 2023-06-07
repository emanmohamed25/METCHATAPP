package com.example.chatapp.student.student_chat_receive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.R
import java.text.SimpleDateFormat
import java.util.*

class BubbleAdapter(

    private val contex: Context,
    private val BubbleItem: MutableList<MessageX>,
) : RecyclerView.Adapter<BubbleAdapter.ViewHolder>() {
    private val callBack = object : DiffUtil.ItemCallback<MessageX>() {
        override fun areItemsTheSame(
            oldItem: MessageX,
            newItem: MessageX
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MessageX,
            newItem: MessageX
        ): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, callBack)

    var dateFormateInput = "yyyy-MM-dd'T'HH:mm:ss"
    var dateFormateOutPut = "dd/MM/yyyy"
    var timeFormate = "hh:mm aa"
    private val longPressedItems = BooleanArray(BubbleItem.size)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(contex).inflate(R.layout.container_receive_messages, parent, false)
        )

    override fun getItemCount(): Int =differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var  data = differ.currentList[position]
        var time = uTCToLocal(dateFormateInput, timeFormate, data.created_at)

        holder.itemView.setOnLongClickListener {
         //   holder.rx.visibility= VISIBLE


                val x = data.id
                val sharedPref = holder.itemView.context.getSharedPreferences(
                    "idpref",
                    Context.MODE_PRIVATE
                )
                val editor = sharedPref.edit()
                editor.putInt("messid", x)
                editor.apply()
                Toast.makeText(contex, x.toString(), Toast.LENGTH_SHORT).show()
                //هغير





            true


        }


        if (data.file == "" && data.content != "") {
            var date = uTCToLocal(dateFormateInput, dateFormateOutPut, data.created_at)
            holder.mess.text = data.content
            holder.timemess.text = data.created_at
            holder.mess.visibility = View.VISIBLE
            holder.recimage.visibility = GONE


        } else if (data.file != "" && data.content == "") {
            var date = uTCToLocal(dateFormateInput, dateFormateOutPut, data.created_at)
            // Picasso.get().load(url).into(holder.recimage)
            // Glide.with(holder.itemView).load(url).override(600,800).into(holder.recimage)
            holder.timemess.text = data.created_at
            holder.recimage.visibility = View.VISIBLE
            holder.mess.visibility = GONE
        } else {
            var date = uTCToLocal(dateFormateInput, dateFormateOutPut, data.created_at)
            // Picasso.get().load(url).into(holder.recimage)
            //Glide.with(holder.itemView.context).load(url).into(holder.recimage)
            holder.mess.text = data.content
            holder.timemess.text = data.created_at
            holder.mess.visibility = View.VISIBLE
            holder.recimage.visibility = View.VISIBLE


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
        val rsv = itemview.findViewById<RelativeLayout>(R.id.resv)
        val rdef = itemview.findViewById<RelativeLayout>(R.id.redefault)
        val rstar = itemview.findViewById<RelativeLayout>(R.id.restar)
       // val rx=itemview.findViewById<LinearLayout>(R.id.selectedd)

    }
    fun uTCToLocal(
        dateFormatInPut: String?,
        dateFomratOutPut: String?,
        datesToConvert: String?
    ): String? {
        var dateToReturn = datesToConvert
        val sdf = SimpleDateFormat(dateFormatInPut)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        var gmt: Date? = null
        val sdfOutPutToSend = SimpleDateFormat(dateFomratOutPut)
        sdfOutPutToSend.timeZone = TimeZone.getDefault()
        try {
            gmt = sdf.parse(datesToConvert)
            dateToReturn = sdfOutPutToSend.format(gmt)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dateToReturn
    }

}