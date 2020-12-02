package com.example.leesanghoon.writesongproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
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


public class ListActivity extends AppCompatActivity {

    private SharedPreferences appData;

    String id, password, name;

    JSONObject requestData = new JSONObject();

    BoardVO boardVO;

    TextView text;
    Button one;
    Button add,btnLogout;

    LinearLayout layout;
    Context context;
    int count = 0;

    String same1,same2;


    String title,userid,content,userseq;
    int seq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();


        layout = (LinearLayout) findViewById(R.id.layout);
        context = this;


        new postTask2().execute("http://192.168.64.157:8080/biz/board/list");

        one=(Button) findViewById(R.id.Room1);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), notifyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);
            }
        });
        add=(Button) findViewById(R.id.btnMkRoom);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);

            }
        });
        btnLogout=(Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);
            }
        });


    }


    class postTask2 extends AsyncTask<String, String, String> {


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

            try {
                JSONArray jArrObject = new JSONArray(result);
                int list_cont=jArrObject.length();
                final int seq[] = new int[list_cont];
                String[] title = new String[list_cont];
                final String[] id = new String[list_cont];
                String[] content = new String[list_cont];

                for(int i = 0 ; i<list_cont;i++){
                    JSONObject jsonObject = jArrObject.getJSONObject(i);
                    seq[i]=jsonObject.getInt("seq");
                    title[i] = jsonObject.getString("title");
                    id[i]=jsonObject.getString("id");
                    content[i]=jsonObject.getString("content");

                    final Button btn = new Button(context);
                    btn.setId(seq[i]);
                    btn.setText(title[i]);
                    LinearLayout.LayoutParams buttonparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
                    btn.setLayoutParams(buttonparam);
                    btn.setWidth(700);
                    btn.setBackgroundResource(R.drawable.button);
                    layout.addView(btn);
                    count++;
                    final int finalI = i;
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(userid.equals(id[finalI])) {
                                Intent intent = new Intent(getApplicationContext(), HostActivity.class);
                                intent.putExtra("seq", btn.getId());
                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivityForResult(intent, 1000);
                            }
                            else
                            {
                                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                                intent.putExtra("seq", btn.getId());
                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivityForResult(intent, 1000);
                            }
                        }
                    });

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        userid = appData.getString("id", "");
        password = appData.getString("password", "");
        name = appData.getString("name","");
        userseq = appData.getString("userSeq", "");
    }
}
