package com.example.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val toolBar = findViewById(R.Andr)

        recyclerView_main.layoutManager = LinearLayoutManager(this)


        getData()
    }

    fun getData() {
        val urlString = "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=066d82458ed84eeeac28a86095ec88b9"
        val request = Request.Builder().url(urlString).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val model = gson.fromJson(body, Model::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(model)
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                println("STILL NOT WORKING")
            }
        })

    }
}

