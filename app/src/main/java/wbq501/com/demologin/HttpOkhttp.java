package wbq501.com.demologin;

import org.json.JSONArray;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2016/8/2.
 */
public class HttpOkhttp {
    OkHttpClient okHttpClient = new OkHttpClient();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String login(String url,String json) throws Exception{
        RequestBody body = RequestBody.create(JSON,json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        String result = response.body().string();

        return result;
    }

}
