package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.NewsAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getDrawableResourceId
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getScreenWidthAndHeight
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.screenHeight
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.screenWidth
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.showToast
import java.io.InputStreamReader


class NewsScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_screen)


        val imgTest = findViewById<ImageView>(R.id.idTestImg)
        val imgTestDetailsTV = findViewById<TextView>(R.id.tvDetails)

        getScreenWidthAndHeight(this)

        imgTest.setImageResource(getDrawableResourceId(this,"img1"))


        val iosWidthIphone15 = 430
        val iosHeightIphone15 = 839

        val calculatedHeight:Float= iosHeightIphone15.toFloat() * (200.toFloat()/375.toFloat()) * (245.toFloat()/445.toFloat())
        val calculatedWidth:Float = iosWidthIphone15.toFloat() * (122.toFloat()/375.toFloat())

        val detailStr =
                "\n\nios ScreenWidth: $iosHeightIphone15\n" +
                "ios ScreenHeight: $iosWidthIphone15\n\n" +
                "Android Screen Height: $screenHeight\n" +
                "Android Screen Width: $screenWidth\n\n" +
                "Android Calculated Height: ${calculatedHeight.toInt()}\n" +
                "Android Calculated Width: ${calculatedWidth.toInt()}\n\n" +
                "ios CalculatedHeight: 246.358052\n\n" +
                "ios CalculatedWidth: 139.893333\n"

        imgTestDetailsTV.text = detailStr

        imgTest.layoutParams.height = calculatedHeight.toInt()
        imgTest.layoutParams.width = calculatedWidth.toInt()

        recyclerView = findViewById(R.id.rvList)
//        setAdapterRV()






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