package com.rav.raverp.ui.fragment.Associate;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rav.raverp.R;
import com.rav.raverp.data.adapter.PlotAvailableListAdapter;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.data.interfaces.ListItemClickListener;
import com.rav.raverp.data.model.api.ApiResponse;
import com.rav.raverp.data.model.api.GetBlockModel;
import com.rav.raverp.data.model.api.GetProjectModel;
import com.rav.raverp.data.model.api.PlotAvailableModel;
import com.rav.raverp.databinding.DialogPlotFilterBinding;
import com.rav.raverp.network.ApiClient;
import com.rav.raverp.network.ApiHelper;
import com.rav.raverp.ui.PlotAvailabilityActivityDetails;
import com.rav.raverp.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlotAvailabilityFragment extends Fragment {
    private ApiHelper apiHelper, apiHelperlocal;
    private View view;
    private RecyclerView recyclerPloatList;
    private PlotAvailableListAdapter plotAvailableListAdapter;
    private boolean isDialogHided;
    private Dialog filterDialog;
    private Spinner project_name_spinner, block_name_spinner;
    String ProjectId,BlockId;
    TextView  no_records_text_view;


    private ListItemClickListener listItemClickListener = new ListItemClickListener() {
        @Override
        public void onItemClicked(int itemPosition) {
            PlotAvailableModel plotAvailable =
                    plotAvailableListAdapter.getPlotAvailables().get(itemPosition);

            Intent intent = new Intent(getActivity(), PlotAvailabilityActivityDetails.class);
            intent.putExtra("ploatData", plotAvailable);
            startActivity(intent);

        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_recycler_view, container, false);
        apiHelper = ApiClient.getClient().create(ApiHelper.class);

        recyclerPloatList = view.findViewById(R.id.recycler_view);
        no_records_text_view=(TextView)view.findViewById(R.id.no_records_text_view);
        recyclerPloatList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerPloatList.getRecycledViewPool().clear();

        if (isDialogHided) {
            isDialogHided = false;


            filterDialog.dismiss();
        }
        execute();



        return view;
    }

    private void Getblockproject(String ProjectId ,String BlockId) {

        ViewUtils.startProgressDialog(getActivity());

        Call<ApiResponse<List<PlotAvailableModel>>> getPlotAvailabilitylistfilterCall =
                apiHelper.getPlotAvailabilitylistfilter(ProjectId,BlockId);
        getPlotAvailabilitylistfilterCall.enqueue(new Callback<ApiResponse<List<PlotAvailableModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<PlotAvailableModel>>> call,
                                   Response<ApiResponse<List<PlotAvailableModel>>> response) {

                ViewUtils.endProgressDialog();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResponse().equalsIgnoreCase("Success")) {
                            List<PlotAvailableModel> plotAvailableList = response.body().getBody();
                            plotAvailableListAdapter=new PlotAvailableListAdapter(getActivity(),listItemClickListener,plotAvailableList);
                            recyclerPloatList.setAdapter(plotAvailableListAdapter);




                        }
                        else{
                            recyclerPloatList.removeAllViewsInLayout();
                            ViewUtils.showErrorDialog(getContext(), response.body().getMessage(),
                                    new DialogActionCallback() {
                                        @Override
                                        public void okAction() {


                                        }
                                    });


                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<PlotAvailableModel>>> call, Throwable t) {
                if (!call.isCanceled()) {

                    ViewUtils.endProgressDialog();

                }
                t.printStackTrace();
            }
        });
    }




    private void GetProject() {
        Call<ApiResponse<List<GetProjectModel>>> getProjectlistCall =
                apiHelper.getProject();
        getProjectlistCall.enqueue(new Callback<ApiResponse<List<GetProjectModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<GetProjectModel>>> call,
                                   Response<ApiResponse<List<GetProjectModel>>> response) {


                if (response.isSuccessful()) {
                    final List<GetProjectModel> getProjectList = new ArrayList<>();
                    GetProjectModel getProject = new GetProjectModel();
                    getProject.setStrProjectName("--Select Project--");
                    getProject.setIntProjectId(0);
                    getProjectList.add(getProject);
                    getProjectList.addAll(response.body().getBody());
                    ArrayAdapter<GetProjectModel> adapter = new ArrayAdapter<>(getContext(),
                            R.layout.simple_spinner_item, getProjectList);
                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    project_name_spinner.setAdapter(adapter);
                    project_name_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String projectName = parent.getSelectedItem().toString();
                            if (!projectName.equals("--Select Project--")) {
                                int ids=getProjectList.get(position).getIntProjectId();
                                GetBlock(String.valueOf(ids));
                                ProjectId=String.valueOf(ids);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<GetProjectModel>>> call, Throwable t) {
                if (!call.isCanceled()) {

                }
                t.printStackTrace();
            }
        });
    }

    private void GetBlock(String ProjectId) {

        Call<ApiResponse<List<GetBlockModel>>> getBlocklistCall =
                apiHelper.getBlocks(ProjectId);
        getBlocklistCall.enqueue(new Callback<ApiResponse<List<GetBlockModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<GetBlockModel>>> call,
                                   Response<ApiResponse<List<GetBlockModel>>> response) {


                if (response.isSuccessful()) {
                    final List<GetBlockModel> getblockList = new ArrayList<>();
                    GetBlockModel getBlock = new GetBlockModel();
                    getBlock.setStrBlockName("--Select Block--");
                    getBlock.setIntBlockId(0);
                    getblockList.add(getBlock);
                    getblockList.addAll(response.body().getBody());
                    ArrayAdapter<GetBlockModel> adapter = new ArrayAdapter<>(getContext(),
                            R.layout.simple_spinner_item, getblockList);
                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    block_name_spinner.setAdapter(adapter);
                    block_name_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String projectName = parent.getSelectedItem().toString();
                            if (!projectName.equals("--Select Project--")) {
                                int ids=getblockList.get(position).getIntBlockId();
                                BlockId=String.valueOf(ids);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<GetBlockModel>>> call, Throwable t) {
                if (!call.isCanceled()) {

                }
                t.printStackTrace();
            }
        });
    }


    private void execute() {

        ViewUtils.startProgressDialog(getActivity());

        Call<ApiResponse<List<PlotAvailableModel>>> getPlotAvailableCall =
                apiHelper.getPlotAvailable();
        getPlotAvailableCall.enqueue(new Callback<ApiResponse<List<PlotAvailableModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<PlotAvailableModel>>> call,
                                   Response<ApiResponse<List<PlotAvailableModel>>> response) {

                ViewUtils.endProgressDialog();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResponse().equalsIgnoreCase("Success")) {
                            List<PlotAvailableModel> plotAvailableList = response.body().getBody();

                            plotAvailableListAdapter = new PlotAvailableListAdapter(getActivity(), listItemClickListener,
                                    response.body().getBody());
                            recyclerPloatList.setAdapter(plotAvailableListAdapter);


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<PlotAvailableModel>>> call, Throwable t) {
                if (!call.isCanceled()) {

                }
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //On click of option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_filter) {
            if (isDialogHided) {
                filterDialog.show();
                isDialogHided = false;
            } else {
                showFilterDialog();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showFilterDialog() {
        filterDialog = new Dialog(getContext());
        final DialogPlotFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_plot_filter, null, false);
        filterDialog.setContentView(binding.getRoot());
        filterDialog.setCancelable(true);
        filterDialog.setCanceledOnTouchOutside(true);
        project_name_spinner = (Spinner) filterDialog.findViewById(R.id.project_name_spinner);
        block_name_spinner=(Spinner)filterDialog.findViewById(R.id.block_name_spinner);

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
        GetProject();

        binding.GetPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.hide();
                isDialogHided = true;
               Getblockproject(ProjectId,BlockId);


            }
        });
    }


}



