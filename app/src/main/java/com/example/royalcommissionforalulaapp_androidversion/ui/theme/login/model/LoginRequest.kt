package com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    @SerializedName("Token") val token: String?,
    @SerializedName("Name") val name: String?,
    @SerializedName("Role")  val role: String?,
    @SerializedName("isOk") val isOk: String?,
    @SerializedName("isException") val isException: String? ,
    @SerializedName("Message") val message: String?,
    @SerializedName("ExceptionMessage") val exceptionMessage: String?

)
