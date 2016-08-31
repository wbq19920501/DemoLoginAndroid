package wbq501.com.demologin;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by admin on 2016/7/20.
 */
public class MainAcitivty extends Activity{

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin,btnLogin2;
    private ViewGroup vsProgress;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        this.etUsername = (EditText) this.findViewById(R.id.etUsername);
        this.etPassword = (EditText) this.findViewById(R.id.etPassword);
        this.btnLogin = (Button) this.findViewById(R.id.btnLogin);
        this.btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                final String username = MainAcitivty.this.etUsername.getText()
//                        .toString().trim();
//                final String password = MainAcitivty.this.etPassword.getText()
//                        .toString().trim();
//                if ("".equals(username)) {
//                    Toast.makeText(MainAcitivty.this, "请填写用户名",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if ("".equals(password)) {
//                    Toast.makeText(MainAcitivty.this, "请填写密码",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                //如果已经填写了用户名和密码，执行登录操作
//
//                new Thread() {
//                    public void run() {
//                        loginByPost(username, password); // 调用loginByPost方法
//                    };
//                }.start();
                intent = new Intent(MainAcitivty.this,OkHttpActivity.class);
                startActivity(intent);

            }
        });
        this.btnLogin2 = (Button) this.findViewById(R.id.btnLogin2);
        this.btnLogin2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent = new Intent(MainAcitivty.this,CoordinatorLayoutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginByPost(String username, String password) {
        try {

            // 请求的地址
            String spec = "http://119.21.100.81:8080/DemoLogin/servlet/LoginServlet";
            // 根据地址创建URL对象
            URL url = new URL(spec);
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 传递的数据
            String data = "username=" + URLEncoder.encode(username, "UTF-8")
                    + "&userpass=" + URLEncoder.encode(password, "UTF-8");
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(data.getBytes().length));
            // 设置请求的头
            urlConnection
                    .setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");

            urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
            urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
            //setDoInput的默认值就是true
            //获取输出流
            OutputStream os = urlConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            if (urlConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                final String result = new String(baos.toByteArray());

                // 通过runOnUiThread方法进行修改主线程的控件内容
                MainAcitivty.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里把返回的数据写在控件上 会出现什么情况尼
                        etUsername.setText(result);
                        etPassword.setText("777");
                    }
                });

            } else {
                System.out.println("链接失败.........");
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
}
}
