package tech.gzone.onlinetaxi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sujeeharan on 23-Feb-16.
 */
public class OnlineDB extends AsyncTask {

    private int flag;
    private Context context;
    private Video video;
    private BannerAd bannerAd;
    private Double latitude,longitude;
    private String result;
    private String bannerid;
    private String videoid;

    public OnlineDB (int flag,String videoid){
        this.flag=flag;
        this.videoid=videoid;
    }

    public OnlineDB(int flag,double latitude, double longitude){
        this.longitude=longitude;
        this.latitude=latitude;
        this.flag=flag;
    }
    @Override
    protected Object doInBackground(Object[] params) {

        if(flag==1) {
            //  Toast.makeText(context,"FLAG 1 Count Video",Toast.LENGTH_SHORT);
            try {
                String link = "http://adminpanel.gzone.tech/mobileapp/videocount.php?videoid=" + videoid;
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
                /*
                AlertDialog.Builder a_builder = new AlertDialog.Builder(context);
                a_builder.setMessage("").
                        setCancelable(false).
                        setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                            }
                        });*/
            }
        }

        return null;
    }
}
