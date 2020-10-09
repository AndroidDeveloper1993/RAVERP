package com.rav.raverp.data.model.api;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerListModel implements Serializable
{

    @SerializedName("bigIntCustomerId")
    @Expose
    private Integer bigIntCustomerId;
    @SerializedName("strName")
    @Expose
    private String strName;
    @SerializedName("strCustId")
    @Expose
    private String strCustId;
    @SerializedName("strEmail")
    @Expose
    private String strEmail;
    @SerializedName("strMobileNo")
    @Expose
    private String strMobileNo;
    @SerializedName("strPinCode")
    @Expose
    private String strPinCode;
    @SerializedName("strStateName")
    @Expose
    private String strStateName;
    @SerializedName("strCityName")
    @Expose
    private String strCityName;
    @SerializedName("strAddress")
    @Expose
    private String strAddress;
    @SerializedName("intIsDelated")
    @Expose
    private Integer intIsDelated;
    private final static long serialVersionUID = -7660999846264073231L;

    public Integer getBigIntCustomerId() {
        return bigIntCustomerId;
    }

    public void setBigIntCustomerId(Integer bigIntCustomerId) {
        this.bigIntCustomerId = bigIntCustomerId;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrCustId() {
        return strCustId;
    }

    public void setStrCustId(String strCustId) {
        this.strCustId = strCustId;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrMobileNo() {
        return strMobileNo;
    }

    public void setStrMobileNo(String strMobileNo) {
        this.strMobileNo = strMobileNo;
    }

    public String getStrPinCode() {
        return strPinCode;
    }

    public void setStrPinCode(String strPinCode) {
        this.strPinCode = strPinCode;
    }

    public String getStrStateName() {
        return strStateName;
    }

    public void setStrStateName(String strStateName) {
        this.strStateName = strStateName;
    }

    public String getStrCityName() {
        return strCityName;
    }

    public void setStrCityName(String strCityName) {
        this.strCityName = strCityName;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public Integer getIntIsDelated() {
        return intIsDelated;
    }

    public void setIntIsDelated(Integer intIsDelated) {
        this.intIsDelated = intIsDelated;
    }

}
