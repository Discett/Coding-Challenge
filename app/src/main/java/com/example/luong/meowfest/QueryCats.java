package com.example.luong.meowfest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luong on 10/23/2018.
 */

public class QueryCats extends AsyncTask<String, Void, List<CatType>> {
    //String server_response;
    List<CatType> lct = new ArrayList<>();
    MainActivity activity;

    public QueryCats(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected List<CatType> doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                //server_response = readStream(urlConnection.getInputStream());
                JSONArray jsonArray = new JSONArray(readStream(urlConnection.getInputStream()));
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jo = jsonArray.getJSONObject(i);
                    lct.add(new CatType(jo.getString("title"),
                                        formatTimeStamp(jo.getString("timestamp")),
                                        jo.getString("description"),
                                        formatImage(jo.getString("image_url"))));
                }
                JSONObject jo = jsonArray.getJSONObject(0);
                jo.getString("image");
                Log.d("CatalogClient", jsonArray.toString());
                //Log.v("CatalogClient", server_response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Bitmap formatImage(String image) {
        try {
            URL url = new URL(image);
            Bitmap bmImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            Log.d("QueryCats", bmImage.toString());
            return bmImage;
        } catch(IOException e) {
            System.out.println(e);
        }
        return null;
    }

    private String formatTimeStamp(String timestamp) {
        String[] split = timestamp.split("T");
        String result = split[0];
        return result;
    }

    @Override
    protected void onPostExecute(List<CatType> catTypes) {
        super.onPostExecute(catTypes);
        if(!lct.isEmpty()){
            for(int i = 0; i < lct.size();i++){
                Log.d("lct",lct.get(i).toString());
            }
        }
        activity.setList(lct);
        //Log.e("Response", "" + server_response);
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
