package com.example.newnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val per = Permission()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        getData()
    }

    private fun getData() {
        val urlString = "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=${per.getKey()}"
        val request = Request.Builder().url(urlString).build()

        // Third party help: Fetch json object using OKHttpClient
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("JSON body: $body")

                val gson = GsonBuilder().create()
                val model = gson.fromJson(body, Model::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(model)
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                println("Something went wrong fetching json object: ${e.printStackTrace()}")
            }
        })

    }
}

