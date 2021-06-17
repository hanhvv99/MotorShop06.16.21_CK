package com.example.motorshop.activity.warranty.act;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Customer;
import com.example.motorshop.datasrc.MotorDetail;
import com.example.motorshop.helper.Helper;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VerifyRequestActivity extends AppCompatActivity {
    private ImageView ImgVProduct;
    private TextView frameNums;
    private EditText EditTextName, EditTextIdentityId, EditTextPhone, EditTextAddress, EditTextNotes;
    private Button btnSendInfo;

    public static Helper h = new Helper();
    String getUsr;
    String numberFr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numberFr = getFrameNumsIntent();
        getUsr = getIntent().getStringExtra("user");

        getControl();
        getEvent();

        frameNums.setText(numberFr);
        getValues(getUsr);
    }

    public String getFrameNumsIntent() {
        return getIntent().getStringExtra("content");
    }

    private void getControl() {
        ImgVProduct = (ImageView) findViewById(R.id.ImgVProduct);
        frameNums = (TextView) findViewById(R.id.frameNums);
        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextIdentityId = (EditText) findViewById(R.id.EditTextIdentityId);
        EditTextPhone = (EditText) findViewById(R.id.EditTextPhone);
        EditTextAddress = (EditText) findViewById(R.id.EditTextAddress);
        EditTextNotes = (EditText) findViewById(R.id.EditTextNotes);
        btnSendInfo = (Button) findViewById(R.id.btnSendInfo);
    }

    private void getEvent() {
        btnSendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                alerLogError();
            }
        });
    }

    private void getValues(String userPhone) {
        String name = getIntent().getStringExtra("name");
        String identityId = getIntent().getStringExtra("identityId");
        String address = getIntent().getStringExtra("address");
        String phone = getIntent().getStringExtra("phone");
        System.out.println(name+","+
                identityId+","+
                address+","+
                phone);
        EditTextName.setText(name);
        EditTextIdentityId.setText(identityId);
        EditTextAddress.setText(address);
        EditTextPhone.setText(phone);
    }

//    public void getValuesCustomer(String userPhone){
//        ArrayList<Customer> customers = new ArrayList<>();
//
//        String UrlMotorDetails = "http://192.168.11.5:8080/api/motorshop/customers";
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest objectRequest = new StringRequest(
//                Request.Method.GET, UrlMotorDetails,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("RestResponse",response.toString());
//                        JSONArray jsonArray = null;
//                        try {
//                            jsonArray = new JSONArray(response.toString());
//                            for (int i=0;i< jsonArray.length();i++){
//                                JSONObject object = jsonArray.getJSONObject(i);
//                                Customer c = new Customer();
//                                c.setId(object.get("id").toString());
//                                c.setId(object.get("identityId").toString());
//                                c.setId(object.get("name").toString());
//                                c.setId(object.get("address").toString());
//                                c.setId(object.get("phone").toString());
//                                c.setId(object.get("passWord").toString());
//                                c.setId(object.get("createdDate").toString());
//                                customers.add(c);
//                            }
//                            for (Customer ctm : customers){
//                                if(ctm.getPhone().contains(userPhone)){
//                                    EditTextName.setText(ctm.getName());
//                                    EditTextIdentityId.setText(ctm.getIdentityId());
//                                    EditTextPhone.setText(ctm.getPhone());
//                                    EditTextAddress.setText(ctm.getAddress());
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("ErrorResponse",error.toString());
//                    }
//                }
//        );
//        requestQueue.add(objectRequest);
//    }


    public void alerLogError(){
        new AlertDialog.Builder(this)
                .setMessage("Quý khách vui lòng kiểm tra thông tin, nếu sai sót vui lòng gọi đến hotline 19000606!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"send",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                startActivity(new Intent(getApplicationContext(), TabActivity.class));
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}