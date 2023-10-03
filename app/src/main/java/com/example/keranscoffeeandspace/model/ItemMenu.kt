package com.example.keranscoffeeandspace.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemMenu(
    val image: String,
    val nama: String,
    val deskripsi: String,
    val harga: String
) : Parcelable
