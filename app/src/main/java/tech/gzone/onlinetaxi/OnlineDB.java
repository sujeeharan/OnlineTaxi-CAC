package tech.gzone.onlinetaxi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sujeeharan on 23-Feb-16.
 */
public class OnlineDB extends AsyncTask<String,Void,Void> {

    private int flag;
    private Context context;
    private Video video;
    private BannerAd bannerAd;

    public OnlineDB (int flag){
        this.flag=flag;

    }

    @Override
    protected Void doInBackground(String... params) {
        //addcount to Video
        flag=Integer.parseInt(params[1]);
        if(flag==1) {
            String videoid=params[0];
            Toast.makeText(context,"FLAG 1 Count Video",Toast.LENGTH_SHORT);
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
                AlertDialog.Builder a_builder = new AlertDialog.Builder(context);
                a_builder.setMessage("Adding Count FAILED").
                        setCancelable(false).
                        setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                            }
                        });
            }
        }
        //Add count to Banner
        else if(flag == 2){
            String bannerid=params[0];

            Toast.makeText(context,"FLAG 2 Count Banner",Toast.LENGTH_SHORT);
        }
        else if(flag==3){

        }
        return null;
    }
}
