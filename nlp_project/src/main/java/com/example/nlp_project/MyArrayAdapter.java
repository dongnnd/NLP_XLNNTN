package com.example.nlp_project;

/**
 * Created by DongND on 10/18/2016.
 */
import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<PdfFile>
{
    Activity context=null;
    ArrayList<PdfFile>myArray=null;
    int layoutId;

    public MyArrayAdapter(Activity context,
                          int layoutId,
                          ArrayList<PdfFile>arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArray=arr;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {

        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myArray.size()>0 && position>=0)
        {
            TextView text_name=(TextView)convertView.findViewById(R.id.item_name);
            ImageView img=(ImageView)convertView.findViewById(R.id.item_img);
            //lấy ra nhân viên thứ position
            final PdfFile emp=myArray.get(position);

            text_name.setText(emp.getName());
            img.setImageResource(R.drawable.item_icon);
        }

        return convertView;
    }
}