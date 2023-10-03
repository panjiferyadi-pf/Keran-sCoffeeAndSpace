package com.example.keranscoffeeandspace.model

import android.os.Parcelable

data class PesanModel
    (
    val id: Int,
    val nomerMeja: String,
    val nama: String,
    val harga: String,
    val waktu: String
) : Parcelable