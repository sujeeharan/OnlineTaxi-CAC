package tech.gzone.onlinetaxi;

import android.os.AsyncTask;

/**
 * Created by Sujeeharan on 29-Jan-16.
 */
public class Application extends AsyncTask {

        String uplatitude,uplongitude,uplocationid ;
        void UpdateLocation(String puplatitude,String puplongitude,String puplocationid){

            uplatitude=puplatitude;
            uplongitude=puplongitude;
            uplocationid=puplocationid;


        }

        Database c=new Database();

        @Override
        protected Object doInBackground(Object[] params) {

            c.insert(uplatitude,uplongitude,uplocationid);
            return null;
        }

}
