package com.rav.raverp.data.model.api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlotAvailableModel implements Serializable
{
        @SerializedName("Code")
        @Expose
        private Integer code;
        @SerializedName("Remark")
        @Expose
        private String remark;
        @SerializedName("intPlotId")
        @Expose
        private Integer intPlotId;
        @SerializedName("intBlockId")
        @Expose
        private Integer intBlockId;
        @SerializedName("intProjectId")
        @Expose
        private Integer intProjectId;
        @SerializedName("strPlotNo")
        @Expose
        private String strPlotNo;
        @SerializedName("floatX")
        @Expose
        private Double floatX;
        @SerializedName("floatY")
        @Expose
        private Double floatY;
        @SerializedName("floatPlotArea")
        @Expose
        private Double floatPlotArea;
        @SerializedName("floatRateSqr")
        @Expose
        private String floatRateSqr;
        @SerializedName("flotBV")
        @Expose
        private Double flotBV;
        @SerializedName("fltDBV")
        @Expose
        private Double fltDBV;
        @SerializedName("strBookingPlotStatus")
        @Expose
        private String strBookingPlotStatus;
        @SerializedName("floatPlotBookingAmount")
        @Expose
        private Double floatPlotBookingAmount;
        @SerializedName("floatBookingAmtPercent")
        @Expose
        private Double floatBookingAmtPercent;
        private final static long serialVersionUID = 921806064254640392L;

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

        public Integer getIntPlotId() {
            return intPlotId;
        }

        public void setIntPlotId(Integer intPlotId) {
            this.intPlotId = intPlotId;
        }

        public Integer getIntBlockId() {
            return intBlockId;
        }

        public void setIntBlockId(Integer intBlockId) {
            this.intBlockId = intBlockId;
        }

        public Integer getIntProjectId() {
            return intProjectId;
        }

        public void setIntProjectId(Integer intProjectId) {
            this.intProjectId = intProjectId;
        }

        public String getStrPlotNo() {
            return strPlotNo;
        }

        public void setStrPlotNo(String strPlotNo) {
            this.strPlotNo = strPlotNo;
        }

        public Double getFloatX() {
            return floatX;
        }

        public void setFloatX(Double floatX) {
            this.floatX = floatX;
        }

        public Double getFloatY() {
            return floatY;
        }

        public void setFloatY(Double floatY) {
            this.floatY = floatY;
        }

        public Double getFloatPlotArea() {
            return floatPlotArea;
        }

        public void setFloatPlotArea(Double floatPlotArea) {
            this.floatPlotArea = floatPlotArea;
        }

        public String getFloatRateSqr() {
            return floatRateSqr;
        }

        public void setFloatRateSqr(String floatRateSqr) {
            this.floatRateSqr = floatRateSqr;
        }

        public Double getFlotBV() {
            return flotBV;
        }

        public void setFlotBV(Double flotBV) {
            this.flotBV = flotBV;
        }

        public Double getFltDBV() {
            return fltDBV;
        }

        public void setFltDBV(Double fltDBV) {
            this.fltDBV = fltDBV;
        }

        public String getStrBookingPlotStatus() {
            return strBookingPlotStatus;
        }

        public void setStrBookingPlotStatus(String strBookingPlotStatus) {
            this.strBookingPlotStatus = strBookingPlotStatus;
        }

        public Double getFloatPlotBookingAmount() {
            return floatPlotBookingAmount;
        }

        public void setFloatPlotBookingAmount(Double floatPlotBookingAmount) {
            this.floatPlotBookingAmount = floatPlotBookingAmount;
        }

        public Double getFloatBookingAmtPercent() {
            return floatBookingAmtPercent;
        }

        public void setFloatBookingAmtPercent(Double floatBookingAmtPercent) {
            this.floatBookingAmtPercent = floatBookingAmtPercent;
        }

    }