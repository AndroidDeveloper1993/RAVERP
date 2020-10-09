package com.rav.raverp.data.model.api;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rav.raverp.R;
import com.rav.raverp.utils.CommonUtils;

public class LoginModel implements Serializable {


        @SerializedName("Code")
        @Expose
        private Integer code;
        @SerializedName("Remark")
        @Expose
        private String remark;
        @SerializedName("strLoginID")
        @Expose
        private String strLoginID;
        @SerializedName("strLoginKey")
        @Expose
        private String strLoginKey;
        @SerializedName("strBranchCode")
        @Expose
        private String strBranchCode;
        @SerializedName("strDisplayName")
        @Expose
        private String strDisplayName;
        @SerializedName("intRoleID")
        @Expose
        private Integer intRoleID;
        @SerializedName("strEmail")
        @Expose
        private String strEmail;
        @SerializedName("strPhone")
        @Expose
        private String strPhone;
        @SerializedName("strCreatedBy")
        @Expose
        private String strCreatedBy;
        @SerializedName("dtCreated")
        @Expose
        private String dtCreated;
        @SerializedName("strProfilePic")
        @Expose
        private String strProfilePic;
        @SerializedName("chStatus")
        @Expose
        private String chStatus;
        @SerializedName("strRole")
        @Expose
        private String strRole;
        private final static long serialVersionUID = -1061863612288531445L;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStrLoginID() {
            return strLoginID;
        }

        public void setStrLoginID(String strLoginID) {
            this.strLoginID = strLoginID;
        }

        public String getStrLoginKey() {
            return strLoginKey;
        }

        public void setStrLoginKey(String strLoginKey) {
            this.strLoginKey = strLoginKey;
        }

        public String getStrBranchCode() {
            return strBranchCode;
        }

        public void setStrBranchCode(String strBranchCode) {
            this.strBranchCode = strBranchCode;
        }

        public String getStrDisplayName() {
            return strDisplayName;
        }

        public void setStrDisplayName(String strDisplayName) {
            this.strDisplayName = strDisplayName;
        }

        public Integer getIntRoleID() {
            return intRoleID;
        }

        public void setIntRoleID(Integer intRoleID) {
            this.intRoleID = intRoleID;
        }

        public String getStrEmail() {
            return strEmail;
        }

        public void setStrEmail(String strEmail) {
            this.strEmail = strEmail;
        }

        public String getStrPhone() {
            return strPhone;
        }

        public void setStrPhone(String strPhone) {
            this.strPhone = strPhone;
        }

        public String getStrCreatedBy() {
            return strCreatedBy;
        }

        public void setStrCreatedBy(String strCreatedBy) {
            this.strCreatedBy = strCreatedBy;
        }

        public String getDtCreated() {
            return dtCreated;
        }

        public void setDtCreated(String dtCreated) {
            this.dtCreated = dtCreated;
        }

        public String getStrProfilePic() {
            return strProfilePic;
        }

        public void setStrProfilePic(String strProfilePic) {
            this.strProfilePic = strProfilePic;
        }

        public String getChStatus() {
            return chStatus;
        }

        public void setChStatus(String chStatus) {
            this.chStatus = chStatus;
        }

        public String getStrRole() {
            return strRole;
        }

        public void setStrRole(String strRole) {
            this.strRole = strRole;
        }

    @Override
    public String toString() {
        return strLoginID;
    }


    public String getImageUrl(){
        return strProfilePic;
    }

    @BindingAdapter({"imageUrls"})
    public  static void loadImage(ImageView imageView, String imageUrl) {

        String img=imageUrl;
        Glide.with(imageView.getContext()).load("http://192.168.29.136" + imageUrl)
                .placeholder(R.drawable.account)
                .into(imageView);



    }
    }
