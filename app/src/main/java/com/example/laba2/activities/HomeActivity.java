package com.example.laba2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.laba2.R;
import com.example.laba2.adapters.RecyclerViewAdapter;
import com.example.laba2.model.Civilization;

import java.util.List;

public class HomeActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

//Создаем recyclerview, парсим json и настраиваем recyclerview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerviewid);

        List<Civilization> list = Singleton.getInstance().getItems();

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

}
