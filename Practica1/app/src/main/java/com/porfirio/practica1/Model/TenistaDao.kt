package com.porfirio.practica1.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.porfirio.practica1.Model.TenistaEntity
import com.porfirio.practica1.Utilerias.Constants.DATABASE_TENISTA_TABLE

@Dao
interface TenistaDao {

    //Create
    @Insert
    suspend fun insertTenista(
        tenista: TenistaEntity
    )

    //Crea una lista
    @Insert
    suspend fun insertTenista(
        tenistas: List<TenistaEntity>
    )

    //Read
    @Query("SELECT * FROM ${DATABASE_TENISTA_TABLE}")
    suspend fun getAllTenistas(): List<TenistaEntity>

    //Update
    @Update
    suspend fun updateTenista(tenista: TenistaEntity)

    //Delete
    @Delete
    suspend fun deleteTenista(tenista: TenistaEntity)


}