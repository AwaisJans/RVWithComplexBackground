package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.NewsAdapter
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

        // getting screen width and height
        getScreenWidthAndHeight(this)

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



        setAdapterRV()

    }




    private fun setConstraintsForTopBG(top: String, right: String, imageView: ImageView) {
        Log.d("value123", "top:$top")
        Log.d("value123", "bottom:$right")


        /**
        Case 1 top & right
         */
        val resultTopCase1 =
            getHeightOrWidthFromRegexCase1(patternHeightCase1, top, screenHeight)
        val resultRightCase1 =
            getHeightOrWidthFromRegexCase1(patternWidthCase1, right, screenWidth)

        /**
        Case 2 top & right
         */
        val resultTopCase2 =
            getHeightOrWidthFromRegexCase2(patternHeightCase2, top, screenHeight)
        val resultRightCase2 =
            getHeightOrWidthFromRegexCase2(patternWidthCase2, right, screenWidth)

        /**
        Case 3 top & right
         */
        val resultTopCase3 =
            getHeightOrWidthFromRegexCase3(patternHeightCase3, top, screenHeight)
        val resultRightCase3 =
            getHeightOrWidthFromRegexCase3(patternWidthCase3, right, screenWidth)

        /**
        Case 4 top & right
         */
        val resultTopCase4 =
            getHeightOrWidthFromRegexCase4(patternHeightCase4, top, screenHeight)
        val resultRightCase4 =
            getHeightOrWidthFromRegexCase4(patternWidthCase4, right, screenWidth)


        Log.d("value123", "case 4 top : $resultTopCase4")
        Log.d("value123", "case 4 right : $resultRightCase4")

        // now checking
        val finalTop = when {
            resultTopCase1 != 0 -> resultTopCase1
            resultTopCase2 != 0 -> resultTopCase2
            resultTopCase3 != 0 -> resultTopCase3
            resultTopCase4 != 0 -> resultTopCase4
            else -> 0
        }

        val finalRight =
            when {
                resultRightCase1 != 0 -> resultRightCase1
                resultRightCase2 != 0 -> resultRightCase2
                resultRightCase3 != 0 -> resultRightCase3
                resultRightCase4 != 0 -> resultRightCase4
                else -> 0
            }
        Log.d("value123", "finalTop:$finalTop")
        Log.d("value123", "finalRight:$finalRight")
        if (imageView.layoutParams is MarginLayoutParams) {
            (imageView.layoutParams as MarginLayoutParams).setMargins(0, finalTop, finalRight,0)
            imageView.requestLayout()
        }


    }


    private fun setConstraintsForBottomBG(right: String, bottom: String, imageView: ImageView) {
        Log.d("value123", "right:$right")
        Log.d("value123", "bottom:$bottom")

        /**
        Case 1 bottom & right
         */
        val resultRightCase1 =
            getHeightOrWidthFromRegexCase1(patternWidthCase1, right, screenHeight)
        val resultBottomCase1 =
            getHeightOrWidthFromRegexCase1(patternHeightCase1, bottom, screenWidth)

        /**
        Case 2 bottom & right
         */
        val resultRightCase2 =
            getHeightOrWidthFromRegexCase2(patternWidthCase2, right, screenHeight)
        val resultBottomCase2 =
            getHeightOrWidthFromRegexCase2(patternHeightCase2, bottom, screenWidth)

        /**
        Case 3 bottom & right
         */
        val resultRightCase3 =
            getHeightOrWidthFromRegexCase3(patternWidthCase3, right, screenHeight)
        val resultBottomCase3 =
            getHeightOrWidthFromRegexCase3(patternHeightCase3, bottom, screenWidth)

        /**
        Case 4 bottom & right
         */
        val resultRightCase4 =
            getHeightOrWidthFromRegexCase4(patternWidthCase4, right, screenHeight)
        val resultBottomCase4 =
            getHeightOrWidthFromRegexCase4(patternHeightCase4, bottom, screenWidth)




        // now checking
        val finalRight = when {
            resultRightCase1 != 0 -> resultRightCase1
            resultRightCase2 != 0 -> resultRightCase2
            resultRightCase3 != 0 -> resultRightCase3
            resultRightCase4 != 0 -> resultRightCase4
            else -> 0
        }

        val finalBottom =
            when {
                resultBottomCase1 != 0 -> resultBottomCase1
                resultBottomCase2 != 0 -> resultBottomCase2
                resultBottomCase3 != 0 -> resultBottomCase3
                resultBottomCase4 != 0 -> resultBottomCase4
                else -> 0
            }

        Log.d("value123", "finalRight:$finalRight")
        Log.d("value123", "finalBottom:$finalBottom")
        if (imageView.layoutParams is MarginLayoutParams) {
            (imageView.layoutParams as MarginLayoutParams).setMargins(0, finalRight, finalBottom,0)
            imageView.requestLayout()
        }


    }



    private fun getHeightOrWidth(height: String, width: String, imageView: ImageView) {

        Log.d("value123", "finalHeight:$height")
        Log.d("value123", "finalWidth:$width")
        Log.d("value123", "here it is -> ${2183 * (545%76) * (233%754)}")

        /**
        Case 1 Height & Width
         */
        val resultHeightCase1 =
            getHeightOrWidthFromRegexCase1(patternHeightCase1, height, screenHeight)
        val resultWidthCase1 =
            getHeightOrWidthFromRegexCase1(patternWidthCase1, width, screenWidth)

        /**
        Case 2 Height & Width
         */
        val resultHeightCase2 =
            getHeightOrWidthFromRegexCase2(patternHeightCase2, height, screenHeight)
        val resultWidthCase2 =
            getHeightOrWidthFromRegexCase2(patternWidthCase2, width, screenWidth)

        /**
        Case 3 Height & Width
         */
        val resultHeightCase3 =
            getHeightOrWidthFromRegexCase3(patternHeightCase3, height, screenHeight)
        val resultWidthCase3 =
            getHeightOrWidthFromRegexCase3(patternWidthCase3, width, screenWidth)

        /**
        Case 4 Height & Width
         */
        val resultHeightCase4 =
            getHeightOrWidthFromRegexCase4(patternHeightCase4, height, screenHeight)
        val resultWidthCase4 =
            getHeightOrWidthFromRegexCase4(patternWidthCase4, width, screenWidth)


        Log.d("value123", "case 4 height : $resultHeightCase4")
        Log.d("value123", "case 4 width : $resultWidthCase4")

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

        imageView.layoutParams.height = finalHeight
        imageView.layoutParams.width = finalWidth

        Log.d("value123", "finalHeight:$finalHeight")
        Log.d("value123", "finalWidth:$finalWidth")
    }


    private fun setAdapterRV() {
        val sampleList = mutableListOf<String>()

        repeat(500) {
            sampleList.add("My Favourite Challenge is to Accept Challenge $it")
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(sampleList)
    }

    private fun codeSample(){

    }

}