package tech.gzone.onlinetaxi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sujeeharan on 29-Jan-16.
 */
public class Application extends AsyncTask {

    String uplatitude, uplongitude, upvehicleno;

    Application(String puplatitude, String puplongitude, String pupvehicleno) {
        uplatitude = puplatitude;
        uplongitude = puplongitude;
        upvehicleno = pupvehicleno;
    }

    @Override
    protected Void doInBackground(Object... arg0) {
        Context context = (Context) arg0[2];
        try {
            String username = (String) arg0[0];
            String password = (String) arg0[1];
            String vehicleid=(String) arg0[2];
            String link = "http://adminpanel.gzone.tech/mobileapp/location.php?longitude=" + username + "&latitude=" + password + "&vehicleid"+vehicleid;

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
        }
        catch (Exception e) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(context);
            a_builder.setMessage("Login Failed").
                    setCancelable(false).
                    setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                        }
                    });
        }
        return null;
        //Not Sure
    }

}