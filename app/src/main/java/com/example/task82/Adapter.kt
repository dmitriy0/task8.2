package com.example.task82

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task82.databinding.RecyclerviewItemBinding
import com.squareup.picasso.Picasso

class Adapter(private val data: ArrayList<Hero>, private val activity: Activity) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RecyclerviewItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = data[position]
        with(holder.binding){
            name.text = hero.name
            Picasso.get().load(hero.images.xs).into(icon)
            card.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("name", hero.name)
                bundle.putString("imageUrl", hero.images.lg)
                bundle.putString(
                    "gender",
                    if (hero.appearance.gender == "-" || hero.appearance.gender == "null") "no information available"
                    else hero.appearance.gender
                )
                bundle.putString(
                    "eyeColor",
                    if (hero.appearance.eyeColor == "-" || hero.appearance.eyeColor == "null") "no information available"
                    else hero.appearance.eyeColor
                )
                bundle.putString(
                    "hairColor",
                    if (hero.appearance.hairColor == "-" || hero.appearance.hairColor == "null") "no information available"
                    else hero.appearance.hairColor
                )
                bundle.putString("height", hero.appearance.height[1])
                bundle.putString("weight", hero.appearance.weight[1])

                App.INSTANCE.router.navigateTo(Screens.heroInfo(bundle = bundle))
            }
        }
    }

    override fun getItemCount(): Int = data.size
}