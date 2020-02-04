package com.example.deepmind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.example.deepmind.data.Constants;
import com.example.deepmind.data.DataItem;
import com.example.deepmind.data.ItemAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        List<DataItem> data = initData();
        if(data == null){
            Toast.makeText(getApplicationContext(), "读取文件错误" , Toast.LENGTH_LONG).show();
            finish();
        }
        itemList.addAll(data);

    }

    private List<DataItem> initData(){
        Gson gson = new Gson();

        try{
            AssetManager assetManager = getResources().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(Constants.DATA_FILE)));
            String jsonString = bf.readLine();


            JsonParser parser = new JsonParser();
            JsonArray jsonFileArray = parser.parse(jsonString).getAsJsonArray();
            ArrayList<DataItem> dataItemList = new ArrayList<>();
            for(JsonElement obj : jsonFileArray){
                dataItemList.add(gson.fromJson(obj, DataItem.class));
            }

            return dataItemList;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    
}
