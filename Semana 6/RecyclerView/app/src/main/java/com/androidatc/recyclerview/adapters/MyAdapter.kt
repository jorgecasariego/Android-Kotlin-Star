package com.androidatc.recyclerview.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidatc.recyclerview.R
import com.androidatc.recyclerview.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class MyAdapter(val items: List<Item>, val listener: (Item) -> Unit): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item, listener: (Item) -> Unit) {

            with(itemView) {
                titulo.text = item.titulo
                Picasso.get().load(item.url).into(imagen)

                setOnClickListener { listener(item) }
            }
        }
    }
}