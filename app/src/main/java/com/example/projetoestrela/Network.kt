package com.example.projetoestrela


import android.util.Log

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Network {
    fun services (api: AppRetrofit, nr_atendimento: Int, callBackRetorno:(reponse:ResponseApi)->Unit){
        val call = api.apiService().buscarPerguntas(nr_atendimento)

        call.enqueue(object : Callback<ResponseApi>{
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                callBackRetorno.invoke(response.body()!!)

            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.i("nao funciona",t.message.toString())
            }

        })

    }

}