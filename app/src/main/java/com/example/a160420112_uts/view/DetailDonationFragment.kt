package com.example.a160420112_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420112_uts.R
import com.example.a160420112_uts.viewmodel.detailViewModel
import kotlinx.android.synthetic.main.fragment_detail_donation.*
import kotlinx.android.synthetic.main.fragment_donation.*
import kotlinx.android.synthetic.main.fragment_donation.txtNamaDonation
import java.util.concurrent.TimeUnit


class DetailDonationFragment : Fragment() {
    private lateinit var viewModel: detailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_donation, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(detailViewModel::class.java)
        if(arguments != null){
            val donationId = DetailDonationFragmentArgs.fromBundle(requireArguments()).donationId
            viewModel.fetch(donationId)
            observeViewModel()
        }

    }
    fun observeViewModel(){
        viewModel.DonationsLD.observe(viewLifecycleOwner, Observer {
                    Log.d("tessss", it.toString())
                    txtNamaDonation.setText(it.title)
                    txtTarget.setText(it.target.toString())
                    txtTotalDonasi.setText(it.totalTerkumpul.toString())
                    txtKeterangan.setText(it.keterangan)
                    btnDonate.setOnClickListener(){
                        val action = DetailDonationFragmentDirections.actionDonation()
                        Navigation.findNavController(it).navigate(action)
                    }


        })

    }


}