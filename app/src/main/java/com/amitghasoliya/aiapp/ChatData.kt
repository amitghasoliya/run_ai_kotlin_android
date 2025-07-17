package com.amitghasoliya.aiapp

data class ChatData(
    val message: String,
    val role: String
)

enum class ChatRoleEnum(val role:String){
    USER("user"),
    MODEL("model")
}
