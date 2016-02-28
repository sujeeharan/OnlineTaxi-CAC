package tech.gzone.onlinetaxi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

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
public class Database extends SQLiteOpenHelper {

    InputStream is=null;
    String line=null;
    String result="";
    int code=0;

    //DATABASE SQLLite
    public static final String DATABASE_NAME= "ceylonaddscompany.db";
    public static final String TABLE_NAME="banner";
    public static final String COL_1= "bannerid";
    public static final String COL_2= "latitude";
    public static final String COL_3= "longitude";


    public void insert(String uplatitude,String uplongitude,String userid) {

        ArrayList<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();

        //-------------this is the place update to database
        nameValuePairs.add(new BasicNameValuePair("latitude",uplatitude));
        nameValuePairs.add(new BasicNameValuePair("longitude",uplongitude));
        nameValuePairs.add(new BasicNameValuePair("userid",userid));

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

    boolean validateUNPW(String username, String password) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        //-------------this is the place update to database
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://adminpanel.gzone.tech/logincheck.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
            //   /home/sujeeharan/public_html/www/newlocation.php
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
            // Toast.makeText(getApplicationContext(), "Invalid ip Address", Toast.LENGTH_SHORT).show();
        }
        try {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        } catch (Exception e) {

            Log.e("Fail 2", e.toString());
        }
        try {
            JSONObject jsnobject = new JSONObject(result);
            code = (jsnobject.getInt("code"));
            if (code == 1) {
                ///Toast.makeText(getBaseContext(), "Inserted Successfully",
                //   Toast.LENGTH_SHORT).show();
            } else {
                // Toast.makeText(getBaseContext(), "Sorry, Try Again",
                //   Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

            Log.e("Fail 3", e.toString());
        }
        return false;
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TABLE_NAME + "(bannerid integer PRIMARY KEY, latitude double, longitude,double,)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addcount(String bannerid){
        ArrayList<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();

        //-------------this is the place update to database
        nameValuePairs.add(new BasicNameValuePair("bannerid",bannerid));

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://adminpanel.gzone.tech/bannermanagement.php");
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
              //  Toast.makeText(getBaseContext(), "Inserted Successfully",
                //  Toast.LENGTH_SHORT).show();
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
