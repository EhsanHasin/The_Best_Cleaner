package com.csofcs.ehsanhasin.thebestcleaner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.csofcs.ehsanhasin.thebestcleaner.Adapter.Apps_Adapter;
import com.csofcs.ehsanhasin.thebestcleaner.Model.Apps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppLock extends AppCompatActivity {

    public List<Apps> app_arry;
    ProgressBar bar;
    Apps_Adapter mAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_lock);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        String password = preferences.getString("password", "0");
        if (password.equals("0")){
            Intent intent = new Intent(getApplicationContext(),CreatePasswordActivity.class);
            startActivity(intent);
            finish();
        }else if (password.equals("1")){

        }else {
            Intent intent = new Intent(getApplicationContext(),InputPasswordActivity.class);
            startActivity(intent);
            finish();
        }

        app_arry = new ArrayList<>();

        bar = findViewById(R.id.progressBar);
        bar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new Apps_Adapter(app_arry);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.computeHorizontalScrollExtent();
        recyclerView.setAdapter(mAdapter);

        load load = new load();
        load.execute();


    }

    class load extends AsyncTask<Void, Long, List<Apps>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Apps> doInBackground(Void... voids) {


            Drawable ico = null;
            String pName = "";
            PackageManager pm = getPackageManager();
            List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

            for (ApplicationInfo app : apps) {
                if (pm.getLaunchIntentForPackage(app.packageName) != null) {
                    // app_arry with launcher intent
                    if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1) {
                        // updated system app_arry

                    } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                        // system app_arry

                    } else {

                        Apps item = new Apps();

                        try {
                            item.setImage(ico = getPackageManager().getApplicationIcon(app.packageName));
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }

                        int s = (int) (getPackageSize(getApplicationContext(), app.packageName) / 1000000);
                        item.setSize(s);

                        item.setName((String) app.loadLabel(pm));
                        app_arry.add(item);


                    }

                }

            }

            Log.d("app size", app_arry.size() + "");
            return app_arry;
        }

        @Override
        protected void onPostExecute(List<Apps> apps) {
            super.onPostExecute(apps);
            mAdapter.notifyDataSetChanged();
            Log.d("app size", apps.size() + "");
            bar.setVisibility(View.GONE);


        }
    }
    public static long getPackageSize(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_SHARED_LIBRARY_FILES);
            File file = new File(applicationInfo.publicSourceDir);
            return file.length();
        } catch (PackageManager.NameNotFoundException e) {
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_locak,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_done:
                finish();
                break;
            case R.id.menu_lock:
                Intent intent = new Intent(getApplicationContext(),CreatePasswordActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
