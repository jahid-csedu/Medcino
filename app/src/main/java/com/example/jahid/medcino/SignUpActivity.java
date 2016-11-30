package com.example.jahid.medcino;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText pharmacyNameEditText,addressEditText,ownerNameEditText,registrationEditText,phoneEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initialize();
    }

    public void signUp(View view){
        String pharmacyName = pharmacyNameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String ownerName = ownerNameEditText.getText().toString();
        String registration = registrationEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SharedPreferences pharmacyPhone = getSharedPreferences("pharmacy_phone", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pharmacyPhone.edit();
        editor.putString("pharmacy_phone", phone);
        editor.commit();

        String type="signup";
        if(isInternetConnected()) {
            if(phone.length()!=11){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Warning!!");
                alertDialogBuilder
                        .setCancelable(false)
                        .setMessage(R.string.wrong_phone)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }else {
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, pharmacyName, address, ownerName, registration, phone, password);
            }
        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning!!");
            alertDialogBuilder
                    .setCancelable(false)
                    .setMessage(R.string.internet)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void initialize(){
        pharmacyNameEditText = (EditText) findViewById(R.id.pharmacyNameEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        ownerNameEditText = (EditText) findViewById(R.id.ownerNameEditText);
        registrationEditText = (EditText) findViewById(R.id.registrationEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    private boolean isInternetConnected() {
        // get Connectivity Manager object to check connection
        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            return true;
        } else
            return false;
    }
}
