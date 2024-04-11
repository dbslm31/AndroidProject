package com.example.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


    public class ApiServices {

        private static final String URL_API_SEARCH_IMAGE = "https://api.thecatapi.com/v1/images/search";
        private static final String URL_END_BREEDS = "?has_breeds=1";
        private static final String API_KEY = "&api_key=live_Vd2XNWnMBpWTsjoBUOyEmmJ0xVCGtxQsVgdaW6FPD6cputraAjpNw9tTqkcFmtlF";

        public static void getRandomCatImage(Context context, CatObserver listener) {
            RequestQueue queue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(Request.Method.GET, URL_API_SEARCH_IMAGE + URL_END_BREEDS + API_KEY,
                    response -> {
                        Log.d("getRandomCatImage response", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                //Set Cat Images
                                String imageUrl = object.getString("url");
                                Log.d("Cat Image URL", imageUrl);
                                Cat cat = new Cat();
                                cat.setUrl(object.getString("url"));

                                //Set Cat Infos
                                if (!object.getJSONArray("breeds").isNull(0)) {
                                    JSONObject breedsInfo = object.getJSONArray("breeds").getJSONObject(0);
                                    cat.setName(breedsInfo.optString("name", ""));
                                    cat.setOrigin(breedsInfo.optString("origin", ""));
                                    cat.setAdaptability(breedsInfo.optInt("adaptability", 0));
                                    cat.setAffection_level(breedsInfo.optInt("affection_level", 0));
                                    cat.setChild_friendly(breedsInfo.optInt("child_friendly", 0));
                                    cat.setEnergy_level(breedsInfo.optInt("energy_level", 0));
                                    cat.setIntelligence(breedsInfo.optInt("intelligence", 0));
                                    cat.setSocial_needs(breedsInfo.optInt("social_needs", 0));
                                    listener.onReceiveCatInfo(cat);
                                }
                            }
                        } catch (JSONException e) {
                            Log.e("ApiServices", "JSON parsing error: " + e.getMessage());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });

            queue.add(request);
        }

        public static void loadCatAvatar(Context context, Cat cat, final ImageView imageView){
            RequestQueue queue = Volley.newRequestQueue(context);
            ImageRequest request = new ImageRequest( cat.getUrl(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            imageView.setImageResource(android.R.drawable.ic_menu_gallery);
                        }
                    });
            queue.add(request);
        }

    }