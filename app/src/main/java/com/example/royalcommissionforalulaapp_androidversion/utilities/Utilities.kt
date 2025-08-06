package com.example.royalcommissionforalulaapp_androidversion.utilities

import android.content.Context
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.FileType
import java.io.File
import java.net.URL

class Utilities {
    companion object{
        fun getFileFormat(name: String): FileType?{
           val components = name.split(" - ")
            val format = components.lastOrNull()?.trim()?.lowercase()
            if (format.isNullOrEmpty())
                return null
            return when(format){
                "jpg" -> FileType.IMAGE
                "dwg" -> FileType.IMAGE

                else -> FileType.FILE
            }

        }

        fun downloadImageToCache(context: Context, imageUrl: String): File {
            val fileName = imageUrl.hashCode().toString() + ".jpg"
            val file = File(context.cacheDir, fileName)

            if (file.exists()) {
                return file
            }

            val url = URL(imageUrl)
            val connection = url.openConnection()
            connection.connect()

            val inputStream = connection.getInputStream()
            file.outputStream().use { output ->
                inputStream.copyTo(output)
            }

            return file
        }
    }

}



