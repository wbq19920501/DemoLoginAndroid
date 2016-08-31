package wbq501.com.demologin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;

/**
 * Created by admin on 2016/8/2.
 */
public class OkHttpActivity extends Activity{

    Button testbtn;
    TextView textvalues;
    private AutoCompleteTextView search;
    private MultiAutoCompleteTextView musearch;
    String url = "http://119.29.100.81:8080/DemoLogin/Servlet/LoginServlet";
    String json = "[{\"username\":\"dsdfdsf\",\"password\":\"dsfdfsghdsfg\"}]";
    String[] searchs = {"1","11","111","112","1123"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp);
        testbtn = (Button) findViewById(R.id.testbtn);
        textvalues = (TextView) findViewById(R.id.textvalues);
        search = (AutoCompleteTextView) findViewById(R.id.search);
        musearch = (MultiAutoCompleteTextView) findViewById(R.id.musearch);
        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                okhttp();
                httptest();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(OkHttpActivity.this,android.R.layout.simple_expandable_list_item_1,searchs);
        search.setAdapter(adapter);
        musearch.setAdapter(adapter);
        musearch.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void httptest() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                final String post = HttpPostRequest.post(url, json);
                //更新UI,在UI线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OkHttpActivity.this,post,Toast.LENGTH_LONG).show();
                        textvalues.setText(post);
                    }
                });
            }
        }.start();
    }

    private void okhttp() {

        new Thread(){
            @Override
            public void run() {

                HttpOkhttp okhttp = new HttpOkhttp();

                try {
                    final String result = okhttp.login(url,json);
                    //更新UI,在UI线程中
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OkHttpActivity.this,result,Toast.LENGTH_LONG).show();
                            textvalues.setText(result);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }




            }
        }.start();
    }
}
