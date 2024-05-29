package com.example.task1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Activity3 : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainWindow()
        }
    }
    @Composable
    private fun MainWindow(){
        val focusManager = LocalFocusManager.current
        val label = remember{ mutableStateOf("") }
        val context = LocalContext.current
        var text = remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(singleLine = true,value = text.value, onValueChange = {
                text.value = it
            },modifier = Modifier
                .fillMaxWidth()
                .alpha(0.85f)
                .padding(vertical = 8.dp),
                label = { Text(text="Text") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()  })
            )
            Row {
                Button(onClick = {
                    val intent = Intent(this@Activity3, Activity2::class.java)
                    intent.putExtra("text",text.value)
                    startActivity(intent)
                },
                    modifier = Modifier.padding(vertical = 16.dp),
                )
                {
                    Text(text = "Назад")
                }
                Button(onClick = { val intent = Intent(this@Activity3, MainActivity::class.java)
                    intent.putExtra("text",text.value)
                    startActivity(intent) }) {
                    Text(text = "Вперед")
                }
            }
        }
    }
}