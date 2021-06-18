package com.example.motorshop.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.act.TabActivity;
import com.example.motorshop.activity.warranty.act.WarrantyActivity;
import com.example.motorshop.datasrc.Main;
import com.example.motorshop.datasrc.Warranty;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imvMain;
    TextView tvMain;
    GridView gvMain;

    ArrayList<Main> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        imvMain = (ImageView) findViewById(R.id.imvMain);
        tvMain = (TextView) findViewById(R.id.tvMain);
        gvMain = (GridView) findViewById(R.id.gvMain);
    }

    private void setEvent() {
        MainAdapter adapter = new MainAdapter(this, R.layout.item_main, itemList);
        gvMain.setAdapter(adapter);
        initItemList();
        adapter.notifyDataSetChanged();

        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                }
                if(position == 1){

                }
                if(position == 2){

                }
                if(position == 3){
                    String getUsr = getIntent().getStringExtra("user");
                    String getPss = getIntent().getStringExtra("pass");
                    String getID = getIntent().getStringExtra("id");
                    if(getID.contains("ST")){
                        /*More*/receiveInfoLoginAndSent(new Intent(getApplicationContext(), WarrantyActivity.class));
                    }else {
                        receiveInfoLoginAndSent(new Intent(getApplicationContext(), TabActivity.class));
                    }

                }
                if(position == 4){

                }
                if(position == 5){

                }
            }
        });

    }

    private void initItemList(){
        itemList.add(new Main(R.drawable.brand, getResources().getString(R.string.brands)));
        itemList.add(new Main(R.drawable.product, getResources().getString(R.string.products)));
        itemList.add(new Main(R.drawable.bill, getResources().getString(R.string.bills)));
        itemList.add(new Main(R.drawable.guarantee, getResources().getString(R.string.guarantees)));
        itemList.add(new Main(R.drawable.statistic, getResources().getString(R.string.statistics)));
        itemList.add(new Main(R.drawable.people, getResources().getString(R.string.people)));
    }

    /*More*/
    public void receiveInfoLoginAndSent(Intent intent){
        String getUsr = getIntent().getStringExtra("user");
        String getPss = getIntent().getStringExtra("pass");
        String getID = getIntent().getStringExtra("id");
        System.out.println(getID);
        intent.putExtra("user",getUsr);
        intent.putExtra("pass",getPss);
        intent.putExtra("id",getID);
        startActivity(intent);
    }
}