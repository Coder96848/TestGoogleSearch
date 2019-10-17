package com.example.testgooglesearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testgooglesearch.Models.ResponseModels.ItemResponse;

import java.util.ArrayList;
import java.util.List;

//TODO не знаю, как разобраться с RecyclerView - странное отображение элементов при пролистывании
/**
 * Класс адаптера для RecyclerView
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    private List<ItemResponse> item = new ArrayList<>();

    public void setItem(List<ItemResponse> item) {
        this.item = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.google_result_list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.Bind(item.get(position));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView urlTextView;
        private TextView descriptionTextView;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.resultTitle);
            urlTextView = itemView.findViewById(R.id.resultUrl);
            descriptionTextView = itemView.findViewById(R.id.resultDescription);
        }

        public void Bind(ItemResponse item) {

            titleTextView.setText(item.getTitle());
            urlTextView.setText(item.getFormattedUrl());
            descriptionTextView.setText(item.getSnippet());

        }
    }
}
