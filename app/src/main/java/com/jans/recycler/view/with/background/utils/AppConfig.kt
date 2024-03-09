package com.jans.recycler.view.with.background.utils

import android.app.Activity
import android.graphics.Point
import android.util.Log
import java.util.regex.Pattern


class AppConfig {

    companion object {

        /**
        Case 1 Sample Value from Server
         */
        const val heightFromServerCase1 = "SCREENHEIGHT - 345"
        const val widthFromServerCase1 = "SCREENWIDTH / 854"

        /**
        Case 2 Sample Value from Server
         */
        const val heightFromServerCase2 = "-(SCREENHEIGHT - 345)"
        const val widthFromServerCase2 = "-(SCREENWIDTH / 854)"

        /**
        Case 3 Sample Value from Server
         */
        const val heightFromServerCase3 = "SCREENHEIGHT * (753/345)"
        const val widthFromServerCase3 = "SCREENWIDTH * (253*854)"

        /**
        Case 4 Sample Value from Server
         */
        const val heightFromServerCase4 = "SCREENHEIGHT * (214/375) * (97/214)"
        const val widthFromServerCase4 = "SCREENWIDTH + (214/375) * (97/214)"

        /**
        Case 5 Sample Value from Server
         */
        const val heightFromServerCase5 = "SCREENHEIGHT"
        const val widthFromServerCase5 = "SCREENWIDTH"

        /**
        Screen Height & Width Values Declaration
         */
        var screenWidth: Int = 0
        var screenHeight: Int = 0

        /**
        Function to get Screen Width and Height
         */
        fun getScreenWidthAndHeight(activity: Activity) {
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenWidth = size.x
            screenHeight = size.y

            Log.d("value123", "screenheight: $screenHeight")
            Log.d("value123", "screenWidth: $screenWidth")

        }

        /**
        Case 1 Pattern For Regex
         */
        val patternHeightCase1: Pattern = Pattern.compile("(SCREENHEIGHT) ([-+*/]) (\\d+)")
        val patternWidthCase1: Pattern = Pattern.compile("(SCREENWIDTH) ([-+*/]) (\\d+)")

        /**
        Case 2 Pattern For Regex
         */
        val patternHeightCase2: Pattern =
            Pattern.compile("\\((-)?(SCREENHEIGHT) ([-+*/]) (\\d+)\\)")
        val patternWidthCase2: Pattern = Pattern.compile("\\((-)?(SCREENWIDTH) ([-+*/]) (\\d+)\\)")

        /**
        Case 3 Pattern For Regex
         */
        val patternHeightCase3: Pattern =
            Pattern.compile("""SCREENHEIGHT\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)$""")
        val patternWidthCase3: Pattern =
            Pattern.compile("""SCREENWIDTH\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)$""")

        /**
        Case 4 Pattern For Regex
         */
        val patternHeightCase4: Pattern =
            Pattern.compile("""SCREENHEIGHT\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")
        val patternWidthCase4: Pattern =
            Pattern.compile("""SCREENWIDTH\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")

        /**
        Case 5 Pattern For Regex
         */
        val patternHeightCase5: Pattern = Pattern.compile("SCREENHEIGHT")
        val patternWidthCase5: Pattern = Pattern.compile("SCREENWIDTH")


    }


}