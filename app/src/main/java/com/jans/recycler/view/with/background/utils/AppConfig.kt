package com.jans.recycler.view.with.background.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.widget.Toast
import java.util.regex.Pattern

class AppConfig {

    companion object {

        /**
        Case 1 Sample Height & Width from Server
         */

        const val heightFromServerCase1 = "SCREENHEIGHT - 345"
        const val widthFromServerCase1 = "SCREENWIDTH / 854"

        /**
        Case 2 Sample Height & Width from Server
         */

        const val heightFromServerCase2 = "-(SCREENHEIGHT - 345)"
        const val widthFromServerCase2 = "-(SCREENWIDTH / 854)"

        /**
        Case 3 Sample Height & Width from Server
         */

        const val heightFromServerCase3 = "SCREENHEIGHT * (753/345)"
        const val widthFromServerCase3 = "SCREENWIDTH * (253*854)"

          /**
        Case 4 Sample Height & Width from Server
         */

        const val heightFromServerCase4 = "SCREENHEIGHT * (214/375) * (97/214)"
        const val widthFromServerCase4 = "SCREENWIDTH + (214/375) * (97/214)"

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

        val patternHeightCase2: Pattern = Pattern.compile("\\((-)?(SCREENHEIGHT) ([-+*/]) (\\d+)\\)")
        val patternWidthCase2: Pattern = Pattern.compile("\\((-)?(SCREENWIDTH) ([-+*/]) (\\d+)\\)")

        /**
        Case 3 Pattern For Regex
         */

        val patternHeightCase3: Pattern =
            Pattern.compile( """SCREENHEIGHT\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")
        val patternWidthCase3: Pattern =
            Pattern.compile( """SCREENWIDTH\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")

        /**
        Case 4 Pattern For Regex
         */

        val patternHeightCase4: Pattern =
            Pattern.compile( """SCREENHEIGHT\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")
        val patternWidthCase4: Pattern =
            Pattern.compile( """SCREENWIDTH\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")

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
                Log.d("values123", "getting value from regex case 1 : $value")
                when (operatorMid) {
                    "+" -> screenHeightOrWidth + value!!
                    "-" -> screenHeightOrWidth - value!!
                    "*" -> screenHeightOrWidth * value!!
                    "/" -> screenHeightOrWidth % value!!
                    else -> throw IllegalArgumentException("Invalid operator: $operatorMid")
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

                when (operatorString) {
                    "+" -> -(screenHeightOrWidth + value!!)
                    "-" -> -(screenHeightOrWidth - value!!)
                    "*" -> -(screenHeightOrWidth * value!!)
                    "/" -> -(screenHeightOrWidth % value!!)
                    else -> throw IllegalArgumentException("Invalid operator: $operatorString")
                }

            } else {
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
            Log.d("values123", "regex pattern case 3: ${pattern.pattern()}")

            val matchResult = regex.find(valueFromServer)
            return if (matchResult != null) {

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
                        "/" ->  screenHeightOrWidth * (numberInBracket1 % numberInBracket2)
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "/" -> when (afterOperator) {
                        "+" -> screenHeightOrWidth % (numberInBracket1 + numberInBracket2)
                        "-" ->  screenHeightOrWidth % (numberInBracket1 - numberInBracket2)
                        "*" -> screenHeightOrWidth % (numberInBracket1 * numberInBracket2)
                        "/" -> screenHeightOrWidth % (numberInBracket1 % numberInBracket2)
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    else -> {0}
                }
            }else {
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
            Log.d("value123", "regex pattern case 4: ${pattern.pattern()}")

            val matchResult = regex.find(valueFromServer)
            return if (matchResult != null) {



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
                        "/" ->{
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
                        "/" ->  {
                            screenHeightOrWidth * (numberInBracket1 % numberInBracket2)
                        }
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "/" -> when (afterOperator) {
                        "+" -> {
                            screenHeightOrWidth % (numberInBracket1 + numberInBracket2)
                        }
                        "-" ->  {
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

                    else -> {0}
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
                        "/" ->{
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
                        "/" ->  {
                            group1HeightCase4 * (numberInBracket3 % numberInBracket4)
                        }
                        else -> 0 // Handle unsupported operator before the brackets
                    }

                    "/" -> when (fourthOperator) {
                        "+" -> {
                            group1HeightCase4 % (numberInBracket3 + numberInBracket4)
                        }
                        "-" ->  {
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

                    else -> {0}
                }

                group1HeightCase4+group2HeightCase4

            }else {

                0
            }
        }



        /**
        Code to Check Image Exists or not
         */
        @SuppressLint("DiscouragedApi")
        fun getDrawableResourceId(context: Context, imageNameFromJson: String): Int {
            val packageName = context.packageName
            val resource = context.resources.getIdentifier(imageNameFromJson, "drawable", packageName)
            if(imageNameFromJson.isEmpty() || imageNameFromJson == ""){
                return context.resources.getIdentifier("ic_launcher_background", "drawable", packageName)
            }
            else if (resource == 0){ // if not found
                return context.resources.getIdentifier("ic_launcher_background", "drawable", packageName)
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