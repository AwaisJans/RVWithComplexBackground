package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.NewsAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getScreenWidthAndHeight
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase1
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase2
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase3
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase4
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.heightFromServerCase1
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.heightFromServerCase2
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.heightFromServerCase3
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.heightFromServerCase4
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternHeightCase1
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternHeightCase2
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternHeightCase3
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternHeightCase4
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternWidthCase1
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternWidthCase2
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternWidthCase3
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternWidthCase4
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.screenHeight
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.screenWidth
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.widthFromServerCase1
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.widthFromServerCase2
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.widthFromServerCase3
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.widthFromServerCase4
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
//        val sampleList = mutableListOf<String>()

//        repeat(500) {
//            sampleList.add("My Favourite Challenge is to Accept Challenge $it")
//        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(parseDashboardConfig())
    }

    private fun parseDashboardConfig(): BGPositionModelClass? {
        val inputStream = resources.openRawResource(R.raw.news_layout)
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson(reader, BGPositionModelClass::class.java)
    }

}