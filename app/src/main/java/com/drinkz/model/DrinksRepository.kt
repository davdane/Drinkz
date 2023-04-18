package com.drinkz.model

import com.drinkz.model.api.DrinksWebService
import com.drinkz.model.response.DrinkResponse
import com.drinkz.model.response.DrinksCategoriesResponse

class DrinksRepository(private val webService: DrinksWebService = DrinksWebService()) {

    private var cachedDrinks = listOf<DrinkResponse>()

    suspend fun getDrinksCategories(): DrinksCategoriesResponse{
        return webService.getDrinksCategories()
    }

    suspend fun getRandomDrink(): DrinksCategoriesResponse{
        val response = webService.getRandomDrink()
        cachedDrinks = response.drinks
        return response
    }

    suspend fun getDrinksByCategory(s: String): DrinksCategoriesResponse{
        val response = webService.getDrinksByCategory(s)
        cachedDrinks = response.drinks
        return response
    }

    suspend fun getRandomDrinkById(s: String): DrinksCategoriesResponse {
//        return cachedDrinks.firstOrNull {
//            it.id == s
//        }
        return webService.getDrinkById(s)
    }

//    companion object {
//        @Volatile
//        private var instance: DrinksRepository? = null
//
//        fun getInstance() = instance?: synchronized(this){
//            instance?: DrinksRepository().also { instance = it }
//        }
//    }

}