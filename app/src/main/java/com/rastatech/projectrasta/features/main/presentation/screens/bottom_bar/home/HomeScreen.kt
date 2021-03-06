package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import android.os.Build
import android.util.Log
import android.view.Display
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeViewModel
import com.rastatech.projectrasta.nav_graph.AUTH_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.HOME_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.MAIN_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.components.wish_list_page.WishList
import com.rastatech.projectrasta.ui.theme.AppColorPalette


@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    bottomBarNavController: NavController,
    viewModel:HomeViewModel = hiltViewModel(),
){
    /**
     * TODO(Category)
     * upvotes
     * downvotes
     * updated_at (most recent)
     * wish_name
     * rastagems_donated
     * rastagems_required
     */
    Column {
        TopAppBar(
            title = {
                Text(
                    text = BottomBarScreens.Home.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        )

        Categories(viewModel = viewModel)

        WishList(
            navController = bottomBarNavController,
            displayType = DisplayType.ReadOnly,
            wishEntities = viewModel.allWishes,
            updateList = {viewModel.updateList()
            }
        )
    }
}

@Composable
private fun Categories(viewModel: HomeViewModel) {
    val categories = listOf(
        Text(text = "Hello"),
        Text(text = "Hello"),
        Text(text = "Hello"),
        Text(text = "Hello")
    )

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(categories) { index, _ ->
            Box {
                categories[index]
            }
        }
    }
}
