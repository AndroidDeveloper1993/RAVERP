package com.rav.raverp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.google.gson.Gson;
import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.data.local.prefs.PrefsHelper;
import com.rav.raverp.data.model.api.ApiResponse;
import com.rav.raverp.data.model.api.LoginModel;
import com.rav.raverp.databinding.ActivityLoginBinding;
import com.rav.raverp.network.ApiClient;
import com.rav.raverp.network.ApiHelper;
import com.rav.raverp.utils.AppConstants;
import com.rav.raverp.utils.CommonUtils;
import com.rav.raverp.utils.NetworkUtils;
import com.rav.raverp.utils.ScreenUtils;
import com.rav.raverp.utils.ViewUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Context mContext = LoginActivity.this;
    private ActivityLoginBinding binding;
    private LoginModel loginModel;
    private ApiHelper apiHelper;
    private  String LoginId, Password;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLoginActivity(this);
        binding.setButtonVisibility(true);
        binding.setLoaderVisibility(false);
        ScreenUtils.setupUI(binding.parentLayout, LoginActivity.this);
        loginModel = new LoginModel();
        binding.setLogin(loginModel);
        apiHelper = ApiClient.getClient().create(ApiHelper.class);


    }

    public void checkNetwork() {
        if (NetworkUtils.isNetworkConnected()) {
            loginExecute();
        } else {
            ViewUtils.showOfflineDialog(mContext, new DialogActionCallback() {
                @Override
                public void okAction() {
                    checkNetwork();
                }
            });
        }
    }

    private void loginExecute() {
        binding.setLoaderVisibility(true);
        binding.setButtonVisibility(false);
        Call<ApiResponse<List<LoginModel>>> loginCall = apiHelper.userLogin(binding.loginid.getText().toString(),binding.password.getText().toString());
        loginCall.enqueue(new Callback<ApiResponse<List<LoginModel>>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<List<LoginModel>>> call,
                                   @NonNull Response<ApiResponse<List<LoginModel>>> response) {
                binding.setLoaderVisibility(false);
                binding.setButtonVisibility(true);
                if (response.isSuccessful()) {

                    if (response!=null){
                        if (response.body().getResponse().equalsIgnoreCase("Failure")){
                            ViewUtils.showErrorDialog(mContext,response.body().getMessage(),
                                    new DialogActionCallback() {
                                        @Override
                                        public void okAction() {
                                            binding.password.requestFocus();

                                        }
                                    });

                        }else{

                            List<LoginModel> login=response.body().getBody();

                            String role=login.get(0).getStrRole();
                            String email=login.get(0).getStrEmail();
                            String mobile=login.get(0).getStrPhone();
                            String name=login.get(0).getStrDisplayName();
                            String profile=login.get(0).getStrProfilePic();

                            Gson gson=new Gson();
                            String json=gson.toJson(login.get(0), LoginModel.class);
                            PrefsHelper.putString(mContext, AppConstants.LOGIN,
                                    json);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            String welcome = getString(R.string.welcome);
                            ViewUtils.showToast(welcome);

                        }
                    }

                }else{


                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<List<LoginModel>>> call,
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

    public void onClickLogin(View view) {
        submitForm();
    }

    public void onClickForgotPassword(View view) {
        //Intent intent = new Intent(mContext, ForgotPasswordActivity.class);
       // startActivity(intent);
    }
   private void submitForm() {
        if (!validateLoginId()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        checkNetwork();
    }



    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.loginid:
                   validateLoginId();
                    break;
                case R.id.password:
                   validatePassword();
                    break;
            }
        }
    }





    private boolean validateLoginId() {
        LoginId = binding.loginid.getText().toString().trim();
        if (CommonUtils.isNullOrEmpty(LoginId)) {
            binding.loginid.setError(getString(R.string.error_field_required));
            requestFocus(binding.loginid);
            return false;
        } else

            return true;
    }
    private boolean validatePassword() {
        Password = binding.password.getText().toString().trim();
        if (CommonUtils.isNullOrEmpty(Password)) {
            binding.password.setError(getString(R.string.error_field_required));
            requestFocus(binding.password);
            return false;
        } else

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
}

