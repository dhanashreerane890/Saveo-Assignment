package com.masai.saveo_assignment.model_classes


import com.google.gson.annotations.SerializedName

data class ScheduleModel(

	@field:SerializedName("days")
	val days: List<String?>? = null,

	@field:SerializedName("time")
	val time: String? = null
)