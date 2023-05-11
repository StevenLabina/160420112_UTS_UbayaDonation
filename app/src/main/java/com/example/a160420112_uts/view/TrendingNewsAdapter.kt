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
import com.example.a160420112_uts.model.News
import com.example.a160420112_uts.util.loadImage

class TrendingNewsAdapter( val listNews:ArrayList<News>)
    : RecyclerView.Adapter<TrendingNewsAdapter.NewsViewHolder>()
{
    class NewsViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.trending_news_item, parent, false)
        return NewsViewHolder(view)

    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val txtNameNews =holder.view.findViewById<TextView>(R.id.txtNamaBeritaD)
        txtNameNews.text = listNews[position].namaBerita
        val imageViewNews = holder.view.findViewById<ImageView>(R.id.imageViewNews)
        var progressBarNews = holder.view.findViewById<ProgressBar>(R.id.progressBarItemNews)
        imageViewNews.loadImage(listNews[position].imageBerita, progressBarNews  )
        val btnDetailN =holder.view.findViewById<Button>(R.id.btnDetailNews)
        btnDetailN.setOnClickListener(){
            var id = "0"
            listNews[position].idBerita?.let{
                id = it.toString()
            }
            val action = TrendingNewsFragmentDirections.actionDetailNews(id)
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun getItemCount(): Int {
        return listNews.size
    }
    fun updateNewsList(newsTrendingList: ArrayList<News>) {
        listNews.clear()
        listNews.addAll(newsTrendingList)
        notifyDataSetChanged()

    }
}