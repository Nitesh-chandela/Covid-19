package com.example.covid_19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.reflect.Method;

public class InfoData extends AppCompatActivity {
    ProgressBar pb;
    SwipeRefreshLayout mySwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_data);
        pb = findViewById(R.id.progressTest);
        View b = findViewById(R.id.one);
        View b1 =findViewById(R.id.two);
        View b2= findViewById(R.id.three);
        View b3=findViewById(R.id.four);
        String newString =getIntent().getExtras().getString("getActivity");
        if (newString.equals("symptoms"))
        {
            b1.setVisibility(View.GONE);
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
        }
        else if (newString.equals("emergency"))
        {
           b.setVisibility(View.GONE);
           b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
        }
        else if(newString.equals("precautions")){

            b.setVisibility(View.GONE);
            b1.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
        }else if(newString.equals("testing")){

            b.setVisibility(View.GONE);
            b1.setVisibility(View.GONE);
            b2.setVisibility(View.GONE);
            if(isMobileDataEnabled()||WifiData()){
            final WebView webView = (WebView) findViewById(R.id.testingWeb);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new myweb());
            webView.getSettings().setBuiltInZoomControls(false);
            webView.getSettings().setDisplayZoomControls(false);
                mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainerInfo);
                mySwipeRefreshLayout.setOnRefreshListener(
                        new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                webView.reload();
                                mySwipeRefreshLayout.setRefreshing(false);
                            }
                        }
                );
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pb.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    pb.setVisibility(View.GONE);

                }
            });
            webView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        WebView webView = (WebView) v;

                        switch (keyCode) {
                            case KeyEvent.KEYCODE_BACK:
                                if (webView.canGoBack()) {
                                    webView.goBack();
                                    return true;
                                }
                                break;
                        }
                    }

                    return false;
                }
            });
            webView.loadUrl("https://www.statista.com/statistics/1104075/india-coronavirus-covid-19-public-private-testing-centers-by-state/");
        }

        else {
            //Mobile data is disabled here
            new AlertDialog.Builder(this).setTitle("Unable to connect")
                    .setMessage("Enable data?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // if user clicks ok then it will open network settings
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
        }
    }
    private boolean isMobileDataEnabled(){
        boolean mobileDataEnabled = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true);

            mobileDataEnabled = (Boolean)method.invoke(cm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileDataEnabled;
    }
    private boolean WifiData(){
        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        boolean wifiEnabled = wifiManager.isWifiEnabled();
        if(wifiEnabled!=true){
            wifiManager.setWifiEnabled(true);
        }else{
            Toast.makeText(getApplicationContext(), "Connected to WiFi", Toast.LENGTH_SHORT).show();

        }
        return wifiEnabled ;
    }
class myweb extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        pb.setMax(100);
        pb.setProgress(newProgress);
        if (newProgress == 100) {
            pb.setVisibility(View.GONE);
        }
    }

}
}