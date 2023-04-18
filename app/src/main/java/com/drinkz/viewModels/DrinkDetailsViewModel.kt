package com.drinkz.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drinkz.model.DrinksRepository
import com.drinkz.model.response.DrinkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrinkDetailsViewModel (
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val repository: DrinksRepository = DrinksRepository()
    val stateDrinkDetail = mutableStateOf(emptyList<DrinkResponse>())

    init {
        val id: String = checkNotNull(savedStateHandle["id"])
        viewModelScope.launch(Dispatchers.IO){
            val drink = getDrink(id)
            stateDrinkDetail.value = drink

            withContext(Dispatchers.Main){}
        }
    }

    private suspend fun getDrink(s: String): List<DrinkResponse> {
        return repository.getRandomDrinkById(s).drinks
    }

}