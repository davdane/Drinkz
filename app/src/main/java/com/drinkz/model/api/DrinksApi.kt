package com.drinkz.model.api

import com.drinkz.model.response.DrinksCategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class DrinksWebService {
    private var api: DrinksApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(DrinksApi::class.java)
    }

    suspend fun getDrinksCategories(): DrinksCategoriesResponse{
        return api.getDrinksCategories()
    }

    suspend fun getRandomDrink(): DrinksCategoriesResponse{
        return api.getRandomDrink()
    }

    suspend fun getDrinksByCategory(s: String): DrinksCategoriesResponse {
        return api.getDrinksByCategory("filter.php?c=$s")
    }

    suspend fun getDrinkById(s: String?): DrinksCategoriesResponse {
        return api.getDrinkById("lookup.php?i=$s")
    }

    interface DrinksApi{
        @GET(value = "list.php?c=list")
        suspend fun getDrinksCategories(): DrinksCategoriesResponse

        @GET(value = "random.php")
        suspend fun getRandomDrink(): DrinksCategoriesResponse

        @GET
        suspend fun getDrinksByCategory(@Url endpoint: String): DrinksCategoriesResponse

        @GET
        suspend fun getDrinkById(@Url endpoint: String): DrinksCategoriesResponse
    }
}