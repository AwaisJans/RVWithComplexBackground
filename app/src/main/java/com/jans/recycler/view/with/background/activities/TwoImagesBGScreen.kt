package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.SampleAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass.BGPositionModelClassItem
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getDrawableResourceId
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getScreenWidthAndHeight
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getValueFromExpression
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.screenHeight


class TwoImagesBGScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imgTopBG: ImageView
    private lateinit var imgBottomBG: ImageView

    private lateinit var topImgContainer: RelativeLayout
    private lateinit var bottomImgContainer: RelativeLayout

    private lateinit var list: BGPositionModelClassItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_bgimages_rvscreen)

//      getting screen width and height
        getScreenWidthAndHeight(this)

        btnInit()

        // giving constraints to both containers
        topImgContainer.layoutParams.height = screenHeight / 2
        bottomImgContainer.layoutParams.height = screenHeight / 2

        list = (intent.getSerializableExtra("ARRAY_LIST_NEWS") as BGPositionModelClassItem)
        
        assigningValuesCode()


        // setting recycler view
//        setAdapterRV()

    }

    private fun assigningValuesCode() {
        // giving background to top image
        imgTopBG.setImageResource(
            getDrawableResourceId(
                imgTopBG.context,
                list.backgroundImageNameOnlyDashboard
            )
        )

        // giving background to bottom image
        imgBottomBG.setImageResource(
            getDrawableResourceId(
                imgBottomBG.context,
                list.backgroundImageNameOnlyDetails
            )
        )


        // assign top constraints
        Log.d("listIntAllCasesCheck", "top constraints: --> top,left,right " +
                " ${getValueFromExpression(list.dashboardBackgroundImgTopConstraint)}" +
                " ${getValueFromExpression(list.dashboardBackgroundImgLeftConstraint)}" +
                " ${getValueFromExpression(list.dashboardBackgroundImgRightConstraint)}")
        if (imgTopBG.layoutParams is ViewGroup.MarginLayoutParams) {
            (imgTopBG.layoutParams as ViewGroup.MarginLayoutParams)
                .setMargins(
                    (getValueFromExpression(list.dashboardBackgroundImgLeftConstraint)).toInt() ,
                    (getValueFromExpression(list.dashboardBackgroundImgTopConstraint)).toInt(),
                    (getValueFromExpression(list.dashboardBackgroundImgRightConstraint)).toInt(),
                    0
                )
            imgTopBG.requestLayout()
        }


        // Assigning Bottom Image CONSTRAINTS

        Log.d(
            "listIntAllCasesCheck",
            "bottom constraints: --> bottom,left,right" +
                    " ${getValueFromExpression(list.detailsViewBackgroundImageBottomConstraint)}" +
                    " ${getValueFromExpression(list.detailsViewBackgroundImageLeftConstraint)}" +
                    " ${getValueFromExpression(list.detailsViewBackgroundImageRightConstraint)}")

        if (imgBottomBG.layoutParams is ViewGroup.MarginLayoutParams) {
            (imgBottomBG.layoutParams as ViewGroup.MarginLayoutParams)
                .setMargins(
                    (getValueFromExpression(list.detailsViewBackgroundImageLeftConstraint)).toInt(),
                    0,
                    (getValueFromExpression(list.detailsViewBackgroundImageRightConstraint)).toInt(),
                    (getValueFromExpression(list.detailsViewBackgroundImageBottomConstraint)).toInt(),
                )
            imgBottomBG.requestLayout()
        }


        // Assigning Top Image Height & Width

        val tagTop = "Top BG"

        imgTopBG.layoutParams.height = getValueFromExpression(list.dashboardBackgroundImgHeightConstraint).toInt()
        imgTopBG.layoutParams.width = getValueFromExpression(list.dashboardBackgroundImgWidthConstraint).toInt()

        Log.d(
            "listIntAllCasesCheck",
            "$tagTop height & width constraints: --> height,width" +
                    " ${getValueFromExpression(list.dashboardBackgroundImgHeightConstraint)}" +
                    " ${getValueFromExpression(list.dashboardBackgroundImgWidthConstraint)}"
        )


        // Assigning Bottom Image Height & Width

        val tagBottom = "Bottom BG"

        imgTopBG.layoutParams.height = getValueFromExpression(list.detailsViewBackgroundImageHeightConstant).toInt()
        imgTopBG.layoutParams.width = getValueFromExpression(list.detailsViewBackgroundImageWidthConstant).toInt()

        Log.d(
            "listIntAllCasesCheck",
            "$tagBottom height & width constraints: --> height,width" +
                    " ${getValueFromExpression(list.detailsViewBackgroundImageHeightConstant)}" +
                    " ${getValueFromExpression(list.detailsViewBackgroundImageWidthConstant)}"
        )
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