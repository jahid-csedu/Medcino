package com.example.jahid.medcino;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PatientRegisterActivity extends AppCompatActivity {

    private EditText patientPhoneEditText,doctorNameEditText,medicineNameEditText,nextDateEditText;
    private EditText medicineEditText[] = new EditText[5];
    int count=0;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        initialize();
        final TextView moreMedicine = (TextView) findViewById(R.id.addmedicineTextView);
        moreMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count==5){
                    moreMedicine.setVisibility(View.GONE);
                }
                medicineEditText[count-1].setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.patient_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.patient_contacts){
            Intent intent = new Intent(getApplicationContext(),Contacts.class);
            startActivity(intent);
        }if(itemId==R.id.patient_about){
            Intent intent = new Intent(getApplicationContext(),About.class);
            startActivity(intent);
        }if(itemId==R.id.logout){
            SharedPreferences status = getSharedPreferences("Status", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = status.edit();
            editor1.putString("STATUS", "Logged Out");
            editor1.commit();

            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);

            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void Sumbit(View view){

        SharedPreferences sp=getSharedPreferences("pharmacy_phone",Context.MODE_PRIVATE);

        String patientPhone = patientPhoneEditText.getText().toString();
        String doctorName = doctorNameEditText.getText().toString();
        String medicineName = medicineNameEditText.getText().toString();
        String nextDate = nextDateEditText.getText().toString();
        String pharmacyPhone=sp.getString("pharmacy_phone","");
        String medicine[] = new String[5];
        for (int i=0;i<5;i++){
            medicine[i] = medicineEditText[i].getText().toString();
        }
        String type="patient_register";
        if(isInternetConnected()) {
            if (patientPhone.length() != 11) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Warning!!");
                alertDialogBuilder
                        .setCancelable(false)
                        .setMessage(R.string.wrong_phone)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            } else{
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, patientPhone, doctorName, medicineName, nextDate, pharmacyPhone, medicine[0], medicine[1], medicine[2], medicine[3], medicine[4]);
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
        patientPhoneEditText = (EditText) findViewById(R.id.patientPhoneEditText);
        doctorNameEditText = (EditText) findViewById(R.id.doctorNameEditText);
        medicineNameEditText = (EditText) findViewById(R.id.medicineNameEditText);
        nextDateEditText = (EditText) findViewById(R.id.nestDateEditText);
        medicineEditText[0] = (EditText) findViewById(R.id.medicine1EditText);
        medicineEditText[1] = (EditText) findViewById(R.id.medicine2EditText);
        medicineEditText[2] = (EditText) findViewById(R.id.medicine3EditText);
        medicineEditText[3] = (EditText) findViewById(R.id.medicine4EditText);
        medicineEditText[4] = (EditText) findViewById(R.id.medicine5EditText);
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
