package com.drinkz.screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.drinkz.AppBar
import com.drinkz.DrinkContent
import com.drinkz.DrinkPicture
import com.drinkz.model.response.DrinkResponse
import com.drinkz.ui.theme.LightBlue
import com.drinkz.ui.theme.LightOrange
import com.drinkz.ui.theme.Orange
import com.drinkz.viewModels.DrinkDetailsViewModel

@Composable
fun DrinkDetailsScreen(
    viewModel: DrinkDetailsViewModel,
    navController: NavHostController?
){
    val drinkList = viewModel.stateDrinkDetail.value

    Scaffold(topBar = { AppBar(
        if (drinkList.isEmpty()) "" else "${drinkList.first().name} Details",
        Icons.Default.ArrowBack, null
    ){
        navController?.navigateUp()
    } }){
        Surface(
            color = LightOrange,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
        ){
            DetailCard(drinkList, navController)
        }
    }
}

@Composable
fun ARDisplay(navController: NavHostController?) {
    Button(onClick = { navController?.navigate("drinkAR") }, modifier = Modifier.wrapContentSize(), colors = ButtonDefaults.buttonColors(Orange)) {
        Text(text = "Click here to see the cocktail in AR")
    }
}

@Composable
fun DetailCard(drink: List<DrinkResponse>, navController: NavHostController?) {

    if (drink.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            DrinkPicture(drink.first().thumbnail, 260.dp)
            DrinkContent(drink.first())
            DrinkDetails(drink.first())
            ARDisplay(navController) //{ dest -> findNavController(activity, R.id.arScreen).navigate(dest) }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressAnimated()
        }
    }
}

@Composable
fun CircularProgressAnimated(){
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()
    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(900))
    )
    CircularProgressIndicator(progress = progressAnimationValue)
}

@Composable
fun DrinkDetails(drink: DrinkResponse) {
    val fontWeight = FontWeight.SemiBold
    val styleTitle = MaterialTheme.typography.h6
    val styleContent = MaterialTheme.typography.body1
    val paddingBottom = Modifier.padding(bottom = 12.dp)
    val uriHandler = LocalUriHandler.current
    val ingr = getIngredients(drink)
    val annotatedString = buildAnnotatedString {
        val str: String = drink.video.toString()
        append(str)
        addStyle(
            style = SpanStyle(
                color = LightBlue,
                textDecoration = TextDecoration.Underline
            ), start = str.indexOf(str[0]), str.length
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Category:", style = styleTitle, fontWeight = fontWeight)
        Text(text = drink.category, style = styleContent, modifier = paddingBottom)
        Text(text = "Ingredients: ", style = styleTitle, fontWeight = fontWeight)
        Text(
            text = "${drink.ingr1}, ${drink.ingr2} $ingr",
            style = styleContent,
            modifier = paddingBottom
        )
        Text(text = "Instructions:", style = styleTitle, fontWeight = fontWeight)
        Text(text = drink.instr, style = styleContent, modifier = paddingBottom)

        if (drink.video!=null){
            Text(text = "Video:", style = styleTitle, fontWeight = fontWeight)
            ClickableText(text = annotatedString, style = styleContent, modifier = paddingBottom){
                uriHandler.openUri(drink.video)
            }
        }
    }
}

fun getIngredients(drink: DrinkResponse): String {
    var ingredients = ""
    if(drink.ingr3 != null){
        ingredients += ", " + drink.ingr3
    }
    if(drink.ingr4 != null){
        ingredients += ", " + drink.ingr4
    }
    if(drink.ingr5 != null){
        ingredients += ", " + drink.ingr5
    }
    if(drink.ingr6 != null){
        ingredients += ", " + drink.ingr6
    }
    if(drink.ingr7 != null){
        ingredients += ", " + drink.ingr7
    }
    if(drink.ingr8 != null){
        ingredients += ", " + drink.ingr8
    }
    if(drink.ingr9 != null){
        ingredients += ", " + drink.ingr9
    }
    if(drink.ingr10 != null){
        ingredients += ", " + drink.ingr10
    }
    if(drink.ingr11 != null){
        ingredients += ", " + drink.ingr11
    }
    if(drink.ingr12 != null){
        ingredients += ", " + drink.ingr12
    }
    if(drink.ingr13 != null){
        ingredients += ", " + drink.ingr13
    }
    if(drink.ingr14 != null){
        ingredients += ", " + drink.ingr14
    }
    if(drink.ingr15 != null){
        ingredients += ", " + drink.ingr15
    }
    return ingredients
}

