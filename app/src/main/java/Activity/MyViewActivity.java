package Activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

public class MyViewActivity extends AppCompatActivity {

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_myview);

      View progress = findViewById(R.id.progress);
      progress.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            ObjectAnimator.ofInt(progress,"progress", 0, 100)
                    .setDuration(3000)
                    .start();
         }
      });
   }
}
