package com.rav.raverp.data.model.api;


import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class GetProjectModel implements Serializable
    {

        @SerializedName("intProjectId")
        @Expose
        private Integer intProjectId;
        @SerializedName("strProjectName")
        @Expose
        private String strProjectName;
        private final static long serialVersionUID = 2124828025515222685L;

        public Integer getIntProjectId() {
            return intProjectId;
        }

        public void setIntProjectId(int intProjectId) {
            this.intProjectId = intProjectId;
        }

        public String getStrProjectName() {
            return strProjectName;
        }

        public void setStrProjectName(String strProjectName) {
            this.strProjectName = strProjectName;
        }

        @Override
        public String toString() {
            return strProjectName;
        }

    }

