package com.rav.raverp.data.model.api;


import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class GetBlockModel implements Serializable
    {

        @SerializedName("intBlockId")
        @Expose
        private Integer intBlockId;
        @SerializedName("strBlockName")
        @Expose
        private String strBlockName;
        private final static long serialVersionUID = 5035902471302785666L;

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
        @Override
        public String toString() {
            return strBlockName;
        }

    }

