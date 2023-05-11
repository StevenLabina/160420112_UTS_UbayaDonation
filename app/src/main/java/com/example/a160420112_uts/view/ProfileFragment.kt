package com.example.a160420112_uts.view

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160420112_uts.R
import com.example.a160420112_uts.model.Profiles
import com.example.a160420112_uts.util.loadImage
import com.example.a160420112_uts.viewmodel.listViewModel
import com.example.a160420112_uts.viewmodel.profileViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_donation.*
import kotlinx.android.synthetic.main.fragment_donation.*
import kotlinx.android.synthetic.main.fragment_donation.txtNamaDonation
import kotlinx.android.synthetic.main.fragment_list_donation.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var viewModel: profileViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(profileViewModel::class.java)
        viewModel.refresh()
        observeViewModel()


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    fun observeViewModel() {
        viewModel.ProfileLD.observe(viewLifecycleOwner, Observer {
            txtNamaDonation?.setText(it.nameProfile)
            txtEmail?.setText(it.email)
            txtNoTelp?.setText(it.noTelp)
            txtStatus?.setText(it.status)
            imageViewProfile?.loadImage(it.imageProfile)



        })
    }
    fun ImageView.loadImage(url: String?) {
        Picasso.get()
            .load(url)
            .resize(400, 400)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_24)
            .into(this, object : Callback {
                override fun onSuccess() {

                }
                override fun onError(e: java.lang.Exception?) {
                }
            })

    }



}