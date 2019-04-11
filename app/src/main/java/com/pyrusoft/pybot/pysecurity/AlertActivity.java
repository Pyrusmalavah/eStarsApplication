package com.pyrusoft.pybot.pysecurity;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlertActivity extends AppCompatActivity {



    String urladdress = "http://192.168.0.164/pySecurity/alerts.php";
    String[] a_title;
    String[] a_location;
    String[] a_timedate;
    String[] a_imagepath;
    ListView a_listView;
    BufferedInputStream bufferedInputStream;
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        getSupportActionBar().setTitle("Alerts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        a_listView = (ListView) findViewById(R.id.alert_lview);


        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        fetchAlerts();
        AlertListView alertListView = new AlertListView(this, a_title, a_location, a_timedate, a_imagepath);

        a_listView.setAdapter(alertListView);

    }

    private void fetchAlerts() {

        //connection
        try {

            URL url = new URL(urladdress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            bufferedInputStream = new BufferedInputStream(con.getInputStream());

        } catch (Exception x) {
            x.printStackTrace();
        }
//content
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            bufferedInputStream.close();
            result = stringBuilder.toString();

        } catch (Exception x) {
            x.printStackTrace();
        }
//JSON
        try {
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = null;
            a_location = new String[jsonArray.length()];
            a_timedate = new String[jsonArray.length()];
            a_title = new String[jsonArray.length()];
            a_imagepath = new String[jsonArray.length()];

            for (int i = 0; i <= jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                a_location[i] = jsonObject.getString("p_address");
                a_timedate[i] = jsonObject.getString("alert_datetime");
                a_title[i] = jsonObject.getString("alert_title");
                a_imagepath[i] = jsonObject.getString("alert_image");

            }


        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}
