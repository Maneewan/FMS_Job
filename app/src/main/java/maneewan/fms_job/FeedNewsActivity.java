package maneewan.fms_job;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FeedNewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] id_news,news_topic,news_image,news_date_update,news_detail;
    //private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_news);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
        JSONArray jsonArrayNews = getListNews();
        id_news = new String[jsonArrayNews.length()];
        for (int i = 0; i < jsonArrayNews.length(); i++) {

            try {
                objects.add(new ContentItem(jsonArrayNews.getJSONObject(i).getString("news_topic")));
                id_news[i] = jsonArrayNews.getJSONObject(i).getString("id_news");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        final MyAdapter adapter = new MyAdapter(this, objects);

        final ListView lv = (ListView) findViewById(R.id.listviewNews);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

       /* swipeLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_news);
        swipeLayout.setColorSchemeResources(R.color.company, R.color.feed, R.color.job, R.color.layout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv.setAdapter(adapter);
                        swipeLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });*/

    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i;
        i = new Intent(this, News_detail.class);
        i.putExtra("id_news", id_news[pos]);
        startActivity(i);
    }

    private class ContentItem {
        String desc;

        public ContentItem(String d) {
            desc = d;
        }
    }
    private class MyAdapter extends ArrayAdapter<ContentItem> {

        public MyAdapter(Context context, List<ContentItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ContentItem c = getItem(position);

            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listnews_topic, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);


            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            convertView.setTag(holder);
            holder.name.setText(c.desc);
            return convertView;
        }

        private class ViewHolder {
            TextView name;
        }
    }
    public JSONArray getListNews() {
        InputStream is = null;
        String result = null;
        JSONArray jsonArray = null;

        try {
            System.out.println("+++++++++++ login ++++++++++");
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_news/list_news_topic");
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

    public void clickToFeed2(View view) {

        Intent i = new Intent(FeedNewsActivity.this,FeedNewsActivity.class);
        startActivity(i);
    }
    public void clickToCompany2(View view) {

        Intent i = new Intent(FeedNewsActivity.this,CompanyActivity.class);
        startActivity(i);
    }
    public void clickToJob2(View view) {

        Intent i = new Intent(FeedNewsActivity.this,JobActivity.class);
        startActivity(i);
    }
    public void clickToLayout2(View view) {

        Intent i = new Intent(FeedNewsActivity.this,LayoutActivity.class);
        startActivity(i);
    }
    public void clickToPoll2(View view) {

        Intent i = new Intent(FeedNewsActivity.this,PollActivity.class);
        startActivity(i);
    }


}
