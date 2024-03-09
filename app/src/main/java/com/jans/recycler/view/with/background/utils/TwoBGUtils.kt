package com.jans.recycler.view.with.background.utils

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import com.jans.recycler.view.with.background.model.BGPositionModelClass
import com.jans.recycler.view.with.background.utils.CommonUtilForBoth.Companion.getDrawableResourceId
import com.jans.recycler.view.with.background.utils.CommonUtilForBoth.Companion.getHeightOrWidth

class TwoBGUtils {

    companion object{
        /**
        Code to Get Final Values for Constraint of Two BG
         */
        private fun getFinalValuesForConstraintsTwoBG(
            left: String,
            right: String,
            topOrBottom: String
        ): List<Double> {

            /**
            Case 1
             */
            val resultLeftCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(left)
            val resultRightCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(right)
            val resultTopOrBottomCase1 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase1(topOrBottom)

            /**
            Case 2
             */
            val resultLeftCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(left)
            val resultRightCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(right)
            val resultTopOrBottomCase2 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase2(topOrBottom)

            /**
            Case 3
             */
            val resultLeftCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(left)
            val resultRightCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(right)
            val resultTopOrBottomCase3 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase3(topOrBottom)

            /**
            Case 4
             */
            val resultLeftCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(left)
            val resultRightCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(right)
            val resultTopOrBottomCase4 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase4(topOrBottom)

            /**
            Case 5
             */
            val resultLeftCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(left)
            val resultRightCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(right)
            val resultTopOrBottomCase5 =
                RegexAllCasesImplementation.getHeightOrWidthFromRegexCase5(topOrBottom)

            // now checking
            val finalLeft = when {
                resultLeftCase1 != 0.toDouble() -> resultLeftCase1
                resultLeftCase2 != 0.toDouble() -> resultLeftCase2
                resultLeftCase3 != 0.toDouble() -> resultLeftCase3
                resultLeftCase4 != 0.toDouble() -> resultLeftCase4
                resultLeftCase5.toDouble() != 0.toDouble() -> resultLeftCase5.toDouble()
                else -> 0.toDouble()
            }
            val finalRight =
                when {
                    resultRightCase1 != 0.toDouble() -> resultRightCase1
                    resultRightCase2 != 0.toDouble() -> resultRightCase2
                    resultRightCase3 != 0.toDouble() -> resultRightCase3
                    resultRightCase4 != 0.toDouble() -> resultRightCase4
                    resultRightCase5.toDouble() != 0.toDouble() -> resultRightCase5.toDouble()
                    else -> 0.toDouble()
                }
            val finalTopOrBottom =
                when {
                    resultTopOrBottomCase1 != 0.toDouble() -> resultTopOrBottomCase1
                    resultTopOrBottomCase2 != 0.toDouble() -> resultTopOrBottomCase2
                    resultTopOrBottomCase3 != 0.toDouble() -> resultTopOrBottomCase3
                    resultTopOrBottomCase4 != 0.toDouble() -> resultTopOrBottomCase4
                    resultTopOrBottomCase5.toDouble() != 0.toDouble() -> resultTopOrBottomCase5.toDouble()
                    else -> 0.toDouble()
                }


            return listOf(finalLeft, finalRight, finalTopOrBottom)
        }



        /**
        Code to Set Final Top Image Constraints
         */
        private fun setConstraintsForTopBG(
            top: String,
            right: String,
            left: String,
            imageView: ImageView
        ) {
            val listOfHeightWidth = getFinalValuesForConstraintsTwoBG(top, right, left)
            Log.d("listIntAllCasesCheck", "top constraints: --> top,left,right  $listOfHeightWidth")
            if (imageView.layoutParams is ViewGroup.MarginLayoutParams) {
                (imageView.layoutParams as ViewGroup.MarginLayoutParams)
                    .setMargins(
                        listOfHeightWidth[2].toInt(),
                        listOfHeightWidth[0].toInt(),
                        listOfHeightWidth[1].toInt(),
                        0
                    )
                imageView.requestLayout()
            }
        }


        /**
        Code to Set Final Bottom Image Constraints
         */
        private fun setConstraintsForBottomBG(
            right: String,
            bottom: String,
            left: String,
            imageView: ImageView
        ) {

            val listOfHeightWidth = getFinalValuesForConstraintsTwoBG(bottom, right, left)
            Log.d(
                "listIntAllCasesCheck",
                "bottom constraints: --> bottom,left,right $listOfHeightWidth"
            )

            if (imageView.layoutParams is ViewGroup.MarginLayoutParams) {
                (imageView.layoutParams as ViewGroup.MarginLayoutParams)
                    .setMargins(
                        listOfHeightWidth[2].toInt(),
                        0,
                        listOfHeightWidth[1].toInt(),
                        listOfHeightWidth[0].toInt()
                    )
                imageView.requestLayout()
            }

        }

        /**
        Code to Set Final All in One Value for TwoBG Screen
         */
        fun getConstraintWithHeightWidthForTwoImageBG(
            list: BGPositionModelClass.BGPositionModelClassItem?,
            imgTopBG: ImageView,
            imgBottomBG: ImageView
        ) {
            // giving background to both images
            imgTopBG.setImageResource(
                getDrawableResourceId(
                    imgTopBG.context,
                    list!!.backgroundImageNameOnlyDashboard
                )
            )
            imgBottomBG.setImageResource(
                getDrawableResourceId(
                    imgBottomBG.context,
                    list.backgroundImageNameOnlyDetails
                )
            )

            // Assigning Top IMG CONSTRAINTS
            setConstraintsForTopBG(
                list.dashboardBackgroundImgTopConstraint,
                list.dashboardBackgroundImgRightConstraint,
                list.dashboardBackgroundImgLeftConstraint,
                imgTopBG
            )
            // Assigning Bottom IMG CONSTRAINTS
            setConstraintsForBottomBG(
                list.detailsViewBackgroundImageRightConstraint,
                list.detailsViewBackgroundImageBottomConstraint,
                list.detailsViewBackgroundImageLeftConstraint,
                imgBottomBG
            )
            // Assigning Top IMG Height & Width
            getHeightOrWidth(
                list.dashboardBackgroundImgHeightConstraint,
                list.dashboardBackgroundImgWidthConstraint, "Top BG", imgTopBG
            )
            // Assigning Bottom IMG Height & Width
            getHeightOrWidth(
                list.detailsViewBackgroundImageHeightConstant,
                list.detailsViewBackgroundImageWidthConstant, "Bottom BG", imgBottomBG
            )
        }


    }


}