package com.example.task82

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.task82.databinding.FragmentHeroesListBinding


class HeroesListFragment : Fragment() {

    private lateinit var binding: FragmentHeroesListBinding
    private val viewModel = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAbout.setOnClickListener {
            App.INSTANCE.router.navigateTo(Screens.about())
        }

        viewModel.onCreate()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onUpdate()
        }

        viewModel.heroesLiveData.observe(viewLifecycleOwner) { heroes ->
            if (heroes == null) {
                Toast.makeText(
                    requireContext(),
                    "something went wrong check your internet connection",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                binding.recyclerView.adapter = Adapter(heroes)
            }
            binding.swipeRefresh.isRefreshing = false
        }
    }
}