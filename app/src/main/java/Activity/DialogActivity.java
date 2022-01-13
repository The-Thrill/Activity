package Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        //PopupWindow的使用
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、将弹出的布局文件popup_content转换为view对象
                View contentView = LayoutInflater.from(DialogActivity.this).inflate(R.layout.popup_content,null,false);
                Button bn1 = (Button)contentView.findViewById(R.id.bn1);
                Button bn2 = (Button)contentView.findViewById(R.id.bn2);
                //2、创建PopupWindow控件，将contentView加入其中
                PopupWindow popupWindow = new PopupWindow(contentView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                popupWindow.setAnimationStyle(R.style.popwindow_anim);  //设置加载动画
                //3、这是PopupWindow控件可点击
                //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
                //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
                //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                        // 这里如果返回true的话，touch事件将被拦截
                        // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                    }
                });
                popupWindow.setBackgroundDrawable(new BitmapDrawable());      //要为popWindow设置一个背景才有效
                //设置遮罩
                bgAlpha(DialogActivity.this, 0.4f);
                //4、弹出显示
                //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
                popupWindow.showAtLocation(contentView,Gravity.CENTER,0,0);
                //只能显示正下方
                //popupWindow.showAsDropDown(btn1,10,0, Gravity.BOTTOM);

                //5、设置popupWindow里的按钮的事件
                bn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogActivity.this,"选项1",Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                bn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogActivity.this,"选项2",Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });

                //6、popupWindow消失监听
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //取消遮罩
                        bgAlpha(DialogActivity.this, 1.0f);
                    }
                });
            }
        });


        //AlertDialog的使用
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //建立对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this)
                        //设置标题
                        .setTitle("这是标题")
                        //设置内容
                        .setMessage("这是内容")
                        //设置确认按钮
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this,"点击了ok",Toast.LENGTH_SHORT).show();
                            }
                        })
                        //设置取消按钮
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this,"点击了no",Toast.LENGTH_SHORT).show();
                            }
                        });
                //显示
                builder.show();
            }
        });

    }

    //设置遮罩
    private void bgAlpha(Context context, float alpha) {
        WindowManager.LayoutParams lp = ((AppCompatActivity) context).getWindow().getAttributes();
        lp.alpha = alpha;// 0.0-1.0
        ((AppCompatActivity) context).getWindow().setAttributes(lp);
    }

}
