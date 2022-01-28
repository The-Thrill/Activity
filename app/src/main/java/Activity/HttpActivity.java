package Activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.BuildConfig;
import com.example.activity.R;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.LogUtils;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    //http协议的定义  hypertext transfer protocol（超文本传输协议）
    //TCP/IP协议的一个应用层协议，用于定义WEB浏览器与WEB服务器之间交换数据的过程。
    //客户端连上web服务器后，若想获得web服务器中的某个web资源，需遵守一定的通讯格式，HTTP协议用于定义客户端与web服务器通迅的格式。

    //SYN：TCP/IP建立连接时使用的握手信号
    //ACK：确认字符，确认发来的数据已经接受无误
    //客户端发送SYN包（syn = j）-->服务端发送SYN包（syn = k），ACK包（ack = j+1）--> 客户端发送ACK包（ack = k+1）

    //用户点击浏览器上的url(超链接)，Web浏览器与Web服务器建立连接
    //建立连接后，客户端发送请求给服务器，请求的格式为: 统一资源标识符(URL)+协议版本号(一般是1.1)+MIME信息(多个消息头)+一个空行
    //服务端收到请求后，给予相应的返回信息，返回格式为: 协议版本号 + 状态行(处理结果) + 多个信息头 + 空行 + 实体内容(比如返回的HTML)
    //客户端接收服务端返回信息，通过浏览器显示出来，然后与服务端断开连接；当然如果中途 某步发生错误的话，错误信息会返回到客户端，并显示，比如：经典的404错误！

    //Get：请求获取Request-URI所标识的资源
    //POST：在Request-URI所标识的资源后附加新的数据
    //HEAD 请求获取由Request-URI所标识的资源的响应信息报头
    //PUT：请求服务器存储一个资源，并用Request-URI作为其标识
    //DELETE：请求服务器删除Request-URI所标识的资源
    //TRACE：请求服务器回送收到的请求信息，主要用于测试或诊断
    //CONNECT：保留将来使用
    //OPTIONS：请求查询服务器的性能，或者查询与资源相关的选项

    //创建一个URL对象： URL url = new URL(https://www.baidu.com);
    //调用URL对象的openConnection( )来获取HttpURLConnection对象实例： HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //设置HTTP请求使用的方法:GET或者POST，或者其他请求方式比如：PUT conn.setRequestMethod("GET");
    //设置连接超时，读取超时的毫秒数，以及服务器希望得到的一些消息头 conn.setConnectTimeout(6*1000); conn.setReadTimeout(6 * 1000);
    //调用getInputStream()方法获得服务器返回的输入流，然后输入流进行读取了 InputStream in = conn.getInputStream();
    //最后调用disconnect()方法将HTTP连接关掉 conn.disconnect();

    private static final String TAG = "HttpActivity";
    private OkHttpClient okHttpClient;
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    private Map<String, List<Cookie>> map = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

        okHttpClient = new OkHttpClient().newBuilder()
                //开启缓存，默认是关闭的
                .cache(new Cache(new File("DB"),1024*1024))
                //cookie
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        map.put(httpUrl.host(), list);
                    }

                    @NonNull
                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                        List<Cookie> cookieList = map.get(httpUrl.host());
                        return cookieList == null ? new ArrayList<>() : cookieList;
                    }
                })
                //拦截器
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        //前置处理
                        Request request = chain.request().newBuilder()
                                .addHeader("os", "android")
                                .addHeader("version", BuildConfig.VERSION_NAME)
                                .build();
                        //后置处理
                        return chain.proceed(request);
                    }
                })
                //拦截器
                .addNetworkInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        return null;
                    }
                })
                .build();

    }

    private void getSync() {
        new Thread() {
            @Override
            public void run() {
                //默认为GET请求
                //创建request请求对象
                Request request1 = new Request.Builder()
                        .url("https://www.httpbin.org/get?a=1&b=2")
                        .build();
                //获得准备执行请求的call对象
                Call call1 = okHttpClient.newCall(request1);
                //get同步请求
                try {
                    //直接请求得到响应
                    Response response = call1.execute();
                    LogUtils.i(TAG, "getSync onResponse：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void getASync() {
        //默认为GET请求
        //创建request请求对象
        Request request2 = new Request.Builder()
                .url("https://www.httpbin.org/get?a=1&b=2")
                .build();
        //获得准备执行请求的call对象
        Call call2 = okHttpClient.newCall(request2);
        //get异步请求
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.i(TAG, "getSync onFailure " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    LogUtils.i(TAG, "getASync onResponse：" + response.body().string());
                }
            }
        });
    }

    private void postSync() {
        new Thread() {
            @Override
            public void run() {
                //创建请求体
                FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();
                //创建request请求对象
                Request request3 = new Request.Builder()
                        .url("https://www.httpbin.org/post")
                        .post(formBody)
                        .build();
                //获得准备执行请求的call对象
                Call call3 = okHttpClient.newCall(request3);
                //post同步请求
                try {
                    //直接请求得到响应
                    Response response = call3.execute();
                    LogUtils.i(TAG, "postSync onResponse：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void postASync() {
        //创建请求体
        FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();
        //创建request请求对象
        Request request4 = new Request.Builder()
                .url("https://www.httpbin.org/post")
                .post(formBody)
                .build();
        //获得准备执行请求的call对象
        Call call4 = okHttpClient.newCall(request4);
        //post异步请求
        call4.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    LogUtils.i(TAG, "postASync onResponse：" + response.body().string());
                }
            }
        });

    }

    private void upload() {
        new Thread() {
            @Override
            public void run() {
                File file = new File("DB\\test.txt");
                //创建请求体
                RequestBody requestBody = RequestBody.create(file, MediaType.parse("text/plain"));
                MultipartBody multipartBody = new MultipartBody.Builder()
                        .addFormDataPart("file", file.getName(), requestBody)
                        .build();
                //创建request请求对象
                Request request = new Request.Builder()
                        .url("https://www.httpbin.org/post")
                        .post(multipartBody)
                        .build();
                //获得准备执行请求的call对象
                Call call = okHttpClient.newCall(request);
                try {
                    //post同步请求
                    Response response = call.execute();
                    LogUtils.i(TAG, "postASync onResponse：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void json() {
        //创建请求体
        RequestBody requestBody = RequestBody
                .create("{\"a\":1,\"b\":2}", MediaType.parse("application/json"));
        //创建request请求对象
        Request request = new Request.Builder()
                .url("https://www.httpbin.org/post")
                .post(requestBody)
                .build();
        //获得准备执行请求的call对象
        Call call = okHttpClient.newCall(request);
        //post异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    LogUtils.i(TAG, "json onResponse：" + response.body().string());
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                getSync();
                break;
            case R.id.btn2:
                getASync();
                break;
            case R.id.btn3:
                postSync();
                break;
            case R.id.btn4:
                postASync();
                break;
            case R.id.btn5:
                upload();
                break;
            case R.id.btn6:
                json();
                break;
        }

    }

}
