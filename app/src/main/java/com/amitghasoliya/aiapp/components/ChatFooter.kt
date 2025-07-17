package com.amitghasoliya.aiapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.amitghasoliya.aiapp.KeyboardUtils
import com.amitghasoliya.aiapp.ui.theme.LightGrey
import com.amitghasoliya.aiapp.ui.theme.PrimaryRed
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatFooter(
    onClick: (text:String) -> Unit
) {
    val keyboardUtils = KeyboardUtils()
    var inputText by remember { mutableStateOf("") }
    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    val isKeyboardOpen by keyboardUtils.keyboardAsState()

    LaunchedEffect(focusRequester) {
        if (showKeyboard.value) {
            focusRequester.requestFocus()
            delay(50)
            keyboard?.show()
        }
    }

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(4.dp)
    ) {
        BasicTextField(
            value = inputText,
            onValueChange = {inputText = it},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .background(color = LightGrey, shape = RoundedCornerShape(16.dp))
                .height(48.dp)
                .weight(1f)
                .focusRequester(focusRequester),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = inputText,
                    innerTextField = {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                        }
                    },
                    enabled = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        cursorColor = PrimaryRed,
                        unfocusedContainerColor = Color.Transparent,
                        focusedPlaceholderColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray),
                    singleLine = true,
                    interactionSource = interactionSource,
                    visualTransformation = VisualTransformation.None,
                    contentPadding = TextFieldDefaults.contentPaddingWithLabel(
                        top = 0.dp,
                        bottom = 0.dp
                    ),
                    placeholder = {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Type to ask...")
                        }
                    }
                )
            }
        )

        if (inputText.isEmpty() && isKeyboardOpen){
            IconButton(
                onClick = {
                    keyboard?.hide()
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }else{
            IconButton(
                onClick = {
                    if (inputText.isNotEmpty()){
                        onClick(inputText)
                        inputText = ""
                    }else{
                        keyboard?.show()
                    }

                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowUp,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}