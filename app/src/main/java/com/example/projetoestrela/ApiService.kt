package com.example.projetoestrela

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("atendimento/buscarPerguntasDeSatisfacao/{nr_atendimento}")
    fun buscarPerguntas (
        @Path("nr_atendimento") nr_atendimento:Int
    ):Call<ResponseApi>
}