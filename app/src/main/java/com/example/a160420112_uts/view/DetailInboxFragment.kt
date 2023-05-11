package com.example.a160420112_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420112_uts.R
import com.example.a160420112_uts.viewmodel.detailInboxViewModel
import com.example.a160420112_uts.viewmodel.detailViewModel
import kotlinx.android.synthetic.main.fragment_detail_donation.*
import kotlinx.android.synthetic.main.fragment_detail_inbox.*
import kotlinx.android.synthetic.main.fragment_donation.*
import kotlinx.android.synthetic.main.fragment_donation.txtNamaDonation


class DetailInboxFragment : Fragment() {
    private lateinit var viewModel: detailInboxViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_inbox, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(detailInboxViewModel::class.java)
        if(arguments != null){
            val donationId = DetailInboxFragmentArgs.fromBundle(requireArguments()).inboxId
            viewModel.fetch(donationId)
            observeViewModel()
        }

    }
    fun observeViewModel(){
        viewModel.InboxLD.observe(viewLifecycleOwner, Observer {
            Log.d("tessss", it.toString())
            txtNamaDonasiH.setText(it.namaDonasiH)
            txtNominalDonasi.setText(it.nominalDonasi.toString())
            txtTanggalDonasi.setText(it.tanggal)


        })

    }


}