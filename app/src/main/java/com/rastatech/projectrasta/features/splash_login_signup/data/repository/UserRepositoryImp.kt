package com.rastatech.projectrasta.features.splash_login_signup.data.repository

import android.util.Log
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.splash_login_signup.data.data_source.UserDao
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.TokenDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

private const val TAG = "UserRepositoryImpl"

class UserRepositoryImpl (
    private  val dao : UserDao,
    private val retrofit : RetrofitInstance /**TODO Pass An API not an Insance of Retrofit */
        ) : UserRepository {


    override fun getUsers(): Flow<List<UserEntity>> {
        return dao.getUsers()
    }

    override suspend fun getUserById(id: Int): UserEntity? {

        return dao.getUserById(id = id)
    }

    override suspend fun insertUser(user: UserEntity) {

        dao.insertUser(user = user)
    }

    override suspend fun deleteUser(user: UserEntity) {

        dao.deleteUser(user = user)
    }

    override suspend fun getLoginTokenApiRequest(username: String, password: String): Response<TokenDTO> {

        Log.i(TAG, "Function: getLoginTokenApiRequest " +
                "\nUsername Sent: $username" +
                "\nPassword Sent: $password ")

        val response  =  retrofit.loginApi.getToken(
                username = username,
                password = password
        )

        //bakit ayaw gumana
        if(response.isSuccessful){
            Log.i(TAG, "Request Successful! Received = \n " +
                    "Token: ${response.body()?.access_token} \n Refresh Token: ${response.body()?.refresh_token}")
        }else{
            Log.i(TAG, "Request Failed! Error Code = ${response.code()} ")

        }

        return response
    }
    override suspend fun createUserApiRequest(user: UserRequestDTO) {

        Log.i(TAG, "Function: createUserApiRequest " +
                "\nusername Sent: ${user.username}" +
                "\nemail Sent: ${user.email}" +
                "\npassword Sent: ${user.password} " +
                "\nfirst_name Sent: ${user.first_name} " +
                "\nlast_name Sent: ${user.last_name} " +
                "\nphone_number Sent: ${user.phone_number} ")

         val response = retrofit.signUpApi.createUser(user = user)

        if(response.isSuccessful){
            Log.i(TAG, "Request Successful! Received = \n " +
                    "")
        }else{

           Log.i(TAG, "Request Failed! Error Code = ${response.code()} ")
        }
    }
}