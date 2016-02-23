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

import java.io.InputStream;
import java.util.ArrayList;

public class LoginActivity extends AsyncTask<String,Void,String> {

    String username;
    String password;
    InputStream is;
    private Context context;
    private int byGetOrPost = 0;

    public LoginActivity (Context context,TextView statusField,TextView roleField,int flag) {
        this.context = context;
        byGetOrPost = flag;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
