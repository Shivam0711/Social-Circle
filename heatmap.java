package byone4all.connected.social.socialcircle;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class heatmap extends AppCompatActivity {


    private WebView web;

    ProgressBar pgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heatmap);

        pgb = findViewById(R.id.PGQ );
        web = (WebView) findViewById(R.id.web1);
        web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.getSettings().setAppCacheEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setUseWideViewPort(true);


        web.getSettings().setEnableSmoothTransition(true);

        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);




        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pgb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pgb.setVisibility(View.INVISIBLE);

            }
        });
        web.loadUrl("http://www.heatmap.epizy.com");
        WebSettings Webset= web.getSettings();
        Webset.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {

        if(web.canGoBack()){
            web.goBack();
        }

        else{
            super.onBackPressed();
        }
    }

}
