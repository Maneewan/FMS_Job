package maneewan.fms_job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class GridLayout extends ArrayAdapter {
    Context context;
    JSONArray jsonArray = null;

    public GridLayout(Context context) {
        super(context, 0);
        this.context = context;
    }


    public int getCount() {
        getLayout();
        return jsonArray.length();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.activity_grid_layout, parent, false);
            TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
            ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageGrid);

            for(int i = 0;i < jsonArray.length(); i++) {
                //textViewTitle.setText("ข่าวสาร");
                if(position==i) {
                    Drawable drawable = null;
                    try {
                        drawable = LoadImageFromWebOperations(jsonArray.getJSONObject(i).getString("company_logo"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    imageViewIte.setImageDrawable(drawable);
                    LinearLayout gt = (LinearLayout) row.findViewById(R.id.gridLayout);
                    //gt.setBackgroundResource(R.color.feed);
                }

            }


        }

        return row;
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

    public static Drawable LoadImageFromWebOperations(String nameImage) {
        try {
            InputStream is = (InputStream) new URL(MainActivity.mainhttp + "/fms_job//assets/uploads/"+nameImage).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
