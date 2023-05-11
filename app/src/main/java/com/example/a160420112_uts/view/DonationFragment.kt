package com.example.a160420112_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420112_uts.R
import com.example.a160420112_uts.viewmodel.detailInboxViewModel
import com.example.a160420112_uts.viewmodel.detailViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_detail_inbox.*
import kotlinx.android.synthetic.main.fragment_donation.*


class DonationFragment : Fragment() {
    private lateinit var viewModel: detailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(detailViewModel::class.java)
        val btnDonateNow = view.findViewById<Button>(R.id.btnDonateNow)
        val nominalDonate = view.findViewById<TextInputLayout>(R.id.textInputDonation).editText
        btnDonateNow.setOnClickListener {
            if (nominalDonate?.text.toString() != "") {
                Toast.makeText(
                    activity,
                    "Successfully donated Rp${nominalDonate?.text.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                val action = DonationFragmentDirections.actionListDonation()
                Navigation.findNavController(it).navigate(action)
            } else {
                Toast.makeText(activity, "Please input donation value", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun observeViewModel() {


    }


}