package com.rav.raverp.data.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.ListItemClickListener;
import com.rav.raverp.data.model.api.CustomerListModel;
import com.rav.raverp.data.model.api.LeadListModel;
import com.rav.raverp.databinding.ItemCustomerListBinding;
import com.rav.raverp.databinding.ItemLeadListBinding;

import java.util.List;


public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {

    private static final String TAG = CustomerListModel.class.getSimpleName();
    private ListItemClickListener listItemClickListener;
    private List<CustomerListModel> customerListModels;
    private Context context;

    public CustomerListAdapter(Context context,
                               ListItemClickListener listItemClickListener, List<CustomerListModel> customerListModelList) {
        this.context = context;
        this.customerListModels = customerListModelList;
        this.listItemClickListener = listItemClickListener;
        if (null != customerListModelList && customerListModelList.size() > 0) {
            Log.d(TAG, "No. of Customer List : " + customerListModelList.size());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCustomerListBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_customer_list, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (null != customerListModels && customerListModels.size() > position) {
            holder.getBinding().setCustomerListModel(customerListModels.get(position));
            holder.getBinding().sno.setText(String.valueOf(position+1));
            holder.getBinding().showMoreTextView.setPaintFlags(
                    holder.getBinding().showMoreTextView.getPaintFlags() |
                            Paint.UNDERLINE_TEXT_FLAG);
            holder.getBinding().showMoreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClickListener.onItemClicked(position);
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        if (null != customerListModels && customerListModels.size() > 0) {
            return customerListModels.size();
        } else {
            return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemCustomerListBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemCustomerListBinding getBinding() {
            return binding;
        }
    }

    public List<CustomerListModel> getCustomerListModel() {
        return customerListModels;
    }

}

