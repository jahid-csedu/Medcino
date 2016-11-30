package com.example.jahid.medcino;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by jahid on 11/29/2016.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    private Context context;
    private ProgressDialog pDialog;
    private String type,pharmacyPhone;
    public BackgroundWorker(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        type=strings[0];
        if(type.equals("signup")){
            String pharmacyName = strings[1];
            String address = strings[2];
            String ownerName = strings[3];
            String registration = strings[4];
            pharmacyPhone = strings[5];
            String password = strings[6];
            String signupURL = "http://bdacademichelp.com/medcino/signup.php";
            try{
                URL url=new URL(signupURL);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("pharmacy_name","UTF-8")+"="+URLEncoder.encode(pharmacyName,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("owner_name","UTF-8")+"="+URLEncoder.encode(ownerName,"UTF-8")+"&"+
                        URLEncoder.encode("registration_no","UTF-8")+"="+URLEncoder.encode(registration,"UTF-8")+"&"+
                        URLEncoder.encode("phone_no","UTF-8")+"="+URLEncoder.encode(pharmacyPhone,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(type.equals("login")){
            pharmacyPhone = strings[1];
            String password = strings[2];
            String loginURL = "http://bdacademichelp.com/medcino/login.php";
            try{
                URL url=new URL(loginURL);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=  URLEncoder.encode("phone_no","UTF-8")+"="+URLEncoder.encode(pharmacyPhone,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("patient_register")){
            String patientPhone = strings[1];
            String doctorName = strings[2];
            String medicineName = strings[3];
            String nextDate = strings[4];
            String pharmacyPhone = strings[5];
            String medicine1 = strings[6];
            String medicine2 = strings[7];
            String medicine3 = strings[8];
            String medicine4 = strings[9];
            String medicine5 = strings[10];
            String patientURL = "http://bdacademichelp.com/medcino/patient_register.php";
            try{
                URL url=new URL(patientURL);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("patient_phone","UTF-8")+"="+URLEncoder.encode(patientPhone,"UTF-8")+"&"+
                        URLEncoder.encode("doctor_name","UTF-8")+"="+URLEncoder.encode(doctorName,"UTF-8")+"&"+
                        URLEncoder.encode("medicine_name","UTF-8")+"="+URLEncoder.encode(medicineName,"UTF-8")+"&"+
                        URLEncoder.encode("medicine_name1","UTF-8")+"="+URLEncoder.encode(medicine1,"UTF-8")+"&"+
                        URLEncoder.encode("medicine_name2","UTF-8")+"="+URLEncoder.encode(medicine2,"UTF-8")+"&"+
                        URLEncoder.encode("medicine_name3","UTF-8")+"="+URLEncoder.encode(medicine3,"UTF-8")+"&"+
                        URLEncoder.encode("medicine_name4","UTF-8")+"="+URLEncoder.encode(medicine4,"UTF-8")+"&"+
                        URLEncoder.encode("medicine_name5","UTF-8")+"="+URLEncoder.encode(medicine5,"UTF-8")+"&"+
                        URLEncoder.encode("next_date","UTF-8")+"="+URLEncoder.encode(nextDate,"UTF-8")+"&"+
                        URLEncoder.encode("pharmacy_phone","UTF-8")+"="+URLEncoder.encode(pharmacyPhone,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        pDialog.dismiss();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Status");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        if (type.equals("signup")){
            if (s.contains("success")) {
                SharedPreferences status = context.getSharedPreferences("Status", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = status.edit();
                editor1.putString("STATUS", "Logged In");
                editor1.commit();

                SharedPreferences sharedPreferences = context.getSharedPreferences("pharmacy_phone", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pharmacy_phone", pharmacyPhone);
                editor.commit();

                alertDialogBuilder.setMessage(R.string.signup_success)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(context,PatientRegisterActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                context.startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            } else if (s.contains("account exists")) {
                alertDialogBuilder.setMessage(R.string.account_exists);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }else if(s.contains("failed")){
                alertDialogBuilder.setMessage(R.string.signup_failed);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }else if(type.equals("login")){
            if(s.contains("success")){
                SharedPreferences status = context.getSharedPreferences("Status", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = status.edit();
                editor1.putString("STATUS", "Logged In");
                editor1.commit();

                SharedPreferences sharedPreferences = context.getSharedPreferences("pharmacy_phone", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pharmacy_phone", pharmacyPhone);
                editor.commit();

                alertDialogBuilder.setMessage(R.string.login_success)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(context,PatientRegisterActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                context.startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }else if(s.contains("failed")){
                alertDialogBuilder.setMessage(R.string.login_failed);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        }else if(type.equals("patient_register")){
            if(s.contains("success")){

                alertDialogBuilder.setMessage(R.string.patient_success);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }else if(s.contains("failed")){
                alertDialogBuilder.setMessage(R.string.signup_failed);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }
}
