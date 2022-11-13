package com.assignment.temperatureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HistoryActivity extends AppCompatActivity {

    TempAdapter adapter;
    RecyclerView booksRecycler;
    SqlDb sqlDb;
    ImageButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        booksRecycler=findViewById(R.id.tempsRecycler);
        delete=findViewById(R.id.deleteBtn);

        sqlDb=new SqlDb(this);

        adapter=new TempAdapter(sqlDb.getAllHistory(),this);
        booksRecycler.setLayoutManager(new LinearLayoutManager(this));
        booksRecycler.setAdapter(adapter);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDb.deleteAll();
                adapter=new TempAdapter(sqlDb.getAllHistory(),HistoryActivity.this);
                booksRecycler.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                booksRecycler.setAdapter(adapter);

            }
        });


    }


}