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

public class Company_by_group extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String company_group_main;
    String[] id_company,company_name,company_logo,company_group,name_contact,address,tel,fax,email,website,company_detail;
    int indexfinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_by_group);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Intent intent = getIntent();
        company_group_main = intent.getStringExtra("company_group");

        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
        JSONArray jsonArrayCompany = getListCompany();
        id_company = new String[jsonArrayCompany.length()];
        company_name = new String[jsonArrayCompany.length()];
        company_logo = new String[jsonArrayCompany.length()];
        company_group = new String[jsonArrayCompany.length()];
        name_contact = new String[jsonArrayCompany.length()];
        address = new String[jsonArrayCompany.length()];
        tel = new String[jsonArrayCompany.length()];
        fax = new String[jsonArrayCompany.length()];
        email = new String[jsonArrayCompany.length()];
        website = new String[jsonArrayCompany.length()];
        company_detail = new String[jsonArrayCompany.length()];
        for (int i = 0; i < jsonArrayCompany.length(); i++) {

            try {
                objects.add(new ContentItem(String.valueOf(i + 1), jsonArrayCompany.getJSONObject(i).getString("company_name")));
                id_company[i] = jsonArrayCompany.getJSONObject(i).getString("id_company");
                company_name[i] = jsonArrayCompany.getJSONObject(i).getString("company_name");
                company_logo[i] = jsonArrayCompany.getJSONObject(i).getString("company_logo");
                company_group[i] = jsonArrayCompany.getJSONObject(i).getString("company_group");
                name_contact[i] = jsonArrayCompany.getJSONObject(i).getString("name_contact");
                address[i] = jsonArrayCompany.getJSONObject(i).getString("address");
                tel[i] = jsonArrayCompany.getJSONObject(i).getString("tel");
                fax[i] = jsonArrayCompany.getJSONObject(i).getString("fax");
                email[i] = jsonArrayCompany.getJSONObject(i).getString("email");
                website[i] = jsonArrayCompany.getJSONObject(i).getString("website");
                company_detail[i] = jsonArrayCompany.getJSONObject(i).getString("company_detail");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listviewcompanygroup);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indexfinal = position;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("บริษัท : " + company_name[position] + "\n" + "กลุ่มบริษัท : " + company_group[position] +
                "\n" + "ชื่อผู้ติดต่อ : " + name_contact[position] + "\n" + "สถานที่ปฏิบัติงาน : " + address[position] + "\n"
                + "เบอร์โทรติดต่อ : " + tel[position] + "\n" + "หมายเลขแฟลกซ์ : " + fax[position] + "\n" + "Email : " + email[position] + "\n" +
                "Website : " + website[position] + "\n" + "รายละเอียดอื่น ๆ : " + company_detail[position]);


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

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_company_group, null);
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
    public JSONArray getListCompany() {
        InputStream is = null;
        String result = null;
        JSONArray jsonArray = null;

        try {
            System.out.println("+++++++++++ login ++++++++++");
            HttpPost httppost = new HttpPost(MainActivity.mainhttp + "/fms_job/index.php/mobile_company/company_by_group");
            DefaultHttpClient httpclient = new DefaultHttpClient();

            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("company_group", company_group_main));
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

