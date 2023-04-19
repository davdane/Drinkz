package com.drinkz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.drinkz.model.response.DrinkResponse
import com.drinkz.screens.DrinkARScreen
import com.drinkz.screens.DrinkDetailsScreen
import com.drinkz.screens.DrinksCategoriesListScreen
import com.drinkz.screens.DrinksListScreen
import com.drinkz.screens.SearchView
import com.drinkz.ui.theme.DrinkzTheme
import com.drinkz.ui.theme.Orange
import com.drinkz.viewModels.DrinkDetailsViewModel
import com.drinkz.viewModels.DrinksListViewModel
import com.drinkz.viewModels.DrinksViewModel
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DrinkzTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val viewModel: DrinksViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = "drinkz"){
        composable("drinkz"){
            DrinksCategoriesListScreen(viewModel, navController)
        }
        composable("drinksList/{category}",
            listOf(navArgument("category"){
                type = NavType.StringType
            })){
                val listViewModel: DrinksListViewModel = koinViewModel()
                DrinksListScreen(listViewModel, navController)
        }
        composable("drinkDetails/{id}",
            listOf(navArgument("id"){
                type = NavType.StringType
            })){
                val detailsViewModel: DrinkDetailsViewModel = koinViewModel()
                DrinkDetailsScreen(detailsViewModel, navController)
        }
        composable("drinkAR"){
//            val arViewModel: DrinkARViewModel = koinViewModel()
            DrinkARScreen()
        }
    }
}

@Composable
fun AppBar(title: String, icon: ImageVector, textState: MutableState<TextFieldValue>?, iconClick: ()->Unit){

    TopAppBar(
        modifier = Modifier.height(68.dp),
        backgroundColor = Orange,
        navigationIcon = {
            Icon(icon,
                contentDescription = "",
                Modifier
                    .size(60.dp)
                    .padding(horizontal = 12.dp)
                    .clickable { iconClick.invoke() },
                Color.Black
            ) },
        title = {
            Text(text = title, fontSize = 26.sp, color = Color.Black)
            if (textState != null) {
                SearchView(state = textState)
            }
        }
    )
}

@Composable
fun DrinkCard(drink: DrinkResponse, clickAction: () -> Unit){
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Top)
        .clickable { clickAction.invoke() },
        elevation = 8.dp,
        backgroundColor = Color.LightGray) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            DrinkPicture(drink.thumbnail, 72.dp)
            DrinkContent(drink)
        }
    }
}

@Composable
fun DrinkPicture(thumbnail: String, imageSize: Dp) {
    Card(shape = RoundedCornerShape(2.dp),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp) {
        AsyncImage(model = thumbnail,
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
fun DrinkContent(drink: DrinkResponse){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = drink.name, style = MaterialTheme.typography.h5, overflow = TextOverflow.Ellipsis, maxLines = 1, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Preview
@Composable
fun AppBarPreview(){
    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    AppBar(title = "Home", icon = Icons.Default.Home, textState) { }
}

