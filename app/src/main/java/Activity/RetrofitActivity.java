package Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.WanResponse;
import Interface.HttpbinService;
import Interface.UploadService;
import Interface.WanAndroidService;
import Utils.LogUtils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RetrofitActivity";
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    private Retrofit retrofit;
    private HttpbinService httpbinService;
    private Map<String, List<Cookie>> map = new HashMap<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

        //创建Retrofit对象
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
                .build();
        //生成接口实现类对象
        httpbinService = retrofit.create(HttpbinService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                postAsync();
                break;
            case R.id.btn2:
                getAsync();
                break;
            case R.id.btn3:
                loginGson();
                break;
            case R.id.btn4:
                loginGsonConverter();
                break;
            case R.id.btn5:
                loginRxjava();
                break;
            case R.id.btn6:
                upload();
                break;
            case R.id.btn7:
                dwonload();
                break;
            case R.id.btn8:
                dwonloadRxJava();
                break;

        }
    }

    private void postAsync() {
        //接口实现类对象调用对应方法获得响应
        Call<ResponseBody> call = httpbinService.post("dahaha", "lining");
        //异步请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LogUtils.i(TAG, "postAsync onResponse：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getAsync() {
        //接口实现类对象调用对应方法获得响应
        Call<ResponseBody> call = httpbinService.get(0);
        //异步请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LogUtils.i(TAG, "getAsync onResponse：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    //Array、List、Map、Set反序列化不能直接使用Gson().fromJson(Array,xxx.class)
    //需要使用Type type = new TypeToken<Array<WanResponse>>(){}.getType();  Gson().fromJson(type,xxx.class)
    //当设置@Expose时，Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
   private void loginGson() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .build();
        //生成接口实现类对象
        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        //接口实现类对象调用对应方法获得响应
        Call<ResponseBody> call = wanAndroidService.loginGson("dahaha", "lining");
        //异步请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    WanResponse wanResponse = new Gson().fromJson(response.body().string(), WanResponse.class);
                    LogUtils.i(TAG, "loginGson wanResponse：" + wanResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void loginGsonConverter() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                //添加GsonConverter转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //生成接口实现类对象
        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        //接口实现类对象调用对应方法获得响应
        Call<WanResponse> call = wanAndroidService.loginGsonConverter("dahaha", "lining");
        //异步请求
        call.enqueue(new Callback<WanResponse>() {
            @Override
            public void onResponse(Call<WanResponse> call, Response<WanResponse> response) {
                String result = response.body().toString();
                LogUtils.i(TAG, "loginGsonConverter onResponse：" + result);
            }

            @Override
            public void onFailure(Call<WanResponse> call, Throwable t) {

            }
        });
    }

    private void loginRxjava() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .callFactory(new OkHttpClient.Builder()
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
                        }).build())
                //转换器
                .addConverterFactory(GsonConverterFactory.create())
                //适配器
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        //生成接口实现类对象
        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        //接口实现类对象调用对应方法获得响应
        wanAndroidService.loginRxjava("dahaha", "lining")
                .flatMap(new Function<WanResponse, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(WanResponse wanResponse) throws Throwable {
                        return wanAndroidService.getArticle(0);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Throwable {
                        LogUtils.i(TAG, "loginRxjava responseBody：" + responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                    }
                });
    }

    private void upload() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.httpbin.com/")
                .build();
        //生成接口实现类对象
        UploadService uploadService = retrofit.create(UploadService.class);
        File file1 = new File("C:\\Users\\admin\\Desktop\\test.txt");
        MultipartBody.Part part = MultipartBody.Part.createFormData("file1",
                "test.txt", RequestBody.create(file1, MediaType.parse("text/plain")));
        Call<ResponseBody> call = uploadService.upload(part);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LogUtils.i(TAG, "upload response：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void dwonload() {
        //https://www.runoob.com/try/download/HttpTest.zip
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.httpbin.com/")
                .build();
        //生成接口实现类对象
        UploadService uploadService = retrofit.create(UploadService.class);
        Call<ResponseBody> call = uploadService.download("https://www.runoob.com/try/download/HttpTest.zip");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    InputStream inputStream = response.body().byteStream();
                    FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\test.zip");
                    int len;
                    byte[] bytes = new byte[4096];
                    while ( (len = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0 ,len);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    private void dwonloadRxJava() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.httpbin.com/")
                .build();
        //生成接口实现类对象
        UploadService uploadService = retrofit.create(UploadService.class);
        uploadService.downloadRxJava("https://www.runoob.com/try/download/HttpTest.zip")
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(ResponseBody responseBody) throws Throwable {
                        InputStream inputStream = responseBody.byteStream();
                        File file = new File("C:\\Users\\admin\\Desktop\\test.zip");
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        int len;
                        byte[] bytes = new byte[4096];
                        while ( (len = inputStream.read(bytes)) != -1) {
                            fileOutputStream.write(bytes, 0 ,len);
                        }
                        fileOutputStream.close();
                        inputStream.close();
                        return file;
                    }
                }).subscribe(new Consumer<File>() {
            @Override
            public void accept(File file) throws Throwable {

            }
        });
    }


}
