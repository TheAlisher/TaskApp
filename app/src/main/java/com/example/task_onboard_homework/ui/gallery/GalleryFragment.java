package com.example.task_onboard_homework.ui.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_onboard_homework.R;

import java.io.File;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private final int REQUEST_CODE = 101;
    private FilesAdapter adapter;
    private ArrayList<File> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_gallery);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FilesAdapter(list);
        recyclerView.setAdapter(adapter);
        getPermissions();
    }

    private void getPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            getFiles();
        } else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            getPermissions();
        }
    }

    private void getFiles() {
        File folder = new File(Environment.getExternalStorageDirectory(), "DCIM/Camera");
        /*if (folder.exists()) folder.mkdir();*/
        for (File file : folder.listFiles()) {
            Log.e("anim", "file = " + file.getName());
            list.add(file);
            adapter.notifyDataSetChanged();
        }
    }
}
