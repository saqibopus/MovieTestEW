package com.saqib.movietestew.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinkModel (
	@SerializedName("type") val type : String,
	@SerializedName("url") val url : String,
	@SerializedName("suggested_link_text") val suggested_link_text : String
) : Parcelable