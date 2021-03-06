package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.features.main.domain.util.UserType
import com.rastatech.projectrasta.ui.components.wish_list_page.WishList

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WishListScreen(wishList: List<WishDTO>, navController: NavController, updateList: ()-> Unit, userType: UserType) {
    Box(modifier = Modifier.fillMaxSize()) {
        WishList(
            navController = navController,
            displayType = if(userType == UserType.Current) DisplayType.Editable else DisplayType.ReadOnly,
            wishEntities = wishList,
            updateList = updateList
        )
    }
}