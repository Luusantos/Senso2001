package com.example.logonrmlocal.senso2001.api

import com.google.gson.annotations.SerializedName

data class Endereço(
            val cep: String,
            val logradouro: String,
            val complemento: String,
            val bairro: String,
            @SerializedName("localidade") val cidade: String,
            val uf: String

    )