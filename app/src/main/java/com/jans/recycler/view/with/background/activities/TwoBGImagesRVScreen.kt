package com.jans.recycler.view.with.background.activities

import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.jans.recycler.view.with.background.R
import java.util.regex.Pattern


class TwoBGImagesRVScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_bgimages_rvscreen)
        recyclerView = findViewById(R.id.rvList)

        getScreenWidthAndHeight()

        // sample values
        val value1 = 345
        val value2 = 854

        // case 1 sample height and width
        val heightFromServerCase1 = "SCREENHEIGHT - $value1"
        val widthFromServerCase1 = "SCREENWIDTH / $value2"


        // case 2 sample height and width
        val heightFromServerCase2 = "-(SCREENHEIGHT - $value1)"
        val widthFromServerCase2 = "-(SCREENWIDTH / $value2)"


        // case 1 patterns
        val patternHeightCase1 = Pattern.compile("(SCREENHEIGHT) ([-+*/]) (\\d+)")
        val patternWidthCase1 = Pattern.compile("(SCREENHEIGHT) ([-+*/]) (\\d+)")


        // case 2 patterns
        val patternHeightCase2 = Pattern.compile("\\((-)?(SCREENWIDTH) ([-+*/]) (\\d+)\\)")
        val patternWidthCase2 = Pattern.compile("\\((-)?(SCREENWIDTH) ([-+*/]) (\\d+)\\)")


        // get width  ---> case 1
        val resultHeightCase1 = getValueFromRegexCase1(patternHeightCase1, heightFromServerCase1, screenHeight)
        // get height ---> case 1
        val resultWidthCase1 = getValueFromRegexCase1(patternWidthCase1, widthFromServerCase1, screenWidth)


        // get width ---> case 2
        val resultHeightCase2 = getValueFromRegexCase2(patternHeightCase2, heightFromServerCase2, screenHeight)
        // get height ---> case 2
        val resultWidthCase2 = getValueFromRegexCase2(patternWidthCase2, widthFromServerCase2, screenWidth)



        // now checking
        val resultHeight = if (resultHeightCase1 == 0) {
            resultHeightCase2
        } else {
            resultHeightCase1
        }

        val resultWidth = if (resultWidthCase1 == 0) {
            resultWidthCase2
        } else {
            resultWidthCase1
        }






//        val sampleList = mutableListOf<String>()
//
//        repeat(500) {
//            sampleList.add("My Favourite Challenge is to Accept Challenge $it")
//        }
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = AdapterExample(sampleList)

    }

    private fun getScreenWidthAndHeight() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
    }


    // case 2
    private fun getValueFromRegexCase2(
        pattern: Pattern,
        valueFromServer: String,
        screenHeightOrWidth: Int
    ): Int {
        val patternMatcher = pattern.matcher(valueFromServer)

        if (patternMatcher.find()) {
            val negative = patternMatcher.group(1)
            val operatorString = patternMatcher.group(3)
            val valueString = patternMatcher.group(4)
            val value = valueString?.toInt()

            return case2HeightOrWidthFinal(value, operatorString!!, screenHeightOrWidth)


        } else {
            return 0
        }
    }


    private fun case2HeightOrWidthFinal(
        value: Int?,
        operatorMid: String,
        screenHeightOrWidth: Int
    ): Int {
        return when (operatorMid) {
            "+" -> -(screenHeightOrWidth + value!!)
            "-" -> -(screenHeightOrWidth - value!!)
            "*" -> -(screenHeightOrWidth * value!!)
            "/" ->  -(screenHeightOrWidth / value!!)
            else -> throw IllegalArgumentException("Invalid operator: $operatorMid")
        }
    }

    // case 1
    private fun getValueFromRegexCase1(
        pattern: Pattern,
        valueFromServer: String,
        screenHeightOrWidth: Int
    ): Int {
        val patternMatcher = pattern.matcher(valueFromServer)
        if (patternMatcher.find()) {
            val text = patternMatcher.group(1)
            val operatorMid = patternMatcher.group(2)
            val valueString = patternMatcher.group(3)
            val value = valueString?.toInt()

            Log.d(
                "tag123",
                "result: ${case1HeightOrWidthFinal(value, operatorMid!!, screenHeightOrWidth)}"
            )

            return case1HeightOrWidthFinal(value, operatorMid, screenHeightOrWidth)

        } else {
            return 0
        }
    }

    private fun case1HeightOrWidthFinal(
        value: Int?,
        operatorMid: String,
        screenHeightOrWidth: Int
    ): Int {
        return when (operatorMid) {
            "+" -> screenHeightOrWidth + value!!
            "-" -> screenHeightOrWidth - value!!
            "*" -> screenHeightOrWidth * value!!
            "/" -> screenHeightOrWidth / value!!
            else -> throw IllegalArgumentException("Invalid operator: $operatorMid")
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}