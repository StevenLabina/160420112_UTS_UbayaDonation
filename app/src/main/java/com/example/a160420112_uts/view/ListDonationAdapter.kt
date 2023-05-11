package com.example.a160420112_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420112_uts.R
import com.example.a160420112_uts.model.Donations
import com.example.a160420112_uts.util.loadImage

class ListDonationAdapter( val listDonation:ArrayList<Donations>)
    :RecyclerView.Adapter<ListDonationAdapter.DonationViewHolder>()
{
    class DonationViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_donation_item, parent, false)
        return DonationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        val txtNameDonation = holder.view.findViewById<TextView>(R.id.txtNamaDonation)
        txtNameDonation.text = listDonation[position].title
        val imageView = holder.view.findViewById<ImageView>(R.id.imageViewNews)
        var progressBarVar = holder.view.findViewById<ProgressBar>(R.id.progressBarItemNews)
        imageView.loadImage(listDonation[position].image, progressBarVar  )
        val btnView =holder.view.findViewById<Button>(R.id.btnView)
        btnView.setOnClickListener(){
            var id = "0"
            listDonation[position].id?.let{
                id = it.toString()
            }
            val action = ListDonationFragmentDirections.actionDetailDonation(id)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return listDonation.size
    }
    fun updateDonationList(newDonationList: ArrayList<Donations>) {
        listDonation.clear()
        listDonation.addAll(newDonationList)
        notifyDataSetChanged()

    }
}

