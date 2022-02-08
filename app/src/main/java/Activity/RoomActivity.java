package Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import Room.Manager.DBEngine;
import Room.Student;
import Utils.LogUtils;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener{

   private static final String TAG = "RoomActivity";
   private DBEngine dbEngine;
   private Button btn1, btn2, btn3, btn4, btn5, btn6;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_room);

      dbEngine = new DBEngine(this);
      bindViews();
   }

   private void bindViews() {
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
   }

   @Override
   public void onClick(View v) {
      switch (v.getId()) {
         case R.id.btn1:
            //query
            Student student = new Student(null, 0);
            //查询下标为2的
            student.setId(2);
            dbEngine.queryStudents(student.getId());
            LogUtils.i(TAG, "---queryStudents");
            break;
         case R.id.btn2:
            //queryAll
            dbEngine.queryAllStudents();
            LogUtils.i(TAG, "---queryAllStudents");
            break;
         case R.id.btn3:
            //insert
            Student student1 = new Student("张三", 18);
            Student student2 = new Student("李四", 23);
            Student student3 = new Student("王五", 26);
            dbEngine.insertStudents(student1, student2, student3);
            LogUtils.i(TAG, "---insertStudents");
            break;
         case R.id.btn4:
            //update
            Student student4 = new Student("赵六", 19);
            //修改下标为2的
            student4.setId(2);
            dbEngine.updateStudents(student4);
            LogUtils.i(TAG, "---updateStudents");
            break;
         case R.id.btn5:
            //delete
            Student student5 = new Student(null, 0);
            //删除下标为2的
            student5.setId(2);
            dbEngine.deleteStudents(student5);
            LogUtils.i(TAG, "---deleteStudents");
            break;
         case R.id.btn6:
            //deleteAll
            dbEngine.deleteAllStudents();
            LogUtils.i(TAG, "---deleteAllStudents");
            break;

      }
   }
}
