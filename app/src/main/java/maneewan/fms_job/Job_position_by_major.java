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
import java.util.ArrayList;
import java.util.List;

public class Job_position_by_major extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String id_major1;
    String[] id_major,id_job,id_job_position_main,company_name,amount,work_place,salary,sex,age,education,skill,detail;
    int indexfinal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_position_by_major);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Intent intent = getIntent();
        id_major1 = intent.getStringExtra("id_major");
        System.out.println("--------------------------------test----------" + id_major1);

        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
        JSONArray jsonArrayMajor = getListMajor();
        id_major = new String[jsonArrayMajor.length()];
        id_job = new String[jsonArrayMajor.length()];
        id_job_position_main = new String[jsonArrayMajor.length()];
        company_name = new String[jsonArrayMajor.length()];
        amount = new String[jsonArrayMajor.length()];
        work_place = new String[jsonArrayMajor.length()];
        salary = new String[jsonArrayMajor.length()];
        sex = new String[jsonArrayMajor.length()];
        age = new String[jsonArrayMajor.length()];
        education = new String[jsonArrayMajor.length()];
        skill = new String[jsonArrayMajor.length()];
        detail = new String[jsonArrayMajor.length()];
        for (int i = 0; i < jsonArrayMajor.length(); i++) {

            try {
                objects.add(new ContentItem(jsonArrayMajor.getJSONObject(i).getString("company_name"),
                        jsonArrayMajor.getJSONObject(i).getString("job_name")));
                id_job_position_main[i] = jsonArrayMajor.getJSONObject(i).getString("id_job_position_main");
                id_major[i] = jsonArrayMajor.getJSONObject(i).getString("id_major");
                id_job[i] = jsonArrayMajor.getJSONObject(i).getString("id_job");
                company_name[i] = jsonArrayMajor.getJSONObject(i).getString("company_name");
                amount[i] = jsonArrayMajor.getJSONObject(i).getString("amount");
                work_place[i] = jsonArrayMajor.getJSONObject(i).getString("work_place");
                salary[i] = jsonArrayMajor.getJSONObject(i).getString("salary");
                sex[i] = jsonArrayMajor.getJSONObject(i).getString("sex");
                age[i] = jsonArrayMajor.getJSONObject(i).getString("age");
                education[i] = jsonArrayMajor.getJSONObject(i).getString("education");
                skill[i] = jsonArrayMajor.getJSONObject(i).getString("skill");
                detail[i] = jsonArrayMajor.getJSONObject(i).getString("detail");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listviewjobmajor);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indexfinal = position;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("บริษัท : " + company_name[position] + "\n" + "จำนวนที่รับ : " + amount[position] +
                "\n" + "สถานที่ปฎิบัติงาน : " + work_place[position] + "\n" + "เงินเดือน : " + salary[position] + "\n"
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

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_job_position, null);
                holder.name_company = (TextView) convertView.findViewById(R.id.name_company);
                holder.name = (TextView) convertView.findViewById(R.id.name);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name_company.setText(c.name);
            holder.name.setText(c.desc);
            return convertView;
        }

        private class ViewHolder {

            TextView name_company,name;
        }
    }
    public JSONArray getListMajor() {
        InputStream is = null;
        String result = null;
        JSONArray jsonArray = null;

        try {
            System.out.println("+++++++++++ login ++++++++++");
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_job_position/job_position_by_major");
            DefaultHttpClient httpclient = new DefaultHttpClient();

            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("id_major", id_major1));
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

