package com.djtech.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.djtech.android.adapters.FAQsAdapter;
import com.djtech.android.utils.DummyClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FAQsActivity extends AppCompatActivity {
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private List<DummyClass> list = new ArrayList<>();;
    private RecyclerView rv;
    private FAQsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqs_layout);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = (RecyclerView) findViewById(R.id.faqs_rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new FAQsAdapter(list);
        rv.setAdapter(adapter);












        try {
            jsonArray = new JSONArray(getJsonFromFile());
            for (int i =0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                DummyClass dummyClass = new DummyClass();
                dummyClass.question = jsonObject.getString("Question");
                dummyClass.answer = jsonObject.getString("Answer");
                list.add(dummyClass);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }


    public String getJsonFromFile(){
        String json;
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.faqs_json);
            int size = inputStream.available();
            byte streamByte [] = new byte[size];
            inputStream.read(streamByte);
            inputStream.close();
            json = new String(streamByte,"UTF-8");
        }
        catch (IOException e){
            e.printStackTrace();
            return  null;
        }
        return json;
    }
}
