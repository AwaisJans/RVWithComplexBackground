package com.jans.recycler.view.with.background.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.AdapterExample

class OneBGImageRVScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_bgimages_rvscreen)
        recyclerView = findViewById(R.id.rvList)


        val sampleList = mutableListOf<String>()

        repeat(500) {
            sampleList.add("Its My Destination $it")
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterExample(sampleList)

    }



}