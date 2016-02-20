package tech.gzone.onlinetaxi;

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

public class LoginActivity extends AppCompatActivity {

    String username;
    String password;
    InputStream is;
    Database d= new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText temp1= (EditText) findViewById(R.id.userid);
        EditText temp2 = (EditText) findViewById(R.id.password);
        TextView error= (TextView) findViewById(R.id.error);

        password = temp2.getText().toString();
        username = temp1.getText().toString();
        MainActivity m1 = new MainActivity();
        if(d.validateUNPW(username, password)){
            m1.login=true;

        }
        else{
            error.setText("Incorrect Username or Password");
        }

    }
}
