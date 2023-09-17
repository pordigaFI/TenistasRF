package com.porfirio.practica2.data.remote.model

import com.google.gson.annotations.SerializedName

data class TenistaDto(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("Nombre")
    var nombre: String? = null,
    @SerializedName("Apellido")
    var apellido: String? = null,
    @SerializedName("Edad")
    var edad: Int? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null
)
