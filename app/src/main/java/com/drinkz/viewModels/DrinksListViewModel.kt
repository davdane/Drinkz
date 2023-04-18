package com.drinkz.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drinkz.model.DrinksRepository
import com.drinkz.model.response.DrinkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DrinksListViewModel (
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {
    private val repository: DrinksRepository = DrinksRepository()
    private val category: String = checkNotNull(savedStateHandle["category"])
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val drinks = getDrinksByCategory(category)
            scopeDrinks.value = drinks
        }
    }

    private val scopeDrinks = mutableStateOf(emptyList<DrinkResponse>())

    private suspend fun getDrinksByCategory(s: String): List<DrinkResponse>{
        return repository.getDrinksByCategory(s).drinks
    }

    fun getFilteredDrinks(searchedText: String): List<DrinkResponse> {
        val drinks = scopeDrinks.value
        return if (searchedText.isEmpty()) {
            drinks
        } else {
            val resultList = ArrayList<DrinkResponse>()
            for (drink in drinks){
                if (drink.name.lowercase(Locale.getDefault()).contains(searchedText.lowercase(Locale.getDefault()))){
                    resultList.add(drink)
                }
            }
            resultList
        }
    }

}