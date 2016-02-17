package maneewan.fms_job;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class CompanyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

    }
    public void clickToFeed(View view) {

        Intent i = new Intent(CompanyActivity.this,FeedNewsActivity.class);
        startActivity(i);
    }
    public void clickToCompany(View view) {

        Intent i = new Intent(CompanyActivity.this,CompanyActivity.class);
        startActivity(i);
    }
    public void clickToJob(View view) {

        Intent i = new Intent(CompanyActivity.this,JobActivity.class);
        startActivity(i);
    }
    public void clickToLayout(View view) {

        Intent i = new Intent(CompanyActivity.this,LayoutActivity.class);
        startActivity(i);
    }
    public void clickToPoll(View view) {

        Intent i = new Intent(CompanyActivity.this,PollActivity.class);
        startActivity(i);
    }

    //ไปหน้ากลุ่มบริษัท
    public void clickToGroup(View view) {

        Intent i = new Intent(CompanyActivity.this,Company_groupActivity.class);
        startActivity(i);
    }
    //ไปหน้ารายชื่อบริษัททั้งหมด
    public void clickToAllCom(View view) {

        Intent i = new Intent(CompanyActivity.this,Company_allActivity.class);
        startActivity(i);
    }

}
