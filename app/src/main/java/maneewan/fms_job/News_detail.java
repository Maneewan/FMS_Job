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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class News_detail extends AppCompatActivity {
    String id_news1;
    String[] id_news;
    String[] news_topic;
    int news_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_view);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Intent intent = getIntent();
            id_news1 = intent.getStringExtra("id_news");
            System.out.println("--------------------------------test----------" + id_news1);

            getNews();
        }
    }
    public static Drawable LoadImageFromWebOperations(String nameImage) {
        try {
            InputStream is = (InputStream) new URL(MainActivity.mainhttp + "/fms_job//assets/uploads/"+nameImage).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public void getNews() {
        InputStream is = null;
        String result;
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        final TextView detail = (TextView)findViewById(R.id.detail);
        final TextView date = (TextView)findViewById(R.id.news_date);
        final ImageView imageNews = (ImageView)findViewById(R.id.imgNews);



        try {
            System.out.println("+++++++++++ login ++++++++++");
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_news/get_news");
            DefaultHttpClient httpclient = new DefaultHttpClient();

            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("id_news", id_news1));
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
                jsonObject = jsonArray.getJSONObject(0);
                String dt = "";
                dt = jsonObject.getString("news_detail");
                detail.setText(dt);

                String d = "";
                d = jsonObject.getString("news_date_update");
                date.setText(d);

                String pic = "";
                pic = jsonObject.getString("news_image");
                Drawable drawable = LoadImageFromWebOperations(pic);
                if(drawable != null)
                    imageNews.setImageDrawable(drawable);

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
    }
}
