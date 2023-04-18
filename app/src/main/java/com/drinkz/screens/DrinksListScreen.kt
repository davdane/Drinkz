package com.drinkz.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.drinkz.AppBar
import com.drinkz.DrinkCard
import com.drinkz.model.response.DrinkResponse
import com.drinkz.viewModels.DrinksListViewModel


@Composable
fun DrinksListScreen(viewModel: DrinksListViewModel, navController: NavHostController?){
    //val drinks = viewModel.scopeDrinks.value
    var filteredDrinks: List<DrinkResponse>
    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Scaffold(topBar = { AppBar("Drinks List", Icons.Default.ArrowBack, textState){
        navController?.navigateUp()
    } }) {
        Surface(
            color = Color(234,182,118),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn {
                val searchedText = textState.value.text
                filteredDrinks = viewModel.getFilteredDrinks(searchedText)

                items(filteredDrinks){drink ->
                    DrinkCard(drink){
                        navController?.navigate("drinkDetails/${drink.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        placeholder = { Text(text = "Search...", fontSize = 16.sp, textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(textAlign = TextAlign.Right, fontSize = 16.sp),
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            } else {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(234,182,118),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("Apple")) }
    SearchView(textState)
}