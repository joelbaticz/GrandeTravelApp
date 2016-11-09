package com.example.joel.grandetravelapp;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.app.FragmentManager;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Typeface travelFont;

    private String GRANDE_TRAVEL_API_URL = "http://localhost:5000/Api/GetAllPackages";

    private PackageItemDataSource itemDataSource;


    public class AsyncPackage extends AsyncTask<Void, Void, JSONArray>
    {
        @Override
        protected  JSONArray doInBackground(Void...params)
        {

            JSONArray jsonPackages = null;
            jsonPackages = getPackageJSON();
            return jsonPackages;
        }

        private JSONArray getPackageJSON()
        {


            /*

            try
            {
                //API call
                URL url = new URL(GRANDE_TRAVEL_API_URL);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(urlConnection.getInputStream())
                        );

                StringBuilder jsonData = new StringBuilder();
                String line="";

                while ((line = reader.readLine()) != null)
                {
                    jsonData.append(line + "\n");
                }
                reader.close();

                JSONObject packageData = new JSONObject(jsonData.toString());




                //if (packageData.getInt("cod")!=200)
                //{
                //    return null;
                //}



                return packageData;
            }
            catch (Exception e)
            {
                errorMessage = e.toString();
                e.printStackTrace();
                return null;
            }
            */

            //InputStream raw = getResources().openRawResource(R.raw.sampledata);


            try
            {
                BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(getResources().openRawResource(R.raw.sampledata))
                    );

                StringBuilder jsonData = new StringBuilder();
                String line="";

                while ((line = reader.readLine()) != null)
                {
                    jsonData.append(line + "\n");
                }
                reader.close();

                //JSONObject packageData = new JSONObject(jsonData.toString());

                JSONArray packageData = new JSONArray(jsonData.toString());

                return packageData;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
            //JSONObject packageData = new JSONObject(byteArrayOutputStream.toString());;

        }

        //@Override
        protected  void onPostExecute(JSONArray jsonArray)
        {
            try
            {
                if (jsonArray != null)
                {
                    /*
                    JSONObject packageData = jsonObject.getJSONArray("weather").getJSONObject(0);

                    String location = jsonObject.getString("name");
                    */

                    //--------========     DROP TABLE     ==============--------------
                    itemDataSource = new PackageItemDataSource(getApplicationContext());
                    itemDataSource.open();
                    itemDataSource.deleteAll();
                    itemDataSource.close();

                    for (int i = 0; i<jsonArray.length();i++) {

                        JSONObject packageData = jsonArray.getJSONObject(i);
                        JSONObject packageObject = packageData.getJSONObject("package");

                        /*
                        JSONObject packageRatingObject = packageData.getJSONObject("rating");
                        JSONObject packageFeedbackObject = packageData.getJSONObject("numberOfFeedbacks");
                        */

                        int packageId = packageObject.getInt("packageId");
                        String packageName = packageObject.getString("name");
                        String packageThumbnailUrl = packageObject.getString("thumbnailUrl");
                        String packageLocation = packageObject.getString("location");
                        String packageDescription = packageObject.getString("description");
                        double packagePrice = packageObject.getDouble("price");
                        boolean packageIsActive = packageObject.getBoolean("isActive");
                        int packageProviderId = packageObject.getInt("providerId");

                        /*
                        double packageRating = packageRatingObject.getDouble("");
                        int packageNumberOfFeedback = packageFeedbackObject.getInt("");
                        */

                        /*
                        double packageRating = packageObject.getDouble("rating");
                        int packageNumberOfFeedback = packageObject.getInt("numberOfFeedbacks");
                        */

                        /*
                        double packageRating = Double.parseDouble(packageData.getJSONObject("rating").toString());
                        int packageNumberOfFeedback = Integer.parseInt(packageData.getJSONObject("numberOfFeedbacks").toString());
                        */

                        double packageRating = packageData.getDouble("rating");
                        int packageNumberOfFeedback = packageData.getInt("numberOfFeedbacks");


                        //String packageName = packageActual.getJSONObject("name").toString();

                        //Toast.makeText(getApplicationContext(), "HURRAY WORX: jsonObject is NOT NULL!", Toast.LENGTH_LONG).show();

                        //--------======== INSERT TO DATABASE ==============--------------
                        itemDataSource = new PackageItemDataSource(getApplicationContext());
                        itemDataSource.open();
                        itemDataSource.insert(packageId, packageName, packageThumbnailUrl, packageLocation, packageDescription, packagePrice, packageIsActive, packageProviderId, packageRating, packageNumberOfFeedback);
                        itemDataSource.close();



                        //Refresh list
                        ListView lv = (ListView) findViewById(R.id.lvPackageList);
                        lv.invalidate();


                    }
                    Toast.makeText(getApplicationContext(), jsonArray.length() + " items inserted to database!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "jsonObject is NULL!", Toast.LENGTH_LONG).show();
                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            /*
                //Budy waiting

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             */


            Button btnSyncDatabase = (Button) findViewById(R.id.btnSyncDatabase);
            LinearLayout syncIconContainer = (LinearLayout) findViewById(R.id.syncIconContainer);
            //btnSyncDatabase.setVisibility(View.VISIBLE);
            //syncIconContainer.setVisibility(View.GONE);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textViewLogo=(TextView)findViewById(R.id.textViewLogo);
        Typeface typeface= Typeface.createFromAsset(getAssets(),"fonts/airways.ttf");
        textViewLogo.setTypeface(typeface);

        /*
        TextView textViewLogo=(TextView)findViewById(R.id.textViewLogo);
        travelFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");
        textViewLogo.setTypeface(travelFont);
        */

        getSupportActionBar().hide();

/*


        android.app.FragmentManager fragmentManager = getFragmentManager();

        //Get orientation
        Configuration config = getResources().getConfiguration();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            FragmentPortrait portrait = new FragmentPortrait();
            transaction.replace(android.R.id.content, portrait);
        }
        else {
            FragmentLandscape landcape = new FragmentLandscape();
            transaction.replace(android.R.id.content, landcape);
        }


        transaction.commit();
*/








    }


    public void syncDatabase(View v)
    {
        //Get resources
        Button btnSyncDatabase = (Button) v;
        LinearLayout syncIconContainer = (LinearLayout) findViewById(R.id.syncIconContainer);
        TextView syncIcon = (TextView) findViewById(R.id.syncIcon);

        //Play sync animation
        btnSyncDatabase.setVisibility(View.GONE);
        syncIconContainer.setVisibility(View.VISIBLE);

        /*
        //-----------=========== NORMAL ANIM ==========--------------
        Animation animation = AnimationUtils.loadAnimation(this.getApplication().getApplicationContext(), R.anim.syncanim);
        animation.setDuration(500);
        syncIcon.setAnimation(animation);
        syncIcon.startAnimation(animation);
        */


        /*
        //Animation animation = AnimationUtils.loadAnimation(this.getApplication().getApplicationContext(), R.anim.syncanim);
        //animation.setDuration(5000);
        */

        ViewPropertyAnimator animator = btnSyncDatabase.animate();
        animator.alpha(0f);
        animator.setDuration(1500);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.withEndAction(new Runnable() {
            @Override
            public void run() {

                Button btnSyncDatabase = (Button) findViewById(R.id.btnSyncDatabase);
                LinearLayout syncIconContainer = (LinearLayout) findViewById(R.id.syncIconContainer);
                TextView syncIcon = (TextView) findViewById(R.id.syncIcon);

                //btnSyncDatabase.setVisibility(View.VISIBLE);
                //syncIconContainer.setVisibility(View.GONE);
            }
        });
        animator.start();

        /*
        syncIcon.animate()
                .rotationXBy(180)
                .setDuration(5000)
                .setInterpolator(new AccelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        Button btnSyncDatabase = (Button) findViewById(R.id.btnSyncDatabase);
                        LinearLayout syncIconContainer = (LinearLayout) findViewById(R.id.syncIconContainer);
                        TextView syncIcon = (TextView) findViewById(R.id.syncIcon);

                        //btnSyncDatabase.setVisibility(View.VISIBLE);
                        //syncIconContainer.setVisibility(View.GONE);
                    }
                })
                .start();

        */

        //syncIcon.startAnimation(animation);

        /*
        //Animation animation = AnimationUtils.loadAnimation(this.getApplication().getApplicationContext(), R.anim.syncanim);
        //animation.setDuration(5000);
        //syncIcon.setAnimation(animation);

        ViewPropertyAnimator animator = btnSyncDatabase.animate();
        animator.rotation(360);
        animator.setDuration(500);
        animator.withEndAction(new Runnable()
        {
            @Override
            public void run() {
                //We use INVISIBLE instead of GONE to avoid a requestLayout
                Button btnSyncDatabase = (Button) findViewById(R.id.btnSyncDatabase);
                LinearLayout syncIconContainer = (LinearLayout) findViewById(R.id.syncIconContainer);
                btnSyncDatabase.setVisibility(View.VISIBLE);
                //syncIconContainer.setVisibility(View.GONE);
            }
        });

        animator.start();
*/



        //        GRANDE_TRAVEL_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=Sydney&units=metric&appid=5d8185e279dfca4c8c4c9344f61cf3d7";

        new AsyncPackage().execute();

        //Toast.makeText(getApplicationContext(),errorMessage, Toast.LENGTH_LONG).show();


/*
                .startAnimation(animation);

        ObjectAnimator colorAnim = ObjectAnimator.ofInt(btnSyncDatabase, "textColor", Color.RED, Color.BLUE);
        colorAnim.setDuration(5000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(5);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
*/



        return;


    }

    public void btnShowList(View v)
    {


        /*
        //Hide Empty List label if there are items in the list
        TextView tvEmptyList = (TextView) findViewById(R.id.tvEmptyList);
        tvEmptyList.setTextColor(getResources().getColor(R.color.colorText));
        if (ia.getCount()!=0)
            tvEmptyList.setTextColor(getResources().getColor(R.color.colorTransparent));
        */

        //Refresh list
       // ListView lv = (ListView) findViewById(R.id.lvPackageList);
       // lv.invalidate();

        return;
    }
}
