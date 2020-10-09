package com.rav.raverp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rav.raverp.R;

import com.rav.raverp.data.interfaces.ArrowBackPressed;
import com.rav.raverp.data.model.api.LeadListModel;
import com.rav.raverp.databinding.ActivityLeadListDetailsBinding;

public class LeadListActivityDetails extends BaseActivity  implements ArrowBackPressed {
    private static final String TAG = LeadListActivityDetails.class.getSimpleName();
    private Context mContext = LeadListActivityDetails.this;
    private ActivityLeadListDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=putContentView(R.layout.activity_lead_list_details);
        setToolbarTitle("Lead List");
        showBackArrow();
        setArrowBackPressed(this);


        getIntentData();
    }


    @SuppressLint("ResourceAsColor")
    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.hasExtra("leadlist")) {
            LeadListModel leadListModel =
                    (LeadListModel) intent.getSerializableExtra("leadlist");
            binding.setLeadListModel(leadListModel);

            if (leadListModel.getIntIsDelated() == 0) {
                binding.status.setText("Active");
                binding.status.setTextColor(R.color.red);


            } else if (leadListModel.getIntIsDelated() == 1) {

                binding.status.setText(" IN Active");
                binding.status.setTextColor(R.color.green);

            }


            if (leadListModel.getIntChangeLeadStatus() == 1) {
                binding.action.setText("Assigned");
                binding.action.setTextColor(R.color.red);
                binding.actionLayout.setVisibility(View.GONE);
                binding.action.setVisibility(View.VISIBLE);
                binding.assigntootherLayout.setVisibility(View.GONE);
                binding.assigntoother.setVisibility(View.VISIBLE);
                binding.assigntoother.setText("Lead Changed To Another");


            } else if (leadListModel.getIntChangeLeadStatus() == 0) {


                binding.action.setVisibility(View.GONE);
                binding.actionLayout.setVisibility(View.VISIBLE);
                binding.assigntootherLayout.setVisibility(View.VISIBLE);
                binding.assigntoother.setVisibility(View.GONE);

            }


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

}


