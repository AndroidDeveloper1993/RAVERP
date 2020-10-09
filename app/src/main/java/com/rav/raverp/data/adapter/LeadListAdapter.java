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
import com.rav.raverp.databinding.ItemLeadListBinding;

import java.util.List;


public class LeadListAdapter extends RecyclerView.Adapter<LeadListAdapter.MyViewHolder> {

    private static final String TAG = LeadListModel.class.getSimpleName();
    private ListItemClickListener listItemClickListener;
    private List<LeadListModel> leadListModels;
    private Context context;

    public LeadListAdapter(Context context,
                           ListItemClickListener listItemClickListener, List<LeadListModel> leadListModelList) {
        this.context = context;
        this.leadListModels = leadListModelList;
        this.listItemClickListener = listItemClickListener;
        if (null != leadListModelList && leadListModelList.size() > 0) {
            Log.d(TAG, "No. of Lead List : " + leadListModelList.size());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemLeadListBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_lead_list, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (null != leadListModels && leadListModels.size() > position) {
            holder.getBinding().setLeadListModel(leadListModels.get(position));
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
        if (null != leadListModels && leadListModels.size() > 0) {
            return leadListModels.size();
        } else {
            return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemLeadListBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemLeadListBinding getBinding() {
            return binding;
        }
    }

    public List<LeadListModel> getLeadListModel() {
        return leadListModels;
    }

}

