package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.SampleAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass.BGPositionModelClassItem
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getScreenWidthAndHeight
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.screenHeight
import com.jans.recycler.view.with.background.utils.TwoBGUtils.Companion.getConstraintWithHeightWidthForTwoImageBG


class TwoImagesBGScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imgTopBG: ImageView
    private lateinit var imgBottomBG: ImageView

    private lateinit var topImgContainer: RelativeLayout
    private lateinit var bottomImgContainer: RelativeLayout

    private var list: BGPositionModelClassItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_bgimages_rvscreen)

//      getting screen width and height
        getScreenWidthAndHeight(this)

        btnInit()

        // giving constraints to both containers
        topImgContainer.layoutParams.height = screenHeight / 2
        bottomImgContainer.layoutParams.height = screenHeight / 2

        list = intent.getSerializableExtra("ARRAY_LIST_NEWS") as BGPositionModelClassItem?

        getConstraintWithHeightWidthForTwoImageBG(list,imgTopBG,imgBottomBG)

        // setting recycler view
//        setAdapterRV()

    }

    private fun btnInit(){
        recyclerView = findViewById(R.id.rvList)
        imgTopBG = findViewById(R.id.imageTop)
        imgBottomBG = findViewById(R.id.imageBtm)

        topImgContainer = findViewById(R.id.topContainer)
        bottomImgContainer = findViewById(R.id.btmContainer)

    }

    private fun setAdapterRV() {
        val sampleList = mutableListOf<String>()
        repeat(500) { sampleList.add("My Favourite Challenge is to Accept Challenge $it") }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(sampleList)
    }


}