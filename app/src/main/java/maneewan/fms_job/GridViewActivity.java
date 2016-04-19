package maneewan.fms_job;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridViewActivity extends ArrayAdapter {

    Context context;

    public GridViewActivity(Context context) {
        super(context, 0);
        this.context = context;

    }

    public int getCount() {
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;


        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.activity_grid_view, parent, false);
            TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
            ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageGrid);

            if(position==0)
            {
                textViewTitle.setText("ข่าวสาร");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt = (LinearLayout) row.findViewById(R.id.gridBg);
                gt.setBackgroundResource(R.color.feed);

            }
            if (position==1){
                textViewTitle.setText("ข้อมูลบริษัท");
                imageViewIte.setImageResource(R.drawable.company_1);
                LinearLayout gt1 = (LinearLayout) row.findViewById(R.id.gridBg);
                gt1.setBackgroundResource(R.color.company);
            }
            if(position==2){
                textViewTitle.setText("ตำแหน่งงาน");
                imageViewIte.setImageResource(R.drawable.job);
                LinearLayout gt2 = (LinearLayout) row.findViewById(R.id.gridBg);
                gt2.setBackgroundResource(R.color.job);
            }
            if(position==3){
                textViewTitle.setText("แผนผังงาน");
                imageViewIte.setImageResource(R.drawable.layout);
                LinearLayout gt3 = (LinearLayout) row.findViewById(R.id.gridBg);
                gt3.setBackgroundResource(R.color.layout);
            }
            if(position==4){
                textViewTitle.setText("ประเมิน");
                imageViewIte.setImageResource(R.drawable.poll);
                LinearLayout gt4 = (LinearLayout) row.findViewById(R.id.gridBg);
                gt4.setBackgroundResource(R.color.poll);
            }
            if(position==5)
            {
                textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.exit);
                LinearLayout gt5 = (LinearLayout) row.findViewById(R.id.gridBg);
                gt5.setBackgroundResource(R.color.exit);
            }
        }

        return row;

    }


}

