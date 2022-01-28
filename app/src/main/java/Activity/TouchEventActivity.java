package Activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import Utils.LogUtils;

public class TouchEventActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "TouchEventActivity";
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        imageView = (ImageView)findViewById(R.id.iv);
        imageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            // 单指
            case MotionEvent.ACTION_DOWN:
                LogUtils.i(TAG, "单指: ----------------");
                break;
            // 双指
            case MotionEvent.ACTION_POINTER_DOWN:
                LogUtils.i(TAG, "双指: ----------------");
                break;
            // 手指放开
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                LogUtils.i(TAG, "手指放开: ----------------");
                break;
            // 单指滑动事件
            case MotionEvent.ACTION_MOVE:
                LogUtils.i(TAG, "是一个手指拖动: ----------------");
                break;
        }
        return true;
    }

}
