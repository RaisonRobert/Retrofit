package com.example.projetoestrela

data class ResponseApi(
    val dados: List<Dado>,
    val msg: String,
    val qtdPerguntas: Int,
    val status: Boolean
)