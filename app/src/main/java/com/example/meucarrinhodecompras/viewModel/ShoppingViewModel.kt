package com.example.meucarrinhodecompras.viewModel

import androidx.lifecycle.ViewModel
import com.example.meucarrinhodecompras.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoppingViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())

    val items: StateFlow<List<Item>> = _items.asStateFlow()

    fun addItem(item: Item) {
        _items.value = _items.value + item
    }

    fun toggleItem(item: Item) {
        _items.value = _items.value.map { currentItem ->
            if (currentItem == item) {
                currentItem.copy(checked = !currentItem.checked)
            } else {
                currentItem
            }
        }
    }
}