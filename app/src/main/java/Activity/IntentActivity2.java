package Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

public class IntentActivity2 extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity2_intent);
        Bundle bundle = getIntent().getExtras();
        boolean type = bundle.getBoolean("type",true);

        TextView textView = (TextView)findViewById(R.id.tv);
        webView = (WebView)findViewById(R.id.webView);
        if(type) {
            textView.setText(getResources().getText(R.string.webView1));
            //webView
            WebSettings webSettings = webView.getSettings();
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webSettings.setAllowContentAccess(true);
            webSettings.setJavaScriptEnabled(true);//支持JS
            webSettings.setDomStorageEnabled(true);
            webSettings.setSupportZoom(true);//设置缩放
            webSettings.setBuiltInZoomControls(true);//设置内置的缩放控件
            webSettings.setDisplayZoomControls(true);//是否隐藏原生的缩放控件
            String url = "https://www.baidu.com";
            webView.loadUrl(url);
            //设置不用系统浏览器打开,直接显示在当前webView
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
            });
        }else {
            textView.setText(getResources().getText(R.string.webView2));
            Uri uri = Uri.parse("https://www.baidu.com");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
