package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.SampleAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass.BGPositionModelClassItem
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getDrawableResourceId
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase1
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase2
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase3
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getHeightOrWidthFromRegexCase4
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getScreenWidthAndHeight
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


class TwoImagesBGScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imgTopBG: ImageView
    private lateinit var imgBottomBG: ImageView

    var list: BGPositionModelClassItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_bgimages_rvscreen)

//         getting screen width and height
        getScreenWidthAndHeight(this)

        recyclerView = findViewById(R.id.rvList)
        imgTopBG = findViewById(R.id.imageTop)
        imgBottomBG = findViewById(R.id.imageBtm)

        list = intent.getSerializableExtra("ARRAY_LIST_NEWS") as BGPositionModelClassItem?

        // giving background to both images
        imgTopBG.setImageResource(
            getDrawableResourceId(
                this,
                list!!.backgroundImageNameOnlyDashboard
            )
        )
        imgBottomBG.setImageResource(
            getDrawableResourceId(
                this,
                list!!.backgroundImageNameOnlyDetails
            )
        )

        // Assigning Top IMG CONSTRAINTS
        getHeightOrWidth(
            list!!.dashboardBackgroundImgHeightConstraint,
            list!!.dashboardBackgroundImgWidthConstraint, imgTopBG
        )

        setConstraintsForTopBG(list!!.dashboardBackgroundImgTopConstraint,list!!.dashboardBackgroundImgRightConstraint,
            imageView = imgTopBG)

        // Assigning Bottom IMG CONSTRAINTS
        getHeightOrWidth(
            list!!.detailsViewBackgroundImageHeightConstant,
            list!!.detailsViewBackgroundImageWidthConstant, imgBottomBG
        )

        setConstraintsForBottomBG(list!!.detailsViewBackgroundImageRightConstraint,list!!.detailsViewBackgroundImageBottomConstraint,
            imageView = imgBottomBG)


//        // setting recycler view
        setAdapterRV()

    }

    private fun setConstraintsForTopBG(top: String, right: String, imageView: ImageView) {
        val listOfHeightWidth = getFinalValuesForBG(top,right)


        Log.d("listIntAllCasesCheck","top constraints: $listOfHeightWidth")



        if (imageView.layoutParams is MarginLayoutParams) {
            (imageView.layoutParams as MarginLayoutParams)
                .setMargins(0, listOfHeightWidth[0], listOfHeightWidth[1],0)
            imageView.requestLayout()
        }


    }

    private fun setConstraintsForBottomBG(right: String, bottom: String, imageView: ImageView) {

        val listOfHeightWidth = getFinalValuesForBG(bottom,right)
        Log.d("listIntAllCasesCheck","bottom constraints: $listOfHeightWidth")

        if (imageView.layoutParams is MarginLayoutParams) {
            (imageView.layoutParams as MarginLayoutParams)
                .setMargins(0, listOfHeightWidth[0], listOfHeightWidth[0],0)
            imageView.requestLayout()
        }

    }

    private fun getHeightOrWidth(height: String, width: String, imageView: ImageView) {

        val listOfHeightWidth = getFinalValuesForBG(height,width)

        imageView.layoutParams.height = listOfHeightWidth[0]
        imageView.layoutParams.width = listOfHeightWidth[1]
        Log.d("listIntAllCasesCheck","height & width constraints: $listOfHeightWidth")

    }

    private fun getFinalValuesForBG(height: String, width: String): List<Int> {
        /**
        Case 1
         */
        val resultHeightCase1 =
            getHeightOrWidthFromRegexCase1(patternHeightCase1, height, screenHeight)
        val resultWidthCase1 =
            getHeightOrWidthFromRegexCase1(patternWidthCase1, width, screenWidth)

        /**
        Case 2
         */
        val resultHeightCase2 =
            getHeightOrWidthFromRegexCase2(patternHeightCase2, height, screenHeight)
        val resultWidthCase2 =
            getHeightOrWidthFromRegexCase2(patternWidthCase2, width, screenWidth)

        /**
        Case 3
         */
        val resultHeightCase3 =
            getHeightOrWidthFromRegexCase3(patternHeightCase3, height, screenHeight)
        val resultWidthCase3 =
            getHeightOrWidthFromRegexCase3(patternWidthCase3, width, screenWidth)

        /**
        Case 4
         */
        val resultHeightCase4 =
            getHeightOrWidthFromRegexCase4(patternHeightCase4, height, screenHeight)
        val resultWidthCase4 =
            getHeightOrWidthFromRegexCase4(patternWidthCase4, width, screenWidth)


        // now checking
        val finalHeight = when {
            resultHeightCase1 != 0 -> resultHeightCase1
            resultHeightCase2 != 0 -> resultHeightCase2
            resultHeightCase3 != 0 -> resultHeightCase3
            resultHeightCase4 != 0 -> resultHeightCase4
            else -> 0
        }

        val finalWidth =
            when {
                resultWidthCase1 != 0 -> resultWidthCase1
                resultWidthCase2 != 0 -> resultWidthCase2
                resultWidthCase3 != 0 -> resultWidthCase3
                resultWidthCase4 != 0 -> resultWidthCase4
                else -> 0
            }


        return listOf(finalHeight, finalWidth)
    }

    private fun setAdapterRV() {
        val sampleList = mutableListOf<String>()

        repeat(500) {
            sampleList.add("My Favourite Challenge is to Accept Challenge $it")
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(sampleList)
    }


}