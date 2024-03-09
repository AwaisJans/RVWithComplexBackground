package com.jans.recycler.view.with.background.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.util.TypedValue
import android.widget.ImageView
import android.widget.Toast

class CommonUtilForBoth {

    companion object{
        /**
        Code to Get Final Values for Height & Width of BG
         */
        fun getFinalValuesForHeightWidthBG(height: String, width: String): List<Double> {

            // optional value is left constraint value

            /**
            Case 1
             */
            val resultHeightCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(height)
            val resultWidthCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(width)

            /**
            Case 2
             */
            val resultHeightCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(height)
            val resultWidthCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(width)

            /**
            Case 3
             */
            val resultHeightCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(height)
            val resultWidthCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(width)

            /**
            Case 4
             */
            val resultHeightCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(height)
            val resultWidthCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(width)

            /**
            Case 5
             */
            val resultHeightCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(height).toDouble()
            val resultWidthCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(width).toDouble()

            // now checking
            val finalHeight: Double = when {
                resultHeightCase1 != 0.0 -> resultHeightCase1
                resultHeightCase2 != 0.0 -> resultHeightCase2
                resultHeightCase3 != 0.0 -> resultHeightCase3
                resultHeightCase4 != 0.0 -> resultHeightCase4
                resultHeightCase5 != 0.0 -> resultHeightCase5
                else -> 0.0
            }
            val finalWidth: Double =
                when {
                    resultWidthCase1 != 0.0 -> resultWidthCase1
                    resultWidthCase2 != 0.0 -> resultWidthCase2
                    resultWidthCase3 != 0.0 -> resultWidthCase3
                    resultWidthCase4 != 0.0 -> resultWidthCase4
                    resultWidthCase5 != 0.0 -> resultWidthCase5
                    else -> 0.0
                }


//            when {
////                resultHeightCase1 != 0.0 -> {
////                    Log.d("listIntAllCasesCheck","final height case1: $resultHeightCase1")
////                }
////                resultHeightCase2 != 0.0 -> {
////                    Log.d("listIntAllCasesCheck","final height case2: $resultHeightCase2")
////                }
//                resultHeightCase3 != 0.0 ->{
//                    Log.d("listIntAllCasesCheck","final height case3: $resultHeightCase3")
//                }
////                resultHeightCase4 != 0.0 -> {
////                    Log.d("listIntAllCasesCheck","final height case4: $resultHeightCase4")
////                }
////                resultHeightCase5 != 0.0 -> {
////                    Log.d("listIntAllCasesCheck","final height case5: $resultHeightCase5")
////                }
//                else ->
//            }

//            Log.d("listIntAllCasesCheck","final height case three: ${getHeightOrWidthFromRegexCase3(heightFromServerCase4)}")

            return listOf(finalHeight, finalWidth)
        }


        /**
        Code to Set Final Height & Width
         */
        fun getHeightOrWidth(
            height: String,
            width: String,
            tag: String,
            imageView: ImageView
        ) {

            val listOfHeightWidth = getFinalValuesForHeightWidthBG(height, width)

            val isDp = TypedValue.COMPLEX_UNIT_DIP ==  // used for widgets sizes
                    TypedValue.complexToDimensionPixelSize(
                        listOfHeightWidth[0].toInt(),
                        imageView.context.resources.displayMetrics
                    )
            val isSp = TypedValue.COMPLEX_UNIT_SP == // used for text sizes
                    TypedValue.complexToDimensionPixelSize(
                        listOfHeightWidth[0].toInt(),
                        imageView.context.resources.displayMetrics
                    )


            if (isDp) {
                Log.d("listIntAllCasesCheck", "$tag unit is dp")
            }
            if (isSp) {
                Log.d("listIntAllCasesCheck", "$tag unit is sp")
            }

            imageView.layoutParams.height = listOfHeightWidth[0].toInt()
            imageView.layoutParams.width = listOfHeightWidth[1].toInt()

            Log.d(
                "listIntAllCasesCheck",
                "$tag height & width constraints: --> height,width $listOfHeightWidth"
            )

        }

        /**
        Code to Check Image Exists or not
         */
        @SuppressLint("DiscouragedApi")
        fun getDrawableResourceId(context: Context, imageNameFromJson: String): Int {
            val packageName = context.packageName
            val resource =
                context.resources.getIdentifier(imageNameFromJson, "drawable", packageName)
            if (imageNameFromJson.isEmpty() || imageNameFromJson == "") {
                return context.resources.getIdentifier(
                    "ic_launcher_background",
                    "drawable",
                    packageName
                )
            } else if (resource == 0) { // if not found
                return context.resources.getIdentifier(
                    "ic_launcher_background",
                    "drawable",
                    packageName
                )
            }

            return resource
        }

        /**
        Code to Show Toast Easily
         */
        fun showToast(ctx: Context, msg: String) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }


    }
}