package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Category
import com.squareup.picasso.Picasso


class CategoryAdapter(val itemList: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(itemList[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView
        init {
            image= itemView.findViewById(R.id.category_item_image)
        }
    }

    class doIt(itemView: View){
        val button: Button
        init {
            button= itemView.findViewById(R.id.addToCart)
        }
    }
}