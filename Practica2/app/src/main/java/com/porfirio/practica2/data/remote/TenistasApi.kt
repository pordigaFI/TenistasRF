package com.porfirio.practica2.data.remote

import com.porfirio.practica2.data.remote.model.TenistaDetailDto
import com.porfirio.practica2.data.remote.model.TenistaDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TenistasApi {
    @GET("tenistas/tenistas_list")
    fun getTenistasApiary(): Call<List<TenistaDto>>

    //Tenistas/tenista_detail/21357
    @GET("tenistas/tenista_detail/{id}")
    fun getTenistaDetailApiary(
        @Path("id") id: String?
    ): Call <TenistaDetailDto>
}