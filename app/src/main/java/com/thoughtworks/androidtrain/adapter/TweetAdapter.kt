package com.thoughtworks.androidtrain.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.entity.Tweet

const val TWEET_TYPE = 0
const val BOTTOM_TYPE = 1

class TweetAdapter(
    private val tweets: List<Tweet>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layout: LayoutInflater = LayoutInflater.from(context)


    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nick: TextView = itemView.findViewById(R.id.nick)
        var content: TextView = itemView.findViewById(R.id.content)
        var avatar: ImageView = itemView.findViewById(R.id.avatar)

        fun bind(tweet: Tweet) {
            nick.text = tweet.sender?.nick
            content.text = tweet.content
            avatar.load(tweet.sender?.avatar)
        }
    }

    class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BOTTOM_TYPE -> BottomViewHolder(layout.inflate(R.layout.bottom_item, parent, false))
            else -> TweetViewHolder(layout.inflate(R.layout.tweet_item, parent, false))
        }
    }

    override fun getItemCount(): Int = tweets.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BottomViewHolder) {
            return
        }
        if (holder is TweetViewHolder) {
            holder.bind(tweets[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount - 1 -> BOTTOM_TYPE
            else -> TWEET_TYPE
        }
    }
}