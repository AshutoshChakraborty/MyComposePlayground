package com.example.mycomposeplayground


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondLayout() {
    var counter by remember { mutableStateOf(0) }
    var name by remember {
        mutableStateOf("")
    }
    Column {
        Text(text = "$counter", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Button(onClick = {
            counter++
        }) {
            Text(text = "Add one")
        }
        TextField(value = name, onValueChange = {it -> name = it})

    }
}

@Preview(showSystemUi = true)
@Composable
fun preview() {
    SecondLayout()
}