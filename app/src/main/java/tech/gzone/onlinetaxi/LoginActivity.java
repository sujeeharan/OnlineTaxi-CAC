package tech.gzone.onlinetaxi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LoginActivity extends AsyncTask<String,Void,String> {

    String username;
    String password;
    InputStream is;
    private Context context;
    private int byGetOrPost = 0;

    public LoginActivity (Context context,int flag) {
        this.context = context;
        byGetOrPost = flag;
    }

    @Override
    protected String doInBackground(String... arg0) {
        String username = (String)arg0[0];
        String password = (String)arg0[1];
        String link = "http://adminpanel.gzone.tech/mobileapp/login.php?username="+username+"&password="+password;
        try {
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
            return sb.toString();
        }
        catch (Exception e){
            return new String("Exception "+ e.getMessage());
        }

    }
}
