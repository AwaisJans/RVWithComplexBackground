package com.jans.recycler.view.with.background.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.SampleAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass
import com.jans.recycler.view.with.background.utils.AppConfig
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getConstraintWithHeightWidthForOneImageBG

class OneBGImageRVScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageBG: ImageView

    private var list: BGPositionModelClass.BGPositionModelClassItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_bgimages_rvscreen)

//      getting screen width and height
        AppConfig.getScreenWidthAndHeight(this)

        recyclerView = findViewById(R.id.rvList)
        imageBG = findViewById(R.id.imgOneBG)

        list = intent.getSerializableExtra("ARRAY_LIST_NEWS") as BGPositionModelClass.BGPositionModelClassItem?

        // getting all constraints
        getConstraintWithHeightWidthForOneImageBG(this,list,imageBG)

        setAdapterRV()

    }

    private fun setAdapterRV() {
        val sampleList = mutableListOf<String>()
        repeat(500) { sampleList.add("Its My Destination $it") }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(sampleList)
    }

}