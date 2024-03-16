package com.jans.recycler.view.with.background.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.widget.Toast
import java.util.regex.Matcher
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
        Case 6 Sample Value from Server
         */
        const val heightFromServerCase6 = "56565"
        const val widthFromServerCase6 = "345.54"

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
        Case 1 Regex Implementation
        Formula -> SCREENHEIGHT - 345 (or) SCREENWIDTH - 345
         */
        private fun getHeightOrWidthFromRegexCase1(
            valueFromServer: String,
        ): Double {
            val patternHeightCase1: Pattern = Pattern.compile("(SCREENHEIGHT) ([-+*/]) (\\d+)")
            val patternWidthCase1: Pattern = Pattern.compile("(SCREENWIDTH) ([-+*/]) (\\d+)")

            val patternMatcherHeight = patternHeightCase1.matcher(valueFromServer)
            val patternMatcherWidth = patternWidthCase1.matcher(valueFromServer)

            return if (patternMatcherHeight.find()) {
                case1RegexCode(patternMatcherHeight, screenHeight)
            } else if (patternMatcherWidth.find()) {
                case1RegexCode(patternMatcherWidth, screenWidth)
            } else {
                Log.d("value123", "case1 failed")
                0.0
            }
        }

        private fun case1RegexCode(patternMatcher: Matcher, screenHeightOrWidth: Int): Double {
            val text = patternMatcher.group(1)
            val operatorMid = patternMatcher.group(2)
            val valueString = patternMatcher.group(3)
            val value = valueString?.toDouble()
            Log.d("value123", "case1: running")
            return when (operatorMid) {
                "+" -> screenHeightOrWidth + value!!
                "-" -> screenHeightOrWidth - value!!
                "*" -> screenHeightOrWidth * value!!
                "/" -> screenHeightOrWidth / value!!
                else -> {
                    0.0
                }
            }
        }

        /**
        Case 2 Regex Implementation
        Formula -> -(SCREENHEIGHT - 345) (or) -(SCREENWIDTH - 345)
         */
        private fun getHeightOrWidthFromRegexCase2(
            valueFromServer: String,
        ): Double {
            val patternHeightCase2: Pattern =
                Pattern.compile("\\((-)?(SCREENHEIGHT) ([-+*/]) (\\d+)\\)")
            val patternWidthCase2: Pattern = Pattern.compile("\\((-)?(SCREENWIDTH) ([-+*/]) (\\d+)\\)")

            val patternMatcherHeight = patternHeightCase2.matcher(valueFromServer)
            val patternMatcherWidth = patternWidthCase2.matcher(valueFromServer)

            return if (patternMatcherHeight.find()) {
                case2RegexCode(patternMatcherHeight, screenHeight)
            } else if (patternMatcherWidth.find()) {
                case2RegexCode(patternMatcherWidth, screenWidth)
            } else {
                Log.d("value123", "case2 failed")
                0.0
            }
        }

        private fun case2RegexCode(patternMatcher: Matcher, screenHeightOrWidth: Int): Double {
            val negative = patternMatcher.group(1)
            val operatorString = patternMatcher.group(3)
            val valueString = patternMatcher.group(4)
            val value = valueString?.toDouble()
            Log.d("value123", "case2: running")

            return when (operatorString) {
                "+" -> -(screenHeightOrWidth + value!!)
                "-" -> -(screenHeightOrWidth - value!!)
                "*" -> -(screenHeightOrWidth * value!!)
                "/" -> -(screenHeightOrWidth / value!!)
                else -> 0.0
            }
        }

        /**
        Case 3 Regex Implementation
        Formula -> SCREENHEIGHT * (753/345) (or) SCREENWIDTH * (753/345)
         */
        private fun getHeightOrWidthFromRegexCase3(
            valueFromServer: String,
        ): Double {
            val patternHeightCase3: Pattern =
                Pattern.compile("""SCREENHEIGHT\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)$""")
            val patternWidthCase3: Pattern =
                Pattern.compile("""SCREENWIDTH\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)$""")

            val regexHeight = Regex(patternHeightCase3.pattern())
            val regexWidth = Regex(patternWidthCase3.pattern())

            val matchResultHeight = regexHeight.find(valueFromServer)
            val matchResultWidth = regexWidth.find(valueFromServer)

            return if (matchResultHeight != null) {
                case3RegexCode(matchResultHeight, screenHeight)
            } else if (matchResultWidth != null) {
                case3RegexCode(matchResultWidth, screenWidth)
            } else {
                Log.d("value123", "case3 failed")
                0.0
            }

        }

        private fun case3RegexCode(matchResult: MatchResult, screenHeightOrWidth: Int): Double {
            val beforeOperator = matchResult.groups[1]?.value
            val restOfValue = matchResult.groups[2]?.value
            val afterOperator = restOfValue?.replace(Regex("[0-9]+"), "")
            val numbers = restOfValue?.split(Regex("[^0-9]+"))
            val numberInBracket1 = numbers?.get(0)?.toIntOrNull() ?: 0
            val numberInBracket2 = numbers?.getOrNull(1)?.toIntOrNull() ?: 0

            return when (beforeOperator) {
                "+" -> when (afterOperator) {
                    "+" -> screenHeightOrWidth.toDouble() + (numberInBracket1.toDouble() + (numberInBracket2.toDouble()))
                    "-" -> screenHeightOrWidth.toDouble() + (numberInBracket1.toDouble() - (numberInBracket2.toDouble()))
                    "*" -> screenHeightOrWidth.toDouble() + (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                    "/" -> screenHeightOrWidth.toDouble() + (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                    else -> 0.0 // Handle unsupported operator before the brackets
                }

                "-" -> when (afterOperator) {
                    "+" -> screenHeightOrWidth.toDouble() - (numberInBracket1.toDouble() + numberInBracket2.toDouble())
                    "-" -> screenHeightOrWidth.toDouble() - (numberInBracket1.toDouble() - numberInBracket2.toDouble())
                    "*" -> screenHeightOrWidth.toDouble() - (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                    "/" -> screenHeightOrWidth.toDouble() - (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                    else -> 0.toDouble() // Handle unsupported operator before the brackets
                }

                "*" -> when (afterOperator) {
                    "+" -> screenHeightOrWidth.toDouble() * (numberInBracket1.toDouble() + numberInBracket2.toDouble())
                    "-" -> screenHeightOrWidth.toDouble() * (numberInBracket1.toDouble() - numberInBracket2.toDouble())
                    "*" -> screenHeightOrWidth.toDouble() * (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                    "/" -> screenHeightOrWidth.toDouble() * (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                    else -> 0.toDouble() // Handle unsupported operator before the brackets
                }

                "/" -> when (afterOperator) {
                    "+" -> screenHeightOrWidth.toDouble() / (numberInBracket1.toDouble() + numberInBracket2.toDouble())
                    "-" -> screenHeightOrWidth.toDouble() / (numberInBracket1.toDouble() - numberInBracket2.toDouble())
                    "*" -> screenHeightOrWidth.toDouble() / (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                    "/" -> screenHeightOrWidth.toDouble() / (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                    else -> 0.toDouble() // Handle unsupported operator before the brackets
                }

                else -> {
                    0.toDouble()
                }
            }
        }

        /**
        Case 4 Regex Implementation
        Formula -> SCREENHEIGHT * (214/375) * (97/214) (or) SCREENWIDTH * (214/375) * (97/214)
         */
        private fun getHeightOrWidthFromRegexCase4(
            valueFromServer: String,
        ): Double {
            val patternHeightCase4: Pattern =
                Pattern.compile("""SCREENHEIGHT\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")
            val patternWidthCase4: Pattern =
                Pattern.compile("""SCREENWIDTH\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)\s*(\+|\-|\*|\/)\s*\(\s*(.*?)\s*\)""")

            val regexHeight = Regex(patternHeightCase4.pattern())
            val regexWidth = Regex(patternWidthCase4.pattern())

            val matchResultHeight = regexHeight.find(valueFromServer)
            val matchResultWidth = regexWidth.find(valueFromServer)

            return if (matchResultHeight != null) {
                case4RegexCode(matchResultHeight, screenHeight)
            } else if (matchResultWidth != null) {
                case4RegexCode(matchResultWidth, screenWidth)
            } else {
                Log.d("value123", "case4 failed")
                0.toDouble()
            }
        }

        private fun case4RegexCode(matchResult: MatchResult, screenHeightOrWidth: Int): Double {
            Log.d("value123", "case4: running")

            // i divided given string into two parts

            // values for group 1
            val beforeOperator = matchResult.groups[1]?.value
            val restOfValueFirstBracket = matchResult.groups[2]?.value
            val afterOperator = restOfValueFirstBracket?.replace(Regex("[0-9]+"), "")
            val numbersFirstBracket = restOfValueFirstBracket?.split(Regex("[^0-9]+"))
            val numberInBracket1 = numbersFirstBracket?.get(0)?.toIntOrNull() ?: 0
            val numberInBracket2 = numbersFirstBracket?.getOrNull(1)?.toIntOrNull() ?: 0

            // values for group 2
            val thirdOperator = matchResult.groups[3]?.value
            val restOfValueSecondBracket = matchResult.groups[4]?.value
            val fourthOperator = restOfValueSecondBracket?.replace(Regex("[0-9]+"), "")
            val numbersSecondBracket = restOfValueSecondBracket?.split(Regex("[^0-9]+"))
            val numberInBracket3 = numbersSecondBracket?.get(0)?.toIntOrNull() ?: 0
            val numberInBracket4 = numbersSecondBracket?.getOrNull(1)?.toIntOrNull() ?: 0

            val group1HeightCase4: Double = when (beforeOperator) {
                "+" ->
                    when (afterOperator) {
                        "+" -> {
                            screenHeightOrWidth + (numberInBracket1.toDouble() + (numberInBracket2.toDouble()))
                        }

                        "-" -> {
                            screenHeightOrWidth + (numberInBracket1.toDouble() - (numberInBracket2.toDouble()))
                        }

                        "*" -> {
                            screenHeightOrWidth + (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                        }

                        "/" -> {
                            screenHeightOrWidth + (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                        }

                        else -> 0.0 // Handle unsupported operator before the brackets
                    }

                "-" -> when (afterOperator) {
                    "+" -> {
                        screenHeightOrWidth - (numberInBracket1.toDouble() + numberInBracket2.toDouble())
                    }

                    "-" -> {
                        screenHeightOrWidth - (numberInBracket1.toDouble() - numberInBracket2.toDouble())
                    }

                    "*" -> {
                        screenHeightOrWidth - (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                    }

                    "/" -> {
                        screenHeightOrWidth - (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                    }

                    else -> 0.0 // Handle unsupported operator before the brackets
                }

                "*" -> when (afterOperator) {
                    "+" -> {
                        screenHeightOrWidth * (numberInBracket1.toDouble() + numberInBracket2.toDouble())
                    }

                    "-" -> {
                        screenHeightOrWidth * (numberInBracket1.toDouble() - numberInBracket2.toDouble())
                    }

                    "*" -> {
                        screenHeightOrWidth * (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                    }

                    "/" -> {
                        screenHeightOrWidth * (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                    }

                    else -> 0.toDouble() // Handle unsupported operator before the brackets
                }

                "/" -> when (afterOperator) {
                    "+" -> {
                        screenHeightOrWidth / (numberInBracket1.toDouble() + numberInBracket2.toDouble())
                    }

                    "-" -> {
                        screenHeightOrWidth / (numberInBracket1.toDouble() - numberInBracket2.toDouble())
                    }

                    "*" -> {
                        screenHeightOrWidth / (numberInBracket1.toDouble() * numberInBracket2.toDouble())
                    }

                    "/" -> {
                        screenHeightOrWidth / (numberInBracket1.toDouble() / numberInBracket2.toDouble())
                    }

                    else -> 0.0 // Handle unsupported operator before the brackets
                }

                else -> {
                    0.0
                }
            }

            val group2HeightCase4: Double = when (thirdOperator) {
                "+" ->
                    when (fourthOperator) {
                        "+" -> {
                            group1HeightCase4.toDouble() + (numberInBracket3.toDouble() + (numberInBracket4.toDouble()))
                        }

                        "-" -> {
                            group1HeightCase4.toDouble() + (numberInBracket3.toDouble() - (numberInBracket4.toDouble()))
                        }

                        "*" -> {
                            group1HeightCase4.toDouble() + (numberInBracket3.toDouble() * numberInBracket4.toDouble())
                        }

                        "/" -> {
                            group1HeightCase4.toDouble() + (numberInBracket3.toDouble() / numberInBracket4.toDouble())
                        }

                        else -> 0.toDouble() // Handle unsupported operator before the brackets
                    }

                "-" -> when (fourthOperator) {
                    "+" -> {
                        group1HeightCase4.toDouble() - (numberInBracket3.toDouble() + numberInBracket4.toDouble())
                    }

                    "-" -> {
                        group1HeightCase4.toDouble() - (numberInBracket3.toDouble() - numberInBracket4.toDouble())
                    }

                    "*" -> {
                        group1HeightCase4.toDouble() - (numberInBracket3.toDouble() * numberInBracket4.toDouble())
                    }

                    "/" -> {
                        group1HeightCase4.toDouble() - (numberInBracket3.toDouble() / numberInBracket4.toDouble())
                    }

                    else -> 0.toDouble() // Handle unsupported operator before the brackets
                }

                "*" -> when (fourthOperator) {
                    "+" -> {
                        group1HeightCase4.toDouble() * (numberInBracket3.toDouble() + numberInBracket4.toDouble())
                    }

                    "-" -> {
                        group1HeightCase4.toDouble() * (numberInBracket3.toDouble() - numberInBracket4.toDouble())
                    }

                    "*" -> {
                        group1HeightCase4.toDouble() * (numberInBracket3.toDouble() * numberInBracket4.toDouble())
                    }

                    "/" -> {
                        group1HeightCase4.toDouble() * (numberInBracket3.toDouble() / numberInBracket4.toDouble())
                    }

                    else -> 0.toDouble() // Handle unsupported operator before the brackets
                }

                "/" -> when (fourthOperator) {
                    "+" -> {
                        group1HeightCase4.toDouble() / (numberInBracket3.toDouble() + numberInBracket4.toDouble())
                    }

                    "-" -> {
                        group1HeightCase4.toDouble() / (numberInBracket3.toDouble() - numberInBracket4.toDouble())
                    }

                    "*" -> {
                        group1HeightCase4.toDouble() / (numberInBracket3.toDouble() * numberInBracket4.toDouble())
                    }

                    "/" -> {
                        group1HeightCase4.toDouble() / (numberInBracket3.toDouble() / numberInBracket4.toDouble())
                    }

                    else -> 0.toDouble() // Handle unsupported operator before the brackets
                }

                else -> {
                    0.toDouble()
                }
            }


            Log.d(
                "listIntAllCasesCheck", "group1 : $group1HeightCase4" +
                        " group2 : $group2HeightCase4"
            )
            val originalReturningValue = group1HeightCase4 + group2HeightCase4

            Log.d("listIntAllCasesCheck", "adding both groups : $originalReturningValue")



            return originalReturningValue


        }

        /**
        Case 5 Regex Implementation
        Formula -> SCREENHEIGHT (or) SCREENWIDTH
         */
        private fun getHeightOrWidthFromRegexCase5(
            valueFromServer: String,
        ): Int {
            val patternHeightCase5: Pattern = Pattern.compile("SCREENHEIGHT")
            val patternWidthCase5: Pattern = Pattern.compile("SCREENWIDTH")

            val patternMatcherHeight = patternHeightCase5.matcher(valueFromServer)
            val patternMatcherWidth = patternWidthCase5.matcher(valueFromServer)
            return if (patternMatcherHeight.find()) {
                screenHeight
            } else if (patternMatcherWidth.find()) {
                screenWidth
            } else {
                Log.d("value123", "case5 failed")
                0
            }
        }


        /**
        Case 6 Regex Implementation
        Formula -> 67.66 or 2356
         */
        private fun getHeightOrWidthFromRegexCase6(
            valueFromServer: String,
        ): Double {
            val regex = """^-?\d+(\.\d+)?$""".toRegex()
            return if (regex.matches(valueFromServer)) {
                Log.d("check123","case 6 is true")
                valueFromServer.toDouble()
            } else {
                Log.d("check123", "case6 failed")
                0.0
            }
        }


        /**
        A Simple Method to Return Calculated Value from Expressions
         */
        fun getValueFromExpression(
            expressionFromServer: String
        ): Number {
            // now checking the expression
            return when {
                /**
                Case 1
                Formula -> SCREENHEIGHT - 345 (or) SCREENWIDTH - 345
                 */

                getHeightOrWidthFromRegexCase1(expressionFromServer) != 0.toDouble() ->
                    getHeightOrWidthFromRegexCase1(expressionFromServer)

                /**
                Case 2
                Formula -> -(SCREENHEIGHT - 345) (or) -(SCREENWIDTH - 345)
                 */

                getHeightOrWidthFromRegexCase2(expressionFromServer) != 0.toDouble() ->
                    getHeightOrWidthFromRegexCase2(expressionFromServer)

                /**
                Case 3
                Formula -> SCREENHEIGHT * (753/345) (or) SCREENWIDTH * (753/345)
                 */

                getHeightOrWidthFromRegexCase3(expressionFromServer) != 0.toDouble() ->
                    getHeightOrWidthFromRegexCase3(expressionFromServer)

                /**
                Case 4
                Formula -> SCREENHEIGHT * (214/375) * (97/214) (or) SCREENWIDTH * (214/375) * (97/214)
                 */

                getHeightOrWidthFromRegexCase4(expressionFromServer) != 0.toDouble() ->
                    getHeightOrWidthFromRegexCase4(expressionFromServer)

                /**
                Case 5
                Formula -> SCREENHEIGHT (or) SCREENWIDTH
                 */

                getHeightOrWidthFromRegexCase5(expressionFromServer).toDouble() != 0.toDouble() ->
                    getHeightOrWidthFromRegexCase5(expressionFromServer).toDouble()


                /**
                Case 6
                Formula -> 67.66 or 2356
                 */
                getHeightOrWidthFromRegexCase6(expressionFromServer) != 0.toDouble() ->
                    getHeightOrWidthFromRegexCase6(expressionFromServer)


                else -> 0.toDouble()
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
        Code to Show Toast Easily
         */
        fun showToast(ctx: Context, msg: String) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }
        
        
        
        

    }


}