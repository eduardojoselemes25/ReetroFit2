package com.reetrofit2

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.retrofit2.dominio.RetrofitService
import com.retrofit2.dominio.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.List as List1


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscaDados()
    }

    private fun buscaDados() {
        val serviceRetrofit = RetrofitService()
        serviceRetrofit.api?.obterUsuarios()?.enqueue(object : Callback<List1<Usuario>> {
            override fun onResponse(
                call: Call<List1<Usuario>?>?,
                response: Response<List1<Usuario>?>?
            ) {
                val lista = response?.body();
                val linearLayout = findViewById<LinearLayout>(R.id.rootLayout)
                linearLayout.gravity = Gravity.CENTER_VERTICAL;
                if (lista != null) {
                    for (user in lista) {
                        val linear2 = LinearLayout(this@MainActivity)
                        linear2.setPadding(9,9,9,9)
                        linear2.setOrientation(LinearLayout.VERTICAL)
                        val tV1 = TextView(this@MainActivity)
                        val tV2 = TextView(this@MainActivity)
                        tV1.textSize = 20f
                        tV1.text = "Nome: " + user.name.toString()
                        tV1.setGravity(Gravity.CENTER);
                        tV2.textSize = 20f
                        tV2.text = "Email:" + user.email.toString()
                        tV2.setGravity(Gravity.CENTER);
                        linear2.addView(tV1)
                        linear2.addView(tV2)
                        linearLayout.addView(linear2)
                    }
                }
            }

            override fun onFailure(call: Call<List1<Usuario>?>?, t: Throwable?) {
                Log.e("Erro", "************** erro **********\n" + t?.message.toString())
            }
        })
    }
}