package Activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

public class AnimationActivity extends AppCompatActivity {

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_animation);
   }


   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.alphaAnimation:
            Animation alphaAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha);
            view.startAnimation(alphaAnimation);
            break;
         case R.id.scaleAnimation:
            Animation scaleAnimation = AnimationUtils.loadAnimation(this,R.anim.scale);
            view.startAnimation(scaleAnimation);
            break;
         case R.id.translateAnimation:
            Animation translateAnimation = AnimationUtils.loadAnimation(this,R.anim.translate);
            view.startAnimation(translateAnimation);
            break;
         case R.id.rotateAnimation:
            Animation rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
            view.startAnimation(rotateAnimation);
            break;
      }
   }
}
