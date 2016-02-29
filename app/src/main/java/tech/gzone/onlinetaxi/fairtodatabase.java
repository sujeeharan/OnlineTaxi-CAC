package tech.gzone.onlinetaxi;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sujeeharan on 25-Feb-16.
 */
public class fairtodatabase {
    InputStream is = null;
    String line = null;
    String result = "";
    static int firstkm;
    static int extrakm;
    static int waiting;
    int code = 0;

    public fairtodatabase() {
    }

    public void insertfair() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        //-------------this is the place update to database
        //   nameValuePairs.add(new BasicNameValuePair("latitude",uplatitude));
        //   nameValuePairs.add(new BasicNameValuePair("longitude",uplongitude));
        //  nameValuePairs.add(new BasicNameValuePair("locationid",uplocationid));

        try {
            /*
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://adminpanel.gzone.tech/mobileapp/fair_cal.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            */

            String link = "http://adminpanel.gzone.tech/mobileapp/fair_cal.php";
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
            Log.e("pass 1_1", "connection success ");
            //   /home/sujeeharan/public_html/www/newlocation.php
        } catch (Exception e) {
            Log.e("Fail 1_1", e.toString());
            // Toast.makeText(getApplicationContext(), "Invalid ip Address", Toast.LENGTH_SHORT).show();
        }
        try {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                break;
            }

            is.close();
            result = sb.toString();
            Log.e("pass 2_2", result);
        } catch (Exception e) {

            Log.e("Fail 2_2", e.toString());
        }

        try {

            JSONObject jsnobject = new JSONObject(result);

            //code=(jsnobject.getInt("code"));
            this.firstkm = (jsnobject.getInt("firstkm"));
            this.extrakm = (jsnobject.getInt("extrakm"));
            this.waiting = (jsnobject.getInt("waiting"));
            Log.e("Pass 3_3", +firstkm + "");
            ///Toast.makeText(getBaseContext(), "Inserted Successfully",


        } catch (Exception e) {

            Log.e("Fail 3_3", e.toString());
        }
    }

    public int check_firstkm() {

        return this.firstkm;
    }

    public int check_extrakm() {
        return this.extrakm;
    }

    public int check_waiting() {
        return this.waiting;
    }


}
