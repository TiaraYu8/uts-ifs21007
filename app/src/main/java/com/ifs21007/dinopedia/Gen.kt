package com.ifs21007.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gen(
    val icon: Int,
    val dino: String,
    val des: String,
    val food: String,
    val chars: String,
    val height: String,
    val weight: String,
    val habit: String,
    val behav: String,
    val weak: String,
) : Parcelable


