package com.example.task82

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.task82.databinding.FragmentHeroInfoBinding
import com.squareup.picasso.Picasso


class HeroInfoFragment(private val bundle: Bundle) : Fragment() {

    private lateinit var binding: FragmentHeroInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroInfoBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            name.text = bundle.getString("name")
            height.text = bundle.getString("height")
            weight.text = bundle.getString("weight")
            gender.text = "Gender: ${bundle.getString("gender")}"
            eyeColor.text = "Eye color: ${bundle.getString("eyeColor")}"
            hairColor.text = "Hair color: ${bundle.getString("hairColor")}"
            Picasso.get().load(bundle.getString("imageUrl"))
                .into(image)
        }

    }
}