package com.kryptkode.cyberman.djtech;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InformationListActivity extends AppCompatActivity {
    private ListView infoListView;
    private ArrayAdapter infoAdapter;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_list_layout);
        infoListView = (ListView) findViewById(R.id.information_listview);
        infoAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.info_options));
        infoListView.setAdapter(infoAdapter);
        infoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        intent = new Intent(InformationListActivity.this, AppInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1: break;
                    case 2: break;
                    case 3:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.djtech.com.ng"));
                        startActivity(intent);
                        break;

                }
            }
        });
    }
}
