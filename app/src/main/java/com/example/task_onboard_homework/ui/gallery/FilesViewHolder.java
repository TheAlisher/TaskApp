package com.example.task_onboard_homework.ui.gallery;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_onboard_homework.R;

import java.io.File;

public class FilesViewHolder extends RecyclerView.ViewHolder {

    private TextView textGallery;

    public FilesViewHolder(@NonNull View itemView) {
        super(itemView);
        textGallery = itemView.findViewById(R.id.text_gallery);
    }

    public void onBind(File data) {
        textGallery.setText(data.getName());
    }
}
