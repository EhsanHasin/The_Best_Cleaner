package com.csofcs.ehsanhasin.thebestcleaner;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;

import com.csofcs.ehsanhasin.thebestcleaner.Adapter.Junk_Apps_Adapter;
import com.csofcs.ehsanhasin.thebestcleaner.Model.Apps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class UnInstal_App_Activity extends AppCompatActivity {


    List<ApplicationInfo> packages;
    public List<Apps> app_arry;
  ProgressBar bar;

    Junk_Apps_Adapter mAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_instal__app);

        app_arry = new ArrayList<>();


bar=findViewById(R.id.progressBar);
bar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new Junk_Apps_Adapter(app_arry);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        recyclerView.computeHorizontalScrollExtent();

        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        load load=new load();
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

                        item.setName((String)app.loadLabel(pm));
                        app_arry.add(item);



                    }

                }

            }

            Log.d("app size",app_arry.size()+"");
            return app_arry;
        }

        @Override
        protected void onPostExecute(List<Apps> apps) {
            super.onPostExecute(apps);
            mAdapter.notifyDataSetChanged();
            Log.d("app size",apps.size()+"");
            bar.setVisibility(View.GONE);



        }
    }

    public void add(String text, int position) {



//        for (int i = 0; i < packages.size() - 1; i++) {
//
//
//            Drawable ico = null;
//
//            Apps item = new Apps();
//
//            String packageName = packages.get(i).packageName;
//            String pName = "";
//            int size = 0;
//            try {
//                pName = (String) pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
//                ApplicationInfo a = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
//                if (packageName.startsWith("com.android.") || packageName.startsWith("com.google.")) {
//
//                } else {
//                    item.setCatgory("p");
//                    item.setImage(ico = getPackageManager().getApplicationIcon(packages.get(i).packageName));
//                    item.setSize(pName);
//                    app_arry.add(item);
//                    mAdapter.notifyItemInserted(position);
//
//                }
//
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//
//
////        mDataSet.add(position, text);
//
//        }
    }


    public void remove(int position) {
//        mDataSet.add(position, text);
        mAdapter.notifyItemRemoved(position);
        app_arry.remove(position);
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

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mDivider = context.getResources().getDrawable(R.drawable.line_divvide, context.getTheme());
            } else {
                mDivider = context.getResources().getDrawable(R.drawable.line_divvide);

            }
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
