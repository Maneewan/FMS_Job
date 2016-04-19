package maneewan.fms_job;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static String mainhttp = "http://172.27.14.94";
    GridView gridView;
    GridViewActivity gridViewHome;
    String[] event_no, event_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start grid view
        gridView = (GridView) findViewById(R.id.GridViewCustom);
        // Create the Custom Adapter Object
        gridViewHome = new GridViewActivity(this);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewHome);
        // Handling touch/click Event on GridView Item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

                if (position == 0) {
                    Intent i = new Intent(MainActivity.this, FeedNewsActivity.class);
                    startActivity(i);

                }
                if (position == 1) {
                    Intent i = new Intent(MainActivity.this, CompanyActivity.class);
                    startActivity(i);

                }
                if (position == 2) {
                    Intent i = new Intent(MainActivity.this, JobActivity.class);
                    startActivity(i);

                }
                if (position == 3) {
                    Intent i = new Intent(MainActivity.this, LayoutActivity.class);
                    startActivity(i);

                }
                if (position == 4) {
                    Intent i = new Intent(MainActivity.this, PollActivity.class);
                    startActivity(i);

                }

                if (position == 5) {
                    finish();

                }
            }
        });
    }
}


