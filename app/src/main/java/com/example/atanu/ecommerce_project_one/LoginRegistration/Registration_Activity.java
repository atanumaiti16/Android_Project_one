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
import com.android.volley.toolbox.StringRequest;
import com.example.atanu.ecommerce_project_one.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration_Activity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity" ;
    EditText editTextFirstName, editTextLastName, editTextPhone, editTextEmail, editTextAddress, editTextPassword;
    Button buttonRegister , buttonSignIn;
    String FirstName, LastName, phone, email, address, password;
    AlertDialog.Builder builder;

    String registration_url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        editTextFirstName = findViewById(R.id.et_firstname_r);
        editTextLastName = findViewById(R.id.et_lastnmame_r);
        editTextEmail = findViewById(R.id.et_email_r);
        editTextAddress = findViewById(R.id.et_address_R);
        editTextPhone = findViewById(R.id.et_mobile_R);
        editTextPassword = findViewById(R.id.et_password_R);

        builder = new AlertDialog.Builder(Registration_Activity.this);

        buttonRegister = findViewById(R.id.btn_register_r);
        buttonSignIn = findViewById(R.id.btn_sign_in_r);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration_Activity.this, MainActivity.class));
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName = editTextFirstName.getText().toString();
                LastName = editTextLastName.getText().toString();
                email = editTextEmail.getText().toString();
                phone = editTextPhone.getText().toString();
                address = editTextAddress.getText().toString();
                password = editTextPassword.getText().toString();

                if (FirstName.isEmpty() || LastName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty()) {

                    builder.setTitle("Alert");
                    builder.setMessage("Please fill all the fields");
                    displayAlert("input error");
                }
                else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, registration_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG,response);

                            if(response == "successfully registered"){
                                Toast.makeText(Registration_Activity.this,
                                        "Thank you for registering with us. Please log in now",Toast.LENGTH_SHORT).show();
                            }
                            else if (response == "Mobile number already exsist"){
                                Toast.makeText(Registration_Activity.this,
                                        "Mobile number already exsist, please try with different number",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d( error.getMessage());
                            Toast.makeText(Registration_Activity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                            Log.e(TAG, error.getMessage());
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String , String > params = new HashMap<String, String>();
                            params.put("fname",FirstName);
                            params.put("lname",LastName);
                            params.put("address",address);
                            params.put("email",email);
                            params.put("mobile",phone);
                            params.put("password",password  );

                            return params;
                        }
                    };

                    MySingleton.getInstance(Registration_Activity.this).add2requestQueue(stringRequest);
                }
            }
        });
    }

    public void displayAlert(final String code){

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("input error")){
                    editTextPassword.setText("");
                    editTextPhone.setText("");

                }
                else if(code.equals("successfully registered")){
                    Toast.makeText(Registration_Activity.this, "registration successful", Toast.LENGTH_SHORT).show();

                }
                else if(code.equals("Mobile number already exist")){
                    Toast.makeText(Registration_Activity.this, "Mobile number already exist", Toast.LENGTH_SHORT).show();

                }


            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }








}
