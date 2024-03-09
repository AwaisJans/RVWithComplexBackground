package com.jans.recycler.view.with.background.utils

import android.widget.ImageView
import android.widget.RelativeLayout
import com.jans.recycler.view.with.background.model.BGPositionModelClass
import com.jans.recycler.view.with.background.utils.CommonUtilForBoth.Companion.getDrawableResourceId
import com.jans.recycler.view.with.background.utils.CommonUtilForBoth.Companion.getFinalValuesForHeightWidthBG

class OneBGUtils {

    companion object{
        /**
        Code to Get Final Values for Constraint of One BG
         */
        private fun getFinalValuesForConstraintsOneBG(
            top: String,
            right: String,
            left: String,
            bottom: String
        ): List<Double> {

            /**
            Case 1
             */
            val resultLeftCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(left)
            val resultRightCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(right)
            val resultTopCase1 = RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(top)
            val resultBottomCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(bottom)

            /**
            Case 2
             */
            val resultLeftCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(left)
            val resultRightCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(right)
            val resultTopCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(top)
            val resultBottomCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(bottom)

            /**
            Case 3
             */
            val resultLeftCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(left)
            val resultRightCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(right)
            val resultTopCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(top)
            val resultBottomCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(bottom)

            /**
            Case 4
             */
            val resultLeftCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(left)
            val resultRightCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(right)
            val resultTopCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(top)
            val resultBottomCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(bottom)

            /**
            Case 5
             */
            val resultLeftCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(left).toDouble()
            val resultRightCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(right).toDouble()
            val resultTopCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(top).toDouble()
            val resultBottomCase5 = RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(
                bottom
            ).toDouble()

            // now checking
            val finalLeft = when {
                resultLeftCase1 != 0.toDouble() -> resultLeftCase1
                resultLeftCase2 != 0.toDouble() -> resultLeftCase2
                resultLeftCase3 != 0.toDouble() -> resultLeftCase3
                resultLeftCase4 != 0.toDouble() -> resultLeftCase4
                resultLeftCase5 != 0.toDouble() -> resultLeftCase5
                else -> 0.toDouble()
            }
            val finalRight = when {
                resultRightCase1 != 0.toDouble() -> resultRightCase1
                resultRightCase2 != 0.toDouble() -> resultRightCase2
                resultRightCase3 != 0.toDouble() -> resultRightCase3
                resultRightCase4 != 0.toDouble() -> resultRightCase4
                resultRightCase5 != 0.toDouble() -> resultRightCase5
                else -> 0.toDouble()
            }
            val finalTop = when {
                resultTopCase1 != 0.toDouble() -> resultTopCase1
                resultTopCase2 != 0.toDouble() -> resultTopCase2
                resultTopCase3 != 0.toDouble() -> resultTopCase3
                resultTopCase4 != 0.toDouble() -> resultTopCase4
                resultTopCase5 != 0.toDouble() -> resultTopCase5
                else -> 0.toDouble()
            }
            val finalBottom = when {
                resultBottomCase1 != 0.toDouble() -> resultBottomCase1
                resultBottomCase2 != 0.toDouble() -> resultBottomCase2
                resultBottomCase3 != 0.toDouble() -> resultBottomCase3
                resultBottomCase4 != 0.toDouble() -> resultBottomCase4
                resultBottomCase5 != 0.toDouble() -> resultBottomCase5
                else -> 0.toDouble()
            }

            return listOf(finalLeft, finalTop, finalRight, finalBottom)
        }



        /**
        Code to Set Final All in One Value for OneBG Screen
         */
        fun getConstraintWithHeightWidthForOneImageBG(
            list: BGPositionModelClass.BGPositionModelClassItem?,
            imageBG: ImageView, parent: RelativeLayout
        ) {
            // giving background to BG image
            imageBG.setImageResource(
                getDrawableResourceId(
                    imageBG.context,
                    list!!.backgroundImageNameOnlyDashboard
                )
            )

            // send these strings from server for calculating constraints
            val top = list.dashboardBackgroundImgTopConstraint
            val right = list.dashboardBackgroundImgRightConstraint
            val left = list.dashboardBackgroundImgLeftConstraint
            val bottom = list.dashboardBackgroundImgBottomConstraint
            val listOfConstraints = getFinalValuesForConstraintsOneBG(top, right, left, bottom)
            val listOfHeightWidth = getFinalValuesForHeightWidthBG(
                list.dashboardBackgroundImgHeightConstraint,
                list.dashboardBackgroundImgWidthConstraint
            )

            // getting calculated constraints
            val marginTop = listOfConstraints[1].toInt()
            val marginRight = listOfConstraints[2].toInt()
            val marginLeft = listOfConstraints[0].toInt()
            val marginBottom = listOfConstraints[3].toInt()

            // getting calculated height & Width
            val heightValue = listOfHeightWidth[0].toInt()
            val widthValue = listOfHeightWidth[1].toInt()

            // use Height & Width for constraints that has at least one non zero constraint
            val paramsAnyConstraintNonZero = RelativeLayout.LayoutParams(widthValue,heightValue)

            // use Height & Width for constraints that has all constraints are zero constraint
            val paramsZeroConstraintAll = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )

            // make cases here
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


    }


}