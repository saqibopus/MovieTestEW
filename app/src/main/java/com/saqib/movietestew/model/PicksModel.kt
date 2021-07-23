package com.saqib.movietestew.model

import com.google.gson.annotations.SerializedName

data class PicksModel (
	@SerializedName("status") val status : String,
	@SerializedName("copyright") val copyright : String,
	@SerializedName("has_more") val has_more : Boolean,
	@SerializedName("num_results") val num_results : Int,
	@SerializedName("results") val results : List<ResultsModel>
)