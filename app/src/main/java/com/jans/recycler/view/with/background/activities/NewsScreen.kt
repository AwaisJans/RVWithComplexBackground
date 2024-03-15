package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.NewsAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.showToast
import java.io.InputStreamReader


class NewsScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_bgimages_rvscreen)

        recyclerView = findViewById(R.id.rvList)
        setAdapterRV()

    }


    private fun setAdapterRV(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(parseDashboardConfig())
    }

    private fun parseDashboardConfig(): BGPositionModelClass? {
        val inputStream = resources.openRawResource(R.raw.news_layout)
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson(reader, BGPositionModelClass::class.java)
    }

}