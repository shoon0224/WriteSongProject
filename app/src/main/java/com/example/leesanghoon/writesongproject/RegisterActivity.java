package com.example.leesanghoon.writesongproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etId;
    private EditText etPassword;
    private Button btnDone;
    private Button btnCancel;
    String id,password,name;
    JSONObject requestData = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etName = (EditText) findViewById(R.id.etName);
        etId = (EditText) findViewById(R.id.etID);
        etPassword = (EditText) findViewById(R.id.etPWD);
        btnDone = (Button) findViewById(R.id.btnDone);
        btnCancel = (Button) findViewById(R.id.btnCancel);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }
                if (etId.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etId.requestFocus();
                    return;
                }
                if (etPassword.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                id = etId.getText().toString();
                password = etPassword.getText().toString();
                name = etName.getText().toString();
                try {
                    requestData.accumulate("id",id);
                    requestData.accumulate("password",password);
                    requestData.accumulate("name",name);
                    Log.d("requestData", requestData.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PostTask postTask = new PostTask(requestData);
                postTask.execute("http://192.168.64.157:8080/biz/join");
                finish();
            }


        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}