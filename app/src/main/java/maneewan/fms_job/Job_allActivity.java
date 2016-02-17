package maneewan.fms_job;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

public class Job_allActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    HttpURLConnection connection = null;

    String[] id_major,id_job,id_job_position_main,companyname,majorname,amount,work_place,salary,sex,age,education,skill,detail;
    int indexfinal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_all);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
        JSONArray jsonArrayAll = getListAll();
        id_major = new String[jsonArrayAll.length()];
        id_job = new String[jsonArrayAll.length()];
        id_job_position_main = new String[jsonArrayAll.length()];
        companyname = new String[jsonArrayAll.length()];
        majorname = new String[jsonArrayAll.length()];
        amount = new String[jsonArrayAll.length()];
        work_place = new String[jsonArrayAll.length()];
        salary = new String[jsonArrayAll.length()];
        sex = new String[jsonArrayAll.length()];
        age = new String[jsonArrayAll.length()];
        education = new String[jsonArrayAll.length()];
        skill = new String[jsonArrayAll.length()];
        detail = new String[jsonArrayAll.length()];
        for (int i = 0; i < jsonArrayAll.length(); i++) {

            try {
                objects.add(new ContentItem(String.valueOf(i + 1), jsonArrayAll.getJSONObject(i).getString("job_name")));
                id_job_position_main[i] = jsonArrayAll.getJSONObject(i).getString("id_job_position_main");
                id_major[i] = jsonArrayAll.getJSONObject(i).getString("id_major");
                id_job[i] = jsonArrayAll.getJSONObject(i).getString("id_job");
                companyname[i] = jsonArrayAll.getJSONObject(i).getString("company_name");
                majorname[i] = jsonArrayAll.getJSONObject(i).getString("major_name");
                amount[i] = jsonArrayAll.getJSONObject(i).getString("amount");
                work_place[i] = jsonArrayAll.getJSONObject(i).getString("work_place");
                salary[i] = jsonArrayAll.getJSONObject(i).getString("salary");
                sex[i] = jsonArrayAll.getJSONObject(i).getString("sex");
                age[i] = jsonArrayAll.getJSONObject(i).getString("age");
                education[i] = jsonArrayAll.getJSONObject(i).getString("education");
                skill[i] = jsonArrayAll.getJSONObject(i).getString("skill");
                detail[i] = jsonArrayAll.getJSONObject(i).getString("detail");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listviewjoball);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indexfinal = position;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("บริษัท : " + companyname[position] + "\n" + "สาขาที่เกี่ยวข้อง : " + majorname[position] + "\n"
                + "จำนวนที่รับ : " + amount[position] + "\n" + "สถานที่ปฎิบัติงาน : " + work_place[position] + "\n" + "เงินเดือน : " + salary[position] + "\n"
                + "เพศ : " + sex[position] + "\n" + "อายุ : " + age[position] + "\n" + "การศึกษา : " + education[position] + "\n" +
                "ความสามารถ : " + skill[position] + "\n" + "รายละเอียดอื่น ๆ : " + detail[position]);


        alertDialogBuilder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listjoball, null);
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
    public JSONArray getListAll() {
        InputStream is = null;
        String result = null;
        JSONArray jsonArray = null;

        try {
            System.out.println("+++++++++++ login ++++++++++");
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_job_position/job_position_list_all");
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
