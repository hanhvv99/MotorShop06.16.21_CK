package com.example.motorshop.activity.warranty.act;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Customer;
import com.example.motorshop.datasrc.Motor;
import com.example.motorshop.datasrc.MotorDetail;
import com.example.motorshop.datasrc.MotorInfo;
import com.example.motorshop.helper.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChooseSPActivity extends AppCompatActivity {
    private EditText frameNumber;
    private Button btnAddSP;
    private final Helper h = new Helper();
    String getUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_s_p);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControl();
        getEvent();
        getUsr = getIntent().getStringExtra("user");
        System.out.println(getUsr);

    }

    private void getControl() {
        frameNumber = (EditText) findViewById(R.id.frameNumber);
        btnAddSP = (Button) findViewById(R.id.btnAddSP);
    }

    public boolean isValidAddFrameNumber(String frameNum) {
        if (isNull(frameNum)) {
            Toast.makeText(getApplicationContext(), "FrameNumber must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (frameNum.length() < 7 || frameNum.length() > 17) {
            Toast.makeText(getApplicationContext(), "FrameNumber's length is in range of 7 & 17!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isNull(String frameNumber) {
        return h.isNull(frameNumber);
    }

    private void getEvent() {
        btnAddSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = frameNumber.getText().toString();
                if(isValidAddFrameNumber(input)){

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    getValuesInMotorDetail(input);
                }
                else {
                    Toast.makeText(getApplicationContext(),"FrameNumber's error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getValuesInMotorDetail(String input){
        ArrayList<MotorDetail> motorDetails = new ArrayList<>();

        String UrlMotorDetails = "http://192.168.11.5:8080/api/motorshop/motorDetails";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest objectRequest = new StringRequest(
                Request.Method.GET, UrlMotorDetails,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse",response.toString());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response.toString());
                            for (int i=0;i< jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                MotorDetail md = new MotorDetail();
                                md.setId(Integer.parseInt(object.get("id").toString()));
                                md.setMotorId(Integer.parseInt(object.get("motorId").toString()));
                                md.setMotorInfoId(Integer.parseInt(object.get("motorInfoId").toString()));
                                md.setContent(object.get("content").toString());
                                motorDetails.add(md);
                            }
                            for (MotorDetail md : motorDetails){
                                if(md.getContent().contains(input)){
                                    if(md.getMotorInfoId() == 1) {
                                        alerLog(input);
                                    } else{
                                        frameNumber.setText("");
                                        Toast.makeText(getApplicationContext(),"Không có dữ liệu",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse",error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }

    public void alerLog(String input){
        new AlertDialog.Builder(this)
                .setMessage("Số khung quý khách đã nhập có trên hệ thống. Kiểm tra lại chắc chắn số khung này nếu quý khách muốn yếu cầu bảo hành thì hãy bấm vào 'Yêu cầu' bên dưới.")
                .setPositiveButton("Yêu cầu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), VerifyRequestActivity.class);
                        intent.putExtra("content",input);
                        getValuesCustomer(intent);
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    public void getValuesCustomer(Intent intent){
        ArrayList<Customer> customers = new ArrayList<>();

        String UrlMotorDetails = "http://192.168.11.5:8080/api/motorshop/customers";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest objectRequest = new StringRequest(
                Request.Method.GET, UrlMotorDetails,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse",response.toString());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response.toString());
                            for (int i=0;i< jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Customer c = new Customer();
                                c.setId(object.get("id").toString());
                                c.setIdentityId(object.get("identityId").toString());
                                c.setName(object.get("name").toString());
                                c.setAddress(object.get("address").toString());
                                c.setPhone(object.get("phone").toString());
                                c.setPassWord(object.get("passWord").toString());
                                c.setCreatedDate(object.get("createdDate").toString());
                                customers.add(c);
                            }
                            for (Customer ctm : customers){
                                if(ctm.getPhone().contains(getUsr)){
                                    intent.putExtra("user",getUsr);
                                    intent.putExtra("name",ctm.getName());
                                    intent.putExtra("identityId",ctm.getIdentityId());
                                    intent.putExtra("address",ctm.getAddress());
                                    intent.putExtra("phone",ctm.getPhone());

                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse",error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }

    private void getFrameNumberAndSentInfoCustomer(){
        String URL = "http://192.168.11.5:8080/api/motorshop/motorDetails/getFrameNumber/idmotorInfo?idmotorInfo="+ 1 +"&phone="+getUsr;

    }
}