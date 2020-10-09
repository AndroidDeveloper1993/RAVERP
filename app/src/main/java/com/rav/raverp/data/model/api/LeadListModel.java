package com.rav.raverp.data.model.api;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LeadListModel implements Serializable
{

    @SerializedName("intChangeLeadStatus")
    @Expose
    private Integer intChangeLeadStatus;
    @SerializedName("bigIntLeadId")
    @Expose
    private Integer bigIntLeadId;
    @SerializedName("strEmpName")
    @Expose
    private String strEmpName;
    @SerializedName("intEmployeeId")
    @Expose
    private Integer intEmployeeId;
    @SerializedName("intProjectId")
    @Expose
    private Integer intProjectId;
    @SerializedName("strProjectName")
    @Expose
    private String strProjectName;
    @SerializedName("intBlockId")
    @Expose
    private Integer intBlockId;
    @SerializedName("strBlockName")
    @Expose
    private String strBlockName;
    @SerializedName("intSiteVisitStatusId")
    @Expose
    private Integer intSiteVisitStatusId;
    @SerializedName("strSiteVisitStatus")
    @Expose
    private String strSiteVisitStatus;
    @SerializedName("strPlotNo")
    @Expose
    private String strPlotNo;
    @SerializedName("dtSiteVisitDate")
    @Expose
    private String dtSiteVisitDate;
    @SerializedName("strRequesterName")
    @Expose
    private String strRequesterName;
    @SerializedName("strReqMobileNo")
    @Expose
    private String strReqMobileNo;
    @SerializedName("strReqEmailId")
    @Expose
    private String strReqEmailId;
    @SerializedName("dtSiteVisitDate1")
    @Expose
    private String dtSiteVisitDate1;
    @SerializedName("intIsDelated")
    @Expose
    private Integer intIsDelated;
    private final static long serialVersionUID = -6881914092799711350L;

    public Integer getIntChangeLeadStatus() {
        return intChangeLeadStatus;
    }

    public void setIntChangeLeadStatus(Integer intChangeLeadStatus) {
        this.intChangeLeadStatus = intChangeLeadStatus;
    }

    public Integer getBigIntLeadId() {
        return bigIntLeadId;
    }

    public void setBigIntLeadId(Integer bigIntLeadId) {
        this.bigIntLeadId = bigIntLeadId;
    }

    public String getStrEmpName() {
        return strEmpName;
    }

    public void setStrEmpName(String strEmpName) {
        this.strEmpName = strEmpName;
    }

    public Integer getIntEmployeeId() {
        return intEmployeeId;
    }

    public void setIntEmployeeId(Integer intEmployeeId) {
        this.intEmployeeId = intEmployeeId;
    }

    public Integer getIntProjectId() {
        return intProjectId;
    }

    public void setIntProjectId(Integer intProjectId) {
        this.intProjectId = intProjectId;
    }

    public String getStrProjectName() {
        return strProjectName;
    }

    public void setStrProjectName(String strProjectName) {
        this.strProjectName = strProjectName;
    }

    public Integer getIntBlockId() {
        return intBlockId;
    }

    public void setIntBlockId(Integer intBlockId) {
        this.intBlockId = intBlockId;
    }

    public String getStrBlockName() {
        return strBlockName;
    }

    public void setStrBlockName(String strBlockName) {
        this.strBlockName = strBlockName;
    }

    public Integer getIntSiteVisitStatusId() {
        return intSiteVisitStatusId;
    }

    public void setIntSiteVisitStatusId(Integer intSiteVisitStatusId) {
        this.intSiteVisitStatusId = intSiteVisitStatusId;
    }

    public String getStrSiteVisitStatus() {
        return strSiteVisitStatus;
    }

    public void setStrSiteVisitStatus(String strSiteVisitStatus) {
        this.strSiteVisitStatus = strSiteVisitStatus;
    }

    public String getStrPlotNo() {
        return strPlotNo;
    }

    public void setStrPlotNo(String strPlotNo) {
        this.strPlotNo = strPlotNo;
    }

    public String getDtSiteVisitDate() {
        return dtSiteVisitDate;
    }

    public void setDtSiteVisitDate(String dtSiteVisitDate) {
        this.dtSiteVisitDate = dtSiteVisitDate;
    }

    public String getStrRequesterName() {
        return strRequesterName;
    }

    public void setStrRequesterName(String strRequesterName) {
        this.strRequesterName = strRequesterName;
    }

    public String getStrReqMobileNo() {
        return strReqMobileNo;
    }

    public void setStrReqMobileNo(String strReqMobileNo) {
        this.strReqMobileNo = strReqMobileNo;
    }

    public String getStrReqEmailId() {
        return strReqEmailId;
    }

    public void setStrReqEmailId(String strReqEmailId) {
        this.strReqEmailId = strReqEmailId;
    }

    public String getDtSiteVisitDate1() {
        return dtSiteVisitDate1;
    }

    public void setDtSiteVisitDate1(String dtSiteVisitDate1) {
        this.dtSiteVisitDate1 = dtSiteVisitDate1;
    }

    public Integer getIntIsDelated() {
        return intIsDelated;
    }

    public void setIntIsDelated(Integer intIsDelated) {
        this.intIsDelated = intIsDelated;
    }

}
