package com.rav.raverp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rav.raverp.MyApplication;
import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.ArrowBackPressed;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.data.model.api.ApiResponse;
import com.rav.raverp.data.model.api.EditEmailModel;
import com.rav.raverp.data.model.api.EditMobileModel;
import com.rav.raverp.data.model.api.FollowUpListModel;
import com.rav.raverp.data.model.api.FollowUpModel;
import com.rav.raverp.data.model.api.GetProfileModel;
import com.rav.raverp.data.model.api.LeadListModel;
import com.rav.raverp.data.model.api.LoginModel;
import com.rav.raverp.databinding.ActivityFollowUpLeadListDetailsBinding;
import com.rav.raverp.databinding.ActivityLeadListDetailsBinding;
import com.rav.raverp.databinding.DialogAddFollowupBinding;
import com.rav.raverp.databinding.DialogEditMobileNoProfileBinding;
import com.rav.raverp.network.ApiClient;
import com.rav.raverp.network.ApiHelper;
import com.rav.raverp.ui.fragment.Associate.FollowUpLeadListFragment;
import com.rav.raverp.utils.ViewUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowUPLeadListActivityDetails extends BaseActivity  implements ArrowBackPressed {
    private static final String TAG = FollowUPLeadListActivityDetails.class.getSimpleName();
    private Context mContext = FollowUPLeadListActivityDetails.this;
    private ActivityFollowUpLeadListDetailsBinding binding;
    private LoginModel login;
    private FollowUpListModel followUpList;
    private ApiHelper apiHelper;
    private Dialog filterDialog;
    String Note;
    String FollowUPDate="";
    private FollowUpListModel followUpListModel;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = putContentView(R.layout.activity_follow_up_lead_list_details);
        setToolbarTitle("Follow UP Lead List");
        showBackArrow();
        setArrowBackPressed(this);
        getIntentData();
    }


    @SuppressLint("ResourceAsColor")
    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.hasExtra("followupleadlist")) {
            followUpListModel =
                    (FollowUpListModel) intent.getSerializableExtra("followupleadlist");
            binding.setFollowUpListModel(followUpListModel);

            id = followUpListModel.getBigIntLeadId();
            login = MyApplication.getLoginModel();
            if (followUpListModel.getFollowUpStatus() == 1) {
                binding.action.setText("Success");
                binding.action.setTextColor(R.color.red);
                binding.action.setVisibility(View.VISIBLE);
                binding.followUp.setVisibility(View.GONE);




            } else if (followUpListModel.getFollowUpStatus() == 0) {


               binding.action.setVisibility(View.GONE);
               binding.followUp.setVisibility(View.VISIBLE);

            }


            binding.setFollowUpListModel(followUpListModel);
            apiHelper = ApiClient.getClient().create(ApiHelper.class);


        }
        binding.followUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFilterDialogAddFollowUp();
            }
        });
    }

    private void showFilterDialogAddFollowUp() {
        filterDialog = new Dialog(this);
        final DialogAddFollowupBinding binding = DataBindingUtil.inflate(LayoutInflater.from((this)),
                R.layout.dialog_add_followup, null, false);
        filterDialog.setContentView(binding.getRoot());
        filterDialog.setCancelable(true);
        filterDialog.setCanceledOnTouchOutside(true);


        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 70);
        Window window = filterDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(inset);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        filterDialog.show();



        binding.followUpDate.setText(FollowUPDate);

        binding.btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.dismiss();
            }
        });

        binding.imgcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.dismiss();
            }
        });

        binding.btnsubmit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Note = binding.note.getText().toString();
                AddFollowUp();


            }
        });

        binding.followUpDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                filterDialog.dismiss();
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(FollowUPLeadListActivityDetails.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                FollowUPDate=dayOfMonth+"/"+monthOfYear+"/"+year;
                                showFilterDialogAddFollowUp();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });



    }



    private void AddFollowUp() {

        String loginid = login.getStrLoginID();

        showProgress(true);
        Call<FollowUpModel> getFollowUpModelCall = apiHelper.getFollowUpModel(loginid, String.valueOf(id), Note, FollowUPDate);
        getFollowUpModelCall.enqueue(new Callback<FollowUpModel>() {
            @Override
            public void onResponse(@NonNull Call<FollowUpModel> call,
                                   @NonNull Response<FollowUpModel> response) {
                showProgress(false);
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equalsIgnoreCase("Success")) {

                                   filterDialog.dismiss();

                    } else {

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<FollowUpModel> call,
                                  @NonNull Throwable t) {
                if (!call.isCanceled()) {
                    showProgress(false);
                    ViewUtils.showToast(t.getLocalizedMessage());
                }
                t.printStackTrace();
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void arrowBackPressed() {
        onBackPressed();
    }

}















