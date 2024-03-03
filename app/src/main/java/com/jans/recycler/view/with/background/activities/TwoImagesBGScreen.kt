package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.SampleAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass.BGPositionModelClassItem
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getConstraintWithHeightWidthForTwoImageBG
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getScreenWidthAndHeight


class TwoImagesBGScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imgTopBG: ImageView
    private lateinit var imgBottomBG: ImageView

    private var list: BGPositionModelClassItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_bgimages_rvscreen)

//      getting screen width and height
        getScreenWidthAndHeight(this)

        recyclerView = findViewById(R.id.rvList)
        imgTopBG = findViewById(R.id.imageTop)
        imgBottomBG = findViewById(R.id.imageBtm)

        list = intent.getSerializableExtra("ARRAY_LIST_NEWS") as BGPositionModelClassItem?

        getConstraintWithHeightWidthForTwoImageBG(this,list,imgTopBG,imgBottomBG)

        // setting recycler view
        setAdapterRV()

    }

    private fun setAdapterRV() {
        val sampleList = mutableListOf<String>()
        repeat(500) { sampleList.add("My Favourite Challenge is to Accept Challenge $it") }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(sampleList)
    }


}