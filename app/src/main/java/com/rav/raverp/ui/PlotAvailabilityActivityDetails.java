package com.rav.raverp.ui;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.rav.raverp.R;

import com.rav.raverp.data.interfaces.ArrowBackPressed;
import com.rav.raverp.data.model.api.PlotAvailableModel;
import com.rav.raverp.databinding.ActivityPlotAvailabilityActivityDetailsBinding;

public class PlotAvailabilityActivityDetails extends BaseActivity  implements ArrowBackPressed {
    private static final String TAG = PlotAvailabilityActivityDetails.class.getSimpleName();
    private Context mContext = PlotAvailabilityActivityDetails.this;
    private ActivityPlotAvailabilityActivityDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=putContentView(R.layout.activity_plot_availability_activity_details);
        setToolbarTitle("Plot Available");
        showBackArrow();
        setArrowBackPressed(this);


        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.hasExtra("ploatData") ){
            PlotAvailableModel plotAvailable =
                    (PlotAvailableModel) intent.getSerializableExtra("ploatData");
            binding.setPlotAvailable(plotAvailable);
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


