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

public class LoginActivity extends AppCompatActivity {

    private EditText phoneNumberEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }

    public void Login(View view){

        String phone,password;
        phone = phoneNumberEditText.getText().toString();
        password = passwordEditText.getText().toString();

        String type="login";
        if(isInternetConnected()) {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, phone, password);
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
        phoneNumberEditText = (EditText) findViewById(R.id.login_phone);
        passwordEditText = (EditText) findViewById(R.id.login_password);
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
