package com.bd.bd_career;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String FACEBOOK_URL = "https://www.facebook.com/bdcareerorg";
    public static String FACEBOOK_PAGE_ID = "120025415339734";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkConnected()) {

                    Intent i = new Intent(Intent.ACTION_VIEW);

                    i.setData(Uri.parse("market://details?id=com.bd.bd_career"));
                    startActivity(i);

                } else {
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //For internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //End internet connection

    public void allJobs(View view) {

        Intent i = new Intent(MainActivity.this, bdcareer.class);
        i.putExtra("URL","https://bd-career.org");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void governmentJobs(View view) {

        Intent i = new Intent(MainActivity.this, bdcareer.class);
        i.putExtra("URL","https://bd-career.org/category/government-jobs-circular");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void ngoJobs(View view) {
        Intent i = new Intent(MainActivity.this, bdcareer.class);
        i.putExtra("URL","https://bd-career.org/category/ngodevelopment");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void BankJobs(View view) {
        Intent i = new Intent(MainActivity.this, bdcareer.class);
        i.putExtra("URL","https://bd-career.org/category/bank-jobs");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void teletalkApplication(View view) {
        Intent i = new Intent(MainActivity.this, bdcareer.class);
        i.putExtra("URL","https://bd-career.org/category/teletalk-application");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void assignment(View view) {
        Intent i = new Intent(MainActivity.this, bdcareer.class);
        i.putExtra("URL","https://bd-career.org/category/assignment");
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }


    //Open facebook page

    public static Intent openFacebook(Context context) {


        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

            boolean activated = packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
            if (activated) {
                if ((versionCode >= 3002850)) {

                    return new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://facewebmodal/f?href=" + FACEBOOK_URL));

                } else {
                    return new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://page/" + FACEBOOK_PAGE_ID));

                }
            } else {
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse(FACEBOOK_URL));
            }
        } catch (PackageManager.NameNotFoundException e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(FACEBOOK_URL));
        }
    }

    //End opne facebook page

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.socialFb) {
            if (isNetworkConnected()) {
                Intent facebookIntent = openFacebook(this);
                startActivity(facebookIntent);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }
}