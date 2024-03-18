package com.ifs21007.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Fam(
    var dino: String,
    var icon:Int,
    var des: String,
    var habit:String,
    var per: String,
    var chare:String,
    var behav: String,
) : Parcelable