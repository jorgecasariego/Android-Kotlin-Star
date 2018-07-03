package com.androidatc.listview.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidatc.listview.R
import com.androidatc.listview.modelos.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class MiAdapter(val items: List<Item>, val listener: (Item) -> Unit): RecyclerView.Adapter<MiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], listener)


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item, listener: (Item) -> Unit) {
            itemView.nombreComida.text = item.titulo
            Picasso.get().load(item.url).into(itemView.imagenComida)
            itemView.setOnClickListener{listener(item)}

//            with(itemView) {
//                nombreComida.text = item.titulo
//                Picasso.get().load(item.url).into(imagenComida)
//
//                setOnClickListener{ listener(item)}
//            }
        }
    }
}