package com.rav.raverp.data.model.api;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.io.Serializable;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rav.raverp.R;

public class MyGoalListModel implements Serializable
{

    @SerializedName("intMyGoalId")
    @Expose
    private Integer intMyGoalId;
    @SerializedName("strMyGoal")
    @Expose
    private String strMyGoal;
    @SerializedName("dtGoalStartDate")
    @Expose
    private String dtGoalStartDate;
    @SerializedName("dtGoalEndDate")
    @Expose
    private String dtGoalEndDate;
    @SerializedName("strMyGoaIImage")
    @Expose
    private String strMyGoaIImage;
    private final static long serialVersionUID = 5023414775916186215L;

    public Integer getIntMyGoalId() {
        return intMyGoalId;
    }

    public void setIntMyGoalId(Integer intMyGoalId) {
        this.intMyGoalId = intMyGoalId;
    }

    public String getStrMyGoal() {
        return strMyGoal;
    }

    public void setStrMyGoal(String strMyGoal) {
        this.strMyGoal = strMyGoal;
    }

    public String getDtGoalStartDate() {
        return dtGoalStartDate;
    }

    public void setDtGoalStartDate(String dtGoalStartDate) {
        this.dtGoalStartDate = dtGoalStartDate;
    }

    public String getDtGoalEndDate() {
        return dtGoalEndDate;
    }

    public void setDtGoalEndDate(String dtGoalEndDate) {
        this.dtGoalEndDate = dtGoalEndDate;
    }

    public String getStrMyGoaIImage() {
        return strMyGoaIImage;
    }

    public void setStrMyGoaIImage(String strMyGoaIImage) {
        this.strMyGoaIImage = strMyGoaIImage;
    }
    public String getImageUrl(){
        return strMyGoaIImage;
    }

    @BindingAdapter({"imageUrls"})
    public  static void loadImage(ImageView imageView, String imageUrl) {

        String img=imageUrl;
        Glide.with(imageView.getContext()).load("http://192.168.29.136" + imageUrl)
                .placeholder(R.drawable.account)
                .into(imageView);



    }

}