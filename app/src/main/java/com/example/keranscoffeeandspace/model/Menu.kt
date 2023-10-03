package com.example.keranscoffeeandspace.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val Menu: String,
    val itemMenu: List<ItemMenu>
) : Parcelable
