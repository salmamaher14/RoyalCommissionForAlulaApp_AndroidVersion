package com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model

import com.google.gson.annotations.SerializedName
import java.util.UUID


data class Page(
    var id: String = UUID.randomUUID().toString(),
    @SerializedName("Name") val name: String?,
    @SerializedName("Files") val  files: List<FileData>?
)

data class FileData(
    var id: String = UUID.randomUUID().toString(),
    @SerializedName("Name") val name: String?,
    @SerializedName("Url") val url: String?
)

enum class FileType(val rawValue: String){
    IMAGE("Image"),
    FILE("Description"),
    DWG("dwg")

}




