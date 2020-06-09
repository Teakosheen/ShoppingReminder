package com.example.myshoppingreminder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductHolder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewDetail;
    private TextView textViewPriority;

    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.text_view_name);
        textViewDetail = itemView.findViewById(R.id.text_view_detail);
        textViewPriority = itemView.findViewById(R.id.text_view_priority);
    }

    public ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ProductHolder(itemView);
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public TextView getTextViewDetail() {
        return textViewDetail;
    }

    public TextView getTextViewPriority() {
        return textViewPriority;
    }

    public void setTextViewName(TextView textViewName) {
        this.textViewName = textViewName;
    }

    public void setTextViewDetail(TextView textViewDetail) {
        this.textViewDetail = textViewDetail;
    }

    public void setTextViewPriority(TextView textViewPriority) {
        this.textViewPriority = textViewPriority;
    }
}