package com.jans.recycler.view.with.background.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.jans.recycler.view.with.background.model.BGPositionModelClass
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
        Case 4 Sample Value from Server
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
            Pattern.compile("""SCREENHEIGHT\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")
        val patternWidthCase3: Pattern =
            Pattern.compile("""SCREENWIDTH\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")

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

        /**
        Case 1 Regex Implementation
         */
        fun getHeightOrWidthFromRegexCase1(
            pattern: Pattern,
            valueFromServer: String,
            screenHeightOrWidth: Int
        ): Int {
            val patternMatcher = pattern.matcher(valueFromServer)
            return if (patternMatcher.find()) {
                val text = patternMatcher.group(1)
                val operatorMid = patternMatcher.group(2)
                val valueString = patternMatcher.group(3)
                val value = valueString?.toInt()
                Log.d("value123", "case1: running")
//                Log.d("values123", "getting value from regex case 1 : $value")
                when (operatorMid) {
                    "+" -> screenHeightOrWidth + value!!
                    "-" -> screenHeightOrWidth - value!!
                    "*" -> screenHeightOrWidth * value!!
                    "/" -> screenHeightOrWidth % value!!
                    else -> 0
                }

            } else {
                0
            }
        }

        /**
        Case 2 Regex Implementation
         */
        fun getHeightOrWidthFromRegexCase2(
            pattern: Pattern,
            valueFromServer: String,
            screenHeightOrWidth: Int
        ): Int {
            val patternMatcher = pattern.matcher(valueFromServer)

            return if (patternMatcher.find()) {
                val negative = patternMatcher.group(1)
                val operatorString = patternMatcher.group(3)
                val valueString = patternMatcher.group(4)
                val value = valueString?.toInt()
                Log.d("value123", "case2: running")

                when (operatorString) {
                    "+" -> -(screenHeightOrWidth + value!!)
                    "-" -> -(screenHeightOrWidth - value!!)
                    "*" -> -(screenHeightOrWidth * value!!)
                    "/" -> -(screenHeightOrWidth % value!!)
                    else -> 0
                }

            } else {
                Log.d("check123Case2", "case2 is not matching")
                0
            }
        }

        /**
        Case 3 Regex Implementation
         */
        fun getHeightOrWidthFromRegexCase3(
            pattern: Pattern,
            valueFromServer: String,
            screenHeightOrWidth: Int
        ): Int {
            val regex = Regex(pattern.pattern())
//            Log.d("values123", "regex pattern case 3: ${pattern.pattern()}")

            val matchResult = regex.find(valueFromServer)
            return if (matchResult != null) {
                Log.d("value123", "case3: running")

                val beforeOperator = matchResult.groups[1]?.value
                val restOfValue = matchResult.groups[2]?.value
                val afterOperator = restOfValue?.replace(Regex("[0-9]+"), "")
                val numbers = restOfValue?.split(Regex("[^0-9]+"))
                val numberInBracket1 = numbers?.get(0)?.toIntOrNull() ?: 0
                val numberInBracket2 = numbers?.getOrNull(1)?.toIntOrNull() ?: 0

//                Log.d("value123", "getting value from case 3: $beforeOperator $afterOperator $numberInBracket1 $numberInBracket2")


                when (beforeOperator) {
                    "+" -> when (afterOperator) {
                        "+" -> screenHeightOrWidth + (numberInBracket1 + (numberInBracket2))
                        "-" -> screenHeightOrWidth + (numberInBracket1 - (numberInBracket2))
                        "*" -> screenHeightOrWidth + (numberInBracket1 * numberInBracket2)
                        "/" -> screenHeightOrWidth + (numberInBracket1 % numberInBracket2)
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "-" -> when (afterOperator) {
                        "+" -> screenHeightOrWidth - (numberInBracket1 + numberInBracket2)
                        "-" -> screenHeightOrWidth - (numberInBracket1 - numberInBracket2)
                        "*" -> screenHeightOrWidth - (numberInBracket1 * numberInBracket2)
                        "/" -> screenHeightOrWidth - (numberInBracket1 % numberInBracket2)
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "*" -> when (afterOperator) {
                        "+" -> screenHeightOrWidth * (numberInBracket1 + numberInBracket2)
                        "-" -> screenHeightOrWidth * (numberInBracket1 - numberInBracket2)
                        "*" -> screenHeightOrWidth * (numberInBracket1 * numberInBracket2)
                        "/" -> screenHeightOrWidth * (numberInBracket1 % numberInBracket2)
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "/" -> when (afterOperator) {
                        "+" -> screenHeightOrWidth % (numberInBracket1 + numberInBracket2)
                        "-" -> screenHeightOrWidth % (numberInBracket1 - numberInBracket2)
                        "*" -> screenHeightOrWidth % (numberInBracket1 * numberInBracket2)
                        "/" -> screenHeightOrWidth % (numberInBracket1 % numberInBracket2)
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    else -> {
                        0
                    }
                }
            } else {
                0
            }
        }

        /**
        Case 4 Regex Implementation
         */
        fun getHeightOrWidthFromRegexCase4(
            pattern: Pattern,
            valueFromServer: String,
            screenHeightOrWidth: Int
        ): Int {
            val regex = Regex(pattern.pattern())

            val matchResult = regex.find(valueFromServer)
            return if (matchResult != null) {
                Log.d("value123", "case4: running")


                val beforeOperator = matchResult.groups[1]?.value
                val restOfValueFirstBracket = matchResult.groups[2]?.value
                val afterOperator = restOfValueFirstBracket?.replace(Regex("[0-9]+"), "")
                val numbersFirstBracket = restOfValueFirstBracket?.split(Regex("[^0-9]+"))
                val numberInBracket1 = numbersFirstBracket?.get(0)?.toIntOrNull() ?: 0
                val numberInBracket2 = numbersFirstBracket?.getOrNull(1)?.toIntOrNull() ?: 0

                val thirdOperator = matchResult.groups[3]?.value
                val restOfValueSecondBracket = matchResult.groups[4]?.value
                val fourthOperator = restOfValueSecondBracket?.replace(Regex("[0-9]+"), "")
                val numbersSecondBracket = restOfValueSecondBracket?.split(Regex("[^0-9]+"))
                val numberInBracket3 = numbersSecondBracket?.get(0)?.toIntOrNull() ?: 0
                val numberInBracket4 = numbersSecondBracket?.getOrNull(1)?.toIntOrNull() ?: 0
//                Log.d("value123", "getting value from case 4:" +
//                        " $beforeOperator " +
//                        " $numberInBracket1 $afterOperator $numberInBracket2" +
//                        "$thirdOperator" +
//                        "$numberInBracket3 $fourthOperator  $numberInBracket4" +
//                        "")


                val group1HeightCase4 = when (beforeOperator) {
                    "+" ->
                        when (afterOperator) {
                            "+" -> {
                                screenHeightOrWidth + (numberInBracket1 + (numberInBracket2))
                            }

                            "-" -> {
                                screenHeightOrWidth + (numberInBracket1 - (numberInBracket2))
                            }

                            "*" -> {
                                screenHeightOrWidth + (numberInBracket1 * numberInBracket2)
                            }

                            "/" -> {
                                screenHeightOrWidth + (numberInBracket1 % numberInBracket2)
                            }

                            else -> 0 // Handle unsupported operator before the brackets
                        }

                    "-" -> when (afterOperator) {
                        "+" -> {
                            screenHeightOrWidth - (numberInBracket1 + numberInBracket2)
                        }

                        "-" -> {
                            screenHeightOrWidth - (numberInBracket1 - numberInBracket2)
                        }

                        "*" -> {
                            screenHeightOrWidth - (numberInBracket1 * numberInBracket2)
                        }

                        "/" -> {
                            screenHeightOrWidth - (numberInBracket1 % numberInBracket2)
                        }

                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "*" -> when (afterOperator) {
                        "+" -> {
                            screenHeightOrWidth * (numberInBracket1 + numberInBracket2)
                        }

                        "-" -> {
                            screenHeightOrWidth * (numberInBracket1 - numberInBracket2)
                        }

                        "*" -> {
                            screenHeightOrWidth * (numberInBracket1 * numberInBracket2)
                        }

                        "/" -> {
                            screenHeightOrWidth * (numberInBracket1 % numberInBracket2)
                        }

                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "/" -> when (afterOperator) {
                        "+" -> {
                            screenHeightOrWidth % (numberInBracket1 + numberInBracket2)
                        }

                        "-" -> {
                            screenHeightOrWidth % (numberInBracket1 - numberInBracket2)
                        }

                        "*" -> {
                            screenHeightOrWidth % (numberInBracket1 * numberInBracket2)
                        }

                        "/" -> {
                            screenHeightOrWidth % (numberInBracket1 % numberInBracket2)
                        }

                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    else -> {
                        0
                    }
                }

                val group2HeightCase4 = when (thirdOperator) {
                    "+" ->
                        when (fourthOperator) {
                            "+" -> {
                                group1HeightCase4 + (numberInBracket3 + (numberInBracket4))
                            }

                            "-" -> {
                                group1HeightCase4 + (numberInBracket3 - (numberInBracket4))
                            }

                            "*" -> {
                                group1HeightCase4 + (numberInBracket3 * numberInBracket4)
                            }

                            "/" -> {
                                group1HeightCase4 + (numberInBracket3 % numberInBracket4)
                            }

                            else -> 0 // Handle unsupported operator before the brackets
                        }

                    "-" -> when (fourthOperator) {
                        "+" -> {
                            group1HeightCase4 - (numberInBracket3 + numberInBracket4)
                        }

                        "-" -> {
                            group1HeightCase4 - (numberInBracket3 - numberInBracket4)
                        }

                        "*" -> {
                            group1HeightCase4 - (numberInBracket3 * numberInBracket4)
                        }

                        "/" -> {
                            group1HeightCase4 - (numberInBracket3 % numberInBracket4)
                        }

                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "*" -> when (fourthOperator) {
                        "+" -> {
                            group1HeightCase4 * (numberInBracket3 + numberInBracket4)
                        }

                        "-" -> {
                            group1HeightCase4 * (numberInBracket3 - numberInBracket4)
                        }

                        "*" -> {
                            group1HeightCase4 * (numberInBracket3 * numberInBracket4)
                        }

                        "/" -> {
                            group1HeightCase4 * (numberInBracket3 % numberInBracket4)
                        }

                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "/" -> when (fourthOperator) {
                        "+" -> {
                            group1HeightCase4 % (numberInBracket3 + numberInBracket4)
                        }

                        "-" -> {
                            group1HeightCase4 % (numberInBracket3 - numberInBracket4)
                        }

                        "*" -> {
                            group1HeightCase4 % (numberInBracket3 * numberInBracket4)
                        }

                        "/" -> {
                            group1HeightCase4 % (numberInBracket3 % numberInBracket4)
                        }

                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    else -> {
                        0
                    }
                }

                group1HeightCase4 + group2HeightCase4

            } else {

                0
            }
        }

        /**
        Case 5 Regex Implementation
         */
        private fun getHeightOrWidthFromRegexCase5(
            pattern: Pattern,
            valueFromServer: String,
            screenHeightOrWidth: Int
        ): Int {
            val patternMatcher = pattern.matcher(valueFromServer)
            return if (patternMatcher.find()) {
                screenHeightOrWidth
            } else {
                0
            }
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
        Code to Get Final Values
         */
        private fun getFinalValuesForBG(height: String, width: String,optionalValue:String): List<Int> {

            // optional value is left constraint value

            /**
            Case 1
             */
            val resultHeightCase1 =
                getHeightOrWidthFromRegexCase1(patternHeightCase1, height, screenHeight)
            val resultWidthCase1 =
                getHeightOrWidthFromRegexCase1(patternWidthCase1, width,screenWidth)
            val resultOptionalValueCase1 =
                getHeightOrWidthFromRegexCase1(patternWidthCase1, optionalValue,screenWidth)

            /**
            Case 2
             */
            val resultHeightCase2 =
                getHeightOrWidthFromRegexCase2(patternHeightCase2, height, screenHeight)
            val resultWidthCase2 =
                getHeightOrWidthFromRegexCase2(patternWidthCase2, width, screenWidth)
            val resultOptionalValueCase2 =
                getHeightOrWidthFromRegexCase2(patternWidthCase2, optionalValue,screenWidth)

            /**
            Case 3
             */
            val resultHeightCase3 =
                getHeightOrWidthFromRegexCase3(patternHeightCase3, height, screenHeight)
            val resultWidthCase3 =
                getHeightOrWidthFromRegexCase3(patternWidthCase3, width, screenWidth)
            val resultOptionalValueCase3 =
                getHeightOrWidthFromRegexCase3(patternWidthCase3, optionalValue,screenWidth)

            /**
            Case 4
             */
            val resultHeightCase4 =
                getHeightOrWidthFromRegexCase4(patternHeightCase4, height, screenHeight)
            val resultWidthCase4 =
                getHeightOrWidthFromRegexCase4(patternWidthCase4, width, screenWidth)
            val resultOptionalValueCase4 =
                getHeightOrWidthFromRegexCase4(patternWidthCase4, optionalValue,screenWidth)

            /**
            Case 5
             */
            val resultHeightCase5 =
                getHeightOrWidthFromRegexCase5(patternHeightCase5, height, screenHeight)
            val resultWidthCase5 =
                getHeightOrWidthFromRegexCase5(patternWidthCase5, width, screenWidth)
            val resultOptionalValueCase5 =
                getHeightOrWidthFromRegexCase5(patternWidthCase5, optionalValue,screenWidth)

            // now checking
            val finalHeight = when {
                resultHeightCase1 != 0 -> resultHeightCase1
                resultHeightCase2 != 0 -> resultHeightCase2
                resultHeightCase3 != 0 -> resultHeightCase3
                resultHeightCase4 != 0 -> resultHeightCase4
                resultHeightCase5 != 0 -> resultHeightCase5
                else -> 0
            }
            val finalWidth =
                when {
                    resultWidthCase1 != 0 -> resultWidthCase1
                    resultWidthCase2 != 0 -> resultWidthCase2
                    resultWidthCase3 != 0 -> resultWidthCase3
                    resultWidthCase4 != 0 -> resultWidthCase4
                    resultWidthCase5 != 0 -> resultWidthCase5
                    else -> 0
                }
            val finalOptionalValue =
                when {
                    resultOptionalValueCase1 != 0 -> resultOptionalValueCase1
                    resultOptionalValueCase2 != 0 -> resultOptionalValueCase2
                    resultOptionalValueCase3 != 0 -> resultOptionalValueCase3
                    resultOptionalValueCase4 != 0 -> resultOptionalValueCase4
                    resultOptionalValueCase5 != 0 -> resultOptionalValueCase5
                    else -> 0
                }


            return listOf(finalHeight, finalWidth,finalOptionalValue)
        }

        /**
        Code to Set Final Top Image Constraints
         */
        private fun setConstraintsForTopBG(top: String, right: String,left:String, imageView: ImageView) {
            val listOfHeightWidth = getFinalValuesForBG(top,right,left)
            Log.d("listIntAllCasesCheck","top constraints: $listOfHeightWidth")
            if (imageView.layoutParams is ViewGroup.MarginLayoutParams) {
                (imageView.layoutParams as ViewGroup.MarginLayoutParams)
                    .setMargins(listOfHeightWidth[2], listOfHeightWidth[0], listOfHeightWidth[1],0)
                imageView.requestLayout()
            }
        }

        /**
        Code to Set Final Bottom Image Constraints
         */
        private fun setConstraintsForBottomBG(right: String, bottom: String,left: String, imageView: ImageView) {

            val listOfHeightWidth = getFinalValuesForBG(bottom,right,left)
            Log.d("listIntAllCasesCheck","bottom constraints: $listOfHeightWidth")

            if (imageView.layoutParams is ViewGroup.MarginLayoutParams) {
                (imageView.layoutParams as ViewGroup.MarginLayoutParams)
                    .setMargins(listOfHeightWidth[2], listOfHeightWidth[0], listOfHeightWidth[1],0)
                imageView.requestLayout()
            }

        }

        /**
        Code to Set Final Height & Width
         */
        private fun getHeightOrWidth(height: String, width: String, imageView: ImageView) {

            val listOfHeightWidth = getFinalValuesForBG(height,width,"")

            imageView.layoutParams.height = listOfHeightWidth[0]
            imageView.layoutParams.width = listOfHeightWidth[1]
            Log.d("listIntAllCasesCheck","height & width constraints: $listOfHeightWidth")

        }

        /**
        Code to Set Final All in One Value for TwoBG Screen
         */
        fun getConstraintWithHeightWidthForTwoImageBG(context: Context, list: BGPositionModelClass.BGPositionModelClassItem?, imgTopBG:ImageView, imgBottomBG:ImageView){
            // giving background to both images
            imgTopBG.setImageResource(getDrawableResourceId(context, list!!.backgroundImageNameOnlyDashboard))
            imgBottomBG.setImageResource(getDrawableResourceId(context, list.backgroundImageNameOnlyDetails))

            // Assigning Top IMG CONSTRAINTS
            setConstraintsForTopBG(
                list.dashboardBackgroundImgTopConstraint,
                list.dashboardBackgroundImgRightConstraint,list.dashboardBackgroundImgLeftConstraint, imgTopBG)
            // Assigning Bottom IMG CONSTRAINTS
            setConstraintsForBottomBG(list.detailsViewBackgroundImageRightConstraint,
                list.detailsViewBackgroundImageBottomConstraint,list.detailsViewBackgroundImageLeftConstraint, imgBottomBG)
            // Assigning Top IMG Height & Width
            getHeightOrWidth(list.dashboardBackgroundImgHeightConstraint,
                list.dashboardBackgroundImgWidthConstraint, imgTopBG)
            // Assigning Bottom IMG Height & Width
            getHeightOrWidth(list.detailsViewBackgroundImageHeightConstant,
                list.detailsViewBackgroundImageWidthConstant, imgBottomBG)
        }

        /**
        Code to Set Final All in One Value for OneBG Screen
         */
        fun getConstraintWithHeightWidthForOneImageBG(context: Context,list: BGPositionModelClass.BGPositionModelClassItem?,imageBG:ImageView){
            // giving background to BG image
            imageBG.setImageResource(
                getDrawableResourceId(
                    context,
                    list!!.backgroundImageNameOnlyDashboard
                )
            )
            // Assigning BG IMG CONSTRAINTS
            getHeightOrWidth(
                list.dashboardBackgroundImgHeightConstraint,
                list.dashboardBackgroundImgWidthConstraint,
                imageBG
            )
            setConstraintsForTopBG(
                list.dashboardBackgroundImgTopConstraint,
                list.dashboardBackgroundImgRightConstraint,
                list.dashboardBackgroundImgLeftConstraint,
                imageBG
            )
        }

        /**
        Code to Show Toast Easily
         */
        fun showToast(ctx: Context, msg: String) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }


    }


}