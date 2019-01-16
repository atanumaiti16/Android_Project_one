package com.example.atanu.ecommerce_project_one.App;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.atanu.ecommerce_project_one.LoginRegistration.MainActivity;
import com.example.atanu.ecommerce_project_one.LoginRegistration.MySingleton;
import com.example.atanu.ecommerce_project_one.R;
import com.example.atanu.ecommerce_project_one.subCategoryFragment.SubCategoryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandingActivity extends AppCompatActivity implements LandingRecyclerViewAdapter.OnItemClickListener {
    String TAG = "Landing_Activity";


    RecyclerView recyclerView;
    String producut_url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php";

    private List<MyPojo> list_data;
    private LandingRecyclerViewAdapter landingRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<>();
        landingRecyclerViewAdapter =new LandingRecyclerViewAdapter(list_data,this);



        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String apiKey = intent.getStringExtra("apikey");


        StringRequest stringRequest = new StringRequest(Request.Method.POST, producut_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray= null;
                Log.e(TAG, response );

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray  array=jsonObject.getJSONArray("category");

                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);
                        MyPojo category_items=new MyPojo(ob.getString("cname"),
                                ob.getString("cdiscription"),ob.getString("cimagerl"),ob.getString("cid"));
                        list_data.add(category_items);
                        recyclerView.setAdapter(landingRecyclerViewAdapter);
                        //mExampleAdapter.setOnItemClickListener(MainActivity.this);
                        landingRecyclerViewAdapter.setOnItemClickListener(LandingActivity.this);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage() );

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String > params = new HashMap<String, String>();
                params.put("api_key",apiKey);
                params.put("user_id",id);
                return params;
            }
        };

        MySingleton.getInstance(LandingActivity.this).add2requestQueue(stringRequest);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);



        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(LandingActivity.this, SubCategoryActivity.class);
        startActivity(i);

    }
}
