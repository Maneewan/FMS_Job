package maneewan.fms_job;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Job_position_by_company extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String id_company1;
    String[] id_company,id_job,id_job_position_main,companyname,majorname,amount,work_place,salary,sex,age,education,skill,detail;
    int indexfinal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_position_by_company);

        Intent intent = getIntent();
        id_company1 = intent.getStringExtra("id_company");
        System.out.println("--------------------------------test----------" + id_company1);

        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
        JSONArray jsonArrayCompany = getListCompany();
        id_company = new String[jsonArrayCompany.length()];
        id_job = new String[jsonArrayCompany.length()];
        id_job_position_main = new String[jsonArrayCompany.length()];
        majorname = new String[jsonArrayCompany.length()];
        companyname = new String[jsonArrayCompany.length()];
        amount = new String[jsonArrayCompany.length()];
        work_place = new String[jsonArrayCompany.length()];
        salary = new String[jsonArrayCompany.length()];
        sex = new String[jsonArrayCompany.length()];
        age = new String[jsonArrayCompany.length()];
        education = new String[jsonArrayCompany.length()];
        skill = new String[jsonArrayCompany.length()];
        detail = new String[jsonArrayCompany.length()];
        for (int i = 0; i < jsonArrayCompany.length(); i++) {

            try {
                objects.add(new ContentItem(String.valueOf(i + 1), jsonArrayCompany.getJSONObject(i).getString("job_name")));
                id_job_position_main[i] = jsonArrayCompany.getJSONObject(i).getString("id_job_position_main");
                id_company[i] = jsonArrayCompany.getJSONObject(i).getString("id_company");
                majorname[i] = jsonArrayCompany.getJSONObject(i).getString("major_name");
                companyname[i] = jsonArrayCompany.getJSONObject(i).getString("company_name");
                amount[i] = jsonArrayCompany.getJSONObject(i).getString("amount");
                work_place[i] = jsonArrayCompany.getJSONObject(i).getString("work_place");
                salary[i] = jsonArrayCompany.getJSONObject(i).getString("salary");
                sex[i] = jsonArrayCompany.getJSONObject(i).getString("sex");
                age[i] = jsonArrayCompany.getJSONObject(i).getString("age");
                education[i] = jsonArrayCompany.getJSONObject(i).getString("education");
                skill[i] = jsonArrayCompany.getJSONObject(i).getString("skill");
                detail[i] = jsonArrayCompany.getJSONObject(i).getString("detail");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listviewjobcompany);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            indexfinal = position;

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("สาขาที่เกี่ยวข้อง : " + majorname[position] + "\n" + "จำนวนที่รับ : " + amount[position] +
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

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_job_position_2, null);
                //holder.tvName = (TextView) convertView.findViewById(R.id.tvName1);
                holder.name = (TextView) convertView.findViewById(R.id.name1);

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
            i = new Intent(Job_position_by_company.this, MainActivity.class);
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
                HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_job_position/job_position_by_company");
                DefaultHttpClient httpclient = new DefaultHttpClient();

                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("id_company", id_company1));
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
