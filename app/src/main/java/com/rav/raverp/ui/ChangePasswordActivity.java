package com.rav.raverp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.rav.raverp.MyApplication;
import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.ArrowBackPressed;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.data.model.api.ApiResponse;
import com.rav.raverp.data.model.api.LoginModel;
import com.rav.raverp.data.model.local.ChangePasswordModel;
import com.rav.raverp.databinding.ActivityChangePasswordBinding;
import com.rav.raverp.network.ApiClient;
import com.rav.raverp.network.ApiHelper;
import com.rav.raverp.utils.CommonUtils;
import com.rav.raverp.utils.NetworkUtils;
import com.rav.raverp.utils.ViewUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangePasswordActivity extends BaseActivity implements ArrowBackPressed {


    private static final String TAG = ChangePasswordActivity.class.getSimpleName();
    private Context mContext = ChangePasswordActivity.this;
    private ActivityChangePasswordBinding binding;
    private String oldPassword, newPassword, confirmPassword;
    private ApiHelper apiHelper;
    private LoginModel login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = putContentView(R.layout.activity_change_password);
        binding.setChangePasswordActivity(this);
        setToolbarTitle("Change Password");
        showBackArrow();
        setArrowBackPressed(this);



        apiHelper = ApiClient.getClient().create(ApiHelper.class);
        binding.oldPasswordEditText
                .addTextChangedListener(new MyTextWatcher(binding.oldPasswordEditText));
        binding.newPasswordEditText
                .addTextChangedListener(new MyTextWatcher(binding.newPasswordEditText));
        binding.confirmPasswordEditText
                .addTextChangedListener(new MyTextWatcher(binding.confirmPasswordEditText));
        login = MyApplication.getLoginModel();
        binding.setLogin(login);
    }

    private void submitForm() {
        if (!validateOldPassword()) {
            return;
        }

        if (!validateNewPassword()) {
            return;
        }

        if (!validateConfirmPassword()) {
            return;
        }

        checkNetwork();

    }

    public void checkNetwork() {
        if (NetworkUtils.isNetworkConnected()) {
            changePassword();
        } else {
            ViewUtils.showOfflineDialog(mContext, new DialogActionCallback() {
                @Override
                public void okAction() {
                    checkNetwork();
                }
            });
        }
    }

    private void changePassword() {
        binding.setLoaderVisibility(true);
        binding.setButtonVisibility(false);

        String loginid=login.getStrLoginID();
        Integer role=login.getIntRoleID();
        Call<ApiResponse<List<ChangePasswordModel>>> ChangePasswordCall= apiHelper.UpdateChangePassword(loginid,oldPassword,newPassword,confirmPassword,role);
        ChangePasswordCall.enqueue(new Callback<ApiResponse<List<ChangePasswordModel>>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<List<ChangePasswordModel>>> call,
                                   @NonNull Response<ApiResponse<List<ChangePasswordModel>>> response) {
                binding.setLoaderVisibility(false);
                binding.setButtonVisibility(true);
                if (response.isSuccessful()) {

                    if (response!=null){
                        if (response.body().getResponse().equalsIgnoreCase("Failure")){
                            ViewUtils.showErrorDialog(mContext,response.body().getMessage(),
                                    new DialogActionCallback() {
                                        @Override
                                        public void okAction() {


                                        }
                                    });

                        }else{

                            ViewUtils.showSuccessDialog(mContext,response.body().getMessage(),
                                    new DialogActionCallback() {
                                        @Override
                                        public void okAction() {

                                            Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });


                        }
                    }

                }else{


                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<List<ChangePasswordModel>>> call,
                                  @NonNull Throwable t) {
                if (!call.isCanceled()) {
                    binding.setLoaderVisibility(false);
                    binding.setButtonVisibility(true);
                    ViewUtils.showToast(t.getLocalizedMessage());
                }
                t.printStackTrace();
            }
        });
    }

    private boolean validateOldPassword() {
        oldPassword = binding.oldPasswordEditText.getText().toString().trim();
        if (CommonUtils.isNullOrEmpty(oldPassword)) {
            binding.oldPasswordInputLayout.setError(getString(R.string.error_field_required));
            requestFocus(binding.oldPasswordEditText);
            return false;
        } else {
            binding.oldPasswordInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateNewPassword() {
        newPassword = binding.newPasswordEditText.getText().toString().trim();
        if (CommonUtils.isNullOrEmpty(newPassword)) {
            binding.newPasswordInputLayout.setError("Please Enter New Password.");
            requestFocus(binding.newPasswordEditText);
            return false;
        } if (!CommonUtils.isPasswordValid(newPassword)) {
            binding.newPasswordInputLayout.setError(getString(R.string.invalid_password));
            requestFocus(binding.newPasswordEditText);
            return false;
        } else {
            binding.newPasswordInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }

    private boolean validateConfirmPassword() {
        confirmPassword = binding.confirmPasswordEditText.getText().toString().trim();
        if (CommonUtils.isNullOrEmpty(confirmPassword)) {
            binding.confirmPasswordInputLayout.setError("Please Retype New Password");
            requestFocus(binding.confirmPasswordEditText);
            return false;
        } else if (!newPassword.equalsIgnoreCase(confirmPassword)) {
            binding.confirmPasswordInputLayout.setError(getString(R.string.error_password_mismatching));
            requestFocus(binding.confirmPasswordEditText);
            return false;
        } else {
            binding.confirmPasswordInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void arrowBackPressed() {
        onBackPressed();
    }

    public void onClickChangePassword(View view) {
        submitForm();
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (view.getId()) {
                case R.id.old_password_edit_text:
                    validateOldPassword();
                    break;
                case R.id.new_password_edit_text:
                    validateNewPassword();
                    break;
                case R.id.confirm_password_edit_text:
                    validateConfirmPassword();
                    break;
            }
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.old_password_edit_text:
                    validateOldPassword();
                    break;
                case R.id.new_password_edit_text:
                    validateNewPassword();
                    break;
                case R.id.confirm_password_edit_text:
                    validateConfirmPassword();
                    break;
            }
        }
    }

}
