package maneewan.fms_job;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }
    public void clickToFeed4(View view) {

        Intent i = new Intent(LayoutActivity.this,FeedNewsActivity.class);
        startActivity(i);
    }
    public void clickToCompany4(View view) {

        Intent i = new Intent(LayoutActivity.this,CompanyActivity.class);
        startActivity(i);
    }
    public void clickToJob4(View view) {

        Intent i = new Intent(LayoutActivity.this,JobActivity.class);
        startActivity(i);
    }
    public void clickToLayout4(View view) {

        Intent i = new Intent(LayoutActivity.this,LayoutActivity.class);
        startActivity(i);
    }
    public void clickToPoll4(View view) {

        Intent i = new Intent(LayoutActivity.this,PollActivity.class);
        startActivity(i);
    }
}
