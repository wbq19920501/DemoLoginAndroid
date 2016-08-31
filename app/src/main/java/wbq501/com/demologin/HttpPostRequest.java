package wbq501.com.demologin;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 2016/8/11.
 */
public class HttpPostRequest {

    public static String message;

    public static String post(String urlpath,String param){

        try {
            //新建一个URL
            URL url = new URL(urlpath);
            //打开一个httpURLconnection链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("POST");
            //post必须设置允许输出
            conn.setDoOutput(true);
            //post请求不能使用缓存
            conn.setUseCaches(false);
            // 配置请求Content-Type  Content-Length
            conn.addRequestProperty("Content-Length", param.length()+"");
            conn.addRequestProperty("Content-Type", "application/json; charset=utf-8");
            OutputStream out = conn.getOutputStream();
            out.write(param.getBytes());
            // 开始连接
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200){
                InputStream inputStream = conn.getInputStream();

                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = inputStream.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                inputStream.close();
                baos.close();
                String result = new String(baos.toByteArray());
                System.out.println(result);
                message = result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "请求错误了";
        }
        return message;
    }

    public static  String get(String urlpath,String param){
            try {
                //新建一个URL对象
                URL url = new URL(urlpath);
                // 打开一个HttpURLConnection连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置请求方式get请求
                conn.setRequestMethod("GET");
                // 设置连接超时时间
                conn.setConnectTimeout(5000);
                // //再设置超时时间
                conn.setReadTimeout(5000);
                // 开始连接
                conn.connect();
                // 判断请求是否成功     成功码为200
                if(200==conn.getResponseCode()){
                    InputStream inputStream = conn.getInputStream();
                    // 创建字节输出流对象
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    // 定义读取的长度
                    int len = 0;
                    // 定义缓冲区
                    byte buffer[] = new byte[1024];
                    // 按照缓冲区的大小，循环读取
                    while ((len = inputStream.read(buffer)) != -1) {
                        // 根据读取的长度写入到os对象中
                        baos.write(buffer, 0, len);
                    }
                    // 释放资源
                    inputStream.close();
                    baos.close();
                    String result = new String(baos.toByteArray());
                    System.out.println(result);
                    message = result;
                }
            } catch (Exception e) {
                e.printStackTrace();
                message = "请求错误了";
            }
        return message;
    }
}
