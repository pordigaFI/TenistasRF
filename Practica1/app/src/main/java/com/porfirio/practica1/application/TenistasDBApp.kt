package com.porfirio.practica1.application

import android.app.Application
import androidx.room.Entity
import com.porfirio.practica1.Utilerias.Constants
import com.porfirio.practica1.data.TenistaRepository
import com.porfirio.practica1.db.TenistaDatabase


class TenistasDBApp(): Application() {
    private val database by lazy{
        TenistaDatabase.getDatabase(this@TenistasDBApp)
    }

    val repository by lazy {
        TenistaRepository(database.tenistaDao())
    }
}