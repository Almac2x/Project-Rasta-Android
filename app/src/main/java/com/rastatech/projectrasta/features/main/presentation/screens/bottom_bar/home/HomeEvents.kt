package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

sealed class HomeEvents{

    data class GetWishes(val whatWishes : String = ""): HomeEvents() // may what wishes para kuhanin kung ano ididisplay sa home screen na mga wishes

}
