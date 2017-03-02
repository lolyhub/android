package com.neosoft.lolyhub.lolyhubapp.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by yogeshm on 3/1/2017.
 */

public class GetOlaBaseFare extends AsyncTask<String, String, String> {

    private String id;
    private Integer eta;
    private String distance;
    private String displayName;
    private Integer base_fare;
    private Integer min_fare;
    private Integer cost_per_dist;
    CabsRow row = new CabsRow();
    private ArrayList<CabsRow> listcabs = new ArrayList<>();


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        String responseString = "";
        OkHttpClient httpClient = new OkHttpClient();
        Log.e("lat " + Constants.START_LATITUDE, "long " + Constants.START_LONGITUDE);
        Request request = new Request.Builder().header("X-APP-TOKEN", Constants.X_APP_TOKEN).header("Content-Type", "application/json").url(Constants.BASE_UBER_URL_OLA + "/products?pickup_lat=18.5280537" +
                Constants.START_LATITUDE +
                "&pickup_lng=73.8795788" + Constants.START_LONGITUDE
        ).build();
        okhttp3.Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("response string ", "response " + responseString);
        return responseString;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.e(s + "ola response", "");
        try {
            JSONObject main = new JSONObject(s);
            JSONArray categories = main.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                JSONObject sub_category = categories.getJSONObject(i);







                id = sub_category.getString("id");
                if (!id.equals("rental")) {
                    row.setId(id);
                    eta = sub_category.getInt("eta");
                    row.setEta(eta);
                    displayName = sub_category.getString("display_name");
                    row.setDisplayName(displayName);
                    distance = sub_category.getString("distance");
                    row.setDistance(distance);


                    JSONArray price_list_json = sub_category.getJSONArray("fare_breakup");


                    for (int j = 0; j < price_list_json.length(); j++) {

                        JSONObject sub_price_list_json = categories.getJSONObject(i);

                        base_fare = sub_price_list_json.getInt("base_fare");
                        row.setBase_fair(base_fare);
                        min_fare = sub_price_list_json.getInt("minimum_fare");
                        row.setMinimum_fair(min_fare);
                        cost_per_dist = sub_price_list_json.getInt("cost_per_distance");
                        row.setCost_per_KM(cost_per_dist);
                        listcabs.add(row);


                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("cab_size ", "cab_size " + listcabs.size());
        for (int i = 0; i < listcabs.size(); i++) {
            Log.e("util_id ", "util_id " + listcabs.get(i).getMinimum_fair());
        }

        super.onPostExecute(s);
    }
}
