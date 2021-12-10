package com.rastatech.projectrasta.features.add_update_wish

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.ui.components.CustomTextField
import com.rastatech.projectrasta.ui.theme.CardCornerRadius
import com.rastatech.projectrasta.utils.ValidateInput

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/07/2021
 */

/**
 * Add or Update Wish Screen
 *
 * @param id
 * @param title
 * @param wish_name
 * @param wishReason
 * @param wishImageUrl
 * @param gemsRequired
 */
@Composable
fun AddUpdateWishScreen(
    id: Int,
    title: String,
    wish_name: String = "",
    wishReason: String = "",
    wishImageUrl: String = "",
    gemsRequired: Int = 0
) {
    val wishName = remember { mutableStateOf(TextFieldValue(wish_name)) }
    val reason = remember { mutableStateOf(TextFieldValue(wishReason)) }
    val url = remember { mutableStateOf(TextFieldValue(wishImageUrl)) }
    val gems = remember {
        mutableStateOf(
            TextFieldValue(if (gemsRequired == 0)
                "" else gemsRequired.toString()
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(
                    onClick = {
                        // Return to previous screen
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                value = wishName.value,
                onValueChange = {wishName.value = it},
                modifier = Modifier.fillMaxWidth(),
                label = "Wish Name",
                labelFontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = reason.value,
                onValueChange = {reason.value = it},
                modifier = Modifier.fillMaxWidth(),
                label = "Reason for wishing",
                labelFontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = gems.value,
                onValueChange = {gems.value = if (ValidateInput.isNumber(it.text)) it else gems.value},
                modifier = Modifier.fillMaxWidth(),
                label = "How many gems do you need?",
                keyboardType = KeyboardType.Number,
                labelFontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = url.value,
                onValueChange = {url.value = if (ValidateInput.isNumber(it.text)) it else gems.value},
                modifier = Modifier.fillMaxWidth(),
                label = "Image Url",
                labelFontSize = 15.sp
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    shape = CardCornerRadius.small,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    }
                ) {
                    Text(text = "Post Wish")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AddUpdateWishScreen(
        id = 1,
        title = "Update Wish",
        wish_name = "PS5",
        wishReason = "I don't know",
        wishImageUrl = "None",
        gemsRequired = 200
    )
}