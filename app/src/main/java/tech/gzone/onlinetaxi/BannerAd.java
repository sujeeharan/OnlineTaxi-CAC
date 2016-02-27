package tech.gzone.onlinetaxi;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sujeeharan on 26-Jan-16
 */
public class BannerAd extends AsyncTask{
    private int count;
    private String result;
    private String latitude,longitude;
    public ArrayList<String> bannerid;
    public void addCount( String bannerId){
     //   new OnlineDB(2).execute(bannerId);
    }

    BannerAd(String longitude, String latitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }
/*
    protected Void doInBackground(String[] params) {
        String latitude = params[0];
        String longitude = params[1];
        try {
            String link = "http://adminpanel.gzone.tech/mobileapp/findbanner.php?latitude=" + latitude+"?longitude="+longitude;
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = rd.readLine()) != null) {
                sb.append(line);
                break;
            }
            rd.close();
            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("Failed Reading Data",e.toString());
        }
        try{
            JSONObject banner = new JSONObject(result);
            this.bannerid= banner.getString("bannerid");
            Log.e("Fail",bannerid);
        }
        catch (JSONException e)
        {
            Log.e("Fail", e.toString());
        }

    }
*/
    @Override
    protected Object doInBackground(Object[] params) {
        try {
            String link = "http://adminpanel.gzone.tech/mobileapp/findbanner.php?latitude=" + latitude+"&longitude="+longitude;
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = rd.readLine()) != null) {
                sb.append(line);
                break;
            }
            rd.close();
            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("Failed Reading Data",e.toString());
        }
        try{
            JSONObject banner = new JSONObject(result);
            for (int i=0; i<banner.length(); i++) {
                bannerid.add(banner.getString("bannerid")); //add to arraylist
            }
            Log.e("Pass","asd");
        }
        catch (JSONException e)
        {
            Log.e("Fail reading return", e.toString());
        }
        return null;
    }
}