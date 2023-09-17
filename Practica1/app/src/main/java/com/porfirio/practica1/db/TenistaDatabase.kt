package com.porfirio.practica1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.porfirio.practica1.Model.TenistaDao
import com.porfirio.practica1.Model.TenistaEntity
import com.porfirio.practica1.Utilerias.Constants

@Database(
    entities = [TenistaEntity :: class],
    version = 1,
    exportSchema = true
)
abstract class TenistaDatabase: RoomDatabase(){
    //Aqui va el Dao
    abstract fun tenistaDao(): TenistaDao


    companion object{
        @Volatile   //lo que se escriba en este campo, será inmediatamente visible a otros hilos
        private var INSTANCE:TenistaDatabase? = null

            fun getDatabase(context: Context): TenistaDatabase{
                return INSTANCE?: synchronized(this){
                    /* Si la base de datos no es nula,entonces se regresa.
                    si es nula, entonces se crea la base de datos (patrón singleton)*/
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TenistaDatabase :: class.java,
                        Constants.DATABASE_NAME
                    ).fallbackToDestructiveMigration()  //permite a Room recrear las tablas de la BD si las migraciones no se encuentran
                        .build()

                    INSTANCE = instance

                    instance
                }
            }

        }
    }


