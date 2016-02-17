package maneewan.fms_job;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class JobActivity extends AppCompatActivity {
    String[] id_job_position_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
    }
    public void clickToFeed3(View view) {

        Intent i = new Intent(JobActivity.this,FeedNewsActivity.class);
        startActivity(i);
    }
    public void clickToCompany3(View view) {

        Intent i = new Intent(JobActivity.this,CompanyActivity.class);
        startActivity(i);
    }
    public void clickToJob3(View view) {

        Intent i = new Intent(JobActivity.this,JobActivity.class);
        startActivity(i);
    }
    public void clickToLayout3(View view) {

        Intent i = new Intent(JobActivity.this,LayoutActivity.class);
        startActivity(i);
    }
    public void clickToPoll3(View view) {

        Intent i = new Intent(JobActivity.this,PollActivity.class);
        startActivity(i);
    }

    //ไปหน้าค้นหางานตามสาขาวิชา
    public void clickToJobMajor(View view) {

        Intent i = new Intent(JobActivity.this,Job_majorActivity.class);
        startActivity(i);
    }
    //ไปหน้าค้นหางานตามหน่วยงาน
    public void clickToJobCom(View view) {

        Intent i = new Intent(JobActivity.this,Job_companyActivity.class);
        startActivity(i);
    }

    //ไปหน้าตำแหน่งงานทั้งหมด
    public void clickToJobAll(View view) {
        Intent i = new Intent(JobActivity.this,Job_allActivity.class);
        startActivity(i);
    }

}
