package maneewan.fms_job;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PollActivity extends AppCompatActivity {
    Spinner dd1,dd2,dd3,dd4,dd5;
    Button button_ok;
    int sum = 0;
    final Context context = this;
    int[] score ={1,2,3,4,5};
    int score1=0,score2=0,score3=0,score4=0,score5=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        dd1 = (Spinner)findViewById(R.id.dd1);
        dd2 = (Spinner)findViewById(R.id.dd2);
        dd3 = (Spinner)findViewById(R.id.dd3);
        dd4 = (Spinner)findViewById(R.id.dd4);
        dd5 = (Spinner)findViewById(R.id.dd5);
        button_ok = (Button)findViewById(R.id.button_ok);

        List<String> listSpinner = new ArrayList();
        listSpinner.add("ปรับปรุง");
        listSpinner.add("พอใช้");
        listSpinner.add("ปานกลาง");
        listSpinner.add("ดี");
        listSpinner.add("ดีมาก");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dd1.setAdapter(dataAdapter);
        dd2.setAdapter(dataAdapter);
        dd3.setAdapter(dataAdapter);
        dd4.setAdapter(dataAdapter);
        dd5.setAdapter(dataAdapter);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom();
            }
        });
    }

    public void DialogCustom(){
        // custom dialog
        setScore();
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        TextView text = (TextView) dialog.findViewById(R.id.txt_dia);
        text.setText("คะแนนประเมินของคุณ คือ : "+sum+"/25");

        Button btn_yes = (Button) dialog.findViewById(R.id.btn_yes);
        Button btn_no = (Button) dialog.findViewById(R.id.btn_no);
        btn_yes.setText("ยืนยัน");
        btn_no.setText("ยกเลิก");
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream is = null;
                String result = null;
                JSONObject jsonObject = null;
                try {

                    HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_poll/insert_poll");
                    DefaultHttpClient httpclient = new DefaultHttpClient();

                    // Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
                    nameValuePairs.add(new BasicNameValuePair("score1", "" + score1));
                    nameValuePairs.add(new BasicNameValuePair("score2", "" + score2));
                    nameValuePairs.add(new BasicNameValuePair("score3", "" + score3));
                    nameValuePairs.add(new BasicNameValuePair("score4", "" + score4));
                    nameValuePairs.add(new BasicNameValuePair("score5", "" + score5));
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
                        Intent i;
                        i = new Intent(PollActivity.this, MainActivity.class);

                        Toast.makeText(PollActivity.this,"ขอบคุณสำหรับการประเมินค่ะ",Toast.LENGTH_LONG).show();

                        startActivity(i);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (ClientProtocolException e) {
                    Log.e("ConnectServer", e.toString());
                } catch (IOException e) {
                    Log.e("ConnectServer", e.toString());
                }
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
            i = new Intent(PollActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickToFeed5(View view) {

        Intent i = new Intent(PollActivity.this,FeedNewsActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToCompany5(View view) {

        Intent i = new Intent(PollActivity.this,CompanyActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToJob5(View view) {

        Intent i = new Intent(PollActivity.this,JobActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToLayout5(View view) {

        Intent i = new Intent(PollActivity.this,LayoutActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToPoll5(View view) {

        Intent i = new Intent(PollActivity.this,PollActivity.class);
        startActivity(i);
    }
    public void setScore(){
        sum = 0;
        sum += score[dd1.getSelectedItemPosition()];
        sum += score[dd2.getSelectedItemPosition()];
        sum += score[dd3.getSelectedItemPosition()];
        sum += score[dd4.getSelectedItemPosition()];
        sum += score[dd5.getSelectedItemPosition()];
        score1 = score[dd1.getSelectedItemPosition()];
        score2 = score[dd2.getSelectedItemPosition()];
        score3 = score[dd3.getSelectedItemPosition()];
        score4 = score[dd4.getSelectedItemPosition()];
        score5 = score[dd5.getSelectedItemPosition()];
    }
}
