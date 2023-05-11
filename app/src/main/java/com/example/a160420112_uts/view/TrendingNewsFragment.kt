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
import com.example.a160420112_uts.viewmodel.listNewsViewModel
import kotlinx.android.synthetic.main.fragment_list_donation.*
import kotlinx.android.synthetic.main.fragment_list_donation.txtErrorNews
import kotlinx.android.synthetic.main.fragment_trending_news.*


class TrendingNewsFragment : Fragment() {

    private lateinit var viewModel: listNewsViewModel
    private val TrendingNewsAdapter = TrendingNewsAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(listNewsViewModel::class.java)
        viewModel.refresh()
        recyclerViewNews.layoutManager = LinearLayoutManager(context)
        recyclerViewNews.adapter = TrendingNewsAdapter
        observeViewModel()
        swipeRefreshTrending.setOnRefreshListener {
            recyclerViewNews.visibility = View.GONE
            txtErrorNews.visibility = View.GONE
            progressBarNews.visibility = View.VISIBLE
            viewModel.refresh()
            swipeRefreshTrending.isRefreshing = false
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending_news, container, false)
    }
    fun observeViewModel() {
        viewModel.NewsLD.observe(viewLifecycleOwner, Observer {
            TrendingNewsAdapter.updateNewsList(it)

        })
        viewModel.NewsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorNews.visibility = View.VISIBLE
            } else {
                txtErrorNews.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recyclerViewNews.visibility = View.GONE
                progressBarNews.visibility = View.VISIBLE
            } else {
                recyclerViewNews.visibility = View.VISIBLE
                progressBarNews.visibility = View.GONE
            }
        })

    }

}