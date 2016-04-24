package maneewan.fms_job;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
    JSONArray jsonArray = null,jsonArrayDetail = null;
    String id_company = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        getLayout();
        //start grid view
        gridView = (GridView) findViewById(R.id.GridViewLayout);
        gridView.setBackgroundColor(Color.WHITE);

        // Create the Custom Adapter Object
        gridViewHome = new GridLayout(this);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewHome);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                for(int i = 0;i < jsonArray.length(); i++) {
                    if(position==i)
                    {
                        try {
                            id_company = jsonArray.getJSONObject(i).getString("id_company");
                            getDetailCompany();
                            String job = "";
                            for(int j = 0; j < jsonArrayDetail.length(); j++){
                                if(j == 0) job += jsonArrayDetail.getJSONObject(j).getString("job_name");
                                else job += ","+jsonArrayDetail.getJSONObject(j).getString("job_name");
                            }
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LayoutActivity.this);
                            alertDialogBuilder.setMessage("บริษัท : " + jsonArrayDetail.getJSONObject(0).getString("company_name") +
                                    "\n" + "กลุ่มบริษัท : " + jsonArrayDetail.getJSONObject(0).getString("company_group") +
                                    "\n" + "ตำแหน่งงาน : " + job );


                            alertDialogBuilder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public JSONArray getLayout() {
        InputStream is = null;
        String result = null;

        try {
            System.out.println("+++++++++++ login ++++++++++");
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_company/company_layout");
            DefaultHttpClient httpclient = new DefaultHttpClient();

            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();
            result = sb.toString();

            try {
                jsonArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println("++++++++++++++++Main+++++++++++++++++ " + jsonArray.length());
            System.out.println("++++++++++++++++toString+++++++++++++++++ " + jsonArray.toString());

        } catch (ClientProtocolException e) {
            Log.e("ConnectServer", e.toString());

        } catch (IOException e) {
            Log.e("ConnectServer", e.toString());
        }
        return jsonArray;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            Intent i;
            i = new Intent(LayoutActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getDetailCompany() {
        InputStream is = null;
        String result = null;

        try {
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_company/layout_detail");
            DefaultHttpClient httpclient = new DefaultHttpClient();

            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("id_company", id_company));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();
            result = sb.toString();

            try {
                jsonArrayDetail = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (ClientProtocolException e) {
            Log.e("ConnectServer", e.toString());

        } catch (IOException e) {
            Log.e("ConnectServer", e.toString());
        }
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
