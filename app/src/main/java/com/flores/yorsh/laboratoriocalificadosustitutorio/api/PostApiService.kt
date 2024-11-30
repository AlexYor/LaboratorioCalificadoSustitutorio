package com.flores.yorsh.laboratoriocalificadosustitutorio.api

import com.flores.yorsh.laboratoriocalificadosustitutorio.model.Post
import retrofit2.http.GET

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}