package com.example.nlp_project;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShowPdfFile extends AppCompatActivity {
    public AssetManager assetManager;
    public EditText text_content;
    ScrollView scrollView;
    public File root;
    String path;
    String name;
    ProgressDialog progressDialog;
    String data=null;
    String parsedText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf_file);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pdf File");


        // getData from last Activity
        Bundle bundle=getIntent().getExtras();
        path=bundle.getString("absolutePath");

        setup();
        stripText();
        findIndexes(parsedText,"anh");
        scrollView.scrollTo(15,15);
        text_content.setScrollY(200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_detail, menu);
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

    private void setup() {
        // Enable Android-style asset loading (highly recommended)
        PDFBoxResourceLoader.init(getApplicationContext());
        // Find the root of the external storage.
        root = android.os.Environment.getExternalStorageDirectory();
        assetManager = getAssets();
        text_content = (EditText) findViewById(R.id.text_content);
        scrollView=new ScrollView(this);

    }

    public void stripText() {
        //String parsedText = null;
        PDDocument document = null;
        try {
            document = PDDocument.load(getDatabasePath(path));

        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(0);
            pdfStripper.setEndPage(document.getNumberOfPages());
            parsedText = "Parsed text: " + pdfStripper.getText(document);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        data=parsedText;
        text_content.setText(parsedText);
    }


    public static void findIndexes(String data, String key) {
        int index = data.indexOf(key);
        while (index >= 0) {
            System.out.println("Index : " + index);
            index = data.indexOf(key, index + key.length());
        }
    }
}
