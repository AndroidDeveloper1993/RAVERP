package com.rav.raverp.data.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.ListItemClickListener;
import com.rav.raverp.data.model.api.MyGoalListModel;
import com.rav.raverp.databinding.ItemMyGoalListBinding;

import java.util.List;


public class MyGoalListAdapter extends RecyclerView.Adapter<MyGoalListAdapter.MyViewHolder> {

    private static final String TAG = MyGoalListModel.class.getSimpleName();
    private ListItemClickListener listItemClickListener;
    private List<MyGoalListModel> myGoalListModels;
    private Context context;


    public MyGoalListAdapter(Context context,
                             ListItemClickListener listItemClickListener, List<MyGoalListModel> myGoalListModelList) {
        this.context = context;
        this.myGoalListModels = myGoalListModelList;
        this.listItemClickListener = listItemClickListener;
        if (null != myGoalListModelList && myGoalListModelList.size() > 0) {
            Log.d(TAG, "No. of Goal List : " + myGoalListModelList.size());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMyGoalListBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_my_goal_list, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (null != myGoalListModels && myGoalListModels.size() > position) {
            if (position==0){
                holder.getBinding().headerLayout.setVisibility(View.VISIBLE);
                holder.getBinding().contentLayout.setVisibility(View.GONE);
            }else{
                holder.getBinding().headerLayout.setVisibility(View.GONE);
                holder.getBinding().contentLayout.setVisibility(View.VISIBLE);
                holder.getBinding().setMyGoalListModel(myGoalListModels.get(position));
                holder.getBinding().sno.setText(String.valueOf(position));
            }



        }
    }


    @Override
    public int getItemCount() {
        if (null != myGoalListModels && myGoalListModels.size() > 0) {
            return myGoalListModels.size();
        } else {
            return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemMyGoalListBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemMyGoalListBinding getBinding() {
            return binding;
        }
    }

    public List<MyGoalListModel> getMyGoalListModels() {
        return myGoalListModels;
    }

}

