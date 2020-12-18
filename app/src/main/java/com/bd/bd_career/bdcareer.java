package com.bd.bd_career;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class bdcareer extends AppCompatActivity {
    private ProgressBar proBar;
    private WebView bdJobsCareers;
    private static String URL = "https://bd-career.org";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (isNetworkConnected()) {
                        bdJobsCareers.loadUrl(URL);

                    } else {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                    }

                    return true;
                case R.id.govtJob:
                    if (isNetworkConnected()) {
                        bdJobsCareers.loadUrl("https://bd-career.org/category/government-jobs-circular");

                    } else {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                    }


                    return true;
                case R.id.ngoJob:
                    if (isNetworkConnected()) {

                        bdJobsCareers.loadUrl("https://bd-career.org/category/ngodevelopment");

                    } else {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                    }


                    return true;
                case R.id.bankJob:
                    if (isNetworkConnected()) {
                        bdJobsCareers.loadUrl("https://bd-career.org/category/bank-jobs");

                    } else {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                    }


                    return true;
                case R.id.teletalk:
                    if (isNetworkConnected()) {
                        bdJobsCareers.loadUrl("https://bd-career.org/category/teletalk-application");

                    } else {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                    }


                    return true;

            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdcareer);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Webview
        bdJobsCareers = (WebView) findViewById(R.id.web1);
        WebSettings webSettings = bdJobsCareers.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Improve wevView performance

        bdJobsCareers.clearCache(true);
        bdJobsCareers.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        bdJobsCareers.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        bdJobsCareers.getSettings().setAppCacheEnabled(false);
        bdJobsCareers.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        bdJobsCareers.setInitialScale(1);
        // Configure related browser settings
        // bdJobsCareers.getSettings().setLoadsImagesAutomatically(true);
        // Enable responsive layout
        //bdJobsCareers.getSettings().setUseWideViewPort(true);
// Zoom out if the content width is greater than the width of the viewport
        //  bdJobsCareers.getSettings().setLoadWithOverviewMode(true);
        //bdJobsCareers.getSettings().setSupportZoom(true);
        bdJobsCareers.getSettings().setDisplayZoomControls(false);
        bdJobsCareers.getSettings().setBuiltInZoomControls(true);
        // chakrirkhobor.setVerticalScrollBarEnabled(false);
        bdJobsCareers.setHorizontalScrollBarEnabled(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);

        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);

        if (isNetworkConnected()) {
            Bundle bundle = getIntent().getExtras();
            String url = bundle.getString("URL");
            bdJobsCareers.loadUrl(url);
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }


        //bdJobsCareers.loadUrl("https://scheduler-hcir-int-us1.sec3ure.com/Scheduler?HCIRID=977272&SSOID=977272&token=87ae4b83a2e47c31d480c5749253653a");
        bdJobsCareers.setWebViewClient(new mywebClient());

        proBar = (ProgressBar) findViewById(R.id.progressBar1);


//        // OneSignal Initialization
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();
    }


    //For internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //End internet connection


    //For webview progress bar loading

    public class mywebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            proBar.setVisibility(View.GONE);
            //setTitle(view.getTitle());

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            proBar.setVisibility(View.VISIBLE);
            //setTitle("Loading.....");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    //End webview progress bar


    @Override
    public void onBackPressed() {

        if (bdJobsCareers.canGoBack()) {
            bdJobsCareers.goBack();
        } else {
            super.onBackPressed();
        }
    }


    ///Animation/////
    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    //Animation end//


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.topmenumainactivity, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.close) {
            finish();

        }


        return super.onOptionsItemSelected(item);
    }


}