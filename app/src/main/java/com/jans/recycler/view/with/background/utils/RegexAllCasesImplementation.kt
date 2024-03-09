package com.jans.recycler.view.with.background.utils

import android.util.Log
import com.jans.recycler.view.with.background.utils.AppConfig.Companion.patternHeightCase1
import java.util.regex.Matcher

class RegexAllCasesImplementation {
    companion object{
        /**
        Case 1 Regex Implementation
         */
        fun getHeightOrWidthFromRegexCase1(
            valueFromServer: String,
        ): Double {
            val patternMatcherHeight = patternHeightCase1.matcher(valueFromServer)
            val patternMatcherWidth = AppConfig.patternWidthCase1.matcher(valueFromServer)

            return if (patternMatcherHeight.find()) {
                case1RegexCode(patternMatcherHeight, AppConfig.screenHeight)
            } else if (patternMatcherWidth.find()) {
                case1RegexCode(patternMatcherWidth, AppConfig.screenWidth)
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
         */
        fun getHeightOrWidthFromRegexCase2(
            valueFromServer: String,
        ): Double {
            val patternMatcherHeight = AppConfig.patternHeightCase2.matcher(valueFromServer)
            val patternMatcherWidth = AppConfig.patternWidthCase2.matcher(valueFromServer)

            return if (patternMatcherHeight.find()) {
                case2RegexCode(patternMatcherHeight, AppConfig.screenHeight)
            } else if (patternMatcherWidth.find()) {
                case2RegexCode(patternMatcherWidth, AppConfig.screenWidth)
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
         */
        fun getHeightOrWidthFromRegexCase3(
            valueFromServer: String,
        ): Double {
            val regexHeight = Regex(AppConfig.patternHeightCase3.pattern())
            val regexWidth = Regex(AppConfig.patternWidthCase3.pattern())

            val matchResultHeight = regexHeight.find(valueFromServer)
            val matchResultWidth = regexWidth.find(valueFromServer)

            return if (matchResultHeight != null) {
                case3RegexCode(matchResultHeight, AppConfig.screenHeight)
            } else if (matchResultWidth != null) {
                case3RegexCode(matchResultWidth, AppConfig.screenWidth)
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
         */
        fun getHeightOrWidthFromRegexCase4(
            valueFromServer: String,
        ): Double {
            val regexHeight = Regex(AppConfig.patternHeightCase4.pattern())
            val regexWidth = Regex(AppConfig.patternWidthCase4.pattern())

            val matchResultHeight = regexHeight.find(valueFromServer)
            val matchResultWidth = regexWidth.find(valueFromServer)

            return if (matchResultHeight != null) {
                case4RegexCode(matchResultHeight, AppConfig.screenHeight)
            } else if (matchResultWidth != null) {
                case4RegexCode(matchResultWidth, AppConfig.screenWidth)
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
         */
        fun getHeightOrWidthFromRegexCase5(
            valueFromServer: String,
        ): Int {
            val patternMatcherHeight = AppConfig.patternHeightCase5.matcher(valueFromServer)
            val patternMatcherWidth = AppConfig.patternWidthCase5.matcher(valueFromServer)
            return if (patternMatcherHeight.find()) {
                AppConfig.screenHeight
            } else if (patternMatcherWidth.find()) {
                AppConfig.screenWidth
            } else {
                Log.d("value123", "case5 failed")
                0
            }
        }
    }
}