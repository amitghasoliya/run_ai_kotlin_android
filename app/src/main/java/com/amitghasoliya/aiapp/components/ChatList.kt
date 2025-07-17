package com.amitghasoliya.aiapp.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amitghasoliya.aiapp.ChatData
import com.amitghasoliya.aiapp.ChatRoleEnum
import com.amitghasoliya.aiapp.ui.theme.LightGrey
import com.amitghasoliya.aiapp.ui.theme.LightGreyF
import com.amitghasoliya.aiapp.ui.theme.PrimaryRed

@Composable
fun ChatList(list: MutableList<ChatData>) {
    val context = LocalContext.current
    val chatListState = rememberLazyListState()
    LaunchedEffect(key1 = list.size) {
        if (list.size>1){
            chatListState.scrollToItem(list.size - 2)
        }else
            chatListState.scrollToItem(list.size - 1)
    }

    LazyColumn(modifier = Modifier.fillMaxSize(),
        state = chatListState) {
        items(list){
            if (it.role == ChatRoleEnum.USER.role){
                Row(modifier = Modifier
                    .background(Color.Gray)
                    .padding(4.dp, 0.dp, 0.dp, 0.dp)
                ){
                    Text(text = it.message,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(LightGreyF)
                            .padding(12.dp),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }else{
                Row(modifier = Modifier
                    .background(PrimaryRed)
                    .padding(4.dp, 0.dp, 0.dp, 0.dp)
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(LightGrey)
                    ){
                        Text(text = "Copy",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(0.dp, 8.dp, 15.dp, 0.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                                .background(LightGrey)
                                .clickable {
                                    val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                                    val clipData: ClipData = ClipData.newPlainText("text", it.message)
                                    clipboardManager.setPrimaryClip(clipData)
                                    Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
                                }
                                .padding(8.dp, 0.dp),
                            color = Color.DarkGray,
                            fontSize = 11.sp
                        )

                        Text(text = it.message,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(LightGrey)
                                .padding(12.dp, 8.dp),
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }

                }
            }
        }
    }
}