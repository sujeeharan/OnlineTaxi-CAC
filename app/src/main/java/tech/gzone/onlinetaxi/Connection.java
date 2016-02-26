package tech.gzone.onlinetaxi;

import android.os.AsyncTask;

/**
 * Created by Sujeeharan on 20-Feb-16.
 */
public class Connection extends AsyncTask {
    String uplatitude,uplongitude,upvehicleid ;
    Connection(String puplatitude,String puplongitude,String pupvehicleid){
        uplatitude=puplatitude;
        uplongitude=puplongitude;
        upvehicleid=pupvehicleid;
    }

    gpstodatabase c=new gpstodatabase();
    fairtodatabase b=new fairtodatabase();

    @Override
    protected Object doInBackground(Object[] params) {
        c.insert(uplatitude,uplongitude,upvehicleid);
        b.insertfair();
        return null;
    }
}
