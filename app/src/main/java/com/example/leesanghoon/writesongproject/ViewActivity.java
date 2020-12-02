package com.example.leesanghoon.writesongproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ViewActivity extends AppCompatActivity {

    TextView em,content;

    BoardVO boardVO;

    TextView view;
    Context context;

    private SharedPreferences appData;

    String id, password, name,userseq;

    JSONObject requestData = new JSONObject();
    JSONObject requestData2 = new JSONObject();
    JSONObject requestData3 = new JSONObject();


    private Button sendButton,enterWritingRoom;
    private EditText editsend;
    private TextView tvChat;

    int seq;
    int si=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        view = (TextView) findViewById(R.id.tvchat);
        context = this;

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();


        Intent intent = getIntent();
        seq = intent.getExtras().getInt("seq");

        try {
            requestData.accumulate("seq", seq);
            Log.d("requestData", requestData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new postTask4().execute("http://192.168.64.157:8080/biz/board/select");



        enterWritingRoom=(Button)findViewById(R.id.btnEnter);

        enterWritingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=String.valueOf(seq);
                Log.d("123",userseq);
                if(userseq.equals(s)) {
                    Intent intent = new Intent(getApplicationContext(), WritingActivity.class);
                    intent.putExtra("seq", seq);
                    startActivity(intent);
                }
            }
        });

    }

    class postTask4 extends AsyncTask<String, String, String> {


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
                String[] id = new String[list_cont];
                String[] content = new String[list_cont];

                for(int i = 0 ; i<list_cont;i++){
                    JSONObject jsonObject = jArrObject.getJSONObject(i);
                    seq[i]=jsonObject.getInt("seq");
                    title[i] = jsonObject.getString("title");
                    id[i]=jsonObject.getString("id");
                    content[i]=jsonObject.getString("content");


                }
                Log.d("jsonData", String.valueOf(seq[0]));
                boardVO = new BoardVO(seq[0],title[0],id[0],content[0]);
                Log.d("requestData", boardVO.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                requestData3.accumulate("seq", seq);
                Log.d("requestData", requestData.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            new postTask7().execute("http://192.168.64.157:8080/biz/comment/list");

            tvChat  = (TextView) findViewById(R.id.tvchat);
            editsend = (EditText) findViewById(R.id.etSend);
            sendButton = (Button) findViewById(R.id.btnSend);


            content=(TextView) findViewById(R.id.content);

            content.setText(boardVO.getContent());

            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = editsend.getText().toString()  + "\n";
                    tvChat.append(id+" : "+msg);
                    editsend.setText("");
                    try {
                        requestData2.accumulate("seq", seq);
                        requestData2.accumulate("id", id);
                        requestData2.accumulate("comment", msg);
                        Log.d("requestData", requestData.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    new postTask6().execute("http://192.168.64.157:8080/biz/comment/insert");

                }
            });


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

    class postTask6 extends AsyncTask<String, String, String> {


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
                    Log.d("requestData2", "1");
                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(requestData2.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌
                    Log.d("requestData2", "2");
                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();


                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    Log.d("requestData2", "3");
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


        }
    }

    class postTask7 extends AsyncTask<String, String, String> {


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
                    Log.d("requestData3", "1");
                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(requestData3.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌
                    Log.d("requestData3", "2");
                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();


                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    Log.d("requestData3", "3");
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
        protected void onPostExecute(String result3) {
            super.onPostExecute(result3);
            Log.d("postData", result3);
            tvChat  = (TextView) findViewById(R.id.tvchat);
            try {
                JSONArray jArrObject = new JSONArray(result3);
                int list_cont=jArrObject.length();
                final int seq[] = new int[list_cont];
                String[] id = new String[list_cont];
                final String[] comment = new String[list_cont];



                for(int i = 0 ; i<list_cont;i++){
                    JSONObject jsonObject = jArrObject.getJSONObject(i);
                    seq[i]=jsonObject.getInt("seq");
                    comment[i] = jsonObject.getString("comment");
                    id[i]=jsonObject.getString("id");
                    tvChat.append(id[i]+" : "+comment[i]);
                    editsend.setText("");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
