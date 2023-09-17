package com.porfirio.practica1.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.porfirio.practica1.Utilerias.Constants

@Entity(tableName = Constants.DATABASE_TENISTA_TABLE)
data class TenistaEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tenista_id")
    val id: Long = 0,

    @ColumnInfo(name = "tenista_nombre")
    var nombre: String,

    @ColumnInfo(name = "tenista_ranking")
    var ranking: String,

    @ColumnInfo(name = "tenista_puntos")
    var puntos: String

)