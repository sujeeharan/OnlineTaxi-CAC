package tech.gzone.onlinetaxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText temp1= (EditText) findViewById(R.id.userid);
        EditText temp2 = (EditText) findViewById(R.id.password);
        TextView error= (TextView) findViewById(R.id.error);

        password = temp2.getText().toString();
        username = temp1.getText().toString();
        MainActivity m1=new MainActivity();
        if(validateUNPW(username,password)){
            m1.login=true;
        }
        else{
            error.setText("Incorrect Username or Password");
        }

    }

    boolean validateUNPW(String username,String password){


        return true;
    }
}
