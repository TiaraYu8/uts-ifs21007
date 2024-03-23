package com.ifs21007.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Fam(
    val icon : Int,
    val title : String,
    val desc :String,
    val per :String,
    val chara :String,
    val habit :String,
    val behavi :String,
    val sIn :Int,
    val eIn :Int,
) :Parcelable


