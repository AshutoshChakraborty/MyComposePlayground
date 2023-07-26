package com.example.mycomposeplayground

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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


    private var _myListState:MutableStateFlow<List<MyChild>> = MutableStateFlow(myList)
    val myListState: StateFlow<List<MyChild>>
        get() = _myListState

    fun onItemSelected(item: MyChild) {
        viewModelScope.launch {
            val index = _myListState.value.indexOf(item)
            if (index != -1) {
                val updatedItem = item.copy(isSelected = !item.isSelected)
                _myListState.update {
                    it.toMutableList().apply {
                        set(index,updatedItem)
                    }
                }
            }

        }

    }
}

data class MyChild(val id: Int, val image: Int, var name: String, var isSelected: Boolean = false)

internal fun <E> List<E>.addOrRemove(element: E): List<E> {
    return this.toMutableList().apply {
        if (!add(element)) {
            remove(element)
        }
    }.toList()
}
