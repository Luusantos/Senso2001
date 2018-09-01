package com.example.logonrmlocal.senso2001.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepApi {

    @GET("/ws/{cep}/json")
    fun pesquisar(@Path("cep") cep: String) : Call<Endereço>
}