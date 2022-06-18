package com.example.task82

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class HeroInfoFragment(val bundle: Bundle) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hero_info, container, false)

        view.findViewById<TextView>(R.id.name).text = bundle.getString("name")
        view.findViewById<TextView>(R.id.height).text = bundle.getString("height")
        view.findViewById<TextView>(R.id.weight).text = bundle.getString("weight")
        view.findViewById<TextView>(R.id.gender).text = "Gender: ${bundle.getString("gender")}"
        view.findViewById<TextView>(R.id.eye_color).text = "Eye color: ${bundle.getString("eyeColor")}"
        view.findViewById<TextView>(R.id.hair_color).text = "Hair color: ${bundle.getString("hairColor")}"

        Picasso.get().load(bundle.getString("imageUrl")).into(view.findViewById<ImageView>(R.id.image))
        return view
    }
}