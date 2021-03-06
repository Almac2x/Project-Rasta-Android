package com.rastatech.projectrasta.ui.components.wish_list_page

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.ui.components.CustomWishTile

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun WishList(navController : NavController, displayType: DisplayType,
             wishEntities : List<WishDTO>,
             viewModel: WishViewModel = hiltViewModel(),
             updateList: ()-> Unit
){

    val shimmerTile = listOf<Int>(1,2,3,5,4)
    LazyVerticalGrid(cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 10.dp, end = 10.dp,
            top = 10.dp, bottom = 10.dp
        ),
        content = {

            if (wishEntities.isEmpty()){

                items(items = shimmerTile) { item ->
                    ShimmerTile()
                }

            }else
             {
                items(items = wishEntities) { wish ->
                    CustomWishTile(
                        wishEntity = wish,
                        navController = navController,
                        viewModel = viewModel,
                        displayType = displayType
                    )
                }

            }


        }
    )
}

@ExperimentalMaterialApi
@Composable
fun WishTile(wishEntity : WishEntity? = null ){
    val heightWeight = 1f

    val imageSize = 150.dp
    val itemName = "WishEntity Name"
    val nameOfWisher = "Alejandro G. Blando III"

    Card(modifier = Modifier
        .height(250.dp)
        .padding(10.dp)
        .testTag("WishtTile"), elevation = 2.dp, backgroundColor = MaterialTheme.colors.primary,
        onClick = { Log.i("Clicked","Clicked o")},
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painterResource(id = wishEntity!!.image), contentDescription = "Image of ${wishEntity?.wish_name}",// image of WishEntity
                contentScale = ContentScale.Crop
                ,modifier = Modifier
                    .width(imageSize)
                    .height(imageSize)
                    .padding(bottom = 20.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(text = itemName, textAlign = TextAlign.Start, style = TextStyle(
                    )
                )
                Text(text = nameOfWisher)
            }

            // Add here Fill Bar

            // Add here Row for Heart and UPVOTE and Down Vote for WishEntity TIle
        }

    }

}