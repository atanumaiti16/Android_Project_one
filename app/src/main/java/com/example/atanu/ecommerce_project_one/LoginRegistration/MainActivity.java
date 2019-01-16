package com.example.atanu.ecommerce_project_one.LoginRegistration;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.atanu.ecommerce_project_one.App.LandingActivity;
import com.example.atanu.ecommerce_project_one.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    String  TAG = "MainActivity";

    EditText editTextMobile , editTextPassword;
    Button buttonSignIn, buttonRegister;
    String signin_url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?";
    String password;
    String  Mobile;


    AlertDialog.Builder builder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMobile = findViewById(R.id.et_mobile_S);
        editTextPassword = findViewById(R.id.et_password_S);
        buttonRegister = findViewById(R.id.btn_register_s);
        buttonSignIn = findViewById(R.id.btn_sign_in_s);

        builder = new AlertDialog.Builder(MainActivity.this);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registration_Activity.class);
                startActivity(i);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mobile = editTextMobile.getText().toString();
                password = editTextPassword.getText().toString();

                if((String.valueOf(Mobile)).isEmpty() || password.isEmpty()){
                    builder.setTitle("Error");
                    //builder.setMessage("Please enter Mobile and password");
                    displayAlert("Enter a valid Mobile no and Password");
                }
                else{
                    final StringRequest stringRequest = new StringRequest(Request.Method.POST, signin_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, response );
                            try {
                                JSONArray jsonArray= new JSONArray(response);
                                JSONObject object = jsonArray.getJSONObject(0);

                                String msg = object.getString("msg");
                                String id = object.getString("id");
                                String apiKey = object.getString("appapikey ");

//                                String msg = "success";
//                                String id = "1542";
//                                String apiKey = "0292f23d65020077455e0aa1e2699ee3";



                                Toast.makeText(MainActivity.this, msg+" "+apiKey, Toast.LENGTH_SHORT).show();
                                Log.e(TAG, msg+" "+apiKey );

                                Intent i = new Intent(MainActivity.this, LandingActivity.class);
                                i.putExtra("id", id);
                                i.putExtra("apikey", apiKey);
                                startActivity(i);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, error.getMessage() );

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String , String > params = new HashMap<String, String>();
                            params.put("mobile",Mobile);
                            params.put("password",password);
                            return params;
                        }
                    };

                    MySingleton.getInstance(MainActivity.this).add2requestQueue(stringRequest);
                }



            }
        });
    }

    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editTextMobile.setText("");
                editTextPassword.setText("");

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
