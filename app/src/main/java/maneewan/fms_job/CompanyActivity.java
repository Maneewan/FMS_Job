package maneewan.fms_job;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
            i = new Intent(CompanyActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void clickToFeed(View view) {

        Intent i = new Intent(CompanyActivity.this,FeedNewsActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
    public void clickToCompany(View view) {

        Intent i = new Intent(CompanyActivity.this,CompanyActivity.class);
        startActivity(i);
    }
    public void clickToJob(View view) {

        Intent i = new Intent(CompanyActivity.this,JobActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }
    public void clickToLayout(View view) {

        Intent i = new Intent(CompanyActivity.this,LayoutActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }
    public void clickToPoll(View view) {

        Intent i = new Intent(CompanyActivity.this,PollActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }

    //ไปหน้ากลุ่มบริษัท
    public void clickToGroup(View view) {

        Intent i = new Intent(CompanyActivity.this,Company_groupActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    //ไปหน้ารายชื่อบริษัททั้งหมด
    public void clickToAllCom(View view) {

        Intent i = new Intent(CompanyActivity.this,Company_allActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.move_left_in, R.anim.move_left_out);
    }


}
