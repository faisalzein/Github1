package com.example.githubuser.api

import com.example.githubuser.data.model.DetailUserResponse
import com.example.githubuser.data.model.User
import com.example.githubuser.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>


    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>


}