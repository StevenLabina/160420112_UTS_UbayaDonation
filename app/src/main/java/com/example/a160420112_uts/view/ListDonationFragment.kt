package com.example.a160420112_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160420112_uts.R
import com.example.a160420112_uts.viewmodel.listViewModel
import kotlinx.android.synthetic.main.fragment_list_donation.*


class ListDonationFragment : Fragment() {

    private lateinit var viewModel: listViewModel
    private val ListDonationAdapter = ListDonationAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(listViewModel::class.java)
        viewModel.refresh()
        recyclerViewHistory.layoutManager = LinearLayoutManager(context)
        recyclerViewHistory.adapter = ListDonationAdapter
        observeViewModel()
        swipeRefresh.setOnRefreshListener {
            recyclerViewHistory.visibility = View.GONE
            txtErrorNews.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            swipeRefresh.isRefreshing = false
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_donation, container, false)
    }
    fun observeViewModel() {
        viewModel.DonationsLD.observe(viewLifecycleOwner, Observer {
            ListDonationAdapter.updateDonationList(it)

        })
        viewModel.DonationsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
               txtErrorNews.visibility = View.VISIBLE
            } else {
                txtErrorNews.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recyclerViewHistory.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                recyclerViewHistory.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })

    }


}