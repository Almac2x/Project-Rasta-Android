package com.rastatech.projectrasta.features.add_update_wish

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.AddUpdateWishEvents
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.AddUpdateWishViewModel
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util.WishProcess
import com.rastatech.projectrasta.ui.components.CustomTextField
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.CardCornerRadius
import com.rastatech.projectrasta.utils.Convert
import com.rastatech.projectrasta.utils.ValidateInput

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/07/2021
 */

/**
 * Add and Update Screen
 *
 * @param processType type of wish process
 * @param viewModel Screen view model
 */
@Composable
fun AddUpdateWishScreen(

    navController: NavController,
    processType : WishProcess,
    viewModel: AddUpdateWishViewModel = hiltViewModel()

) {
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.navigateUp  ){

        if (viewModel.navigateUp){
            navController.navigateUp()
        }
    }

        //Toast
        if(viewModel.showToast.value){

            // This only solves, Error Exception not 403 Errors
            Toast.makeText(context, viewModel.toastMessage.value, Toast.LENGTH_SHORT).show()

    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            elevation = 0.dp,
            backgroundColor = AppColorPalette.background
        ) {

                IconButton(
                    onClick = {
                        navController.navigateUp()
                        // Return to previous screen
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }


            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(if (processType == WishProcess.Add) "Add" else "Update")
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Wish Name TextField
            CustomTextField(
                value = viewModel.wishName.value,
                onValueChange = {viewModel.wishName.value = it},
                modifier = Modifier.fillMaxWidth(),
                label = "Wish Name",
                labelFontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = viewModel.reason.value,
                onValueChange = {viewModel.reason.value = it},
                modifier = Modifier.fillMaxWidth(),
                label = "Reason for wishing",
                labelFontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = viewModel.gemsRequired.value,
                onValueChange = {
                    viewModel.gemsRequired.value = if (ValidateInput.isNumber(it.text)) it else viewModel.gemsRequired.value },
                modifier = Modifier.fillMaxWidth(),
                label = "How many gems do you need?",
                keyboardType = KeyboardType.Number,
                labelFontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = viewModel.imageURL.value,
                onValueChange = {viewModel.imageURL.value = it},
                modifier = Modifier.fillMaxWidth(),
                label = "Image Url",
                labelFontSize = 15.sp
            )

            Box(
                modifier = Modifier.fillMaxSize(0.5f),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    shape = CardCornerRadius.small,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        if(processType == WishProcess.Update){

                             viewModel.onEvent(AddUpdateWishEvents.UpdateWish)


                        }else{
                            viewModel.onEvent(AddUpdateWishEvents.AddWish)
                        }

                    }
                ) {
                    if(processType == WishProcess.Update){
                        Text(text = "Update Wish")
                    }else{
                        Text(text = "Post Wish")
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AddUpdateWishScreen(processType = WishProcess.Add, navController = rememberNavController())
}