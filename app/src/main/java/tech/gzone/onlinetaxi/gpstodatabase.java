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
 * Created by Sujeeharan on 25-Feb-16.
 */
public class gpstodatabase {
    InputStream is=null;
    String line=null;
    String result="";
    int code=0;

    public gpstodatabase(){}

    public void insert(String uplatitude,String uplongitude,String upvehicleid) {

        ArrayList<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();

        //-------------this is the place update to database
        nameValuePairs.add(new BasicNameValuePair("latitude",uplatitude));
        nameValuePairs.add(new BasicNameValuePair("longitude",uplongitude));
        nameValuePairs.add(new BasicNameValuePair("vehicleid",upvehicleid));

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://adminpanel.gzone.tech/mobileapp/newlocation.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("Pass", "Connection Established ");
        }
        catch (Exception e){
            Log.e("Failed Connecting", e.toString());
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
                break;
            }
            is.close();
            result=sb.toString();
            Log.e("Passed Got Results", result);
        }
        catch (Exception e){

            Log.e("Error Reading Results", e.toString());
        }
        try
        {
            JSONObject jsnobject = new JSONObject(result);
            code=(jsnobject.getInt("code"));
            if(code==1)
            {
                Log.e("Pass 3", "succesfull");
            }
            else
            {
                Log.e("Location Passed", "JSON Received");
            }

        }
        catch (Exception e){

            Log.e("Fail 3",result );
        }
    }
}