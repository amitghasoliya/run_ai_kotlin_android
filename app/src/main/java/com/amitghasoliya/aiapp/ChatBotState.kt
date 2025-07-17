package com.amitghasoliya.aiapp

sealed interface ChatBotState {
    data object Ideal:ChatBotState
    data object Loading:ChatBotState
    data class Success(val chatData: String) : ChatBotState
    data class Error(val chatError: String) : ChatBotState
}