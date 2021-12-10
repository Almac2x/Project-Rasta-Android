package com.rastatech.projectrasta.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeViewModel
import com.rastatech.projectrasta.ui.components.WishList


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(

    viewModel:HomeViewModel = hiltViewModel(),
    navController: NavController
){

    var wishEntities: List<WishEntity> = listOf(
        WishEntity(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift),
        WishEntity(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift)
    )
    wishEntities = wishEntities+wishEntities
    wishEntities = wishEntities+wishEntities

    WishList(wishEntities = wishEntities)

}
