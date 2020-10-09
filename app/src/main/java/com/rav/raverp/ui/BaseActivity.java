package com.rav.raverp.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.android.material.snackbar.Snackbar;
import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.ArrowBackPressed;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.data.interfaces.StoragePermissionListener;
import com.rav.raverp.databinding.ActivityBaseBinding;
import com.rav.raverp.utils.AppConstants;
import com.rav.raverp.utils.ViewUtils;


public class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();
    private ActivityBaseBinding binding;
    private Context mContext = BaseActivity.this;
    private StoragePermissionListener storagePermissionListener;
    private ArrowBackPressed arrowBackPressed;

    public void setStoragePermissionListener(StoragePermissionListener storagePermissionListener) {
        this.storagePermissionListener = storagePermissionListener;
    }

    public void setArrowBackPressed(ArrowBackPressed arrowBackPressed) {
        this.arrowBackPressed = arrowBackPressed;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        binding.setBaseActivity(this);

        setSupportActionBar(binding.toolbar);
    }

    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId) {
        return DataBindingUtil.inflate(getLayoutInflater(), resId,
                binding.viewForm, true);
    }

    protected void showBackArrow() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void showProgress(boolean b) {
        ViewUtils.showProgress(b, binding.viewForm, binding.progressBar);
    }

    protected void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        binding.setToolbarTitle(title);
    }

    protected void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }

    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    protected void showMessage(String message) {
        if (message != null) {
            ViewUtils.showToast(message);
        } else {
            ViewUtils.showToast(getString(R.string.some_error));
        }
    }

    protected void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            arrowBackPressed.arrowBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

   protected void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS,
                        AppConstants.STORAGE_PERMISSION_REQUEST);
            } else {
                storagePermissionListener.isStoragePermissionGranted(true);
            }
        } else {
            storagePermissionListener.isStoragePermissionGranted(true);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AppConstants.STORAGE_PERMISSION_REQUEST) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                ViewUtils.showStoragePermissionDialog(this,
                        getString(R.string.allow_storage_permission),
                        getString(R.string.msg_storage_permission_denied_explanation),
                        new DialogActionCallback() {
                            @Override
                            public void okAction() {
                                //checkStoragePermission();
                            }
                        });
            } else {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    storagePermissionListener.isStoragePermissionGranted(true);
                } else {
                    // User selected the Never Ask Again Option
                    ViewUtils.showAlertDialog(this, getString(R.string.need_permission),
                            getString(R.string.go_to_settings_give_permissions),
                            getString(R.string.goto_settings),
                            getString(R.string.action_cancel), new DialogActionCallback() {
                                @Override
                                public void okAction() {
                                    storagePermissionListener.isUserPressedSetting(true);
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package",
                                            BaseActivity.this.getPackageName(), null);
                                    intent.setData(uri);
                                    BaseActivity.this.startActivity(intent);
                                }
                            });
                }
            }
        }
    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null
                && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context,
                        permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}

