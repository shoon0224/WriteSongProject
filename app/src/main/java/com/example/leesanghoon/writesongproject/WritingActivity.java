package com.example.leesanghoon.writesongproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.Timer;
import java.util.TimerTask;


public class WritingActivity extends AppCompatActivity{
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;
    ImageView imageView23;
    ImageView imageView24;
    ImageView imageView25;
    ImageView imageView26;
    ImageView imageView27;
    ImageView imageView28;
    ImageView imageView29;
    ImageView imageView30;
    ImageView imageView31;
    ImageView imageView32;
    ImageView imageView33;
    ImageView imageView34;
    ImageView imageView35;
    ImageView imageView36;
    ImageView imageView37;
    ImageView imageView38;
    ImageView imageView39;
    ImageView imageView40;
    ImageView imageView41;
    ImageView imageView42;
    ImageView imageView43;
    ImageView imageView44;
    ImageView imageView45;
    ImageView imageView46;
    ImageView imageView47;
    ImageView imageView48;
    ImageView imageView49;
    ImageView imageView50;
    ImageView imageView51;
    ImageView imageView52;
    ImageView imageView53;
    ImageView imageView54;
    ImageView imageView55;
    ImageView imageView56;
    ImageView imageView57;
    ImageView imageView58;
    ImageView imageView59;
    ImageView imageView60;
    ImageView imageView61;
    ImageView imageView62;
    ImageView imageView63;
    ImageView imageView64;
    ImageView imageView65;
    ImageView imageView66;
    ImageView imageView67;
    ImageView imageView68;
    ImageView imageView69;
    ImageView imageView70;
    ImageView imageView71;
    ImageView imageView72;
    ImageView imageView73;
    ImageView imageView74;
    ImageView imageView75;
    ImageView imageView76;
    ImageView imageView77;
    ImageView imageView78;
    ImageView imageView79;
    ImageView imageView80;

    Button out;

    TextView people;

    int boardSeq;

    BoardVO boardVO;

    JSONObject requestData;
    JSONObject requestData2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);



        Intent intent = getIntent();
        boardSeq = intent.getExtras().getInt("seq");
        try {
            requestData2= new JSONObject();
            requestData2.accumulate("seq", boardSeq);
            Log.d("requestData", requestData2.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        new postTask3().execute("http://192.168.64.157:8080/biz/board/select");
        TimerTask adtask = new TimerTask() {
            @Override
            public void run() {
                new postTask2().execute("http://192.168.64.157:8080/biz/music/list");
            }
        };
        final Timer timer = new Timer();
        timer.schedule(adtask,0,5000);
        Button out = (Button) findViewById(R.id.out);

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                timer.cancel();
                startActivityForResult(intent, 1000);
            }
        });
    }


    float[] oldXvalue=new float[80]; //음표의 x값
    float[] oldYvalue=new float[80]; //음표의 y값
    int ViewName;



    class postTask extends AsyncTask<String, String, String> {


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

        }
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

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



            try {
                JSONArray jArrObject = new JSONArray(result);
                final int list_cont=jArrObject.length();
                final int seq[] = new int[81];
                int[] musicId = new int[81];
                float[] x = new float[81];
                float[] y = new float[81];
                final int[] exist = new int[81];

                int a=2131165271;
                int b=2131165282;
                int c=2131165293;
                int d=2131165304;
                int e=2131165315;
                int f=2131165326;
                int g=2131165337;
                int h=2131165348;
                int aa=2131165350;
                int bb=2131165272;
                int cc=2131165273;
                int dd=2131165274;
                int ee=2131165275;
                int ff=2131165276;
                int gg=2131165277;
                int hh=2131165278;
                int aaa=2131165279;
                int bbb=2131165280;
                int ccc=2131165281;
                int ddd=2131165283;
                int eee=2131165284;
                int fff=2131165285;
                int ggg=2131165286;
                int hhh=2131165287;
                int aaaa=2131165288;
                int bbbb=2131165289;
                int cccc=2131165290;
                int dddd=2131165291;
                int eeee=2131165292;
                int ffff=2131165294;
                int gggg=2131165295;
                int hhhh=2131165296;
                int aaaaa=2131165297;
                int bbbbb=2131165298;
                int ccccc=2131165299;
                int ddddd=2131165300;
                int eeeee=2131165301;
                int fffff=2131165302;
                int ggggg=2131165303;
                int hhhhh=2131165305;
                int aaaaaa=2131165306;
                int bbbbbb=2131165307;
                int cccccc=2131165308;
                int dddddd=2131165309;
                int eeeeee=2131165310;
                int ffffff=2131165311;
                int gggggg=2131165312;
                int hhhhhh=2131165313;
                int aaaaaaa=2131165314;
                int bbbbbbb=2131165316;
                int ccccccc=2131165317;
                int ddddddd=2131165318;
                int eeeeeee=2131165319;
                int fffffff=2131165320;
                int ggggggg=2131165321;
                int hhhhhhh=2131165322;
                int aaaaaaaa=2131165323;
                int bbbbbbbb=2131165324;
                int cccccccc=2131165325;
                int dddddddd=2131165327;
                int eeeeeeee=2131165328;
                int ffffffff=2131165329;
                int gggggggg=2131165330;
                int hhhhhhhh=2131165331;
                int aaaaaaaaa=2131165332;
                int bbbbbbbbb=2131165333;
                int ccccccccc=2131165334;
                int ddddddddd=2131165335;
                int eeeeeeeee=2131165336;
                int fffffffff=2131165338;
                int ggggggggg=2131165339;
                int hhhhhhhhh=2131165340;
                int aaaaaaaaaa=2131165341;
                int bbbbbbbbbb=2131165342;
                int cccccccccc=2131165343;
                int dddddddddd=2131165344;
                int eeeeeeeeee=2131165345;
                int ffffffffff=2131165346;
                int gggggggggg=2131165347;
                int hhhhhhhhhh=2131165349;



                for(int i = 0 ; i<list_cont;i++){
                    JSONObject jsonObject = jArrObject.getJSONObject(i);
                    seq[80]=jsonObject.getInt("seq");             // 개수만큼 설정
                    musicId[80] = jsonObject.getInt("musicId");
                    x[80]= (float) jsonObject.getDouble("x");
                    y[80]= (float) jsonObject.getDouble("y");
                    exist[80]=jsonObject.getInt("exist");
                    if(musicId[80] == a)  //복사복사
                    {
                        seq[0]=seq[80];
                        musicId[0]=seq[80];
                        x[0]=x[80];
                        y[0]=y[80];
                        exist[0]=exist[80];
                    }
                    else if (musicId[80]==b)
                    {
                        seq[1]=seq[80];
                        musicId[1]=seq[80];
                        x[1]=x[80];
                        y[1]=y[80];
                        exist[1]=exist[80];
                    }
                    else if (musicId[80]==c)
                    {
                        seq[2]=seq[80];
                        musicId[2]=seq[80];
                        x[2]=x[80];
                        y[2]=y[80];
                        exist[2]=exist[80];
                    }
                    else if (musicId[80]==d)
                    {
                        seq[3]=seq[80];
                        musicId[3]=seq[80];
                        x[3]=x[80];
                        y[3]=y[80];
                        exist[3]=exist[80];
                    }
                    else if (musicId[80]==e)
                    {
                        seq[4]=seq[80];
                        musicId[4]=seq[80];
                        x[4]=x[80];
                        y[4]=y[80];
                        exist[4]=exist[80];
                    }
                    else if (musicId[80]==f)
                    {
                        seq[5]=seq[80];
                        musicId[5]=seq[80];
                        x[5]=x[80];
                        y[5]=y[80];
                        exist[5]=exist[80];
                    }
                    else if (musicId[80]==g)
                    {
                        seq[6]=seq[80];
                        musicId[6]=seq[80];
                        x[6]=x[80];
                        y[6]=y[80];
                        exist[6]=exist[80];
                    }
                    else if (musicId[80]==h)
                    {
                        seq[7]=seq[80];
                        musicId[7]=seq[80];
                        x[7]=x[80];
                        y[7]=y[80];
                        exist[7]=exist[80];
                    }
                    else if (musicId[80]==aa)  //복사
                    {
                        seq[8]=seq[80];
                        musicId[8]=seq[80];
                        x[8]=x[80];
                        y[8]=y[80];
                        exist[8]=exist[80];
                    }
                    else if (musicId[80]==bb)
                    {
                        seq[9]=seq[80];
                        musicId[9]=seq[80];
                        x[9]=x[80];
                        y[9]=y[80];
                        exist[9]=exist[80];
                    }
                    else if (musicId[80]==cc)
                    {
                        seq[10]=seq[80];
                        musicId[10]=seq[80];
                        x[10]=x[80];
                        y[10]=y[80];
                        exist[10]=exist[80];
                    }
                    else if (musicId[80]==dd)
                    {
                        seq[11]=seq[80];
                        musicId[11]=seq[80];
                        x[11]=x[80];
                        y[11]=y[80];
                        exist[11]=exist[80];
                    }
                    else if (musicId[80]==ee)
                    {
                        seq[12]=seq[80];
                        musicId[12]=seq[80];
                        x[12]=x[80];
                        y[12]=y[80];
                        exist[12]=exist[80];
                    }
                    else if (musicId[80]==ff)
                    {
                        seq[13]=seq[80];
                        musicId[13]=seq[80];
                        x[13]=x[80];
                        y[13]=y[80];
                        exist[13]=exist[80];
                    }
                    else if (musicId[80]==gg)
                    {
                        seq[14]=seq[80];
                        musicId[14]=seq[80];
                        x[14]=x[80];
                        y[14]=y[80];
                        exist[14]=exist[80];
                    }
                    else if (musicId[80]==hh)
                    {
                        seq[15]=seq[80];
                        musicId[15]=seq[80];
                        x[15]=x[80];
                        y[15]=y[80];
                        exist[15]=exist[80];
                    }
                    else if (musicId[80]==aaa)  //복사
                    {
                        seq[16]=seq[80];
                        musicId[16]=seq[80];
                        x[16]=x[80];
                        y[16]=y[80];
                        exist[16]=exist[80];
                    }
                    else if (musicId[80]==bbb)
                    {
                        seq[17]=seq[80];
                        musicId[17]=seq[80];
                        x[17]=x[80];
                        y[17]=y[80];
                        exist[17]=exist[80];
                    }
                    else if (musicId[80]==ccc)
                    {
                        seq[18]=seq[80];
                        musicId[18]=seq[80];
                        x[18]=x[80];
                        y[18]=y[80];
                        exist[18]=exist[80];
                    }
                    else if (musicId[80]==ddd)
                    {
                        seq[19]=seq[80];
                        musicId[19]=seq[80];
                        x[19]=x[80];
                        y[19]=y[80];
                        exist[19]=exist[80];
                    }
                    else if (musicId[80]==eee)
                    {
                        seq[20]=seq[80];
                        musicId[20]=seq[80];
                        x[20]=x[80];
                        y[20]=y[80];
                        exist[20]=exist[80];
                    }
                    else if (musicId[80]==fff)
                    {
                        seq[21]=seq[80];
                        musicId[21]=seq[80];
                        x[21]=x[80];
                        y[21]=y[80];
                        exist[21]=exist[80];
                    }
                    else if (musicId[80]==ggg)
                    {
                        seq[22]=seq[80];
                        musicId[22]=seq[80];
                        x[22]=x[80];
                        y[22]=y[80];
                        exist[22]=exist[80];
                    }
                    else if (musicId[80]==hhh)
                    {
                        seq[23]=seq[80];
                        musicId[23]=seq[80];
                        x[23]=x[80];
                        y[23]=y[80];
                        exist[23]=exist[80];
                    }
                    else if (musicId[80]==aaaa)  //복사
                    {
                        seq[24]=seq[80];
                        musicId[24]=seq[80];
                        x[24]=x[80];
                        y[24]=y[80];
                        exist[24]=exist[80];
                    }
                    else if (musicId[80]==bbbb)
                    {
                        seq[25]=seq[80];
                        musicId[25]=seq[80];
                        x[25]=x[80];
                        y[25]=y[80];
                        exist[25]=exist[80];
                    }
                    else if (musicId[80]==cccc)
                    {
                        seq[26]=seq[80];
                        musicId[26]=seq[80];
                        x[26]=x[80];
                        y[26]=y[80];
                        exist[26]=exist[80];
                    }
                    else if (musicId[80]==dddd)
                    {
                        seq[27]=seq[80];
                        musicId[27]=seq[80];
                        x[27]=x[80];
                        y[27]=y[80];
                        exist[27]=exist[80];
                    }
                    else if (musicId[80]==eeee)
                    {
                        seq[28]=seq[80];
                        musicId[28]=seq[80];
                        x[28]=x[80];
                        y[28]=y[80];
                        exist[28]=exist[80];
                    }
                    else if (musicId[80]==ffff)
                    {
                        seq[29]=seq[80];
                        musicId[29]=seq[80];
                        x[29]=x[80];
                        y[29]=y[80];
                        exist[29]=exist[80];
                    }
                    else if (musicId[80]==gggg)
                    {
                        seq[30]=seq[80];
                        musicId[30]=seq[80];
                        x[30]=x[80];
                        y[30]=y[80];
                        exist[30]=exist[80];
                    }
                    else if (musicId[80]==hhhh)
                    {
                        seq[31]=seq[80];
                        musicId[31]=seq[80];
                        x[31]=x[80];
                        y[31]=y[80];
                        exist[31]=exist[80];
                    }
                    else if (musicId[80]==aaaaa)  //복사
                    {
                        seq[32]=seq[80];
                        musicId[32]=seq[80];
                        x[32]=x[80];
                        y[32]=y[80];
                        exist[32]=exist[80];
                    }
                    else if (musicId[80]==bbbbb)
                    {
                        seq[33]=seq[80];
                        musicId[33]=seq[80];
                        x[33]=x[80];
                        y[33]=y[80];
                        exist[33]=exist[80];
                    }
                    else if (musicId[80]==ccccc)
                    {
                        seq[34]=seq[80];
                        musicId[34]=seq[80];
                        x[34]=x[80];
                        y[34]=y[80];
                        exist[34]=exist[80];
                    }
                    else if (musicId[80]==ddddd)
                    {
                        seq[35]=seq[80];
                        musicId[35]=seq[80];
                        x[35]=x[80];
                        y[35]=y[80];
                        exist[35]=exist[80];
                    }
                    else if (musicId[80]==eeeee)
                    {
                        seq[36]=seq[80];
                        musicId[36]=seq[80];
                        x[36]=x[80];
                        y[36]=y[80];
                        exist[36]=exist[80];
                    }
                    else if (musicId[80]==fffff)
                    {
                        seq[37]=seq[80];
                        musicId[37]=seq[80];
                        x[37]=x[80];
                        y[37]=y[80];
                        exist[37]=exist[80];
                    }
                    else if (musicId[80]==ggggg)
                    {
                        seq[38]=seq[80];
                        musicId[38]=seq[80];
                        x[38]=x[80];
                        y[38]=y[80];
                        exist[38]=exist[80];
                    }
                    else if (musicId[80]==hhhhh)
                    {
                        seq[39]=seq[80];
                        musicId[39]=seq[80];
                        x[39]=x[80];
                        y[39]=y[80];
                        exist[39]=exist[80];
                    }
                    else if (musicId[80]==aaaaaa)  //복사
                    {
                        seq[40]=seq[80];
                        musicId[40]=seq[80];
                        x[40]=x[80];
                        y[40]=y[80];
                        exist[40]=exist[80];
                    }
                    else if (musicId[80]==bbbbbb)
                    {
                        seq[41]=seq[80];
                        musicId[41]=seq[80];
                        x[41]=x[80];
                        y[41]=y[80];
                        exist[41]=exist[80];
                    }
                    else if (musicId[80]==cccccc)
                    {
                        seq[42]=seq[80];
                        musicId[42]=seq[80];
                        x[42]=x[80];
                        y[42]=y[80];
                        exist[42]=exist[80];
                    }
                    else if (musicId[80]==dddddd)
                    {
                        seq[43]=seq[80];
                        musicId[43]=seq[80];
                        x[43]=x[80];
                        y[43]=y[80];
                        exist[43]=exist[80];
                    }
                    else if (musicId[80]==eeeeee)
                    {
                        seq[44]=seq[80];
                        musicId[44]=seq[80];
                        x[44]=x[80];
                        y[44]=y[80];
                        exist[44]=exist[80];
                    }
                    else if (musicId[80]==ffffff)
                    {
                        seq[45]=seq[80];
                        musicId[45]=seq[80];
                        x[45]=x[80];
                        y[45]=y[80];
                        exist[45]=exist[80];
                    }
                    else if (musicId[80]==gggggg)
                    {
                        seq[46]=seq[80];
                        musicId[46]=seq[80];
                        x[46]=x[80];
                        y[46]=y[80];
                        exist[46]=exist[80];
                    }
                    else if (musicId[80]==hhhhhh)
                    {
                        seq[47]=seq[80];
                        musicId[47]=seq[80];
                        x[47]=x[80];
                        y[47]=y[80];
                        exist[47]=exist[80];
                    }
                    else if (musicId[80]==aaaaaaa)  //복사
                    {
                        seq[48]=seq[80];
                        musicId[48]=seq[80];
                        x[48]=x[80];
                        y[48]=y[80];
                        exist[48]=exist[80];
                    }
                    else if (musicId[80]==bbbbbbb)
                    {
                        seq[49]=seq[80];
                        musicId[49]=seq[80];
                        x[49]=x[80];
                        y[49]=y[80];
                        exist[49]=exist[80];
                    }
                    else if (musicId[80]==ccccccc)
                    {
                        seq[50]=seq[80];
                        musicId[50]=seq[80];
                        x[50]=x[80];
                        y[50]=y[80];
                        exist[50]=exist[80];
                    }
                    else if (musicId[80]==ddddddd)
                    {
                        seq[51]=seq[80];
                        musicId[51]=seq[80];
                        x[51]=x[80];
                        y[51]=y[80];
                        exist[51]=exist[80];
                    }
                    else if (musicId[80]==eeeeeee)
                    {
                        seq[52]=seq[80];
                        musicId[52]=seq[80];
                        x[52]=x[80];
                        y[52]=y[80];
                        exist[52]=exist[80];
                    }
                    else if (musicId[80]==fffffff)
                    {
                        seq[53]=seq[80];
                        musicId[53]=seq[80];
                        x[53]=x[80];
                        y[53]=y[80];
                        exist[53]=exist[80];
                    }
                    else if (musicId[80]==ggggggg)
                    {
                        seq[54]=seq[80];
                        musicId[54]=seq[80];
                        x[54]=x[80];
                        y[54]=y[80];
                        exist[54]=exist[80];
                    }
                    else if (musicId[80]==hhhhhhh)
                    {
                        seq[55]=seq[80];
                        musicId[55]=seq[80];
                        x[55]=x[80];
                        y[55]=y[80];
                        exist[55]=exist[80];
                    }
                    else if (musicId[80]==aaaaaaaa)  //복사
                    {
                        seq[56]=seq[80];
                        musicId[56]=seq[80];
                        x[56]=x[80];
                        y[56]=y[80];
                        exist[56]=exist[80];
                    }
                    else if (musicId[80]==bbbbbbbb)
                    {
                        seq[57]=seq[80];
                        musicId[57]=seq[80];
                        x[57]=x[80];
                        y[57]=y[80];
                        exist[57]=exist[80];
                    }
                    else if (musicId[80]==cccccccc)
                    {
                        seq[58]=seq[80];
                        musicId[58]=seq[80];
                        x[58]=x[80];
                        y[58]=y[80];
                        exist[58]=exist[80];
                    }
                    else if (musicId[80]==dddddddd)
                    {
                        seq[59]=seq[80];
                        musicId[59]=seq[80];
                        x[59]=x[80];
                        y[59]=y[80];
                        exist[59]=exist[80];
                    }
                    else if (musicId[80]==eeeeeeee)
                    {
                        seq[60]=seq[80];
                        musicId[60]=seq[80];
                        x[60]=x[80];
                        y[60]=y[80];
                        exist[60]=exist[80];
                    }
                    else if (musicId[80]==ffffffff)
                    {
                        seq[61]=seq[80];
                        musicId[61]=seq[80];
                        x[61]=x[80];
                        y[61]=y[80];
                        exist[61]=exist[80];
                    }
                    else if (musicId[80]==gggggggg)
                    {
                        seq[62]=seq[80];
                        musicId[62]=seq[80];
                        x[62]=x[80];
                        y[62]=y[80];
                        exist[62]=exist[80];
                    }
                    else if (musicId[80]==hhhhhhhh)
                    {
                        seq[63]=seq[80];
                        musicId[63]=seq[80];
                        x[63]=x[80];
                        y[63]=y[80];
                        exist[63]=exist[80];
                    }
                    else if (musicId[80]==aaaaaaaaa)  //복사
                    {
                        seq[64]=seq[80];
                        musicId[64]=seq[80];
                        x[64]=x[80];
                        y[64]=y[80];
                        exist[64]=exist[80];
                    }
                    else if (musicId[80]==bbbbbbbbb)
                    {
                        seq[65]=seq[80];
                        musicId[65]=seq[80];
                        x[65]=x[80];
                        y[65]=y[80];
                        exist[65]=exist[80];
                    }
                    else if (musicId[80]==ccccccccc)
                    {
                        seq[66]=seq[80];
                        musicId[66]=seq[80];
                        x[66]=x[80];
                        y[66]=y[80];
                        exist[66]=exist[80];
                    }
                    else if (musicId[80]==ddddddddd)
                    {
                        seq[67]=seq[80];
                        musicId[67]=seq[80];
                        x[67]=x[80];
                        y[67]=y[80];
                        exist[67]=exist[80];
                    }
                    else if (musicId[80]==eeeeeeeee)
                    {
                        seq[68]=seq[80];
                        musicId[68]=seq[80];
                        x[68]=x[80];
                        y[68]=y[80];
                        exist[68]=exist[80];
                    }
                    else if (musicId[80]==fffffffff)
                    {
                        seq[69]=seq[80];
                        musicId[69]=seq[80];
                        x[69]=x[80];
                        y[69]=y[80];
                        exist[69]=exist[80];
                    }
                    else if (musicId[80]==ggggggggg)
                    {
                        seq[70]=seq[80];
                        musicId[70]=seq[80];
                        x[70]=x[80];
                        y[70]=y[80];
                        exist[70]=exist[80];
                    }
                    else if (musicId[80]==hhhhhhhhh)
                    {
                        seq[71]=seq[80];
                        musicId[71]=seq[80];
                        x[71]=x[80];
                        y[71]=y[80];
                        exist[71]=exist[80];
                    }
                    else if (musicId[80]==aaaaaaaaaa)  //복사
                    {
                        seq[72]=seq[80];
                        musicId[72]=seq[80];
                        x[72]=x[80];
                        y[72]=y[80];
                        exist[72]=exist[80];
                    }
                    else if (musicId[80]==bbbbbbbbbb)
                    {
                        seq[73]=seq[80];
                        musicId[73]=seq[80];
                        x[73]=x[80];
                        y[73]=y[80];
                        exist[73]=exist[80];
                    }
                    else if (musicId[80]==cccccccccc)
                    {
                        seq[74]=seq[80];
                        musicId[74]=seq[80];
                        x[74]=x[80];
                        y[74]=y[80];
                        exist[74]=exist[80];
                    }
                    else if (musicId[80]==dddddddddd)
                    {
                        seq[75]=seq[80];
                        musicId[75]=seq[80];
                        x[75]=x[80];
                        y[75]=y[80];
                        exist[75]=exist[80];
                    }
                    else if (musicId[80]==eeeeeeeeee)
                    {
                        seq[76]=seq[80];
                        musicId[76]=seq[80];
                        x[76]=x[80];
                        y[76]=y[80];
                        exist[76]=exist[80];
                    }
                    else if (musicId[80]==ffffffffff)
                    {
                        seq[77]=seq[80];
                        musicId[77]=seq[80];
                        x[77]=x[80];
                        y[77]=y[80];
                        exist[77]=exist[80];
                    }
                    else if (musicId[80]==gggggggggg)
                    {
                        seq[78]=seq[80];
                        musicId[78]=seq[80];
                        x[78]=x[80];
                        y[78]=y[80];
                        exist[78]=exist[80];
                    }
                    else if (musicId[80]==hhhhhhhhhh)
                    {
                        seq[79]=seq[80];
                        musicId[79]=seq[80];
                        x[79]=x[80];
                        y[79]=y[80];
                        exist[79]=exist[80];
                    }
                }



                imageView1 = (ImageView) findViewById(R.id.img1);
                imageView2 = (ImageView) findViewById(R.id.img2);
                imageView3 = (ImageView) findViewById(R.id.img3);
                imageView4 = (ImageView) findViewById(R.id.img4);
                imageView5 = (ImageView) findViewById(R.id.img5);
                imageView6 = (ImageView) findViewById(R.id.img6);
                imageView7 = (ImageView) findViewById(R.id.img7);
                imageView8 = (ImageView) findViewById(R.id.img8);
                imageView9 = (ImageView) findViewById(R.id.img9);
                imageView10 = (ImageView) findViewById(R.id.img10);
                imageView11 = (ImageView) findViewById(R.id.img11);
                imageView12 = (ImageView) findViewById(R.id.img12);
                imageView13 = (ImageView) findViewById(R.id.img13);
                imageView14 = (ImageView) findViewById(R.id.img14);
                imageView15 = (ImageView) findViewById(R.id.img15);
                imageView16 = (ImageView) findViewById(R.id.img16);
                imageView17 = (ImageView) findViewById(R.id.img17);
                imageView18 = (ImageView) findViewById(R.id.img18);
                imageView19 = (ImageView) findViewById(R.id.img19);
                imageView20 = (ImageView) findViewById(R.id.img20);
                imageView21 = (ImageView) findViewById(R.id.img21);
                imageView22 = (ImageView) findViewById(R.id.img22);
                imageView23 = (ImageView) findViewById(R.id.img23);
                imageView24 = (ImageView) findViewById(R.id.img24);
                imageView25 = (ImageView) findViewById(R.id.img25);
                imageView26 = (ImageView) findViewById(R.id.img26);
                imageView27 = (ImageView) findViewById(R.id.img27);
                imageView28 = (ImageView) findViewById(R.id.img28);
                imageView29 = (ImageView) findViewById(R.id.img29);
                imageView30 = (ImageView) findViewById(R.id.img30);
                imageView31 = (ImageView) findViewById(R.id.img31);
                imageView32 = (ImageView) findViewById(R.id.img32);
                imageView33 = (ImageView) findViewById(R.id.img33);
                imageView34 = (ImageView) findViewById(R.id.img34);
                imageView35 = (ImageView) findViewById(R.id.img35);
                imageView36 = (ImageView) findViewById(R.id.img36);
                imageView37 = (ImageView) findViewById(R.id.img37);
                imageView38 = (ImageView) findViewById(R.id.img38);
                imageView39 = (ImageView) findViewById(R.id.img39);
                imageView40 = (ImageView) findViewById(R.id.img40);
                imageView41 = (ImageView) findViewById(R.id.img41);
                imageView42 = (ImageView) findViewById(R.id.img42);
                imageView43 = (ImageView) findViewById(R.id.img43);
                imageView44 = (ImageView) findViewById(R.id.img44);
                imageView45 = (ImageView) findViewById(R.id.img45);
                imageView46 = (ImageView) findViewById(R.id.img46);
                imageView47 = (ImageView) findViewById(R.id.img47);
                imageView48 = (ImageView) findViewById(R.id.img48);
                imageView49 = (ImageView) findViewById(R.id.img49);
                imageView50 = (ImageView) findViewById(R.id.img50);
                imageView51 = (ImageView) findViewById(R.id.img51);
                imageView52 = (ImageView) findViewById(R.id.img52);
                imageView53 = (ImageView) findViewById(R.id.img53);
                imageView54 = (ImageView) findViewById(R.id.img54);
                imageView55 = (ImageView) findViewById(R.id.img55);
                imageView56 = (ImageView) findViewById(R.id.img56);
                imageView57 = (ImageView) findViewById(R.id.img57);
                imageView58 = (ImageView) findViewById(R.id.img58);
                imageView59 = (ImageView) findViewById(R.id.img59);
                imageView60 = (ImageView) findViewById(R.id.img60);
                imageView61 = (ImageView) findViewById(R.id.img61);
                imageView62 = (ImageView) findViewById(R.id.img62);
                imageView63 = (ImageView) findViewById(R.id.img63);
                imageView64 = (ImageView) findViewById(R.id.img64);
                imageView65 = (ImageView) findViewById(R.id.img65);
                imageView66 = (ImageView) findViewById(R.id.img66);
                imageView67 = (ImageView) findViewById(R.id.img67);
                imageView68 = (ImageView) findViewById(R.id.img68);
                imageView69 = (ImageView) findViewById(R.id.img69);
                imageView70 = (ImageView) findViewById(R.id.img70);
                imageView71 = (ImageView) findViewById(R.id.img71);
                imageView72 = (ImageView) findViewById(R.id.img72);
                imageView73 = (ImageView) findViewById(R.id.img73);
                imageView74 = (ImageView) findViewById(R.id.img74);
                imageView75 = (ImageView) findViewById(R.id.img75);
                imageView76 = (ImageView) findViewById(R.id.img76);
                imageView77 = (ImageView) findViewById(R.id.img77);
                imageView78 = (ImageView) findViewById(R.id.img78);
                imageView79 = (ImageView) findViewById(R.id.img79);
                imageView80 = (ImageView) findViewById(R.id.img80);









                //복사 복사


                if(x[0]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView1.setX((float) 116.43591);
                    imageView1.setY((float) 1150.4937); //a
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView1.setX(x[0]);
                    imageView1.setY(y[0]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[1]==0.0)
                {
                    imageView2.setX((float) 384.02466);//b
                    imageView2.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView2.setX(x[1]);
                    imageView2.setY(y[1]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[2]==0.0)
                {
                    imageView3.setX((float) 631.29675);
                    imageView3.setY((float) 1105.6864);//c
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView3.setX(x[2]);
                    imageView3.setY(y[2]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[3]==0.0)
                {
                    imageView4.setX((float) 853.9537);//d
                    imageView4.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView4.setX(x[3]);
                    imageView4.setY(y[3]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[4]==0.0)
                {
                    imageView5.setX((float) 149.48547);
                    imageView5.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");//e
                }
                else
                {
                    imageView5.setX(x[4]);
                    imageView5.setY(y[4]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[5]==0.0)
                {
                    imageView6.setX((float) 358.5067);
                    imageView6.setY((float) 1257.5405);//f
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView6.setX(x[5]);
                    imageView6.setY(y[5]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[6]==0.0)
                {
                    imageView7.setX((float) 602.4646);
                    imageView7.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView7.setX(x[6]);
                    imageView7.setY(y[6]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[7]==0.0)
                {
                    imageView8.setX((float) 849.999);
                    imageView8.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView8.setX(x[7]);
                    imageView8.setY(y[7]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[8]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView9.setX((float) 116.43591);
                    imageView9.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView9.setX(x[8]);
                    imageView9.setY(y[8]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[9]==0.0)
                {
                    imageView10.setX((float) 384.02466);
                    imageView10.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView10.setX(x[9]);
                    imageView10.setY(y[9]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[10]==0.0)
                {
                    imageView11.setX((float) 631.29675);
                    imageView11.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView11.setX(x[10]);
                    imageView11.setY(y[10]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[11]==0.0)
                {
                    imageView12.setX((float) 853.9537);
                    imageView12.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView12.setX(x[11]);
                    imageView12.setY(y[11]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[12]==0.0)
                {
                    imageView13.setX((float) 149.48547);
                    imageView13.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView13.setX(x[12]);
                    imageView13.setY(y[12]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[13]==0.0)
                {
                    imageView14.setX((float) 358.5067);
                    imageView14.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView14.setX(x[13]);
                    imageView14.setY(y[13]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[14]==0.0)
                {
                    imageView15.setX((float) 602.4646);
                    imageView15.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView15.setX(x[14]);
                    imageView15.setY(y[14]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[15]==0.0)
                {
                    imageView16.setX((float) 849.999);
                    imageView16.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView16.setX(x[15]);
                    imageView16.setY(y[15]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[16]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView17.setX((float) 116.43591);
                    imageView17.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView17.setX(x[16]);
                    imageView17.setY(y[16]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[17]==0.0)
                {
                    imageView18.setX((float) 384.02466);
                    imageView18.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView18.setX(x[17]);
                    imageView18.setY(y[17]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[18]==0.0)
                {
                    imageView19.setX((float) 631.29675);
                    imageView19.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView19.setX(x[18]);
                    imageView19.setY(y[18]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[19]==0.0)
                {
                    imageView20.setX((float) 853.9537);
                    imageView20.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView20.setX(x[19]);
                    imageView20.setY(y[19]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[20]==0.0)
                {
                    imageView21.setX((float) 149.48547);
                    imageView21.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView21.setX(x[20]);
                    imageView21.setY(y[20]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[21]==0.0)
                {
                    imageView22.setX((float) 358.5067);
                    imageView22.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView22.setX(x[21]);
                    imageView22.setY(y[21]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[22]==0.0)
                {
                    imageView23.setX((float) 602.4646);
                    imageView23.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView23.setX(x[22]);
                    imageView23.setY(y[22]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[23]==0.0)
                {
                    imageView24.setX((float) 849.999);
                    imageView24.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView24.setX(x[23]);
                    imageView24.setY(y[23]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[24]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView25.setX((float) 116.43591);
                    imageView25.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView25.setX(x[24]);
                    imageView25.setY(y[24]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[25]==0.0)
                {
                    imageView26.setX((float) 384.02466);
                    imageView26.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView26.setX(x[25]);
                    imageView26.setY(y[25]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[26]==0.0)
                {
                    imageView27.setX((float) 631.29675);
                    imageView27.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView27.setX(x[26]);
                    imageView27.setY(y[26]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[27]==0.0)
                {
                    imageView28.setX((float) 853.9537);
                    imageView28.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView28.setX(x[27]);
                    imageView28.setY(y[27]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[28]==0.0)
                {
                    imageView29.setX((float) 149.48547);
                    imageView29.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView29.setX(x[28]);
                    imageView29.setY(y[28]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[29]==0.0)
                {
                    imageView30.setX((float) 358.5067);
                    imageView30.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView30.setX(x[29]);
                    imageView30.setY(y[29]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[30]==0.0)
                {
                    imageView31.setX((float) 602.4646);
                    imageView31.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView31.setX(x[30]);
                    imageView31.setY(y[30]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[31]==0.0)
                {
                    imageView32.setX((float) 849.999);
                    imageView32.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView32.setX(x[31]);
                    imageView32.setY(y[31]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[32]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView33.setX((float) 116.43591);
                    imageView33.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView33.setX(x[32]);
                    imageView33.setY(y[32]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[33]==0.0)
                {
                    imageView34.setX((float) 384.02466);
                    imageView34.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView34.setX(x[33]);
                    imageView34.setY(y[33]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[34]==0.0)
                {
                    imageView35.setX((float) 631.29675);
                    imageView35.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView35.setX(x[34]);
                    imageView35.setY(y[34]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[35]==0.0)
                {
                    imageView36.setX((float) 853.9537);
                    imageView36.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView36.setX(x[35]);
                    imageView36.setY(y[35]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[36]==0.0)
                {
                    imageView37.setX((float) 149.48547);
                    imageView37.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView37.setX(x[36]);
                    imageView37.setY(y[36]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[37]==0.0)
                {
                    imageView38.setX((float) 358.5067);
                    imageView38.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView38.setX(x[37]);
                    imageView38.setY(y[37]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[38]==0.0)
                {
                    imageView39.setX((float) 602.4646);
                    imageView39.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView39.setX(x[38]);
                    imageView39.setY(y[38]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[39]==0.0)
                {
                    imageView40.setX((float) 849.999);
                    imageView40.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView40.setX(x[39]);
                    imageView40.setY(y[39]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[40]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView41.setX((float) 116.43591);
                    imageView41.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView41.setX(x[40]);
                    imageView41.setY(y[40]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[41]==0.0)
                {
                    imageView42.setX((float) 384.02466);
                    imageView42.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView42.setX(x[41]);
                    imageView42.setY(y[41]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[42]==0.0)
                {
                    imageView43.setX((float) 631.29675);
                    imageView43.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView43.setX(x[42]);
                    imageView43.setY(y[42]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[43]==0.0)
                {
                    imageView44.setX((float) 853.9537);
                    imageView44.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView44.setX(x[43]);
                    imageView44.setY(y[43]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[44]==0.0)
                {
                    imageView45.setX((float) 149.48547);
                    imageView45.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView45.setX(x[44]);
                    imageView45.setY(y[44]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[45]==0.0)
                {
                    imageView46.setX((float) 358.5067);
                    imageView46.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView46.setX(x[45]);
                    imageView46.setY(y[45]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[46]==0.0)
                {
                    imageView47.setX((float) 602.4646);
                    imageView47.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView47.setX(x[46]);
                    imageView47.setY(y[46]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[47]==0.0)
                {
                    imageView48.setX((float) 849.999);
                    imageView48.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView48.setX(x[47]);
                    imageView48.setY(y[47]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[48]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView49.setX((float) 116.43591);
                    imageView49.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView49.setX(x[48]);
                    imageView49.setY(y[48]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[49]==0.0)
                {
                    imageView50.setX((float) 384.02466);
                    imageView50.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView50.setX(x[49]);
                    imageView50.setY(y[49]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[50]==0.0)
                {
                    imageView51.setX((float) 631.29675);
                    imageView51.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView51.setX(x[50]);
                    imageView51.setY(y[50]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[51]==0.0)
                {
                    imageView52.setX((float) 853.9537);
                    imageView52.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView52.setX(x[51]);
                    imageView52.setY(y[51]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[52]==0.0)
                {
                    imageView53.setX((float) 149.48547);
                    imageView53.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView53.setX(x[52]);
                    imageView53.setY(y[52]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[53]==0.0)
                {
                    imageView54.setX((float) 358.5067);
                    imageView54.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView54.setX(x[53]);
                    imageView54.setY(y[53]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[54]==0.0)
                {
                    imageView55.setX((float) 602.4646);
                    imageView55.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView55.setX(x[54]);
                    imageView55.setY(y[54]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[55]==0.0)
                {
                    imageView56.setX((float) 849.999);
                    imageView56.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView56.setX(x[55]);
                    imageView56.setY(y[55]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[56]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView57.setX((float) 116.43591);
                    imageView57.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView57.setX(x[56]);
                    imageView57.setY(y[56]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[57]==0.0)
                {
                    imageView58.setX((float) 384.02466);
                    imageView58.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView58.setX(x[57]);
                    imageView58.setY(y[57]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[58]==0.0)
                {
                    imageView59.setX((float) 631.29675);
                    imageView59.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView59.setX(x[58]);
                    imageView59.setY(y[58]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[59]==0.0)
                {
                    imageView60.setX((float) 853.9537);
                    imageView60.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView60.setX(x[59]);
                    imageView60.setY(y[59]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[60]==0.0)
                {
                    imageView61.setX((float) 149.48547);
                    imageView61.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView61.setX(x[60]);
                    imageView61.setY(y[60]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[61]==0.0)
                {
                    imageView62.setX((float) 358.5067);
                    imageView62.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView62.setX(x[61]);
                    imageView62.setY(y[61]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[62]==0.0)
                {
                    imageView63.setX((float) 602.4646);
                    imageView63.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView63.setX(x[62]);
                    imageView63.setY(y[62]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[63]==0.0)
                {
                    imageView64.setX((float) 849.999);
                    imageView64.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView64.setX(x[63]);
                    imageView64.setY(y[63]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[64]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView65.setX((float) 116.43591);
                    imageView65.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView65.setX(x[64]);
                    imageView65.setY(y[64]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[65]==0.0)
                {
                    imageView66.setX((float) 384.02466);
                    imageView66.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView66.setX(x[65]);
                    imageView66.setY(y[65]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[66]==0.0)
                {
                    imageView67.setX((float) 631.29675);
                    imageView67.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView67.setX(x[66]);
                    imageView67.setY(y[66]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[67]==0.0)
                {
                    imageView68.setX((float) 853.9537);
                    imageView68.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView68.setX(x[67]);
                    imageView68.setY(y[67]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[68]==0.0)
                {
                    imageView69.setX((float) 149.48547);
                    imageView69.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView69.setX(x[68]);
                    imageView69.setY(y[68]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[69]==0.0)
                {
                    imageView70.setX((float) 358.5067);
                    imageView70.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView70.setX(x[69]);
                    imageView70.setY(y[69]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[70]==0.0)
                {
                    imageView71.setX((float) 602.4646);
                    imageView71.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView71.setX(x[70]);
                    imageView71.setY(y[70]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[71]==0.0)
                {
                    imageView72.setX((float) 849.999);
                    imageView72.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView72.setX(x[71]);
                    imageView72.setY(y[71]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[72]==0.0)                               // 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사 복사
                {
                    imageView73.setX((float) 116.43591);
                    imageView73.setY((float) 1150.4937);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView73.setX(x[72]);
                    imageView73.setY(y[72]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[73]==0.0)
                {
                    imageView74.setX((float) 384.02466);
                    imageView74.setY((float) 1089.9939);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView74.setX(x[73]);
                    imageView74.setY(y[73]);
                    Log.d("수정 : ", "수정상태");
                }

                if(x[74]==0.0)
                {
                    imageView75.setX((float) 631.29675);
                    imageView75.setY((float) 1105.6864);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView75.setX(x[74]);
                    imageView75.setY(y[74]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[75]==0.0)
                {
                    imageView76.setX((float) 853.9537);
                    imageView76.setY((float) 1118.5337);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView76.setX(x[75]);
                    imageView76.setY(y[75]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[76]==0.0)
                {
                    imageView77.setX((float) 149.48547);
                    imageView77.setY((float) 1285.1016);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView77.setX(x[76]);
                    imageView77.setY(y[76]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[77]==0.0)
                {
                    imageView78.setX((float) 358.5067);
                    imageView78.setY((float) 1257.5405);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView78.setX(x[77]);
                    imageView78.setY(y[77]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[78]==0.0)
                {
                    imageView79.setX((float) 602.4646);
                    imageView79.setY((float) 1276.9744);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView79.setX(x[78]);
                    imageView79.setY(y[78]);
                    Log.d("수정 : ", "수정상태");
                }
                if(x[79]==0.0)
                {
                    imageView80.setX((float) 849.999);
                    imageView80.setY((float) 1302.5059);
                    Log.d("초기 : ", "초기상태");
                }
                else
                {
                    imageView80.setX(x[79]);
                    imageView80.setY(y[79]);
                    Log.d("수정 : ", "수정상태");
                }

                imageView1.setOnTouchListener(new View.OnTouchListener() {          // 복사
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[0] = event.getX();
                            oldYvalue[0] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[0] + "," + oldYvalue[0]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[0]);
                            v.setY(event.getRawY() - (oldYvalue[0] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[0] = event.getRawX() - oldXvalue[0];
                            oldYvalue[0] = event.getRawY() - (oldYvalue[0] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 0 + " : " + oldXvalue[0] + "," + oldYvalue[0]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[0]);
                                requestData.accumulate("y", oldYvalue[0]);
                                Log.d("requestData a", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[0]==1){
                                if(oldXvalue[0]>800.0&oldXvalue[0]<1000.96875&oldYvalue[0] > -25.0&oldYvalue[0]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData a", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[0]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }

                        return true;
                    }

                });
                imageView2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[1] = event.getX();
                            oldYvalue[1] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[1] + "," + oldYvalue[1]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[1]);
                            v.setY(event.getRawY() - (oldYvalue[1] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[1] = event.getRawX() - oldXvalue[1];
                            oldYvalue[1] = event.getRawY() - (oldYvalue[1] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 1 + " : " + oldXvalue[1] + "," + oldYvalue[1]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[1]);
                                requestData.accumulate("y", oldYvalue[1]);
                                Log.d("requestData b", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[1]==1){
                                if(oldXvalue[1]>800.0&oldXvalue[1]<1000.96875&oldYvalue[1] > -25.0&oldYvalue[1]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData b", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[1]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView3.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[2] = event.getX();
                            oldYvalue[2] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[2] + "," + oldYvalue[2]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[2]);
                            v.setY(event.getRawY() - (oldYvalue[2] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[2] = event.getRawX() - oldXvalue[2];
                            oldYvalue[2] = event.getRawY() - (oldYvalue[2] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 2 + " : " + oldXvalue[2] + "," + oldYvalue[2]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[2]);
                                requestData.accumulate("y", oldYvalue[2]);
                                Log.d("requestData c", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[2]==1){
                                if(oldXvalue[2]>800.0&oldXvalue[2]<1000.96875&oldYvalue[2] > -25.0&oldYvalue[2]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData c", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[2]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView4.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[3] = event.getX();
                            oldYvalue[3] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[3] + "," + oldYvalue[3]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[3]);
                            v.setY(event.getRawY() - (oldYvalue[3] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[3] = event.getRawX() - oldXvalue[3];
                            oldYvalue[3] = event.getRawY() - (oldYvalue[3] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 3 + " : " + oldXvalue[3] + "," + oldYvalue[3]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[3]);
                                requestData.accumulate("y", oldYvalue[3]);
                                Log.d("requestData d", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[3]==1){
                                if(oldXvalue[3]>800.0&oldXvalue[3]<1000.96875&oldYvalue[3] > -25.0&oldYvalue[3]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData d", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[3]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView5.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[4] = event.getX();
                            oldYvalue[4] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[4] + "," + oldYvalue[4]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[4]);
                            v.setY(event.getRawY() - (oldYvalue[4] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[4] = event.getRawX() - oldXvalue[4];
                            oldYvalue[4] = event.getRawY() - (oldYvalue[4] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[4] + "," + oldYvalue[4]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[4]);
                                requestData.accumulate("y", oldYvalue[4]);
                                Log.d("requestData e", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[4]==1){
                                if(oldXvalue[4]>800.0&oldXvalue[4]<1000.96875&oldYvalue[4] > -25.0&oldYvalue[4]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData e", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[4]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView6.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[5] = event.getX();
                            oldYvalue[5] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[5] + "," + oldYvalue[5]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[5]);
                            v.setY(event.getRawY() - (oldYvalue[5] + v.getHeight()*3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[5] = event.getRawX() - oldXvalue[5];
                            oldYvalue[5] = event.getRawY() - (oldYvalue[5] + v.getHeight()*3);
                            Log.i("Tag1", "Action Down rX 1 " + 5 + " : " + oldXvalue[5] + "," + oldYvalue[5]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[5]);
                                requestData.accumulate("y", oldYvalue[5]);
                                Log.d("requestData f", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[5]==1){
                                if(oldXvalue[5]>800.0&oldXvalue[5]<1000.96875&oldYvalue[5] > -25.0&oldYvalue[5]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData f", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[5]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView7.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[6] = event.getX();
                            oldYvalue[6] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[6] + "," + oldYvalue[6]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[6]);
                            v.setY(event.getRawY() - (oldYvalue[6] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[6] = event.getRawX() - oldXvalue[6];
                            oldYvalue[6] = event.getRawY() - (oldYvalue[6] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[6] + "," + oldYvalue[6]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[6]);
                                requestData.accumulate("y", oldYvalue[6]);
                                Log.d("requestData g", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[6]==1){
                                if(oldXvalue[6]>800.0&oldXvalue[6]<1000.96875&oldYvalue[6] > -25.0&oldYvalue[6]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData g", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[6]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView8.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[7] = event.getX();
                            oldYvalue[7] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[7] + "," + oldYvalue[7]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[7]);
                            v.setY(event.getRawY() - (oldYvalue[7] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[7] = event.getRawX() - oldXvalue[7];
                            oldYvalue[7] = event.getRawY() - (oldYvalue[7] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 7 + " : " + oldXvalue[7] + "," + oldYvalue[7]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[7]);
                                requestData.accumulate("y", oldYvalue[7]);
                                Log.d("requestData h", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[7]==1){
                                if(oldXvalue[7]>800.0&oldXvalue[7]<1000.96875&oldYvalue[7] > -25.0&oldYvalue[7]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData h", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[7]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView9.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[8] = event.getX();
                            oldYvalue[8] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[8] + "," + oldYvalue[8]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[8]);
                            v.setY(event.getRawY() - (oldYvalue[8] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[8] = event.getRawX() - oldXvalue[8];
                            oldYvalue[8] = event.getRawY() - (oldYvalue[8] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 0 + " : " + oldXvalue[8] + "," + oldYvalue[8]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[8]);
                                requestData.accumulate("y", oldYvalue[8]);
                                Log.d("requestData aa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[8]==1){
                                if(oldXvalue[8]>800.0&oldXvalue[8]<1000.96875&oldYvalue[8] > -25.0&oldYvalue[8]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData aa", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[8]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }

                        return true;
                    }

                });
                imageView10.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[9] = event.getX();
                            oldYvalue[9] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[9] + "," + oldYvalue[9]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[9]);
                            v.setY(event.getRawY() - (oldYvalue[9] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[9] = event.getRawX() - oldXvalue[9];
                            oldYvalue[9] = event.getRawY() - (oldYvalue[9] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[9] + "," + oldYvalue[9]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[9]);
                                requestData.accumulate("y", oldYvalue[9]);
                                Log.d("requestData bb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[9]==1){
                                if(oldXvalue[9]>800.0&oldXvalue[9]<1000.96875&oldYvalue[9] > -25.0&oldYvalue[9]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData bb", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[9]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView11.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[10] = event.getX();
                            oldYvalue[10] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[10] + "," + oldYvalue[10]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[10]);
                            v.setY(event.getRawY() - (oldYvalue[10] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[10] = event.getRawX() - oldXvalue[10];
                            oldYvalue[10] = event.getRawY() - (oldYvalue[10] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[10] + "," + oldYvalue[10]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[10]);
                                requestData.accumulate("y", oldYvalue[10]);
                                Log.d("requestData cc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[10]==1){
                                if(oldXvalue[10]>800.0&oldXvalue[10]<1000.96875&oldYvalue[10] > -25.0&oldYvalue[10]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData cc", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[10]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView12.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[11] = event.getX();
                            oldYvalue[11] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[11] + "," + oldYvalue[11]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[11]);
                            v.setY(event.getRawY() - (oldYvalue[11] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[11] = event.getRawX() - oldXvalue[11];
                            oldYvalue[11] = event.getRawY() - (oldYvalue[11] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[11] + "," + oldYvalue[11]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[11]);
                                requestData.accumulate("y", oldYvalue[11]);
                                Log.d("requestData dd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[11]==1){
                                if(oldXvalue[11]>800.0&oldXvalue[11]<1000.96875&oldYvalue[11] > -25.0&oldYvalue[11]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData dd", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[11]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView13.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[12] = event.getX();
                            oldYvalue[12] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[12] + "," + oldYvalue[12]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[12]);
                            v.setY(event.getRawY() - (oldYvalue[12] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[12] = event.getRawX() - oldXvalue[12];
                            oldYvalue[12] = event.getRawY() - (oldYvalue[12] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[12] + "," + oldYvalue[12]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[12]);
                                requestData.accumulate("y", oldYvalue[12]);
                                Log.d("requestData ee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[12]==1){
                                if(oldXvalue[12]>800.0&oldXvalue[12]<1000.96875&oldYvalue[12] > -25.0&oldYvalue[12]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData ee", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[12]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView14.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[13] = event.getX();
                            oldYvalue[13] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[13] + "," + oldYvalue[13]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[13]);
                            v.setY(event.getRawY() - (oldYvalue[13] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[13] = event.getRawX() - oldXvalue[13];
                            oldYvalue[13] = event.getRawY() - (oldYvalue[13] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[13] + "," + oldYvalue[13]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[13]);
                                requestData.accumulate("y", oldYvalue[13]);
                                Log.d("requestData ff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[13]==1){
                                if(oldXvalue[13]>800.0&oldXvalue[13]<1000.96875&oldYvalue[13] > -25.0&oldYvalue[13]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData ff", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[13]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView15.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[14] = event.getX();
                            oldYvalue[14] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[14] + "," + oldYvalue[14]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[14]);
                            v.setY(event.getRawY() - (oldYvalue[14] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[14] = event.getRawX() - oldXvalue[14];
                            oldYvalue[14] = event.getRawY() - (oldYvalue[14] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[14] + "," + oldYvalue[14]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[14]);
                                requestData.accumulate("y", oldYvalue[14]);
                                Log.d("requestData gg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[14]==1){
                                if(oldXvalue[14]>800.0&oldXvalue[14]<1000.96875&oldYvalue[14] > -25.0&oldYvalue[14]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData gg", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[14]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView16.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[15] = event.getX();
                            oldYvalue[15] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[15] + "," + oldYvalue[15]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[15]);
                            v.setY(event.getRawY() - (oldYvalue[15] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[15] = event.getRawX() - oldXvalue[15];
                            oldYvalue[15] = event.getRawY() - (oldYvalue[15] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[15] + "," + oldYvalue[15]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[15]);
                                requestData.accumulate("y", oldYvalue[15]);
                                Log.d("requestData hh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[15]==1){
                                if(oldXvalue[15]>800.0&oldXvalue[15]<1000.96875&oldYvalue[15] > -25.0&oldYvalue[15]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData hh", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[15]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView17.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[16] = event.getX();
                            oldYvalue[16] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[16] + "," + oldYvalue[16]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[16]);
                            v.setY(event.getRawY() - (oldYvalue[16] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[16] = event.getRawX() - oldXvalue[16];
                            oldYvalue[16] = event.getRawY() - (oldYvalue[16] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[16] + "," + oldYvalue[16]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[16]);
                                requestData.accumulate("y", oldYvalue[16]);
                                Log.d("requestData aaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[16]==1){
                                if(oldXvalue[16]>800.0&oldXvalue[16]<1000.96875&oldYvalue[16] > -25.0&oldYvalue[16]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData aaa", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[16]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView18.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[17] = event.getX();
                            oldYvalue[17] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[17] + "," + oldYvalue[17]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[17]);
                            v.setY(event.getRawY() - (oldYvalue[17] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[17] = event.getRawX() - oldXvalue[17];
                            oldYvalue[17] = event.getRawY() - (oldYvalue[17] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[17] + "," + oldYvalue[17]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[17]);
                                requestData.accumulate("y", oldYvalue[17]);
                                Log.d("requestData bbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[17]==1){
                                if(oldXvalue[17]>800.0&oldXvalue[17]<1000.96875&oldYvalue[17] > -25.0&oldYvalue[17]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData bbb", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[17]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView19.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[18] = event.getX();
                            oldYvalue[18] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[18] + "," + oldYvalue[18]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[18]);
                            v.setY(event.getRawY() - (oldYvalue[18] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[18] = event.getRawX() - oldXvalue[18];
                            oldYvalue[18] = event.getRawY() - (oldYvalue[18] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[18] + "," + oldYvalue[18]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[18]);
                                requestData.accumulate("y", oldYvalue[18]);
                                Log.d("requestData ccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[18]==1){
                                if(oldXvalue[18]>800.0&oldXvalue[18]<1000.96875&oldYvalue[18] > -25.0&oldYvalue[18]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData ccc", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[18]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView20.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[19] = event.getX();
                            oldYvalue[19] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[19] + "," + oldYvalue[19]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[19]);
                            v.setY(event.getRawY() - (oldYvalue[19] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[19] = event.getRawX() - oldXvalue[19];
                            oldYvalue[19] = event.getRawY() - (oldYvalue[19] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[19] + "," + oldYvalue[19]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[19]);
                                requestData.accumulate("y", oldYvalue[19]);
                                Log.d("requestData ddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[19]==1){
                                if(oldXvalue[19]>800.0&oldXvalue[19]<1000.96875&oldYvalue[19] > -25.0&oldYvalue[19]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData ddd", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[19]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView21.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[20] = event.getX();
                            oldYvalue[20] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[20] + "," + oldYvalue[20]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[20]);
                            v.setY(event.getRawY() - (oldYvalue[20] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[20] = event.getRawX() - oldXvalue[20];
                            oldYvalue[20] = event.getRawY() - (oldYvalue[20] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[20] + "," + oldYvalue[20]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[20]);
                                requestData.accumulate("y", oldYvalue[20]);
                                Log.d("requestData eee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[20]==1){
                                if(oldXvalue[20]>800.0&oldXvalue[20]<1000.96875&oldYvalue[20] > -25.0&oldYvalue[20]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData eee", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[20]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView22.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[21] = event.getX();
                            oldYvalue[21] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[21] + "," + oldYvalue[21]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[21]);
                            v.setY(event.getRawY() - (oldYvalue[21] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[21] = event.getRawX() - oldXvalue[21];
                            oldYvalue[21] = event.getRawY() - (oldYvalue[21] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[21] + "," + oldYvalue[21]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[21]);
                                requestData.accumulate("y", oldYvalue[21]);
                                Log.d("requestData fff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[21]==1){
                                if(oldXvalue[21]>800.0&oldXvalue[21]<1000.96875&oldYvalue[21] > -25.0&oldYvalue[21]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData21", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[21]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView23.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[22] = event.getX();
                            oldYvalue[22] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[22] + "," + oldYvalue[22]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[22]);
                            v.setY(event.getRawY() - (oldYvalue[22] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[22] = event.getRawX() - oldXvalue[22];
                            oldYvalue[22] = event.getRawY() - (oldYvalue[22] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[22] + "," + oldYvalue[22]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[22]);
                                requestData.accumulate("y", oldYvalue[22]);
                                Log.d("requestData ggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[22]==1){
                                if(oldXvalue[22]>800.0&oldXvalue[22]<1000.96875&oldYvalue[22] > -25.0&oldYvalue[22]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData22", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[22]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView24.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[23] = event.getX();
                            oldYvalue[23] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[23] + "," + oldYvalue[23]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[23]);
                            v.setY(event.getRawY() - (oldYvalue[23] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[23] = event.getRawX() - oldXvalue[23];
                            oldYvalue[23] = event.getRawY() - (oldYvalue[23] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[23] + "," + oldYvalue[23]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[23]);
                                requestData.accumulate("y", oldYvalue[23]);
                                Log.d("requestData hhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[23]==1){
                                if(oldXvalue[23]>800.0&oldXvalue[23]<1000.96875&oldYvalue[23] > -25.0&oldYvalue[23]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData23", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[23]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView25.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[24] = event.getX();
                            oldYvalue[24] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[24] + "," + oldYvalue[24]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[24]);
                            v.setY(event.getRawY() - (oldYvalue[24] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[24] = event.getRawX() - oldXvalue[24];
                            oldYvalue[24] = event.getRawY() - (oldYvalue[24] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[24] + "," + oldYvalue[24]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[24]);
                                requestData.accumulate("y", oldYvalue[24]);
                                Log.d("requestData24 aaaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[24]==1){
                                if(oldXvalue[24]>800.0&oldXvalue[24]<1000.96875&oldYvalue[24] > -25.0&oldYvalue[24]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[24]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView26.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[25] = event.getX();
                            oldYvalue[25] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[25] + "," + oldYvalue[25]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[25]);
                            v.setY(event.getRawY() - (oldYvalue[25] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[25] = event.getRawX() - oldXvalue[25];
                            oldYvalue[25] = event.getRawY() - (oldYvalue[25] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[25] + "," + oldYvalue[25]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[25]);
                                requestData.accumulate("y", oldYvalue[25]);
                                Log.d("requestData bbbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[25]==1){
                                if(oldXvalue[25]>800.0&oldXvalue[25]<1000.96875&oldYvalue[25] > -25.0&oldYvalue[25]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[25]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView27.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[26] = event.getX();
                            oldYvalue[26] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[26] + "," + oldYvalue[26]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[26]);
                            v.setY(event.getRawY() - (oldYvalue[26] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[26] = event.getRawX() - oldXvalue[26];
                            oldYvalue[26] = event.getRawY() - (oldYvalue[26] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[26] + "," + oldYvalue[26]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[26]);
                                requestData.accumulate("y", oldYvalue[26]);
                                Log.d("requestData cccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[26]==1){
                                if(oldXvalue[26]>800.0&oldXvalue[26]<1000.96875&oldYvalue[26] > -25.0&oldYvalue[26]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[26]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView28.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[27] = event.getX();
                            oldYvalue[27] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[27] + "," + oldYvalue[27]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[27]);
                            v.setY(event.getRawY() - (oldYvalue[27] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[27] = event.getRawX() - oldXvalue[27];
                            oldYvalue[27] = event.getRawY() - (oldYvalue[27] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[27] + "," + oldYvalue[27]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[27]);
                                requestData.accumulate("y", oldYvalue[27]);
                                Log.d("requestData dddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[27]==1){
                                if(oldXvalue[27]>800.0&oldXvalue[27]<1000.96875&oldYvalue[27] > -25.0&oldYvalue[27]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[27]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView29.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[28] = event.getX();
                            oldYvalue[28] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[28] + "," + oldYvalue[28]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[28]);
                            v.setY(event.getRawY() - (oldYvalue[28] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[28] = event.getRawX() - oldXvalue[28];
                            oldYvalue[28] = event.getRawY() - (oldYvalue[28] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[28] + "," + oldYvalue[28]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[28]);
                                requestData.accumulate("y", oldYvalue[28]);
                                Log.d("requestData eeee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[28]==1){
                                if(oldXvalue[28]>800.0&oldXvalue[28]<1000.96875&oldYvalue[28] > -25.0&oldYvalue[28]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[28]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView30.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[29] = event.getX();
                            oldYvalue[29] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[29] + "," + oldYvalue[29]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[29]);
                            v.setY(event.getRawY() - (oldYvalue[29] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[29] = event.getRawX() - oldXvalue[29];
                            oldYvalue[29] = event.getRawY() - (oldYvalue[29] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[29] + "," + oldYvalue[29]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[29]);
                                requestData.accumulate("y", oldYvalue[29]);
                                Log.d("requestData ffff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[29]==1){
                                if(oldXvalue[29]>800.0&oldXvalue[29]<1000.96875&oldYvalue[29] > -25.0&oldYvalue[29]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[29]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView31.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[30] = event.getX();
                            oldYvalue[30] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[30] + "," + oldYvalue[30]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[30]);
                            v.setY(event.getRawY() - (oldYvalue[30] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[30] = event.getRawX() - oldXvalue[30];
                            oldYvalue[30] = event.getRawY() - (oldYvalue[30] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[30] + "," + oldYvalue[30]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[30]);
                                requestData.accumulate("y", oldYvalue[30]);
                                Log.d("requestData gggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[30]==1){
                                if(oldXvalue[30]>800.0&oldXvalue[30]<1000.96875&oldYvalue[30] > -25.0&oldYvalue[30]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[30]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView32.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[31] = event.getX();
                            oldYvalue[31] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[31] + "," + oldYvalue[31]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[31]);
                            v.setY(event.getRawY() - (oldYvalue[31] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[31] = event.getRawX() - oldXvalue[31];
                            oldYvalue[31] = event.getRawY() - (oldYvalue[31] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[31] + "," + oldYvalue[31]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[31]);
                                requestData.accumulate("y", oldYvalue[31]);
                                Log.d("requestData hhhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[31]==1){
                                if(oldXvalue[31]>800.0&oldXvalue[31]<1000.96875&oldYvalue[31] > -25.0&oldYvalue[31]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[31]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView33.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[32] = event.getX();
                            oldYvalue[32] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[32] + "," + oldYvalue[32]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[32]);
                            v.setY(event.getRawY() - (oldYvalue[32] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[32] = event.getRawX() - oldXvalue[32];
                            oldYvalue[32] = event.getRawY() - (oldYvalue[32] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[32] + "," + oldYvalue[32]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[32]);
                                requestData.accumulate("y", oldYvalue[32]);
                                Log.d("requestData32 aaaaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[32]==1){
                                if(oldXvalue[32]>800.0&oldXvalue[32]<1000.96875&oldYvalue[32] > -25.0&oldYvalue[32]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[32]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView34.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[33] = event.getX();
                            oldYvalue[33] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[33] + "," + oldYvalue[33]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[33]);
                            v.setY(event.getRawY() - (oldYvalue[33] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[33] = event.getRawX() - oldXvalue[33];
                            oldYvalue[33] = event.getRawY() - (oldYvalue[33] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[33] + "," + oldYvalue[33]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[33]);
                                requestData.accumulate("y", oldYvalue[33]);
                                Log.d("requestData bbbbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[33]==1){
                                if(oldXvalue[33]>800.0&oldXvalue[33]<1000.96875&oldYvalue[33] > -25.0&oldYvalue[33]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[33]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView35.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[34] = event.getX();
                            oldYvalue[34] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[34] + "," + oldYvalue[34]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[34]);
                            v.setY(event.getRawY() - (oldYvalue[34] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[34] = event.getRawX() - oldXvalue[34];
                            oldYvalue[34] = event.getRawY() - (oldYvalue[34] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[34] + "," + oldYvalue[34]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[34]);
                                requestData.accumulate("y", oldYvalue[34]);
                                Log.d("requestData ccccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[34]==1){
                                if(oldXvalue[34]>800.0&oldXvalue[34]<1000.96875&oldYvalue[34] > -25.0&oldYvalue[34]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[34]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView36.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[35] = event.getX();
                            oldYvalue[35] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[35] + "," + oldYvalue[35]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[35]);
                            v.setY(event.getRawY() - (oldYvalue[35] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[35] = event.getRawX() - oldXvalue[35];
                            oldYvalue[35] = event.getRawY() - (oldYvalue[35] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[35] + "," + oldYvalue[35]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[35]);
                                requestData.accumulate("y", oldYvalue[35]);
                                Log.d("requestData ddddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[35]==1){
                                if(oldXvalue[35]>800.0&oldXvalue[35]<1000.96875&oldYvalue[35] > -25.0&oldYvalue[35]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[35]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView37.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[36] = event.getX();
                            oldYvalue[36] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[36] + "," + oldYvalue[36]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[36]);
                            v.setY(event.getRawY() - (oldYvalue[36] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[36] = event.getRawX() - oldXvalue[36];
                            oldYvalue[36] = event.getRawY() - (oldYvalue[36] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[36] + "," + oldYvalue[36]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[36]);
                                requestData.accumulate("y", oldYvalue[36]);
                                Log.d("requestData eeeee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[36]==1){
                                if(oldXvalue[36]>800.0&oldXvalue[36]<1000.96875&oldYvalue[36] > -25.0&oldYvalue[36]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[36]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView38.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[37] = event.getX();
                            oldYvalue[37] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[37] + "," + oldYvalue[37]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[37]);
                            v.setY(event.getRawY() - (oldYvalue[37] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[37] = event.getRawX() - oldXvalue[37];
                            oldYvalue[37] = event.getRawY() - (oldYvalue[37] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[37] + "," + oldYvalue[37]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[37]);
                                requestData.accumulate("y", oldYvalue[37]);
                                Log.d("requestData fffff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[37]==1){
                                if(oldXvalue[37]>800.0&oldXvalue[37]<1000.96875&oldYvalue[37] > -25.0&oldYvalue[37]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[37]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView39.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[38] = event.getX();
                            oldYvalue[38] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[38] + "," + oldYvalue[38]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[38]);
                            v.setY(event.getRawY() - (oldYvalue[38] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[38] = event.getRawX() - oldXvalue[38];
                            oldYvalue[38] = event.getRawY() - (oldYvalue[38] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[38] + "," + oldYvalue[38]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[38]);
                                requestData.accumulate("y", oldYvalue[38]);
                                Log.d("requestData ggggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[38]==1){
                                if(oldXvalue[38]>800.0&oldXvalue[38]<1000.96875&oldYvalue[38] > -25.0&oldYvalue[38]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[38]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView40.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[39] = event.getX();
                            oldYvalue[39] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[39] + "," + oldYvalue[39]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[39]);
                            v.setY(event.getRawY() - (oldYvalue[39] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[39] = event.getRawX() - oldXvalue[39];
                            oldYvalue[39] = event.getRawY() - (oldYvalue[39] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[39] + "," + oldYvalue[39]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[39]);
                                requestData.accumulate("y", oldYvalue[39]);
                                Log.d("requestData hhhhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[39]==1){
                                if(oldXvalue[39]>800.0&oldXvalue[39]<1000.96875&oldYvalue[39] > -25.0&oldYvalue[39]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[39]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView41.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[40] = event.getX();
                            oldYvalue[40] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[40] + "," + oldYvalue[40]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[40]);
                            v.setY(event.getRawY() - (oldYvalue[40] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[40] = event.getRawX() - oldXvalue[40];
                            oldYvalue[40] = event.getRawY() - (oldYvalue[40] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[40] + "," + oldYvalue[40]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[40]);
                                requestData.accumulate("y", oldYvalue[40]);
                                Log.d("requestData40 aaaaaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[40]==1){
                                if(oldXvalue[40]>800.0&oldXvalue[40]<1000.96875&oldYvalue[40] > -25.0&oldYvalue[40]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[40]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView42.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[41] = event.getX();
                            oldYvalue[41] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[41] + "," + oldYvalue[41]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[41]);
                            v.setY(event.getRawY() - (oldYvalue[41] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[41] = event.getRawX() - oldXvalue[41];
                            oldYvalue[41] = event.getRawY() - (oldYvalue[41] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[41] + "," + oldYvalue[41]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[41]);
                                requestData.accumulate("y", oldYvalue[41]);
                                Log.d("requestData bbbbbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[41]==1){
                                if(oldXvalue[41]>800.0&oldXvalue[41]<1000.96875&oldYvalue[41] > -25.0&oldYvalue[41]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[41]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView43.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[42] = event.getX();
                            oldYvalue[42] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[42] + "," + oldYvalue[42]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[42]);
                            v.setY(event.getRawY() - (oldYvalue[42] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[42] = event.getRawX() - oldXvalue[42];
                            oldYvalue[42] = event.getRawY() - (oldYvalue[42] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[42] + "," + oldYvalue[42]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[42]);
                                requestData.accumulate("y", oldYvalue[42]);
                                Log.d("requestData cccccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[42]==1){
                                if(oldXvalue[42]>800.0&oldXvalue[42]<1000.96875&oldYvalue[42] > -25.0&oldYvalue[42]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[42]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView44.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[43] = event.getX();
                            oldYvalue[43] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[43] + "," + oldYvalue[43]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[43]);
                            v.setY(event.getRawY() - (oldYvalue[43] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[43] = event.getRawX() - oldXvalue[43];
                            oldYvalue[43] = event.getRawY() - (oldYvalue[43] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[43] + "," + oldYvalue[43]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[43]);
                                requestData.accumulate("y", oldYvalue[43]);
                                Log.d("requestData dddddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[43]==1){
                                if(oldXvalue[43]>800.0&oldXvalue[43]<1000.96875&oldYvalue[43] > -25.0&oldYvalue[43]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[43]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView45.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[44] = event.getX();
                            oldYvalue[44] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[44] + "," + oldYvalue[44]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[44]);
                            v.setY(event.getRawY() - (oldYvalue[44] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[44] = event.getRawX() - oldXvalue[44];
                            oldYvalue[44] = event.getRawY() - (oldYvalue[44] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[44] + "," + oldYvalue[44]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[44]);
                                requestData.accumulate("y", oldYvalue[44]);
                                Log.d("requestData eeeeee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[44]==1){
                                if(oldXvalue[44]>800.0&oldXvalue[44]<1000.96875&oldYvalue[44] > -25.0&oldYvalue[44]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[44]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView46.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[45] = event.getX();
                            oldYvalue[45] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[45] + "," + oldYvalue[45]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[45]);
                            v.setY(event.getRawY() - (oldYvalue[45] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[45] = event.getRawX() - oldXvalue[45];
                            oldYvalue[45] = event.getRawY() - (oldYvalue[45] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[45] + "," + oldYvalue[45]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[45]);
                                requestData.accumulate("y", oldYvalue[45]);
                                Log.d("requestData ffffff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[45]==1){
                                if(oldXvalue[45]>800.0&oldXvalue[45]<1000.96875&oldYvalue[45] > -25.0&oldYvalue[45]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[45]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView47.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[46] = event.getX();
                            oldYvalue[46] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[46] + "," + oldYvalue[46]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[46]);
                            v.setY(event.getRawY() - (oldYvalue[46] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[46] = event.getRawX() - oldXvalue[46];
                            oldYvalue[46] = event.getRawY() - (oldYvalue[46] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[46] + "," + oldYvalue[46]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[46]);
                                requestData.accumulate("y", oldYvalue[46]);
                                Log.d("requestData gggggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[46]==1){
                                if(oldXvalue[46]>800.0&oldXvalue[46]<1000.96875&oldYvalue[46] > -25.0&oldYvalue[46]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[46]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView48.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[47] = event.getX();
                            oldYvalue[47] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[47] + "," + oldYvalue[47]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[47]);
                            v.setY(event.getRawY() - (oldYvalue[47] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[47] = event.getRawX() - oldXvalue[47];
                            oldYvalue[47] = event.getRawY() - (oldYvalue[47] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[47] + "," + oldYvalue[47]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[47]);
                                requestData.accumulate("y", oldYvalue[47]);
                                Log.d("requestData hhhhhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[47]==1){
                                if(oldXvalue[47]>800.0&oldXvalue[47]<1000.96875&oldYvalue[47] > -25.0&oldYvalue[47]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[47]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView49.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[48] = event.getX();
                            oldYvalue[48] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[48] + "," + oldYvalue[48]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[48]);
                            v.setY(event.getRawY() - (oldYvalue[48] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[48] = event.getRawX() - oldXvalue[48];
                            oldYvalue[48] = event.getRawY() - (oldYvalue[48] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[48] + "," + oldYvalue[48]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[48]);
                                requestData.accumulate("y", oldYvalue[48]);
                                Log.d("requestData48 aaaaaaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[48]==1){
                                if(oldXvalue[48]>800.0&oldXvalue[48]<1000.96875&oldYvalue[48] > -25.0&oldYvalue[48]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[48]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView50.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[49] = event.getX();
                            oldYvalue[49] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[49] + "," + oldYvalue[49]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[49]);
                            v.setY(event.getRawY() - (oldYvalue[49] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[49] = event.getRawX() - oldXvalue[49];
                            oldYvalue[49] = event.getRawY() - (oldYvalue[49] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[49] + "," + oldYvalue[49]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[49]);
                                requestData.accumulate("y", oldYvalue[49]);
                                Log.d("requestData bbbbbbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[49]==1){
                                if(oldXvalue[49]>800.0&oldXvalue[49]<1000.96875&oldYvalue[49] > -25.0&oldYvalue[49]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[49]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView51.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[50] = event.getX();
                            oldYvalue[50] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[50] + "," + oldYvalue[50]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[50]);
                            v.setY(event.getRawY() - (oldYvalue[50] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[50] = event.getRawX() - oldXvalue[50];
                            oldYvalue[50] = event.getRawY() - (oldYvalue[50] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[50] + "," + oldYvalue[50]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[50]);
                                requestData.accumulate("y", oldYvalue[50]);
                                Log.d("requestData ccccccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[50]==1){
                                if(oldXvalue[50]>800.0&oldXvalue[50]<1000.96875&oldYvalue[50] > -25.0&oldYvalue[50]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[50]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView52.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[51] = event.getX();
                            oldYvalue[51] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[51] + "," + oldYvalue[51]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[51]);
                            v.setY(event.getRawY() - (oldYvalue[51] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[51] = event.getRawX() - oldXvalue[51];
                            oldYvalue[51] = event.getRawY() - (oldYvalue[51] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[51] + "," + oldYvalue[51]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[51]);
                                requestData.accumulate("y", oldYvalue[51]);
                                Log.d("requestData ddddddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[51]==1){
                                if(oldXvalue[51]>800.0&oldXvalue[51]<1000.96875&oldYvalue[51] > -25.0&oldYvalue[51]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[51]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView53.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[52] = event.getX();
                            oldYvalue[52] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[52] + "," + oldYvalue[52]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[52]);
                            v.setY(event.getRawY() - (oldYvalue[52] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[52] = event.getRawX() - oldXvalue[52];
                            oldYvalue[52] = event.getRawY() - (oldYvalue[52] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[52] + "," + oldYvalue[52]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[52]);
                                requestData.accumulate("y", oldYvalue[52]);
                                Log.d("requestData eeeeeee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[52]==1){
                                if(oldXvalue[52]>800.0&oldXvalue[52]<1000.96875&oldYvalue[52] > -25.0&oldYvalue[52]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[52]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView54.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[53] = event.getX();
                            oldYvalue[53] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[53] + "," + oldYvalue[53]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[53]);
                            v.setY(event.getRawY() - (oldYvalue[53] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[53] = event.getRawX() - oldXvalue[53];
                            oldYvalue[53] = event.getRawY() - (oldYvalue[53] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[53] + "," + oldYvalue[53]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[53]);
                                requestData.accumulate("y", oldYvalue[53]);
                                Log.d("requestData fffffff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[53]==1){
                                if(oldXvalue[53]>800.0&oldXvalue[53]<1000.96875&oldYvalue[53] > -25.0&oldYvalue[53]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[53]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView55.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[54] = event.getX();
                            oldYvalue[54] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[54] + "," + oldYvalue[54]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[54]);
                            v.setY(event.getRawY() - (oldYvalue[54] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[54] = event.getRawX() - oldXvalue[54];
                            oldYvalue[54] = event.getRawY() - (oldYvalue[54] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[54] + "," + oldYvalue[54]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[54]);
                                requestData.accumulate("y", oldYvalue[54]);
                                Log.d("requestData ggggggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[54]==1){
                                if(oldXvalue[54]>800.0&oldXvalue[54]<1000.96875&oldYvalue[54] > -25.0&oldYvalue[54]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[54]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView56.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[55] = event.getX();
                            oldYvalue[55] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[55] + "," + oldYvalue[55]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[55]);
                            v.setY(event.getRawY() - (oldYvalue[55] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[55] = event.getRawX() - oldXvalue[55];
                            oldYvalue[55] = event.getRawY() - (oldYvalue[55] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[55] + "," + oldYvalue[55]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[55]);
                                requestData.accumulate("y", oldYvalue[55]);
                                Log.d("requestData hhhhhhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[55]==1){
                                if(oldXvalue[55]>800.0&oldXvalue[55]<1000.96875&oldYvalue[55] > -25.0&oldYvalue[55]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[55]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView57.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[56] = event.getX();
                            oldYvalue[56] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[56] + "," + oldYvalue[56]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[56]);
                            v.setY(event.getRawY() - (oldYvalue[56] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[56] = event.getRawX() - oldXvalue[56];
                            oldYvalue[56] = event.getRawY() - (oldYvalue[56] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[56] + "," + oldYvalue[56]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[56]);
                                requestData.accumulate("y", oldYvalue[56]);
                                Log.d("requestData aaaaaaaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[56]==1){
                                if(oldXvalue[56]>800.0&oldXvalue[56]<1000.96875&oldYvalue[56] > -25.0&oldYvalue[56]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[56]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView58.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[57] = event.getX();
                            oldYvalue[57] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[57] + "," + oldYvalue[57]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[57]);
                            v.setY(event.getRawY() - (oldYvalue[57] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[57] = event.getRawX() - oldXvalue[57];
                            oldYvalue[57] = event.getRawY() - (oldYvalue[57] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[57] + "," + oldYvalue[57]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[57]);
                                requestData.accumulate("y", oldYvalue[57]);
                                Log.d("requestData bbbbbbbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[57]==1){
                                if(oldXvalue[57]>800.0&oldXvalue[57]<1000.96875&oldYvalue[57] > -25.0&oldYvalue[57]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[57]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView59.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[58] = event.getX();
                            oldYvalue[58] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[58] + "," + oldYvalue[58]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[58]);
                            v.setY(event.getRawY() - (oldYvalue[58] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[58] = event.getRawX() - oldXvalue[58];
                            oldYvalue[58] = event.getRawY() - (oldYvalue[58] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[58] + "," + oldYvalue[58]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[58]);
                                requestData.accumulate("y", oldYvalue[58]);
                                Log.d("requestData cccccccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[58]==1){
                                if(oldXvalue[58]>800.0&oldXvalue[58]<1000.96875&oldYvalue[58] > -25.0&oldYvalue[58]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[58]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView60.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[59] = event.getX();
                            oldYvalue[59] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[59] + "," + oldYvalue[59]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[59]);
                            v.setY(event.getRawY() - (oldYvalue[59] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[59] = event.getRawX() - oldXvalue[59];
                            oldYvalue[59] = event.getRawY() - (oldYvalue[59] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[59] + "," + oldYvalue[59]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[59]);
                                requestData.accumulate("y", oldYvalue[59]);
                                Log.d("requestData dddddddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[59]==1){
                                if(oldXvalue[59]>800.0&oldXvalue[59]<1000.96875&oldYvalue[59] > -25.0&oldYvalue[59]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[59]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView61.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[60] = event.getX();
                            oldYvalue[60] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[60] + "," + oldYvalue[60]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[60]);
                            v.setY(event.getRawY() - (oldYvalue[60] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[60] = event.getRawX() - oldXvalue[60];
                            oldYvalue[60] = event.getRawY() - (oldYvalue[60] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[60] + "," + oldYvalue[60]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[60]);
                                requestData.accumulate("y", oldYvalue[60]);
                                Log.d("requestData eeeeeeee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[60]==1){
                                if(oldXvalue[60]>800.0&oldXvalue[60]<1000.96875&oldYvalue[60] > -25.0&oldYvalue[60]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[60]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView62.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[61] = event.getX();
                            oldYvalue[61] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[61] + "," + oldYvalue[61]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[61]);
                            v.setY(event.getRawY() - (oldYvalue[61] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[61] = event.getRawX() - oldXvalue[61];
                            oldYvalue[61] = event.getRawY() - (oldYvalue[61] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[61] + "," + oldYvalue[61]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[61]);
                                requestData.accumulate("y", oldYvalue[61]);
                                Log.d("requestData ffffffff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[61]==1){
                                if(oldXvalue[61]>800.0&oldXvalue[61]<1000.96875&oldYvalue[61] > -25.0&oldYvalue[61]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[61]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView63.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[62] = event.getX();
                            oldYvalue[62] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[62] + "," + oldYvalue[62]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[62]);
                            v.setY(event.getRawY() - (oldYvalue[62] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[62] = event.getRawX() - oldXvalue[62];
                            oldYvalue[62] = event.getRawY() - (oldYvalue[62] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[62] + "," + oldYvalue[62]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[62]);
                                requestData.accumulate("y", oldYvalue[62]);
                                Log.d("requestData gggggggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[62]==1){
                                if(oldXvalue[62]>800.0&oldXvalue[62]<1000.96875&oldYvalue[62] > -25.0&oldYvalue[62]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[62]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView64.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[63] = event.getX();
                            oldYvalue[63] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[63] + "," + oldYvalue[63]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[63]);
                            v.setY(event.getRawY() - (oldYvalue[63] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[63] = event.getRawX() - oldXvalue[63];
                            oldYvalue[63] = event.getRawY() - (oldYvalue[63] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[63] + "," + oldYvalue[63]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[63]);
                                requestData.accumulate("y", oldYvalue[63]);
                                Log.d("requestData hhhhhhhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[63]==1){
                                if(oldXvalue[63]>800.0&oldXvalue[63]<1000.96875&oldYvalue[63] > -25.0&oldYvalue[63]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[63]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView65.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[64] = event.getX();
                            oldYvalue[64] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[64] + "," + oldYvalue[64]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[64]);
                            v.setY(event.getRawY() - (oldYvalue[64] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[64] = event.getRawX() - oldXvalue[64];
                            oldYvalue[64] = event.getRawY() - (oldYvalue[64] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[64] + "," + oldYvalue[64]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[64]);
                                requestData.accumulate("y", oldYvalue[64]);
                                Log.d("requestData aaaaaaaaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[64]==1){
                                if(oldXvalue[64]>800.0&oldXvalue[64]<1000.96875&oldYvalue[64] > -25.0&oldYvalue[64]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[64]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView66.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[65] = event.getX();
                            oldYvalue[65] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[65] + "," + oldYvalue[65]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[65]);
                            v.setY(event.getRawY() - (oldYvalue[65] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[65] = event.getRawX() - oldXvalue[65];
                            oldYvalue[65] = event.getRawY() - (oldYvalue[65] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[65] + "," + oldYvalue[65]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[65]);
                                requestData.accumulate("y", oldYvalue[65]);
                                Log.d("requestData bbbbbbbbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[65]==1){
                                if(oldXvalue[65]>800.0&oldXvalue[65]<1000.96875&oldYvalue[65] > -25.0&oldYvalue[65]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[65]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView67.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[66] = event.getX();
                            oldYvalue[66] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[66] + "," + oldYvalue[66]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[66]);
                            v.setY(event.getRawY() - (oldYvalue[66] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[66] = event.getRawX() - oldXvalue[66];
                            oldYvalue[66] = event.getRawY() - (oldYvalue[66] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[66] + "," + oldYvalue[66]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[66]);
                                requestData.accumulate("y", oldYvalue[66]);
                                Log.d("requestData ccccccccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[66]==1){
                                if(oldXvalue[66]>800.0&oldXvalue[66]<1000.96875&oldYvalue[66] > -25.0&oldYvalue[66]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[66]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView68.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[67] = event.getX();
                            oldYvalue[67] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[67] + "," + oldYvalue[67]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[67]);
                            v.setY(event.getRawY() - (oldYvalue[67] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[67] = event.getRawX() - oldXvalue[67];
                            oldYvalue[67] = event.getRawY() - (oldYvalue[67] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[67] + "," + oldYvalue[67]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[67]);
                                requestData.accumulate("y", oldYvalue[67]);
                                Log.d("requestData ddddddddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[67]==1){
                                if(oldXvalue[67]>800.0&oldXvalue[67]<1000.96875&oldYvalue[67] > -25.0&oldYvalue[67]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[67]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView69.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[68] = event.getX();
                            oldYvalue[68] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[68] + "," + oldYvalue[68]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[68]);
                            v.setY(event.getRawY() - (oldYvalue[68] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[68] = event.getRawX() - oldXvalue[68];
                            oldYvalue[68] = event.getRawY() - (oldYvalue[68] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[68] + "," + oldYvalue[68]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[68]);
                                requestData.accumulate("y", oldYvalue[68]);
                                Log.d("requestData eeeeeeeee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[68]==1){
                                if(oldXvalue[68]>800.0&oldXvalue[68]<1000.96875&oldYvalue[68] > -25.0&oldYvalue[68]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[68]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView70.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[69] = event.getX();
                            oldYvalue[69] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[69] + "," + oldYvalue[69]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[69]);
                            v.setY(event.getRawY() - (oldYvalue[69] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[69] = event.getRawX() - oldXvalue[69];
                            oldYvalue[69] = event.getRawY() - (oldYvalue[69] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[69] + "," + oldYvalue[69]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[69]);
                                requestData.accumulate("y", oldYvalue[69]);
                                Log.d("requestData fffffffff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[69]==1){
                                if(oldXvalue[69]>800.0&oldXvalue[69]<1000.96875&oldYvalue[69] > -25.0&oldYvalue[69]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[69]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView71.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[70] = event.getX();
                            oldYvalue[70] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[70] + "," + oldYvalue[70]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[70]);
                            v.setY(event.getRawY() - (oldYvalue[70] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[70] = event.getRawX() - oldXvalue[70];
                            oldYvalue[70] = event.getRawY() - (oldYvalue[70] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[70] + "," + oldYvalue[70]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[70]);
                                requestData.accumulate("y", oldYvalue[70]);
                                Log.d("requestData ggggggggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[70]==1){
                                if(oldXvalue[70]>800.0&oldXvalue[70]<1000.96875&oldYvalue[70] > -25.0&oldYvalue[70]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[70]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView72.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[71] = event.getX();
                            oldYvalue[71] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[71] + "," + oldYvalue[71]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[71]);
                            v.setY(event.getRawY() - (oldYvalue[71] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[71] = event.getRawX() - oldXvalue[71];
                            oldYvalue[71] = event.getRawY() - (oldYvalue[71] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[71] + "," + oldYvalue[71]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[71]);
                                requestData.accumulate("y", oldYvalue[71]);
                                Log.d("requestData hhhhhhhhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[71]==1){
                                if(oldXvalue[71]>800.0&oldXvalue[71]<1000.96875&oldYvalue[71] > -25.0&oldYvalue[71]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[71]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView73.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[72] = event.getX();
                            oldYvalue[72] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[72] + "," + oldYvalue[72]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[72]);
                            v.setY(event.getRawY() - (oldYvalue[72] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[72] = event.getRawX() - oldXvalue[72];
                            oldYvalue[72] = event.getRawY() - (oldYvalue[72] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[72] + "," + oldYvalue[72]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[72]);
                                requestData.accumulate("y", oldYvalue[72]);
                                Log.d("requestData aaaaaaaaaa", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[72]==1){
                                if(oldXvalue[72]>800.0&oldXvalue[72]<1000.96875&oldYvalue[72] > -25.0&oldYvalue[72]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 116.43591);
                                        requestData.accumulate("y", 1150.4937);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[72]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView74.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[73] = event.getX();
                            oldYvalue[73] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[73] + "," + oldYvalue[73]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[73]);
                            v.setY(event.getRawY() - (oldYvalue[73] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[73] = event.getRawX() - oldXvalue[73];
                            oldYvalue[73] = event.getRawY() - (oldYvalue[73] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[73] + "," + oldYvalue[73]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[73]);
                                requestData.accumulate("y", oldYvalue[73]);
                                Log.d("requestData bbbbbbbbbb", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[73]==1){
                                if(oldXvalue[73]>800.0&oldXvalue[73]<1000.96875&oldYvalue[73] > -25.0&oldYvalue[73]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 384.02466);
                                        requestData.accumulate("y", 1089.9939);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[73]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView75.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[74] = event.getX();
                            oldYvalue[74] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[74] + "," + oldYvalue[74]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[74]);
                            v.setY(event.getRawY() - (oldYvalue[74] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[74] = event.getRawX() - oldXvalue[74];
                            oldYvalue[74] = event.getRawY() - (oldYvalue[74] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[74] + "," + oldYvalue[74]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[74]);
                                requestData.accumulate("y", oldYvalue[74]);
                                Log.d("requestData cccccccccc", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[74]==1){
                                if(oldXvalue[74]>800.0&oldXvalue[74]<1000.96875&oldYvalue[74] > -25.0&oldYvalue[74]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 631.29675);
                                        requestData.accumulate("y", 1105.6864);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[74]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView76.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[75] = event.getX();
                            oldYvalue[75] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[75] + "," + oldYvalue[75]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[75]);
                            v.setY(event.getRawY() - (oldYvalue[75] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[75] = event.getRawX() - oldXvalue[75];
                            oldYvalue[75] = event.getRawY() - (oldYvalue[75] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[75] + "," + oldYvalue[75]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[75]);
                                requestData.accumulate("y", oldYvalue[75]);
                                Log.d("requestData dddddddddd", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[75]==1){
                                if(oldXvalue[75]>800.0&oldXvalue[75]<1000.96875&oldYvalue[75] > -25.0&oldYvalue[75]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 853.9537);
                                        requestData.accumulate("y", 1118.5337);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[75]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView77.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[76] = event.getX();
                            oldYvalue[76] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[76] + "," + oldYvalue[76]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[76]);
                            v.setY(event.getRawY() - (oldYvalue[76] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[76] = event.getRawX() - oldXvalue[76];
                            oldYvalue[76] = event.getRawY() - (oldYvalue[76] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[76] + "," + oldYvalue[76]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[76]);
                                requestData.accumulate("y", oldYvalue[76]);
                                Log.d("requestData eeeeeeeeee", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[76]==1){
                                if(oldXvalue[76]>800.0&oldXvalue[76]<1000.96875&oldYvalue[76] > -25.0&oldYvalue[76]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 149.48547);
                                        requestData.accumulate("y", 1285.1016);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[76]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView78.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[77] = event.getX();
                            oldYvalue[77] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[77] + "," + oldYvalue[77]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[77]);
                            v.setY(event.getRawY() - (oldYvalue[77] + v.getHeight() * 3));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[77] = event.getRawX() - oldXvalue[77];
                            oldYvalue[77] = event.getRawY() - (oldYvalue[77] + v.getHeight() * 3);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[77] + "," + oldYvalue[77]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[77]);
                                requestData.accumulate("y", oldYvalue[77]);
                                Log.d("requestData ffffffffff", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[77]==1){
                                if(oldXvalue[77]>800.0&oldXvalue[77]<1000.96875&oldYvalue[77] > -25.0&oldYvalue[77]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 358.5067);
                                        requestData.accumulate("y", 1257.5405);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[77]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView79.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[78] = event.getX();
                            oldYvalue[78] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[78] + "," + oldYvalue[78]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[78]);
                            v.setY(event.getRawY() - (oldYvalue[78] + v.getHeight() * 4));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[78] = event.getRawX() - oldXvalue[78];
                            oldYvalue[78] = event.getRawY() - (oldYvalue[78] + v.getHeight() * 4);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[78] + "," + oldYvalue[78]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[78]);
                                requestData.accumulate("y", oldYvalue[78]);
                                Log.d("requestData gggggggggg", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[78]==1){
                                if(oldXvalue[78]>800.0&oldXvalue[78]<1000.96875&oldYvalue[78] > -25.0&oldYvalue[78]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x",602.4646);
                                        requestData.accumulate("y", 1276.9744);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[78]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
                imageView80.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) { // 음표를 잡는 순간좌표
                            oldXvalue[79] = event.getX();
                            oldYvalue[79] = event.getY();
                            Log.i("Tag1", "Action Down rX 1 " + oldXvalue[79] + "," + oldYvalue[79]);
                            Log.i("view", "id" + v.getId());

                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //음표를 움직임
                            v.setX(event.getRawX() - oldXvalue[79]);
                            v.setY(event.getRawY() - (oldYvalue[79] + v.getHeight() * 7));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //음표를 놓음
                        {
                            oldXvalue[79] = event.getRawX() - oldXvalue[79];
                            oldYvalue[79] = event.getRawY() - (oldYvalue[79] + v.getHeight() * 7);
                            Log.i("Tag1", "Action Down rX 1 " + 00 + " : " + oldXvalue[79] + "," + oldYvalue[79]);
                            try {
                                requestData= new JSONObject();
                                requestData.accumulate("musicId", v.getId());
                                requestData.accumulate("seq", boardSeq);
                                requestData.accumulate("x", oldXvalue[79]);
                                requestData.accumulate("y", oldYvalue[79]);
                                Log.d("requestData hhhhhhhhhh", requestData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(exist[79]==1){
                                if(oldXvalue[79]>800.0&oldXvalue[79]<1000.96875&oldYvalue[79] > -25.0&oldYvalue[79]<175.4821){
                                    try {
                                        requestData= new JSONObject();
                                        requestData.accumulate("musicId", v.getId());
                                        requestData.accumulate("seq", boardSeq);
                                        requestData.accumulate("x", 849.999);
                                        requestData.accumulate("y", 1302.5059);
                                        requestData.accumulate("exist",0);
                                        Log.d("requestData", requestData.toString());
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    try{
                                        requestData.accumulate("exist",1);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("update","update");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/update");
                            }else{
                                exist[79]=1;
                                Log.d("insert","insert");
                                new postTask().execute("http://192.168.64.157:8080/biz/music/insert");
                            }
                        }
                        return true;
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
            Log.d("postData2", result);


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



            people = (TextView) findViewById(R.id.people);

            people.setText(boardVO.getTitle());
            people.setTextColor(Color.BLACK);


        }
    }
}