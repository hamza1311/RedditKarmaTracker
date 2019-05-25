package com.example.redditkarmatracker.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.redditkarmatracker.R
import com.example.redditkarmatracker.models.Karma
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import java.util.*

class KarmaStatsAdapter(private val context: Context, private val list: List<Karma>) :
    RecyclerView.Adapter<KarmaStatsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        @SuppressLint("InflateParams")
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(karma: Karma) {
            itemView.statsRow_karma_textView.text =
                context.getString(R.string.current_karma, karma.currentKarma.toString())
            itemView.statsRow_time_textView.text =
                context.getString(R.string.current_time, Date(karma.currentTime).toString().split(" GMT")[0])
        }
    }

}