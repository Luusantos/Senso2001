package com.example.logonrmlocal.senso2001

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.logonrmlocal.senso2001.api.CepApi
import com.example.logonrmlocal.senso2001.api.Endereço
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import kotlinx.android.synthetic.main.activity_cadastro.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        Stetho.initializeWithDefaults(this);

        btPesquisar.setOnClickListener { pesquisarCep() }

    }

    private fun pesquisarCep() {

        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://viacep.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        val service = retrofit.create(CepApi::class.java)
        service.pesquisar("08280370")
                .enqueue(object: Callback<Endereço>{
                    override fun onFailure(call: Call<Endereço>?, t: Throwable?) {
                        exibeErro(t)
                    }

                    override fun onResponse(call: Call<Endereço>?, response: Response<Endereço>?) {
                       preencheEndereco(response?.body())
                    }
                })
    }

    private fun preencheEndereco(endereço: Endereço?) {
            Toast.makeText(this, endereço?.logradouro,
                    Toast.LENGTH_LONG).show()
    }

    private fun exibeErro(t: Throwable?) {
        Toast.makeText(this, t?.message,
                Toast.LENGTH_LONG).show()
    }
}
