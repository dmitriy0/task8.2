package com.example.task82

data class Hero(
    val name: String,
    val images: Image,
    val appearance: Appearance,
)


data class Image(
    val xs: String,
    val lg: String
)


data class Appearance(
    val gender: String,
    val height: List<String>,
    val weight: List<String>,
    val eyeColor: String,
    val hairColor: String
)