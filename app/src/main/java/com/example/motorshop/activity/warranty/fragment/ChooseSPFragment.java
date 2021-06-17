package com.example.motorshop.activity.warranty.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.act.ChooseSPActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ChooseSPFragment extends Fragment {
    private FloatingActionButton btnAdd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_s_p,
                container, false);

        getControl(view);
        getEvent();
        return view;
    }

    private void getEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
                Toast.makeText(v.getContext(),"Thêm sản phẩm",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getControl(View v) {
        btnAdd = (FloatingActionButton) v.findViewById(R.id.btnAdd);
    }

    public void buttonClicked (View view) {
        String usr = getActivity().getIntent().getStringExtra("user");
        Intent intent = new Intent(view.getContext(), ChooseSPActivity.class);
        intent.putExtra("user",usr);
        startActivity(intent);
    }

    public void getData(String URL, View v){
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        StringRequest objectRequest = new StringRequest(
                Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse",response.toString());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response.toString());
                            for (int i=0;i< jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
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

    private void getDatas(View v) {
        System.out.println("test api");
        String UrlMotor = "http://192.168.1.237:8080/api/motorshop/motors";
        getData(UrlMotor,v);
        String UrlMotorInfos = "http://192.168.1.237:8080/api/motorshop/motorInfos";
        getData(UrlMotorInfos,v);
        String UrlMotorDetails = "http://192.168.1.237:8080/api/motorshop/motorDetails";
        getData(UrlMotorDetails,v);
        String UrlAccessories = "http://192.168.1.237:8080/api/motorshop/accessories";
        getData(UrlAccessories,v);
        String UrlBills = "http://192.168.1.237:8080/api/motorshop/bills";
        getData(UrlBills,v);
        String UrlBillDetails = "http://192.168.1.237:8080/api/motorshop/billDetails";
        getData(UrlBillDetails,v);
        String UrlWarranties = "http://192.168.1.237:8080/api/motorshop/warranties";
        getData(UrlWarranties,v);
        String UrlWarrantydetails = "http://192.168.1.237:8080/api/motorshop/warrantydetails";
        getData(UrlWarrantydetails,v);
    }
}