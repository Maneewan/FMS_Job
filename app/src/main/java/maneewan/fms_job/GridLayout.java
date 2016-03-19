package maneewan.fms_job;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridLayout extends ArrayAdapter {
    Context context;


    public GridLayout(Context context) {
        super(context, 0);
        this.context = context;
    }


    public int getCount() {
        return 42;
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

            if(position==0)
            {
                //textViewTitle.setText("ข่าวสาร");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt.setBackgroundResource(R.color.feed);

            }
            if (position==1){
                //textViewTitle.setText("ข้อมูลบริษัท");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt1 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt1.setBackgroundResource(R.color.feed);
            }
            if(position==2){
                //textViewTitle.setText("ตำแหน่งงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt2 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt2.setBackgroundResource(R.color.feed);
            }
            if(position==3){
                //textViewTitle.setText("แผนผังงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt3 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt3.setBackgroundResource(R.color.feed);
            }
            if(position==4){
                //textViewTitle.setText("ประเมิน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt4 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt4.setBackgroundResource(R.color.feed);
            }
            if(position==5)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt5 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt5.setBackgroundResource(R.color.feed);
            }
            if(position==6)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt6 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt6.setBackgroundResource(R.color.feed);
            }
            if(position==7)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt7 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt7.setBackgroundResource(R.color.feed);
            }
            if(position==8)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt8 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt8.setBackgroundResource(R.color.feed);
            }
            if(position==9)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt9 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt9.setBackgroundResource(R.color.feed);
            }
            if(position==10)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt10 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt10.setBackgroundResource(R.color.feed);
            }
            if (position==11){
                //textViewTitle.setText("ข้อมูลบริษัท");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt11 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt11.setBackgroundResource(R.color.feed);
            }
            if(position==12){
                //textViewTitle.setText("ตำแหน่งงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt12 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt12.setBackgroundResource(R.color.feed);
            }
            if(position==13){
                //textViewTitle.setText("แผนผังงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt13 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt13.setBackgroundResource(R.color.feed);
            }
            if(position==14){
                //textViewTitle.setText("ประเมิน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt14 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt14.setBackgroundResource(R.color.feed);
            }
            if(position==15)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt15 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt15.setBackgroundResource(R.color.feed);
            }
            if(position==16)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt16 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt16.setBackgroundResource(R.color.feed);
            }
            if(position==17)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt17 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt17.setBackgroundResource(R.color.feed);
            }
            if(position==18)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt18 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt18.setBackgroundResource(R.color.feed);
            }
            if(position==19)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt19 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt19.setBackgroundResource(R.color.feed);
            }
            if(position==20)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt20 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt20.setBackgroundResource(R.color.feed);
            }
            if (position==21){
                //textViewTitle.setText("ข้อมูลบริษัท");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt1 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt1.setBackgroundResource(R.color.feed);
            }
            if(position==22){
                //textViewTitle.setText("ตำแหน่งงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt2 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt2.setBackgroundResource(R.color.feed);
            }
            if(position==23){
                //textViewTitle.setText("แผนผังงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt3 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt3.setBackgroundResource(R.color.feed);
            }
            if(position==24){
                //textViewTitle.setText("ประเมิน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt4 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt4.setBackgroundResource(R.color.feed);
            }
            if(position==25)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt5 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt5.setBackgroundResource(R.color.feed);
            }
            if(position==26)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt6 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt6.setBackgroundResource(R.color.feed);
            }
            if(position==27)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt7 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt7.setBackgroundResource(R.color.feed);
            }
            if(position==28)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt8 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt8.setBackgroundResource(R.color.feed);
            }
            if(position==29)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt9 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt9.setBackgroundResource(R.color.feed);
            }
            if(position==30)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt10 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt10.setBackgroundResource(R.color.feed);
            }
            if (position==31){
                //textViewTitle.setText("ข้อมูลบริษัท");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt11 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt11.setBackgroundResource(R.color.feed);
            }
            if(position==32){
                //textViewTitle.setText("ตำแหน่งงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt12 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt12.setBackgroundResource(R.color.feed);
            }
            if(position==33){
                //textViewTitle.setText("แผนผังงาน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt13 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt13.setBackgroundResource(R.color.feed);
            }
            if(position==34){
                //textViewTitle.setText("ประเมิน");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt14 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt14.setBackgroundResource(R.color.feed);
            }
            if(position==35)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt15 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt15.setBackgroundResource(R.color.feed);
            }
            if(position==36)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt16 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt16.setBackgroundResource(R.color.feed);
            }
            if(position==37)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt17 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt17.setBackgroundResource(R.color.feed);
            }
            if(position==38)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt18 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt18.setBackgroundResource(R.color.feed);
            }
            if(position==39)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt19 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt19.setBackgroundResource(R.color.feed);
            }
            if(position==40)
            {
                //textViewTitle.setText("ออก");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt20 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt20.setBackgroundResource(R.color.feed);
            }
            if (position==41){
                //textViewTitle.setText("ข้อมูลบริษัท");
                imageViewIte.setImageResource(R.drawable.feed);
                LinearLayout gt11 = (LinearLayout) row.findViewById(R.id.gridLayout);
                gt11.setBackgroundResource(R.color.feed);
            }

        }

        return row;
    }

}
