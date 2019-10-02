package com.example.deepmind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.deepmind.data.DataItem;
import com.example.deepmind.data.ItemAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private List<DataItem> itemList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initItems();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

    }

    private void initItems(){
        for(int i=0;i<10;i++){
            DataItem a = new DataItem("test1", R.drawable.cross);
            DataItem b = new DataItem("test2", R.drawable.tick);
            itemList.add(a);
            itemList.add(b);
        }
//
//        JsonObject json = new JsonObject();
//        Gson gson = new Gson();
//        itemList = gson.fromJson(json, new TypeToken<List<DataItem>>() {}.getType());
    }

    
}
