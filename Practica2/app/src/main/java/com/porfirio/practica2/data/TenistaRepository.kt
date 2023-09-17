package com.porfirio.practica2.data

import com.porfirio.practica2.data.remote.TenistasApi
import com.porfirio.practica2.data.remote.model.TenistaDetailDto
import com.porfirio.practica2.data.remote.model.TenistaDto
import retrofit2.Call
import retrofit2.Retrofit

class TenistaRepository (private val retrofit: Retrofit) {
    private val tenistasApi: TenistasApi = retrofit.create(TenistasApi :: class.java)

    fun getTenistasApiary(): Call<List<TenistaDto>> =
        tenistasApi.getTenistasApiary()

    fun getTenistaDetailApiary(id: String?): Call<TenistaDetailDto> =
        tenistasApi.getTenistaDetailApiary(id)
}