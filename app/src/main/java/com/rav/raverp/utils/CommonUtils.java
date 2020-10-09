package com.rav.raverp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.provider.Settings;
import android.util.Patterns;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;


public class CommonUtils {
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    // A placeholder email validation check
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    // A placeholder phone validation check
    public static boolean isValidMobile(String phone) {
        if (phone == null) {
            return false;
        }
        if (phone.length() == 10) {
            return Patterns.PHONE.matcher(phone).matches();
        }
        return false;
    }

    // A placeholder uid validation check
    public static boolean isValidUID(String uid) {
        if (uid == null) {
            return false;
        }
        return uid.length() == 14;
    }

    // A placeholder password validation check
    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    // A placeholder password validation check
    public static boolean isValidPassword(String password) {
        return password != null && Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,16})")
                .matcher(password).matches();
    }

    public static boolean isOtpValid(String otp) {
        return otp != null && otp.trim().length() == 6;
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.ENGLISH).format(new Date());
    }

    public static Boolean checkIsNULL(String value) {
        return value.equalsIgnoreCase("null");
    }

    public static boolean isSuccess(String input) {
        if (null == input) {
            return false;
        }
        return AppConstants.STATUS_CODE_SUCCESS.equalsIgnoreCase(input.trim());
    }

    public static boolean isNullOrEmpty(String input) {
        if (null == input) {
            return true;
        }
        return AppConstants.EMPTY.equalsIgnoreCase(input.trim());
    }

    public static String parseDate(String time) {
        /**
         * All Dates are normalized to UTC, it is up the client code to convert to the appropriate TimeZone.
         */
        System.out.println("Date_of_birth === "+ time);
        TimeZone UTC = TimeZone.getTimeZone("UTC");
        String inputPattern = "yyyy-dd-MM'T'hh:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        inputFormat.setTimeZone(UTC);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
        Date date;
        String str = null;
        try {
            date = inputFormat.parse(time);
            if (date != null) {
                str = outputFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getExt(String filePath){
        int strLength = filePath.lastIndexOf(".");
        if(strLength > 0)
            return filePath.substring(strLength + 1).toLowerCase();
        return null;
    }


    /**
     * Get IP address from first non-localhost interface
     * @param useIPv4   true=return ipv4, false=return ipv6
     * @return  address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) { } // for now eat exceptions
        return "";
    }
}
