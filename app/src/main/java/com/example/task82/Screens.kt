package com.example.task82

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun heroesList() = FragmentScreen { HeroesListFragment() }
    fun heroInfo(bundle: Bundle) = FragmentScreen { HeroInfoFragment(bundle) }
    fun about() = FragmentScreen { FragmentAbout() }
}