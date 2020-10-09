package com.rav.raverp.data.interfaces;


public interface StoragePermissionListener {
    void isStoragePermissionGranted(boolean granted);

    void isUserPressedSetting(boolean pressed);
}
