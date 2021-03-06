package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(

    state: SavedStateHandle,
    private val useCases: WishUseCases

):ViewModel() {


    private val userToken = state.get<String>(NavigationKey.AccessToken.value) ?: ""

    private val _allWishes   = mutableStateOf(emptyList<WishDTO>())
    val allWishes : List<WishDTO>
        get() = _allWishes.value

    val retrofitToken = "Bearer " + prefs?.accessToken

    init {

        Log.i(TAG, "Initializing")
        Log.i(TAG, "Remember Me: ${prefs?.rememberMe}")

             updateList()
        }

         fun updateList(){

             Log.i(TAG," List Updating")
            viewModelScope.launch (Dispatchers.Main){
                Log.i(TAG, "UserToken: $userToken")
                val nani = useCases.getHomeScreenWishes(token = userToken)

                val requestAllWishes = async{RetrofitInstance.wishApi.getHomeScreenWishes(token = retrofitToken)}
                requestAllWishes.join()

                _allWishes.value = requestAllWishes.await().body()!!

            }
             Log.i(TAG," List Updated")
        }



    fun onEvent(event : HomeEvents){

        when(event){

            is HomeEvents.GetWishes ->{

            }

        }

    }



}