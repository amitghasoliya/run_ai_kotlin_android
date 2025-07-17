package com.amitghasoliya.aiapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amitghasoliya.aiapp.R
import com.amitghasoliya.aiapp.ui.theme.LightGrey

@Composable
fun ChatHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(10.dp)
    ){
        Image(painter = painterResource(id = R.drawable.ai_name), contentDescription = null,
            modifier = Modifier
                .background(color = LightGrey, shape = RoundedCornerShape(10.dp))
                .align(Alignment.Center)
                .height(32.dp)
                .padding(12.dp, 6.dp)
            )
    }

}