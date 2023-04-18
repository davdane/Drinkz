package com.drinkz.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drinkz.model.DrinksRepository
import com.drinkz.model.response.DrinkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinksViewModel (
    private val repository: DrinksRepository
): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val randomDrink = getRandomDrink()
            scopeRandomDrinks.value = randomDrink
            val drinksCategories = getDrinksCategories()
            scopeCategories.value = drinksCategories
        }
    }

    val scopeCategories = mutableStateOf(emptyList<DrinkResponse>())
    val scopeRandomDrinks = mutableStateOf(emptyList<DrinkResponse>())

    private suspend fun getDrinksCategories(): List<DrinkResponse> {
        return repository.getDrinksCategories().drinks
    }

    private suspend fun getRandomDrink(): List<DrinkResponse>{
        return repository.getRandomDrink().drinks
    }
}