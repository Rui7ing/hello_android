package com.thoughtworks.androidtrain.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.model.Tweet

class TweetAdapter(
    private val tweets: List<Tweet>,
    private val context : Context
) : RecyclerView.Adapter<TweetAdapter.TweetViewHolder>() {

    private var layout : LayoutInflater = LayoutInflater.from(context)


    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nick : TextView = itemView.findViewById(R.id.nick)
        var content : TextView = itemView.findViewById(R.id.content)
        var avatar : ImageView = itemView.findViewById(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = layout.inflate(R.layout.tweet_item, parent, false)
        return TweetViewHolder(view)
    }

    override fun getItemCount(): Int = tweets.size

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.nick.text = tweet.sender.nick
        holder.content.text = tweet.content
    }

}