package com.rastatech.projectrasta.features.login.domain.entities

data class UserEntity(

    val id : Int,
    val user_name : String,
    val first_name : String,
    val last_name: String,
    val email : String,
    val rasta_gem_balance: Int,
    val avatar: String,
)