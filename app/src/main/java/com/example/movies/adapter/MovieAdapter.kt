package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Movie

class MovieAdapter(
    private val mList: List<Movie>,
    private var listener: ClickItemListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    interface ClickItemListener {
        fun onClicked(model: Any,position:Int)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = mList.get(position)

        holder.itemView.setOnClickListener {
            listener.onClicked(model, position)
        }

        val ItemsViewModel = mList[position]

        holder.title.text = ItemsViewModel.title

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(
        ItemView: View
    ) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
    }
}