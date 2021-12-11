package com.rastatech.projectrasta.features.main.data.remote.api

import retrofit2.Response
import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishRequestDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import retrofit2.http.*



interface WishApi {

    /**
     * Gets the HomeScreen Wishes to be Displayed
     * Used? = Yes
     * Tested? = Yes
     */

    @GET("/api/wishes")
    suspend fun  getHomeScreenWishes(@Header(ApiKey.Authorization.value) token: String
    ): Response<List<WishDTO>>

    //Gets the WishList of Donated Wishes by the User Specified
    @GET("/api/wishes/donated/{${ApiKey.UserID.value}}")
    suspend fun getWishListContributedByUser()

    //Gets the WishList of a User that is Fulfilled
    @GET("/api/wishes/granted/{${ApiKey.UserID.value}}")
    suspend fun getWishListFulfilled()


    /**
     *
     * Gets  the wish list of a specific user
     */

    @GET("/api/wishes/user/{${ApiKey.UserID.value}}")
    suspend fun getWishListOfAUser(@Header(ApiKey.Authorization.value) token: String,
                                   @Path(ApiKey.UserID.value) userID: Int
    ): Response<List<WishDTO>>

    //Gets a single Wish
    @GET("/api/wishes/{${ApiKey.WishID.value}}")
    suspend fun getWish(@Header(ApiKey.Authorization.value) token: String,
                        @Path(ApiKey.WishID.value)wishID : Int
    ): Response<WishDTO>

    //Post//////////////

    //Current User Creates a wish
    @POST("/api/wishes")
    suspend fun createAWish(@Header(ApiKey.Authorization.value) token: String,
                            @Body createWish: CreateWishRequestDTO
    ): Response<CreateWishRequestDTO>

    // current user likes a wish
    @POST("/api/wishes/{${ApiKey.WishID.value}}/like")
    suspend fun likeAWish(@Header(ApiKey.Authorization.value) token: String,
                          @Path(ApiKey.WishID.value)wishID : Int
    ): Response<Unit>

    // TODO: Did pa nilagay sa WishRespository
    //Get All Votes of a User
    @POST("/api/wishes/{${ApiKey.WishID.value}}/vote")
    suspend fun getAllVotes(@Header(ApiKey.Authorization.value) token: String,
                            @Path(ApiKey.WishID.value)wishID : Int
    ):Response<List<Map<String,Int>>> // Parang mali ito please check with backend

    //Donate to a Wish
    @POST("/api/donate/{${ApiKey.WishID.value}}") // Issue with backend ata
    suspend fun donateToAWish(@Header(ApiKey.Authorization.value) token: String,
                              @Path(ApiKey.WishID.value)wishID : Int,
                              @Body amount: Map<String,Int>
    )

    //Vote on a certain Wish
    //A Map must be pass on: Ex params = mapOf("vote_type" to "UpVote") or DownVote
    @POST("/api/wishes/{${ApiKey.WishID.value}}/vote")
    suspend fun voteAWish (@Header(ApiKey.Authorization.value) token: String,
                           @Path("${ApiKey.WishID.value}") wishID: Int,
                           @Body voteType: Map<String, String>
    )

    ///PUT///////////
    @PUT("/api/wishes/{wish_id}")
    suspend fun updateAWish(@Header(ApiKey.Authorization.value) token: String,
                            @Path("${ApiKey.WishID.value}") wishID: Int,
                            @Body createWish: CreateWishRequestDTO

    )

    //DELETE///////////////////
    @DELETE("/api/wishes/{${ApiKey.WishID.value}}")
    suspend fun deleteAWish(@Header(ApiKey.Authorization.value) token: String,
                            @Path(ApiKey.WishID.value)wishID : Int
    )

}