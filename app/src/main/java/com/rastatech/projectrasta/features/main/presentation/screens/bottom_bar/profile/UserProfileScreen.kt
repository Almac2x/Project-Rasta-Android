package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.domain.util.UserType
import com.rastatech.projectrasta.ui.components.CustomProfileImage
import com.rastatech.projectrasta.ui.components.CustomTextWithCount

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/07/2021
 */

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun UserProfileScreen(
    navController: NavController,
    userType: UserType,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Hard Coded Please adapt this to Make A Wish Screen
        Surface(
            modifier = Modifier.fillMaxWidth(),color = Color.Black.copy(alpha = 0f)
        ) {

                Surface(modifier = Modifier.fillMaxWidth(),color =  Color.Black.copy(alpha = 0f)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Profile",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                        )
                    }
                }
            if(userType == UserType.Other) {
                Surface(modifier = Modifier.fillMaxWidth(), color = Color.Black.copy(alpha = 0f)) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Back Button
                        IconButton(
                            onClick = { }
                        ) {
                            Icon(
                                modifier = Modifier.width(25.dp).height(25.dp),
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Text(
                            text = "Back",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                    }
                }
            }

        }

        // Please add padding para ma distinguish yung sa taas
        CustomProfileImage(
            painter = painterResource(id = R.drawable.profile),
            diameter = 150.dp,
            borderThickness = 5.dp
        )

        Box(modifier = Modifier.padding(10.dp)) {
            Column {
                Text(
                    text = "${viewModel.firstName} ${viewModel.lastName}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )

                Text(
                    text = viewModel.userName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 15.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomTextWithCount(title = "Active Wishes", count = 5)
            CustomTextWithCount(title = "Wishes Fulfilled", count = 5)
        }

        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            UserProfileTabScreen(viewModel = viewModel)
        }
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun Preview() {
    UserProfileScreen(navController = rememberNavController(), userType = UserType.Current)
}