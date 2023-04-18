package com.drinkz.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.drinkz.AppBar
import com.drinkz.DrinkCard
import com.drinkz.model.response.DrinkResponse
import com.drinkz.viewModels.DrinksViewModel
import java.net.URLEncoder

@Composable
fun DrinksCategoriesListScreen(viewModel: DrinksViewModel, navController: NavHostController?) {
    val drinksCategories = viewModel.scopeCategories.value
    val randomDrink = viewModel.scopeRandomDrinks.value

    Scaffold(
        topBar = { AppBar("Drinkz", Icons.Default.Home, null){} }){
        Surface(
            color = Color(234,182,118),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MainContent(drinksCategories, randomDrink, navController)
        }
    }
}

@Composable
fun MainContent(drinks: List<DrinkResponse>, randomDrink: List<DrinkResponse>, navController: NavHostController?){
    Card(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top),
        backgroundColor = Color(238,238,228)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.Black)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CategoriesList(drinks, navController)
            RandomDrink(randomDrink, navController)
        }
    }
}

@Composable
fun CategoriesList(drinks: List<DrinkResponse>, navController: NavHostController?){
    Text(
        text = "Choose a category before selecting your drink",
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
    LazyVerticalGrid(GridCells.Fixed(2), Modifier.height(400.dp)) {
        items(drinks) { drink ->
            Category(drink = drink) {
                val cat = URLEncoder.encode(drink.category)
                navController?.navigate("drinksList/$cat")
            }
        }
    }
}

@Composable
fun Category (drink: DrinkResponse, clickAction: ()-> Unit){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(50.dp)
            .border(BorderStroke(Dp.Hairline, Color.DarkGray), RoundedCornerShape(8.dp))
            .background(Color(234, 182, 118), RoundedCornerShape(8.dp))
            .clickable { clickAction.invoke() },
        backgroundColor = Color(234, 182, 118),
        contentColor = Color.Black
    ) {
        Box(contentAlignment = Alignment.Center){
            Text(
                textAlign = TextAlign.Center,
                text = drink.category,
                style = MaterialTheme.typography.h6
            )
        }

    }
}

@Composable
fun RandomDrink(randomDrink: List<DrinkResponse>, navController: NavHostController?) {
    Text(
        text = "Drink of the day",
        modifier = Modifier
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.Top),
        style = MaterialTheme.typography.h5,
        color = Color(226,135,67),
        fontWeight = FontWeight.Bold
    )
    if (randomDrink.isNotEmpty()){
        DrinkCard(drink = randomDrink[0]){
            navController?.navigate("drinkDetails/${randomDrink[0].id}")
        }
    }



}

