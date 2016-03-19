package maneewan.fms_job;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LayoutActivity extends AppCompatActivity{
    GridView gridView;
    GridLayout gridViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //start grid view
        gridView = (GridView) findViewById(R.id.GridViewLayout);
        // Create the Custom Adapter Object
        gridViewHome = new GridLayout(this);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewHome);
    }

    public void clickToFeed4(View view) {

        Intent i = new Intent(LayoutActivity.this,FeedNewsActivity.class);
        startActivity(i);
    }
    public void clickToCompany4(View view) {

        Intent i = new Intent(LayoutActivity.this,CompanyActivity.class);
        startActivity(i);
    }
    public void clickToJob4(View view) {

        Intent i = new Intent(LayoutActivity.this,JobActivity.class);
        startActivity(i);
    }
    public void clickToLayout4(View view) {

        Intent i = new Intent(LayoutActivity.this,LayoutActivity.class);
        startActivity(i);
    }
    public void clickToPoll4(View view) {

        Intent i = new Intent(LayoutActivity.this,PollActivity.class);
        startActivity(i);
    }

}
