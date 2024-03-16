package com.jans.recycler.view.with.background.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import com.jans.recycler.view.with.background.adapter.SampleAdapter
import com.jans.recycler.view.with.background.model.BGPositionModelClass
import com.jans.recycler.view.with.background.utils.AppConfig
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getDrawableResourceId
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.getValueFromExpression


class OneBGImageRVScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageBG: ImageView

    private lateinit var list: BGPositionModelClass.BGPositionModelClassItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_bgimages_rvscreen)

//      getting screen width and height
        AppConfig.getScreenWidthAndHeight(this)

        recyclerView = findViewById(R.id.rvList)
        imageBG = findViewById(R.id.imgOneBG)

        list = intent.getSerializableExtra("ARRAY_LIST_NEWS") as BGPositionModelClass.BGPositionModelClassItem

        assigningValuesCode()


//        setAdapterRV()

    }



    private fun assigningValuesCode(){
        val parent:RelativeLayout = findViewById(R.id.parentMain)


        // giving background to BG image
        imageBG.setImageResource(
            getDrawableResourceId(
                imageBG.context,
                list.backgroundImageNameOnlyDashboard
            )
        )

        // send these strings from server for calculating constraints
        val top = list.dashboardBackgroundImgTopConstraint
        val right = list.dashboardBackgroundImgRightConstraint
        val left = list.dashboardBackgroundImgLeftConstraint
        val bottom = list.dashboardBackgroundImgBottomConstraint

        // getting calculated constraints
        val marginTop = getValueFromExpression(top).toInt()
        val marginRight = getValueFromExpression(right).toInt()
        val marginLeft = getValueFromExpression(left).toInt()
        val marginBottom = getValueFromExpression(bottom).toInt()

        // getting calculated height & Width
        val heightValue = getValueFromExpression(list.dashboardBackgroundImgHeightConstraint).toInt()
        val widthValue = getValueFromExpression(list.dashboardBackgroundImgWidthConstraint).toInt()

        // use Height & Width for constraints that has at least one non zero constraint
        val paramsAnyConstraintNonZero = RelativeLayout.LayoutParams(widthValue,heightValue)

        // use Height & Width for constraints that has all constraints are zero constraint
        val paramsZeroConstraintAll = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )

        // make cases here for image positioning
        when {
            // if all constraints are empty
            top.isEmpty() && bottom.isEmpty() && right.isEmpty() && left.isEmpty() ->{
                val relativeLayout = RelativeLayout(parent.context)
                imageBG.scaleType = ImageView.ScaleType.FIT_XY;
                imageBG.layoutParams = paramsZeroConstraintAll
                parent.addView(relativeLayout)
            }

            // case is to make image center - right
            top.isEmpty() && left.isEmpty() && bottom.isEmpty() ->{
                val relativeLayout = RelativeLayout(parent.context)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.CENTER_IN_PARENT)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                paramsAnyConstraintNonZero.rightMargin = marginRight
                imageBG.layoutParams = paramsAnyConstraintNonZero
                parent.addView(relativeLayout)
            }

            // case is to make image center - left
            top.isEmpty() && right.isEmpty() && bottom.isEmpty() ->{
                val relativeLayout = RelativeLayout(parent.context)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.CENTER_IN_PARENT)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                paramsAnyConstraintNonZero.leftMargin = marginLeft
                imageBG.layoutParams = paramsAnyConstraintNonZero
                parent.addView(relativeLayout)
            }

            // case is to make image bottom - right
            top.isEmpty() && left.isEmpty() ->{
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                paramsAnyConstraintNonZero.rightMargin = marginRight
                paramsAnyConstraintNonZero.bottomMargin = marginBottom
                val relativeLayout = RelativeLayout(parent.context)
                imageBG.layoutParams = paramsAnyConstraintNonZero
                parent.addView(relativeLayout)
            }

            // case is to make image bottom - left
            top.isEmpty() && right.isEmpty() ->{
                val relativeLayout = RelativeLayout(parent.context)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                paramsAnyConstraintNonZero.leftMargin = marginLeft
                paramsAnyConstraintNonZero.bottomMargin = marginBottom
                imageBG.layoutParams = paramsAnyConstraintNonZero
                parent.addView(relativeLayout)
            }

            // case is to make image top - right
            bottom.isEmpty() && left.isEmpty() ->{
                val relativeLayout = RelativeLayout(parent.context)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_TOP)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                paramsAnyConstraintNonZero.rightMargin = marginRight
                paramsAnyConstraintNonZero.topMargin = marginTop
                imageBG.layoutParams = paramsAnyConstraintNonZero
                parent.addView(relativeLayout)
            }

            // case is to make image top - left
            bottom.isEmpty() && right.isEmpty() ->{
                val relativeLayout = RelativeLayout(parent.context)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_TOP)
                paramsAnyConstraintNonZero.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                paramsAnyConstraintNonZero.leftMargin = marginLeft
                paramsAnyConstraintNonZero.topMargin = marginTop
                imageBG.layoutParams = paramsAnyConstraintNonZero
                parent.addView(relativeLayout)
            }

        }
    }


    private fun setAdapterRV() {
        val sampleList = mutableListOf<String>()
        repeat(500) { sampleList.add("Its My Destination $it") }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(sampleList)
    }

}