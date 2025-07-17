package com.amitghasoliya.aiapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.amitghasoliya.aiapp.components.ChatFooter
import com.amitghasoliya.aiapp.components.ChatHeader
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amitghasoliya.aiapp.components.ChatList
import com.amitghasoliya.aiapp.ui.theme.PrimaryRed
import com.ehsanmsz.mszprogressindicator.progressindicator.SquareSpinProgressIndicator

@Composable
fun ChatBot(
    viewModel: ChatbotViewModel = viewModel()
) {
    val chatBotState = viewModel.chatBotState.value
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ChatHeader()

        Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when (chatBotState) {
                ChatBotState.Loading -> {
                    ChatList(list = viewModel.list)
                    SquareSpinProgressIndicator(modifier = Modifier.background(Color.Transparent), color = PrimaryRed)
                }
                is ChatBotState.Error -> {
                    Toast.makeText(context, chatBotState.chatError, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    if (viewModel.list.isEmpty()) {
                        Text(text = "Ask Something!", color = Color.Gray)
                    } else {
                        ChatList(list = viewModel.list)
                    }
                }
            }
        }

        ChatFooter {
            if (it.isNotEmpty()){
                viewModel.sendMessage(it)
            }
        }
    }
}