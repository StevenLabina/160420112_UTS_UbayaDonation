package com.example.a160420112_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420112_uts.R
import com.example.a160420112_uts.model.Donations
import com.example.a160420112_uts.model.Histories
import com.example.a160420112_uts.util.loadImage

class InboxAdapter( val listInbox:ArrayList<Histories>)
    : RecyclerView.Adapter<InboxAdapter.InboxViewHolder>()
{
    class InboxViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.inbox_item, parent, false)
        return InboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        val txtNamaHistory= holder.view.findViewById<TextView>(R.id.txtInfo)
        txtNamaHistory.text = listInbox[position].namaHistory
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetailH)
        btnDetail.setOnClickListener(){
            var idInbox = "0"
            listInbox[position].idHistory?.let{
                idInbox = it.toString()
            }
            val action = InboxFragmentDirections.actionDetailInbox(idInbox)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return listInbox.size
    }
    fun updateInboxList(newInboxList: ArrayList<Histories>) {
        listInbox.clear()
        listInbox.addAll(newInboxList)
        notifyDataSetChanged()

    }
}