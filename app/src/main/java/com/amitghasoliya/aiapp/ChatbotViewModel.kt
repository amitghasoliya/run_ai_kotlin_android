package com.amitghasoliya.aiapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatbotViewModel: ViewModel() {
    val chatBotState: MutableState<ChatBotState> = mutableStateOf(ChatBotState.Ideal)
    val list by lazy {
        mutableStateListOf<ChatData>()
    }
    private val genAI by lazy {
        GenerativeModel(
            modelName = "gemini-2.0-flash",
            apiKey = ApiKey
        )
    }

    fun sendMessage(message: String) = viewModelScope.launch {
        try {
            val chat = genAI.startChat()
            list.add(ChatData(message, ChatRoleEnum.USER.role))
            chatBotState.value = ChatBotState.Loading

            val modelResponse = chat.sendMessage(content(ChatRoleEnum.USER.role) {
                text(message)
            }).text

            modelResponse?.let {
                list.add(ChatData(it, ChatRoleEnum.MODEL.role))
                chatBotState.value = ChatBotState.Success(it)
            }
        } catch (e: Exception) {
            chatBotState.value = ChatBotState.Error(e.message ?: "Unknown error occurred")
        }
    }
}