package maneewan.fms_job;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

public class JobActivity extends AppCompatActivity {
    String[] id_job_position_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
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
            i = new Intent(JobActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }    public void clickToFeed3(View view) {

        Intent i = new Intent(JobActivity.this,FeedNewsActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToCompany3(View view) {

        Intent i = new Intent(JobActivity.this,CompanyActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToJob3(View view) {

        Intent i = new Intent(JobActivity.this,JobActivity.class);
        startActivity(i);
    }
    public void clickToLayout3(View view) {

        Intent i = new Intent(JobActivity.this,LayoutActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }
    public void clickToPoll3(View view) {

        Intent i = new Intent(JobActivity.this,PollActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }

    //ไปหน้าค้นหางานตามสาขาวิชา
    public void clickToJobMajor(View view) {

        Intent i = new Intent(JobActivity.this,Job_majorActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    //ไปหน้าค้นหางานตามหน่วยงาน
    public void clickToJobCom(View view) {

        Intent i = new Intent(JobActivity.this,Job_companyActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.move_left_in, R.anim.move_left_out);
    }

    //ไปหน้าตำแหน่งงานทั้งหมด
    public void clickToJobAll(View view) {
        Intent i = new Intent(JobActivity.this,Job_allActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
