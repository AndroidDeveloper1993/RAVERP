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
import com.rav.raverp.data.model.api.FollowUpListModel;
import com.rav.raverp.data.model.api.LeadListModel;
import com.rav.raverp.databinding.ItemFollowUpLeadListBinding;
import com.rav.raverp.databinding.ItemLeadListBinding;

import java.util.List;


public class FollowUPLeadListAdapter extends RecyclerView.Adapter<FollowUPLeadListAdapter.MyViewHolder> {

    private static final String TAG = FollowUpListModel.class.getSimpleName();
    private ListItemClickListener listItemClickListener;
    private List<FollowUpListModel> followUpListModels;
    private Context context;

    public FollowUPLeadListAdapter(Context context,
                                   ListItemClickListener listItemClickListener, List<FollowUpListModel> followUpListModelList) {
        this.context = context;
        this.followUpListModels = followUpListModelList;
        this.listItemClickListener = listItemClickListener;
        if (null != followUpListModelList && followUpListModelList.size() > 0) {
            Log.d(TAG, "No. of Follow UP Lead List : " + followUpListModelList.size());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemFollowUpLeadListBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_follow_up_lead_list, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (null != followUpListModels && followUpListModels.size() > position) {
            holder.getBinding().setFollowUpListModel(followUpListModels.get(position));
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
        if (null != followUpListModels && followUpListModels.size() > 0) {
            return followUpListModels.size();
        } else {
            return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemFollowUpLeadListBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemFollowUpLeadListBinding getBinding() {
            return binding;
        }
    }

    public List<FollowUpListModel> getFollowUpListModel() {
        return followUpListModels;
    }

}

