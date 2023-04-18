package com.drinkz.di

import com.drinkz.model.DrinksRepository
import com.drinkz.viewModels.DrinkARViewModel
import com.drinkz.viewModels.DrinkDetailsViewModel
import com.drinkz.viewModels.DrinksListViewModel
import com.drinkz.viewModels.DrinksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { DrinksRepository() }
    viewModel { DrinksViewModel(get()) }
    viewModel { DrinksListViewModel(get()) }
    viewModel { DrinkDetailsViewModel(get()) }
    viewModel { DrinkARViewModel(get()) }
}