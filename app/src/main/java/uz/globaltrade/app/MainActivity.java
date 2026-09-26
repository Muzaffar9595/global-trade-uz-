package uz.globaltrade.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        setContentView(webView);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Bitmap bitmap = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.file_00000000bf748210967964c556c19f2d
                );

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

                String base64 = Base64.encodeToString(
                        outputStream.toByteArray(),
                        Base64.NO_WRAP
                );

                String javascript =
                        "javascript:(function() {" +
                        "var logo = document.querySelector('.logo');" +
                        "if (logo) {" +
                        "logo.innerHTML = '<img src=\"data:image/png;base64," + base64 + "\" alt=\"Global Trade UZ\">';" +
                        "logo.style.display='flex';" +
                        "logo.style.alignItems='center';" +
                        "}" +
                        "})()";

                view.evaluateJavascript(javascript, null);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl("https://muzaffar9595.github.io/global-trade-uz-/");
    }
}
