package com.amitghasoliya.aiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.amitghasoliya.aiapp.ui.theme.AIAppTheme
import com.amitghasoliya.aiapp.ui.theme.LightGrey
import com.amitghasoliya.aiapp.ui.theme.LightGreyF

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightGreyF
                ) {
                    ChatBot()
                }
            }
        }
    }
}
