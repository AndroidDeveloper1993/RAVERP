package com.rav.raverp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rav.raverp.R;

import com.rav.raverp.data.interfaces.ArrowBackPressed;
import com.rav.raverp.data.model.api.CustomerListModel;
import com.rav.raverp.databinding.ActivityCustomerListDetailsBinding;

public class CustomerListActivityDetails extends BaseActivity  implements ArrowBackPressed {
    private static final String TAG = CustomerListActivityDetails.class.getSimpleName();
    private Context mContext = CustomerListActivityDetails.this;
    private ActivityCustomerListDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=putContentView(R.layout.activity_customer_list_details);
        setToolbarTitle("Customer List");
        showBackArrow();
        setArrowBackPressed(this);


        getIntentData();
    }


    @SuppressLint("ResourceAsColor")
    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.hasExtra("Customerlist")) {
           CustomerListModel customerListModel =
                    (CustomerListModel) intent.getSerializableExtra("Customerlist");
            binding.setCustomerListModel(customerListModel);

            if (customerListModel.getIntIsDelated() == 0) {
                binding.status.setText("Active");
                binding.status.setTextColor(R.color.red);


            } else if (customerListModel.getIntIsDelated() == 1) {

                binding.status.setText(" IN Active");
                binding.status.setTextColor(R.color.green);

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


