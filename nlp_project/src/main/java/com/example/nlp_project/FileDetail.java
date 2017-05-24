package com.example.nlp_project;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;

import check.grammar.GetToken;
import check.grammar.Token;

public class FileDetail extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView searchView;

    String nameFile, absolutePath, typeFile;
    EditText text_content;

    String data="";
    String key="";
    ProgressDialog progressDialog;

    ArrayList<Integer> chiso=new ArrayList<>(); // chi so bat dau ky tu tim thay
    ArrayList<String> arr=new ArrayList<>();

    ArrayList<String> strWords=new ArrayList<>();
    ArrayList<Token> arrToken=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getData from last Activity, set Type file
        Bundle extras=getIntent().getExtras();
        nameFile=extras.getString("name");
        absolutePath=extras.getString("absolutePath");
        Uri uri=Uri.fromFile(new File(absolutePath));
        typeFile = MimeTypeMap.getFileExtensionFromUrl(uri.toString());

        // setTitle for Toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(nameFile);

        //getId
        text_content=(EditText)findViewById(R.id.text_content);

        getTextFile();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file_detail, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.action_search_void){
            searchVoid();
        }else if(item.getItemId()==R.id.action_check){
            checkGrammar();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getTextFile(){
        if (typeFile.equals("pdf")){
         /*   GetDataPdf getDataPdf=new GetDataPdf();
            getDataPdf.execute();*/
            PDDocument document = null;
            try {
                document = PDDocument.load(getDatabasePath(absolutePath));

            } catch(IOException e) {
                e.printStackTrace();
            }

            try {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                pdfStripper.setStartPage(0);
                pdfStripper.setEndPage(document.getNumberOfPages());
                data = "Parsed text: " + pdfStripper.getText(document);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (document != null) document.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            text_content.setText(data);
        }else if(typeFile.equals("txt")){
            getDataText();
            text_content.setText(data);
        }
    }


    public class GetDataPdf extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(FileDetail.this);
            progressDialog.setTitle("Đang tách text");
            progressDialog.setMessage("Vui lòng chờ!");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            PDDocument document = null;
            try {
                document = PDDocument.load(getDatabasePath(absolutePath));

            } catch(IOException e) {
                e.printStackTrace();
            }

            try {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                pdfStripper.setStartPage(0);
                pdfStripper.setEndPage(document.getNumberOfPages());
                data = "Parsed text: " + pdfStripper.getText(document);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (document != null) document.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            text_content.setText(s);
            progressDialog.dismiss();
        }
    }

    public void getDataText(){
        StringBuffer s = new StringBuffer();
        try
        {
            FileInputStream fis = new FileInputStream(new File(absolutePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null)
            {
                line = line.trim();
                if ((line != null) && (!line.isEmpty()))
                {
                    s.append(line);
                    s.append("\n");
                }
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        data=s.toString();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        key=query;
        search();
        return false;
    }

    public ArrayList<String> search(){
        String lowKey=key.toLowerCase();
        String lowData=data.toLowerCase();
        int index=lowData.indexOf(lowKey);
        while (index>0){
            String stt=data.substring(index, index+100)+"......";
            chiso.add(index);
            arr.add(stt);
            index=data.indexOf(key, index+key.length());
        }
        if (arr.size() <= 0) {
            resultError();
        }else{
            resultSuccess();
        }
        return arr;
    }

    public void resultError(){
        Toast.makeText(getApplicationContext(), "Không tìm thấy từ khóa phù hợp", Toast.LENGTH_LONG).show();
    }

    public void resultSuccess(){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Kết quả tìm kiếm:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.list_search, R.id.list_search, arr);
        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text_content.setFocusable(true);
                        text_content.requestFocus();
                        text_content.setSelection(chiso.get(which), chiso.get(which)+key.length());
                    }
                });
        builderSingle.show();
    }

    public void searchVoid(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Nhập từ khóa tìm kiếm bằng giọng nói");
        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Thiết bị không được hỗ trợ",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void checkGrammar(){
        String[] str=data.split(" |\\,|\\.|\\?|\\<|\\>|\\;|\\:|\\[|\\]|\\{|\\}" +
                "|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\(|\\)|\\-|\\+|\\=|\\_" +
                "|\\!|\\\n");
        for(int i=0;i<str.length;i++){
            if(str[i].length()==0){
                continue;
            }else{
                strWords.add(str[i]);
            }
        }

        for(int i=0;i<strWords.size();i++){
            GetToken gt=new GetToken(strWords.get(i));
            gt.getPhuAmDauVan(gt.words);
            gt.getVanPhuAmCuoi(gt.van);
            Token tk=new Token(gt.phu_am_dau, gt.getVanKhongDau(gt.van), gt.th_am, gt.phu_am_cuoi, gt.getDauAm(gt.th_am));
            arrToken.add(tk);
        }
        Toast.makeText(getApplicationContext(), strWords.get(0), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),arrToken.get(0).th_am, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            ArrayList<String> result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            key=result.get(0);
            search();
        }
    }
}
