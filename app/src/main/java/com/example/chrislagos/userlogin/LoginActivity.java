package com.example.chrislagos.userlogin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginActivity extends AsyncTask{
    private Context context;
    private final String[] SPECIAL_CHARACTERS = {"!","@","#","$","%","^","&","*"};

    public LoginActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
    }

    private String passwordValidation(String password){
        if(password.length() < 5 || password.length() > 15){
            return "Password must be between 5 and 14 characters long.";
        }
        else if( password.equals(password.toLowerCase()))
        {
            return "Password must contain at least one Uppercase letter.";
        }
        else if(password.equals(password.toUpperCase())){
            return "Password must contain at least one Lowercase letter.";
        }
        for(int i = 0;i<10;i++){
            if(password.contains(i +"")){
                for(String specialChar:SPECIAL_CHARACTERS){
                    if(password.contains(specialChar)){
                        return null;
                    }
                }
                return "Password must contain at least one Special Character.";
            }
        }
        return "Password must contain at least one Number.";
    }

    @Override
    protected String doInBackground(Object... arg0) {
        try{
            String signUpUserName = (String)arg0[0];
            String signUpPassword = (String)arg0[1];

            String test = passwordValidation(signUpPassword);

            if (test != null) {
                return test;
            }

            String link="http://password.myprevio.us/validate_login.php";
            String data  = URLEncoder.encode("signup-username", "UTF-8") + "=" +
                    URLEncoder.encode(signUpUserName, "UTF-8");
            data += "&" + URLEncoder.encode("signup-password", "UTF-8") + "=" +
                    URLEncoder.encode(signUpPassword, "UTF-8");
            data += "&" + URLEncoder.encode("signup-button", "UTF-8") + "=" +
                    URLEncoder.encode("true", "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
             StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            return sb.toString();
        } catch(Exception e){
            String test = e.getMessage();
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Object obj){
        Toast.makeText(context, (String) obj, Toast.LENGTH_LONG).show();
    }

}