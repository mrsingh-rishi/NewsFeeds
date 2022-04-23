package com.example.newsfeeds

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdopter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    val items: ArrayList<News> = ArrayList()
     // to create view holder for news feeds
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
         // converting layout of item_news.xml into view
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
         val viewHolder = NewsViewHolder(view)
         view.setOnClickListener{
             listener.onItemClicked(items[viewHolder.adapterPosition])
         }
         return viewHolder
    }
    // This Binds The Data in Recycler View
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    // Only Calls 1 Times.... Tells how many item in list,... Where List is our News
    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title) // Title ID
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}