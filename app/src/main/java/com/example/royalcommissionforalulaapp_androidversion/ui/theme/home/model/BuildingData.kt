package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.Page
import com.google.gson.annotations.SerializedName

data class BuildingData(
    @SerializedName("Id") val id: String?,
    @SerializedName("Name") val name: String?,
    @SerializedName("Pages") val pages: List<Page>?,
    @SerializedName("isOk") val isOk: Boolean,
    @SerializedName("isException") val isException: Boolean,
    @SerializedName("Message") val message: String?,
    @SerializedName("ExceptionMessage") val exceptionMessage: String?

)
