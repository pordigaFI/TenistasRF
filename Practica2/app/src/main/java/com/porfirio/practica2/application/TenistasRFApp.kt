package com.porfirio.practica2.application

import android.app.Application
import com.porfirio.practica2.data.TenistaRepository
import com.porfirio.practica2.data.remote.model.RetrofitHelper

class TenistasRFApp: Application() {
    private val retrofit by lazy{
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy{
        TenistaRepository(retrofit)
    }
}