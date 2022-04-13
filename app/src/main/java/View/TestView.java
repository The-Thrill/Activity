package View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Dimension;
import androidx.annotation.Nullable;

import com.example.activity.R;

import Utils.LogUtils;

public class TestView extends View {

   private static final String TAG = "TestView";

   private Paint paint;

   public TestView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);

      initPaint();

      TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestView);

      boolean booleanTest = typedArray.getBoolean(R.styleable.TestView_test_boolean,false);
      int integerTest= typedArray.getInteger(R.styleable.TestView_test_integer,-1);
      float dimensionTest = typedArray.getDimension(R.styleable.TestView_test_dimension, 0);
      int enumTest = typedArray.getIndex(R.styleable.TestView_test_enum);
      String stringTest = typedArray.getString(R.styleable.TestView_test_string);

      LogUtils.i(TAG, booleanTest + " , "
              + integerTest + " , "
              + dimensionTest + " , "
              + enumTest + " , "
              + stringTest);

      typedArray.recycle();
   }

   private void initPaint() {
      paint = new Paint();
      //空心
      paint.setStyle(Paint.Style.STROKE);
      //画笔宽带
      paint.setStrokeWidth(6);
      //颜色
      paint.setColor(getResources().getColor(R.color.red));
      //抗锯齿
      paint.setAntiAlias(true);
   }

   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      int width = 0;
      if(widthMode == MeasureSpec.EXACTLY) {
         width = widthSize;
      }else {
         int needWidth = measureWidth() + getPaddingLeft() + getPaddingRight();

         if(widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(needWidth, widthSize);
         }else {
            width = needWidth;
         }
      }

      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      int heightSize = MeasureSpec.getSize(heightMeasureSpec);
      int height = 0;
      if(heightMode == MeasureSpec.EXACTLY) {
         height = heightSize;
      }else {
         int needHeight = measureHeight() + getPaddingTop() + getPaddingBottom();

         if(widthMode == MeasureSpec.AT_MOST) {
            height = Math.min(needHeight, heightSize);
         }else {
            height = needHeight;
         }
      }

      setMeasuredDimension(width,height);

   }

   private int measureWidth() {
      return 0;
   }

   private int measureHeight() {
      return 0;
   }


   private String mText = "imooc";
   @Override
   protected void onDraw(Canvas canvas) {
//      canvas.drawCircle(getWidth()/2, getHeight()/2 ,getWidth()/2-paint.getStrokeWidth()/2, paint);
//      paint.setStrokeWidth(1);
//      canvas.drawLine(0, getHeight()/2 ,getWidth(),getHeight()/2, paint);
//      canvas.drawLine(getHeight()/2, 0 ,getWidth()/2,getHeight(), paint);

      paint.setStrokeWidth(0);
      paint.setTextSize(72);
      paint.setStyle(Paint.Style.FILL);
      LogUtils.i(TAG,"drawText mText = "+ mText);
      canvas.drawText(mText,0,mText.length(),0,getHeight(),paint);

   }

   @Override
   public boolean onTouchEvent(MotionEvent event) {
      mText = "8888";
      invalidate();
      return true;
   }

   private static final String INSTANCE = "instance";
   private static final String KET_TEXT = "ket_text";

   //注意事项：要想保存和读取数据，要设置xml文件里的id
   @Nullable
   @Override
   protected Parcelable onSaveInstanceState() {
      LogUtils.i(TAG,"onSaveInstanceState mText = " + mText);
      Bundle bundle = new Bundle();
      bundle.putString(KET_TEXT, mText);
      //需要保存父View的数据逻辑
      bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
      return bundle;
   }

   @Override
   protected void onRestoreInstanceState(Parcelable state) {
      if(state instanceof Bundle) {
         Bundle bundle = (Bundle)state;
         //和上面存储一样，父View的数据一样要恢复
         Parcelable parcelable = bundle.getParcelable(INSTANCE);
         super.onRestoreInstanceState(parcelable);
         mText = bundle.getString(KET_TEXT);
         LogUtils.i(TAG,"onRestoreInstanceState mText = " + mText);
         return;
      }
      super.onRestoreInstanceState(state);
   }
}
