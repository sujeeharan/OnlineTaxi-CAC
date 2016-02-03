package tech.gzone.onlinetaxi;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * Created by Sujeeharan on 03-Feb-16.
 */
public class Database {

    InputStream is=null;
    String line=null;
    String result="";
    int code=0;

    public Database(){}


    public void insert(String uplatitude,String uplongitude,String uplocationid) {

        ArrayList<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();

        //-------------this is the place update to database
        nameValuePairs.add(new BasicNameValuePair("latitude",uplatitude));
        nameValuePairs.add(new BasicNameValuePair("longitude",uplongitude));
        nameValuePairs.add(new BasicNameValuePair("locationid",uplocationid));



        try{


            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://adminpanel.gzone.tech/newlocation.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");

            //   /home/sujeeharan/public_html/www/newlocation.php
        }
        catch (Exception e){


            Log.e("Fail 1", e.toString());
            // Toast.makeText(getApplicationContext(), "Invalid ip Address", Toast.LENGTH_SHORT).show();


        }
        try
        {
            BufferedReader reader=new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb=new StringBuilder();
            while ((line=reader.readLine())!=null)
            {
                sb.append(line + "\n");



            }

            is.close();
            result=sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch (Exception e){

            Log.e("Fail 2", e.toString());
        }

        try
        {

            JSONObject jsnobject = new JSONObject(result);

            code=(jsnobject.getInt("code"));



            if(code==1)
            {
                ///Toast.makeText(getBaseContext(), "Inserted Successfully",
                //   Toast.LENGTH_SHORT).show();
            }
            else
            {
                // Toast.makeText(getBaseContext(), "Sorry, Try Again",
                //   Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e){

            Log.e("Fail 3", e.toString());
        }
    }
}
