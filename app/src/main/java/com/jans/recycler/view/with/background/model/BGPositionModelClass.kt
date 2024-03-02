package com.jans.recycler.view.with.background.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

class BGPositionModelClass : ArrayList<BGPositionModelClass.BGPositionModelClassItem>(){
    data class BGPositionModelClassItem(
        @SerializedName("dashboardHeaderViewHeight")
        @Expose
        var dashboardHeaderViewHeight: String = "",
        @SerializedName("title")
        @Expose
        var title: String = "",
        @SerializedName("logoImageName")
        @Expose
        var logoImageName: String = "",
        @SerializedName("logoTopConstraint")
        @Expose
        var logoTopConstraint: String = "",
        @SerializedName("dashboardTopLogoHeight")
        @Expose
        var dashboardTopLogoHeight: String = "",
        @SerializedName("backgroundImageNameOnlyDashboard")
        @Expose
        var backgroundImageNameOnlyDashboard: String = "",
        @SerializedName("dashboardBackgroundImgTopConstraint")
        @Expose
        var dashboardBackgroundImgTopConstraint: String = "",
        @SerializedName("dashboardBackgroundImgRightConstraint")
        @Expose
        var dashboardBackgroundImgRightConstraint: String = "",
        @SerializedName("dashboardBackgroundImgWidthConstraint")
        @Expose
        var dashboardBackgroundImgWidthConstraint: String = "",
        @SerializedName("dashboardBackgroundImgHeightConstraint")
        @Expose
        var dashboardBackgroundImgHeightConstraint: String = "",
        @SerializedName("backgroundImageNameOnlyDetails")
        @Expose
        var backgroundImageNameOnlyDetails: String = "",
        @SerializedName("detailsViewBackgroundImageBottomConstraint")
        @Expose
        var detailsViewBackgroundImageBottomConstraint: String = "",
        @SerializedName("detailsViewBackgroundImageRightConstraint")
        @Expose
        var detailsViewBackgroundImageRightConstraint: String = "",
        @SerializedName("detailsViewBackgroundImageWidthConstant")
        @Expose
        var detailsViewBackgroundImageWidthConstant: String = "",
        @SerializedName("detailsViewBackgroundImageHeightConstant")
        @Expose
        var detailsViewBackgroundImageHeightConstant: String = ""
    ):Serializable
}