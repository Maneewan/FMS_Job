package maneewan.fms_job;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
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
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class Company_groupActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    HttpURLConnection connection = null;
    String[] company_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_group);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
        JSONArray jsonArrayCompany = getListCompany();
        company_group = new String[jsonArrayCompany.length()];
        for (int i = 0; i < jsonArrayCompany.length(); i++) {
            try {
                objects.add(new ContentItem(String.valueOf(i + 1), jsonArrayCompany.getJSONObject(i).getString("company_group")));
                company_group[i] = jsonArrayCompany.getJSONObject(i).getString("company_group");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listviewgroup);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }
    public void clickToFeed(View view) {

        Intent i = new Intent(Company_groupActivity.this,FeedNewsActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToCompany(View view) {

        Intent i = new Intent(Company_groupActivity.this,CompanyActivity.class);
        startActivity(i);
    }
    public void clickToJob(View view) {

        Intent i = new Intent(Company_groupActivity.this,JobActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }
    public void clickToLayout(View view) {

        Intent i = new Intent(Company_groupActivity.this,LayoutActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }
    public void clickToPoll(View view) {

        Intent i = new Intent(Company_groupActivity.this,PollActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }

    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {
        Intent i;
        i = new Intent(this, Company_by_group.class);
        i.putExtra("company_group", company_group[pos]);
        startActivity(i);
        //finish();
    }
    private class ContentItem {
        String name;
        String desc;

        public ContentItem(String n, String d) {
            name = n;
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

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listgroup, null);
                //holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                holder.name = (TextView) convertView.findViewById(R.id.name);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //holder.tvName.setText(c.name);
            holder.name.setText(c.desc);
            return convertView;
        }

        private class ViewHolder {

            TextView tvName,name;
        }
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
            i = new Intent(Company_groupActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public JSONArray getListCompany() {
        InputStream is = null;
        String result = null;
        JSONArray jsonArray = null;

        try {
            System.out.println("+++++++++++ login ++++++++++");
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_company/company_list_group");
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
}
