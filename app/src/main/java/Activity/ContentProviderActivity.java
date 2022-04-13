package Activity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import Utils.LogUtils;


public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener{

   private static final String TAG = "ContentProviderActivity";
   private ContentResolver contentResolver;
   private Button btn1, btn2, btn3, btn4, btn5, btn6;
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_content_provider);
      btn1 = (Button)findViewById(R.id.btn1);
      btn2 = (Button)findViewById(R.id.btn2);
      btn3 = (Button)findViewById(R.id.btn3);
      btn4 = (Button)findViewById(R.id.btn4);
      btn5 = (Button)findViewById(R.id.btn5);
      btn6 = (Button)findViewById(R.id.btn6);
      btn1.setOnClickListener(this);
      btn2.setOnClickListener(this);
      btn3.setOnClickListener(this);
      btn4.setOnClickListener(this);
      btn5.setOnClickListener(this);
      btn6.setOnClickListener(this);

      contentResolver = getContentResolver();

//      contentResolver.query();
//      contentResolver.insert();
//      contentResolver.delete();
//      contentResolver.update();
   }

   @Override
   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.btn1:
            //URI统一资源定位符 content://authorities[/path]
            //ContentValues
            ContentValues contentValues = new ContentValues();
            //添加数据
            contentValues.put("name", "value1");
            Uri uri = contentResolver.insert(Uri.parse("content://com.example.myprovider"), contentValues);
            long id = ContentUris.parseId(uri);
            LogUtils.i(TAG, "id————>"+id);
            break;
         case R.id.btn2:
            Cursor cursor = contentResolver.query(Uri.parse("content://com.example.myprovider"), null, null, null, null);
            while (cursor.moveToNext()) {
               int id1 = cursor.getColumnIndex("_id");
               LogUtils.i(TAG, "id————>"+cursor.getInt(id1));
               int name = cursor.getColumnIndex("name");
               LogUtils.i(TAG, "name————>"+cursor.getString(name));
            }
            cursor.close();

            break;
         case R.id.btn3:
            String _id1 = "1";
            int delete = contentResolver.delete(Uri.parse("content://com.example.myprovider"), "_id=?", new String[]{_id1});
            if(delete > 0) {
               LogUtils.i(TAG, "delete————>success");
            }else {
               LogUtils.i(TAG, "delete————>fail");
            }
            break;
         case R.id.btn4:
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put("name", "value2");
            String _id2 = "2";
            int update = contentResolver.update(Uri.parse("content://com.example.myprovider"), contentValues1, "_id=?", new String[]{_id2});
            if(update > 0) {
               LogUtils.i(TAG, "update————>success");
            }else {
               LogUtils.i(TAG, "update————>fail");
            }
            break;
         case R.id.btn5:
            contentResolver.delete(Uri.parse("content://com.example.myprovider/helloworld"), null, null);
            break;
         case R.id.btn6:
            Uri insert = contentResolver.insert(Uri.parse("content://com.example.myprovider/whatever?name=张三"), new ContentValues());
            LogUtils.i(TAG, "Uri————>"+insert);
            break;
      }
   }
}
