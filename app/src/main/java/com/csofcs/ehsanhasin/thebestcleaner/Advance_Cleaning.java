package com.csofcs.ehsanhasin.thebestcleaner;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.csofcs.ehsanhasin.thebestcleaner.Model.Apps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Advance_Cleaning extends AppCompatActivity {
    TextView app_size;
    List<ApplicationInfo> packages;
    public List<Apps> apps;
    PackageManager pm;
    long size = 0;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance__cleaning);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        app_size = findViewById(R.id.app_size);
        pm = getPackageManager();
        apps = new ArrayList<>();
        pm = getPackageManager();
        packages = pm.getInstalledApplications(0);
        load load = new load();
        load.execute(new Long(2));
        //  add();
        // Toast.makeText(this, "size" + size, Toast.LENGTH_SHORT).show();

    }

    class load extends AsyncTask<Long, Long, ArrayList<Long>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Long> doInBackground(Long... integers) {
            ArrayList<Long> arrayList = new ArrayList<>();
            int countt = 0;
            Long sizee = new Long(0);
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
                        countt += 1;
//
                        sizee += getPackageSize(getApplicationContext(), app.packageName);

                    }
                }

            }

            Log.d("size", sizee + "");

            arrayList.add(sizee);
            arrayList.add((long) countt);
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Long> arrayList) {
            super.onPostExecute(arrayList);

            int s = (int) (arrayList.get(0) / 1000000);
            app_size.setText(arrayList.get(1) + "  app are using " + s + " MB");
        }
    }

    public void add() {


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
                    count += 1;
//
                    size += getPackageSize(this, app.packageName);

                }

            }

        }

        // int p=0 + (int)(Math.random() * ((packages.name()-1 - 0) + 1));


//        for (int i = 0; i < packages.size() - 1; i++) {
//
//
//            Drawable ico = null;
//
//            Apps item = new Apps();
//
//            String packageName = packages.get(i).packageName;
//            String pName = "";
//
//            try {
//                pName = (String) pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
//                ApplicationInfo a = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
//                if (a.flags ApplicationInfo.FLAG_SYSTEM=1) {
//
//                } else {
//                    count += 1;
//
//                    size += getPackageSize(this, packageName);
//
//                }
//
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }


//        mDataSet.add(position, text);

        //  }
    }

    public void OnUninstall(View view) {


        startActivity(new Intent(this, UnInstal_App_Activity.class));
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
}
