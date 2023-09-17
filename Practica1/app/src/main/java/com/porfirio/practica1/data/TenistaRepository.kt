package com.porfirio.practica1.data

import com.porfirio.practica1.Model.TenistaEntity
import com.porfirio.practica1.Model.TenistaDao

class TenistaRepository(private val tenistaDao: TenistaDao) {

    suspend fun insertTenista(tenista: TenistaEntity){
        tenistaDao.insertTenista(tenista)
    }

    suspend fun insertTenista(nombre: String, ranking: String, puntos: String){
        tenistaDao.insertTenista(TenistaEntity(nombre = nombre, ranking = ranking, puntos = puntos))
    }

    suspend fun getAllTenistas(): List<TenistaEntity> = tenistaDao.getAllTenistas()

    suspend fun updateTenista(tenista: TenistaEntity){
        tenistaDao.updateTenista(tenista)
    }

    suspend fun deleteTenista(tenista: TenistaEntity){
        tenistaDao.deleteTenista(tenista)
    }

}