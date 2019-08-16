package com.company.mytodowhitfragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FirstFragment extends Fragment {

    private List<User> users;
    private MyRecyclerViewAdapter myRecViewAdapter;
    private RecyclerView recyclerView;
    private final static int REQUEST_CODE = 16;

    public FirstFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = new ArrayList<>();
        myRecViewAdapter = new MyRecyclerViewAdapter(users);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            recyclerView = view.findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                    RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myRecViewAdapter);
        }
        FloatingActionButton floatingActionButton = view.findViewById(R.id.add_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            String importance = "";
            Uri uri = data.getData();
            if (data.getBooleanExtra("high", false)) {
                importance = "high";
            } else if (data.getBooleanExtra("medium", false)) {
                importance = "medium";

            } else if (data.getBooleanExtra("low", false)) {
                importance = "low";

            }
            users.add(new User(name, description, uri, importance));
            myRecViewAdapter.notifyItemInserted(users.size() - 1);
        }
    }

}
