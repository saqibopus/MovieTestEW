package com.saqib.movietestew.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MultimediaModel (
	@SerializedName("type") val type : String,
	@SerializedName("src") val src : String,
	@SerializedName("height") val height : Int,
	@SerializedName("width") val width : Int
) : Parcelable