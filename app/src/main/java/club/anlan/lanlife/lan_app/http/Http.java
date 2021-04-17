package club.anlan.lanlife.lan_app.http;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import club.anlan.lanlife.lan_app.domain.File;
import club.anlan.lanlife.lan_app.domain.ResultMessage;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * HTTP 工具
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 15:08
 */

public class Http {

    private static OkHttpClient client;
    private static String requestUrl;
    private static HashMap<String, Object> mParams;
    public static Http http = new Http();

    public static Http config(String url, HashMap<String, Object> params) {
        client = new OkHttpClient.Builder()
                .build();
        requestUrl = HttpUrl.BASE_URl + url;
        mParams = params;
        return http;
    }

    public void postRequest(final Callback callback) {
//        SharedPreferences sp = context.getSharedPreferences("sp_ttit", MODE_PRIVATE);
//        String token = sp.getString("token", "");
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson =
                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                        , jsonStr);
        //第三步创建Rquest
        Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader("contentType", "application/json;charset=UTF-8")
//                .addHeader("token", token)
                .post(requestBodyJson)
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSuccess(JSONObject.parseObject(response.body().string(), ResultMessage.class));
            }
        });
    }

    public void getRequest(final Callback callback) {
//        SharedPreferences sp = context.getSharedPreferences("sp_ttit", MODE_PRIVATE);
//        String token = sp.getString("token", "");
        String url = getAppendUrl(requestUrl, mParams);
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("token", token)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSuccess(JSONObject.parseObject(response.body().string(), ResultMessage.class));
            }
        });
    }

    private String getAppendUrl(String url, Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (StringUtils.isEmpty(buffer.toString())) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }
                buffer.append(entry.getKey()).append("=").append(entry.getValue());
            }
            url += buffer.toString();
        }
        return url;
    }
}
