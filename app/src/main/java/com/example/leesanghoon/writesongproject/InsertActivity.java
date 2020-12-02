package com.example.leesanghoon.writesongproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InsertActivity extends AppCompatActivity {

    EditText title,content;
    Button ok;

    JSONObject requestData = new JSONObject();

    String title1,content1;

    private SharedPreferences appData;


    String id, password, name, userseq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        title = (EditText) findViewById(R.id.etTitle);
        content = (EditText) findViewById(R.id.content);

        ok=(Button) findViewById(R.id.btnCustom);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title1 = title.getText().toString();

                content1 = content.getText().toString();
                try {
                    requestData.accumulate("title", title1);
                    requestData.accumulate("id",id);
                    requestData.accumulate("content", content1);
                    Log.d("requestData", requestData.toString());
                    new postTask3().execute("http://192.168.64.157:8080/biz/board/insert");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    class postTask3 extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... urls) {
            try {

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(urls[0]);
                    //연결을 함
                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST");//POST방식으로 보냄
//                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();
                    Log.d("requestData", "1");
                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(requestData.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌
                    Log.d("requestData", "2");
                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();


                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    Log.d("requestData", "3");
                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();//버퍼를 닫아줌
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("postData", result);


            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivityForResult(intent, 1000);

        }
    }
        // 설정값을 불러오는 함수
        private void load() {
            // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
            // 저장된 이름이 존재하지 않을 시 기본값
            id = appData.getString("id", "");
            password = appData.getString("password", "");
            name = appData.getString("name","");
            userseq = appData.getString("userSeq", "");
        }




}
