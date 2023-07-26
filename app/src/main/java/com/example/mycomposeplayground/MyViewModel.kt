package com.example.mycomposeplayground

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var myList: List<MyChild> = listOf(
        MyChild(1, R.drawable.ic_launcher_foreground, "One"),
        MyChild(2, R.drawable.ic_launcher_foreground, "Tow"),
        MyChild(3, R.drawable.ic_launcher_foreground, "Three"),
        MyChild(4, R.drawable.ic_launcher_foreground, "Four"),
        MyChild(5, R.drawable.ic_launcher_foreground, "Five"),
        MyChild(6, R.drawable.ic_launcher_foreground, "Six"),
        MyChild(7, R.drawable.ic_launcher_foreground, "Seven"),
        MyChild(8, R.drawable.ic_launcher_foreground, "Eight"),
    )


    private var _myListState = myList.toMutableStateList()
    val myListState: List<MyChild>
        get() = _myListState

    fun onItemSelected(item: MyChild) {
        val index = _myListState.indexOf(item)
        _myListState[index] = item.copy(isSelected = !item.isSelected)

    }
}

data class MyChild(val id: Int, val image: Int, var name: String, var isSelected: Boolean = false)