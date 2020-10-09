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
import com.rav.raverp.data.model.api.PlotAvailableModel;
import com.rav.raverp.databinding.ItemPlotAvailableBinding;

import java.util.List;


public class PlotAvailableListAdapter extends RecyclerView.Adapter<PlotAvailableListAdapter.MyViewHolder> {

    private static final String TAG = PlotAvailableModel.class.getSimpleName();
    private ListItemClickListener listItemClickListener;
    private List<PlotAvailableModel> plotAvailables;
    private Context context;

    public PlotAvailableListAdapter(Context context,
                                    ListItemClickListener listItemClickListener, List<PlotAvailableModel> plotAvailables) {
        this.context = context;
        this.plotAvailables = plotAvailables;
        this.listItemClickListener = listItemClickListener;
        if (null != plotAvailables && plotAvailables.size() > 0) {
            Log.d(TAG, "No. of Plot List : " + plotAvailables.size());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlotAvailableBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_plot_available, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (null != plotAvailables && plotAvailables.size() > position) {
            holder.getBinding().setPlotAvailable(plotAvailables.get(position));
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
        if (null != plotAvailables && plotAvailables.size() > 0) {
            return plotAvailables.size();
        } else {
            return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemPlotAvailableBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemPlotAvailableBinding getBinding() {
            return binding;
        }
    }

    public List<PlotAvailableModel> getPlotAvailables() {
        return plotAvailables;
    }

}

