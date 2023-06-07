package com.example.chatapp.student

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.Chat
import com.example.chatapp.R
import com.example.chatapp.student.student_chat_receive.MessageX
import com.example.chatapp.student.student_chat_receive.StudentChatActivity

class ChatAdapter(
    private val contex: Context,
    private val chatItems: List<Chat>
) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    private val callBack = object : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(
            oldItem: Chat,
            newItem: Chat
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Chat,
            newItem: Chat
        ): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(contex).inflate(R.layout.chatrow, parent, false))

    override fun getItemCount(): Int = differ.currentList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data  = differ.currentList[position]
        var x =data.last_message.user_name.toString()
        val sharedPref = holder.itemView.context.getSharedPreferences(
            "MyPrefs",
            Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putString("drname", x)
        editor.commit()
        Log.d("bbbbbbbb",x)

        if (holder.lastmess.text==""){
            holder.lastmess.text="image"

        }

        holder.name.text = data.last_message.user_name.toString()
        holder.lastmess.text=data.last_message.content
        holder.unseen.text=data.unseen.toString()
        holder.time.text=data.last_message.created_at

        var id =data.id
        if (holder.unseen.getText().toString().equals("0")){
            holder.goldcirc.setVisibility(View.GONE);
            holder.time.setVisibility(View.GONE)
        }else{
            holder.goldcirc.setVisibility(View.VISIBLE )


        }
        if (holder.lastmess.text==""){
            holder.lastmess.text="image"

        }

        holder.itemView.setOnClickListener {
           //holder.unseen.text="0"
            holder.goldcirc.setVisibility(View.GONE);
            val intent = Intent(holder.itemView.context, StudentChatActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("chatid",id)
            holder.itemView.context.startActivity(intent)





        }
        /*if (holder.unseen.getText().toString().equals("0")){
            holder.goldcirc.setVisibility(View.GONE);
        }
        else{
            holder.goldcirc.setVisibility(View.VISIBLE);

        }*/

        /*holder.status.text = data.status
        holder.image.setImageResource(data.image)
        holder.time.text = data.time
        holder.unreadmess.text = data.unreadmess
        holder.mydata=data*/
        // Glide.with(contex).load(data.image).into(holder.image)


    }

    class ViewHolder(itemview: View, /*var mydata: TestClass? = null*/) :
        RecyclerView.ViewHolder(itemview) {
        val name = itemview.findViewById<TextView>(R.id.UserName)
        val lastmess=itemview.findViewById<TextView>(R.id.lastmess)
        val unseen=itemview.findViewById<TextView>(R.id.numunreadmess)
        val  time=itemview.findViewById<TextView>(R.id.timesend)
        val goldcirc=itemview.findViewById<FrameLayout>(R.id.cirunreed)








        /*init {
            itemview.setOnClickListener{
                Toast.makeText(itemview.context, mydata?.name.toString(), Toast.LENGTH_LONG).show()

               // Toast.makeText(itemview.context, mydata[].name, Toast.LENGTH_LONG).show()
                var my_intent = Intent(itemview.context, StudentChatActivity::class.java)
                //my_intent.putExtra("senderphoto", mydata?.image)
                itemview.context.startActivity(my_intent)

                Log.d("MyTag", "message")

            }
        }*/



        /*val status = itemview.findViewById<TextView>(R.id.textmess)
        val image = itemview.findViewById<ImageView>(R.id.senderePhoto)
        val time = itemview.findViewById<TextView>(R.id.timesend)
        val unreadmess = itemview.findViewById<TextView>(R.id.numunreadmess)*/

        /*fun binditem(items: ChatData, listener: (ChatData) -> Unit) {
            name.text = items.name
            status.text = items.status
            time.text = items.time
            unreadmess.text = items.unreadmess
            Glide.with(itemView.context).load(items.image).into(image)


        }*/

    }

}