package View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.activity.R;

import Utils.LogUtils;

public class RoundProgressBar extends View {

   private static final String TAG = "RoundProgressBar";

   private int mRadius;
   private int mColor;
   private int mLineWidth;
   private int mTextSize;
   private int mProgress;

   private Paint paint;
   private RectF rectF;
   private Rect rect;

   public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);

      TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

      mRadius = (int)typedArray.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
      mColor = typedArray.getColor(R.styleable.RoundProgressBar_color, getResources().getColor(R.color.pink));
      mLineWidth = (int)typedArray.getDimension(R.styleable.RoundProgressBar_line_width, dp2px(3));
      mTextSize = (int)typedArray.getDimension(R.styleable.RoundProgressBar_android_textSize, dp2px(16));
      mProgress = typedArray.getInt(R.styleable.RoundProgressBar_android_progress, 0);

      initPaint();

   }

   private float dp2px(int dpVal) {
      return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
   }

   private void initPaint() {
      paint = new Paint();
      paint.setColor(mColor);
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

      //自定义宽高有可能不同，需要设置最大或者最小的值作为直径
      width = Math.min(width, height);
      setMeasuredDimension(width,width);

   }

   //将需要new的对象从onDraw里面拿出来
   @Override
   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);
      rectF = new RectF(0,0,w-getPaddingLeft()-getPaddingRight(), h-getPaddingTop()-getPaddingBottom());
      rect = new Rect();
   }

   private int measureWidth() {
      return mRadius * 2;
   }

   private int measureHeight() {
      return mRadius * 2;
   }


   //onDraw方法里面最好不要new对象
   @Override
   protected void onDraw(Canvas canvas) {
      paint.setStyle(Paint.Style.STROKE);
      paint.setStrokeWidth(mLineWidth * 1.0f / 4);

      int width = getWidth();
      int height = getHeight();
      canvas.drawCircle(width/2, height/2, width/2-getPaddingLeft()-paint.getStrokeWidth()/2,paint);

      paint.setStrokeWidth(mLineWidth);

      //save、restore用于保存
      canvas.save();
      //将基点移动到圆形所在的矩形左上角，或者可以直接用getPaddingLeft(),getPaddingTop()
      canvas.translate(getPaddingLeft(),getPaddingTop());
      float angle = mProgress * 1.0f / 100 *360;
      canvas.drawArc(rectF,0, angle,false, paint);
      canvas.restore();

      String text = mProgress + "%";
      paint.setStrokeWidth(0);
      paint.setTextAlign(Paint.Align.CENTER);
      paint.setTextSize(mTextSize);

      paint.getTextBounds(text, 0 , text.length(), rect);
      int textHeight = rect.height();   //获取text的高度
      //数字不存在descent，所以不需要减去paint.descent() / 2,文字的话要想居中则需要减去
      int y = height/2 + textHeight / 2;  //获取现在文本的y坐标
      canvas.drawText(text,0,text.length(), width/2, y, paint);

   }

   public void setProgress(int progress) {
      mProgress = progress;
      invalidate();
   }

   public int getProgress() {
       return mProgress;
   }

   private static final String INSTANCE = "instance";
   private static final String KET_PROGRESS = "ket_progress";

   //注意事项：要想保存和读取数据，要设置xml文件里的id
   @Nullable
   @Override
   protected Parcelable onSaveInstanceState() {
      Bundle bundle = new Bundle();
      bundle.putInt(KET_PROGRESS, mProgress);
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
         mProgress = bundle.getInt(KET_PROGRESS);
         return;
      }
      super.onRestoreInstanceState(state);
   }


}
