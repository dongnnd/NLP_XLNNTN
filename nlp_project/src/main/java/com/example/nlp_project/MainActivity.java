package com.example.nlp_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<File> fileList=new ArrayList<>();
    ArrayList<PdfFile> data=new ArrayList<>();
    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
    ListView list_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // get File Pdf
        getFile(dir);

        // get Data for arrayList
        getData();

        list_view=(ListView)findViewById(R.id.list_view);
        MyArrayAdapter adapter=new MyArrayAdapter(this, R.layout.list_item, data);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), fileList.get(0).getAbsolutePath()+" ", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(), FileDetail.class);
                intent.putExtra("path", data.get(position).getName());
                intent.putExtra("name", data.get(position).getName());
                intent.putExtra("absolutePath", data.get(position).getAbsolutePath());
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<File> getFile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    //fileList.add(listFile[i]);
                    getFile(listFile[i]);

                } else {
                    if (listFile[i].getName().endsWith(".pdf")||listFile[i].getName().endsWith(".txt"))
                    {
                        fileList.add(listFile[i]);
                    }
                }
            }
        }
        return fileList;
    }

    public void getData(){
        for(int i=0;i<fileList.size();i++){
            PdfFile P=new PdfFile(fileList.get(i).getPath(), fileList.get(i).getName(), fileList.get(i).getAbsolutePath());

            data.add(P);
        }
    }

}
