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
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rav.raverp.R;
import com.rav.raverp.data.adapter.CustomerListAdapter;
import com.rav.raverp.data.adapter.LeadListAdapter;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.data.interfaces.ListItemClickListener;
import com.rav.raverp.data.model.api.ApiResponse;
import com.rav.raverp.data.model.api.CustomerListModel;
import com.rav.raverp.data.model.api.LeadListModel;
import com.rav.raverp.databinding.DialogCustomerListFilterBinding;
import com.rav.raverp.databinding.DialogLeadListFilterBinding;
import com.rav.raverp.network.ApiClient;
import com.rav.raverp.network.ApiHelper;
import com.rav.raverp.ui.CustomerListActivityDetails;
import com.rav.raverp.ui.LeadListActivityDetails;
import com.rav.raverp.utils.ViewUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerListFragment extends Fragment {
    private ApiHelper apiHelper;
    private View view;
    private RecyclerView recyclerCustomerList;
    private CustomerListAdapter customerListAdapter;
    private boolean isDialogHided;
    private Dialog filterDialog;
    private CustomerListModel customerListModel;
    private List<CustomerListModel>  customerListModelList;

    String CustomerName, CustomerEmailId,CustomerMobileNo;


    private ListItemClickListener listItemClickListener = new ListItemClickListener() {
        @Override
        public void onItemClicked(int itemPosition) {
            CustomerListModel customerListModel =
                    customerListAdapter.getCustomerListModel().get(itemPosition);

            Intent intent = new Intent(getActivity(), CustomerListActivityDetails.class);
            intent.putExtra("Customerlist", customerListModel);
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

        view = inflater.inflate(R.layout.activity_recycler_view, container, false);
        apiHelper = ApiClient.getClient().create(ApiHelper.class);
        recyclerCustomerList = view.findViewById(R.id.recycler_view);
        recyclerCustomerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerCustomerList.getRecycledViewPool().clear();

        if (isDialogHided) {
            isDialogHided = false;


            filterDialog.dismiss();
        }
        execute();


        return view;
    }


    private void execute() {

        ViewUtils.startProgressDialog(getActivity());

        Call<ApiResponse<List<CustomerListModel>>> getCustomerListModelcall =
                apiHelper.getCustomerListModel();

        getCustomerListModelcall.enqueue(new Callback<ApiResponse<List<CustomerListModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<CustomerListModel>>> call,
                                   Response<ApiResponse<List<CustomerListModel>>> response) {

                ViewUtils.endProgressDialog();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResponse().equalsIgnoreCase("Success")) {
                           customerListModelList = response.body().getBody();


                            customerListAdapter = new CustomerListAdapter(getActivity(), listItemClickListener,
                                    response.body().getBody());
                            recyclerCustomerList.setAdapter(customerListAdapter);


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<CustomerListModel>>> call, Throwable t) {
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
        final DialogCustomerListFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_customer_list_filter, null, false);
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

        final EditText customername=filterDialog.findViewById(R.id.edit_customer_name);
        final EditText customeremail=filterDialog.findViewById(R.id.edit_customer_email_id);
        final EditText customermobile=filterDialog.findViewById(R.id.edit_customer_mobile_no);
        binding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerName=customername.getText().toString();
                CustomerEmailId=customeremail.getText().toString();
                CustomerMobileNo=customermobile.getText().toString();

                filterDialog.hide();
                isDialogHided = true;

                GetGoCustomerList();


            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            filterDialog.dismiss();
            execute();

            }
        });
    }


    private void GetGoCustomerList() {

        ViewUtils.startProgressDialog(getActivity());

        Call<ApiResponse<List<CustomerListModel>>> CustomerListModel =
                apiHelper.getGoCustomerListModel(CustomerName,CustomerEmailId,CustomerMobileNo);
        CustomerListModel.enqueue(new Callback<ApiResponse<List<CustomerListModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<CustomerListModel>>> call,
                                   Response<ApiResponse<List<CustomerListModel>>> response) {

                ViewUtils.endProgressDialog();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResponse().equalsIgnoreCase("Success")) {
                            customerListModelList = response.body().getBody();
                            customerListAdapter=new CustomerListAdapter(getActivity(),listItemClickListener,customerListModelList);
                            recyclerCustomerList.setAdapter(customerListAdapter);



                        }
                        else{
                            customerListModelList.clear();
                            customerListAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<ApiResponse<List<CustomerListModel>>> call, Throwable t) {
                if (!call.isCanceled()) {

                    ViewUtils.endProgressDialog();

                }
                t.printStackTrace();
            }
        });
    }

}











