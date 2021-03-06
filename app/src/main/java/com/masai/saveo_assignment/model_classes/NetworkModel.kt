package com.masai.saveo_assignment.model_classes


import com.google.gson.annotations.SerializedName

data class NetworkModel(

	@field:SerializedName("country")
	val country: CountryModel? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)