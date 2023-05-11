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
import com.example.a160420112_uts.viewmodel.listInboxViewModel
import kotlinx.android.synthetic.main.fragment_inbox.*
import kotlinx.android.synthetic.main.fragment_inbox.recyclerViewHistory
import kotlinx.android.synthetic.main.fragment_list_donation.*


class InboxFragment : Fragment() {


    private lateinit var viewModel: listInboxViewModel
    private val InboxAdapter = InboxAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(listInboxViewModel::class.java)
        viewModel.refresh()
        recyclerViewHistory.layoutManager = LinearLayoutManager(context)
        recyclerViewHistory.adapter = InboxAdapter
        observeViewModel()

        SwipeRefreshHistories.setOnRefreshListener {
            recyclerViewHistory.visibility = View.GONE
            txtErrorH.visibility = View.GONE
            progressBarH.visibility = View.VISIBLE
            viewModel.refresh()
            SwipeRefreshHistories.isRefreshing = false
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }
    fun observeViewModel() {
        viewModel.InboxLD.observe(viewLifecycleOwner, Observer {
            InboxAdapter.updateInboxList(it)

        })
        viewModel.InboxLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorH.visibility = View.VISIBLE
            } else {
                txtErrorH.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recyclerViewHistory.visibility = View.GONE
                progressBarH.visibility = View.VISIBLE
            } else {
                recyclerViewHistory.visibility = View.VISIBLE
                progressBarH.visibility = View.GONE
            }
        })

    }

}