package com.drinkz.model.response

import com.google.gson.annotations.SerializedName

data class DrinksCategoriesResponse(val drinks: List<DrinkResponse>)

data class DrinkResponse(
    @SerializedName("idDrink")
    val id: String,

    @SerializedName("strDrink")
    val name: String,

    @SerializedName("strVideo")
    val video: String?,

    @SerializedName("strCategory")
    val category: String,

    @SerializedName("strIBA")
    val iba: String?,

    @SerializedName("strAlcoholic")
    val alcoholic: String,

    @SerializedName("strGlass")
    val glass: String,

    @SerializedName("strInstructions")
    val instr: String,

    @SerializedName("strInstructionsIT")
    val instrIT: String?,

    @SerializedName("strDrinkThumb")
    val thumbnail: String,

    @SerializedName("strIngredient1")
    val ingr1: String,

    @SerializedName("strIngredient2")
    val ingr2: String,

    @SerializedName("strIngredient3")
    val ingr3: String?,

    @SerializedName("strIngredient4")
    val ingr4: String?,

    @SerializedName("strIngredient5")
    val ingr5: String?,

    @SerializedName("strIngredient6")
    val ingr6: String?,

    @SerializedName("strIngredient7")
    val ingr7: String?,

    @SerializedName("strIngredient8")
    val ingr8: String?,

    @SerializedName("strIngredient9")
    val ingr9: String?,

    @SerializedName("strIngredient10")
    val ingr10: String?,

    @SerializedName("strIngredient11")
    val ingr11: String?,

    @SerializedName("strIngredient12")
    val ingr12: String?,

    @SerializedName("strIngredient13")
    val ingr13: String?,

    @SerializedName("strIngredient14")
    val ingr14: String?,

    @SerializedName("strIngredient15")
    val ingr15: String?
)