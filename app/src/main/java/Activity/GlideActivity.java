package Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.activity.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Utils.GlideApp;


public class GlideActivity extends AppCompatActivity implements View.OnClickListener {

   private Button bt1, bt2, bt3, bt4;
   private ImageView iv;
   private final String TAG = "GlideActivity";

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_glide);
      bt1 = (Button)findViewById(R.id.bt1);
      bt2 = (Button)findViewById(R.id.bt2);
      bt3 = (Button)findViewById(R.id.bt3);
      bt4 = (Button)findViewById(R.id.bt4);
      iv = (ImageView)findViewById(R.id.iv);
      bt1.setOnClickListener(this);
      bt2.setOnClickListener(this);
      bt3.setOnClickListener(this);
      bt4.setOnClickListener(this);
   }

   @Override
   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.bt1:
            downUrlImage("https://t7.baidu.com/it/u=2604797219,1573897854&fm=193&f=GIF");
            break;
         case R.id.bt2:
            loadUrlImage("https://t7.baidu.com/it/u=2604797219,1573897854&fm=193&f=GIF");
            break;
         case R.id.bt3:
            glideUrlImage("http:/res.lgdsunday.club/big_img.jpg");
            break;
         case R.id.bt4:
            glideAppUrlImage("http:/res.lgdsunday.club/big_img.jpg");
            break;
      }
   }

   private void glideAppUrlImage(final String img) {
      //高级用法
      GlideApp.with(this)
              .load(img)
              .injectOptions()
              .into(iv);
   }

   private void glideUrlImage(final String img) {
      //配置Glide
      RequestOptions requestOptions = new RequestOptions()
              .placeholder(R.mipmap.ic_launcher) //等待中
              .error(R.mipmap.ic_launcher_round) //失败
              .circleCrop();//圆角

      Glide.with(this)
              .load(img)
              .apply(requestOptions)
              .into(iv);
   }

   private void loadUrlImage(final String img) {
      new Thread(new Runnable() {
         @Override
         public void run() {
            try {
               URL url = new URL(img);
               HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
               httpURLConnection.setRequestMethod("GET");
               httpURLConnection.setReadTimeout(5000);

               if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                  //获取数据流
                  InputStream inputStream = httpURLConnection.getInputStream();
                  //转为bitmap
                  Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                  runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                        iv.setImageBitmap(bitmap);
                     }
                  });
               }
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }).start();
   }

   private void downUrlImage(final String img) {
      new Thread(new Runnable() {
         @Override
         public void run() {
            try {
               //  /data/user/0/com.example.activity/files/images
               String s = getFilesDir() + File.separator + "images";
               File file1 = new File(s);
               if(!file1.exists()){
                  file1.mkdir();
               }
               // /data/user/0/com.example.activity/files/images/image.jpg
               File file2 = new File(s,"image.jpg");
               URL url = new URL(img);
               InputStream inputStream = url.openStream();
               FileOutputStream fileOutputStream = new FileOutputStream(file2);
               byte[] bytes = new byte[1024];
               int len;
               while ((len = inputStream.read(bytes)) != -1) {
                  fileOutputStream.write(bytes,0,len);
               }
               inputStream.close();
               fileOutputStream.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }).start();
   }
}
