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
import com.example.a160420112_uts.viewmodel.detailNewsViewModel
import kotlinx.android.synthetic.main.fragment_detail_inbox.*
import kotlinx.android.synthetic.main.fragment_detail_trending_news.*
import kotlinx.android.synthetic.main.trending_news_item.*
import kotlinx.android.synthetic.main.trending_news_item.txtNamaBeritaD


class DetailTrendingNewsFragment: Fragment() {
    private lateinit var viewModel: detailNewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_trending_news, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(detailNewsViewModel::class.java)
        if(arguments != null){
            val newsId = DetailTrendingNewsFragmentArgs.fromBundle(requireArguments()).newsId
            viewModel.fetch(newsId)
            observeViewModel()
        }

    }
    fun observeViewModel(){
        viewModel.NewsLD.observe(viewLifecycleOwner, Observer {
            Log.d("tessss", it.toString())
            txtNamaBeritaD.setText(it.namaBerita)
            txtUrlNews.setText(it.urlNews)
            txtDeskNews.setText(it.deskripsiNews)


        })

    }


}