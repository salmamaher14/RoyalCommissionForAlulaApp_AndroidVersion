package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("Id") val id: Int?,
    @SerializedName("Name") val name: String?,
    @SerializedName("Total") val total: Int?,
    @SerializedName("Available")val available: Int?,
    @SerializedName("Perc1") val perc1: Double?,
    @SerializedName("Perc2") val perc2: Double?,

)

data class ProgressData(
    @SerializedName("Steps") val steps: List<Step>,
    @SerializedName("isOk") val isOk: Boolean?,
    @SerializedName("isException") val isException: Boolean?,
    @SerializedName("Message") val message: String?,
    @SerializedName("ExceptionMessage") val exceptionMessage: String?
)

