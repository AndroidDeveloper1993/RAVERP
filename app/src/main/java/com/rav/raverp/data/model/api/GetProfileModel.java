package com.rav.raverp.data.model.api;


import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rav.raverp.R;

public class GetProfileModel implements Serializable
    {

            @SerializedName("strDisplayName")
            @Expose
            private String strDisplayName;
            @SerializedName("strRole")
            @Expose
            private String strRole;
            @SerializedName("strEmail")
            @Expose
            private String strEmail;
            @SerializedName("strProfilePic")
            @Expose
            private String strProfilePic;
            @SerializedName("strPhone")
            @Expose
            private String strPhone;
            private final static long serialVersionUID = -3092074316829977119L;

            public String getStrDisplayName() {
                return strDisplayName;
            }

            public void setStrDisplayName(String strDisplayName) {
                this.strDisplayName = strDisplayName;
            }

            public String getStrRole() {
                return strRole;
            }

            public void setStrRole(String strRole) {
                this.strRole = strRole;
            }

            public String getStrEmail() {
                return strEmail;
            }

            public void setStrEmail(String strEmail) {
                this.strEmail = strEmail;
            }

            public String getStrProfilePic() {
                return strProfilePic;
            }

            public void setStrProfilePic(String strProfilePic) {
                this.strProfilePic = strProfilePic;
            }

            public String getStrPhone() {
                return strPhone;
            }

            public void setStrPhone(String strPhone) {
                this.strPhone = strPhone;
            }

        }