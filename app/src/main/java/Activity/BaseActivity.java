package Activity;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static BaseActivity baseActivity;
    protected String TAG = super.getClass().getSimpleName();
    private AppCompatActivity activity;

    public static BaseActivity getBaseActivity()
    {
        return baseActivity;
    }

    public static BaseActivity getInstance()
    {
        return baseActivity;
    }

    public String getActivityName()
    {
        return super.getClass().getSimpleName();
    }
}
