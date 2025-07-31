package com.example.royalcommissionforalulaapp_androidversion.utilities

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.FileType

class Utilities {
    companion object{
        fun getFileFormat(name: String): FileType?{
           val components = name.split(" - ")
            val format = components.lastOrNull()?.trim()?.lowercase()
            if (format.isNullOrEmpty())
                return null
            return when(format){
                "jpg" -> FileType.IMAGE
                "dwg" -> FileType.DWG

                else -> FileType.FILE
            }

        }
    }
}



