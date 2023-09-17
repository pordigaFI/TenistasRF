package com.porfirio.practica2.data.remote.model

import com.google.gson.annotations.SerializedName

data class TenistaDetailDto(@SerializedName("imagen")
    var imagen: String? = null,
    @SerializedName("Pais")
    var pais: String? = null,
    @SerializedName("Estatura")  //Asi esta declarada mi variable
    var estatura: Float? = null,   //Asi la manejo en mi programación
    @SerializedName("Ranking")
    var ranking: String? = null,
    @SerializedName("Puntos")
    var puntos: String? = null,
    @SerializedName("Titulos")
    var titulos: String? = null,
    @SerializedName("Ingresos_totales")
    var ingresos: String? = null
)
