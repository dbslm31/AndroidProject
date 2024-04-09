package com.example.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApiServices {

        private static final String URL_API_SEARCH_IMAGE = "https://api.thecatapi.com/v1/images/search";
        private static final String URL_END_BREEDS = "?has_breeds=1";
        private static final String URL_END_API_KEY = "&api_key=";
        private static final String API_KEY = "live_Vd2XNWnMBpWTsjoBUOyEmmJ0xVCGtxQsVgdaW6FPD6cputraAjpNw9tTqkcFmtlF";
        private static final String URL_API_VOTE = "https://api.thecatapi.com/v1/votes";
        private static final String URL_API_FAVOURITE = "https://api.thecatapi.com/v1/favourites"

        //Fetch random cat images and information
        public void getRandomCatImage(Context context, Response.ErrorListener errorListener) {
            RequestQueue queue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(Request.Method.GET, URL_API_SEARCH_IMAGE + URL_END_BREEDS + URL_END_API_KEY +  API_KEY,
                    response -> {
                        Log.d("getRandomCatImage response", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                //Set Cat ImageId
                                String catImageId = object.getString("id");
                                Cat cat = new Cat();
                                cat.setImageId(object.getString("id"));

                                //Set Cat Images
                                String imageUrl = object.getString("url");
                                Log.d("Cat Image URL", imageUrl);
                                cat.setUrl(object.getString("url"));

                                //Set Cat Infos
                                if (!object.getJSONArray("breeds").isNull(0)) {
                                    JSONObject breedsInfo = object.getJSONArray("breeds").getJSONObject(0);
                                    cat.setName(breedsInfo.optString("name", ""));
                                    cat.setOrigin(breedsInfo.optString("origin", ""));
                                    cat.setAdaptability(breedsInfo.optInt("adaptability", 0));
                                    cat.setAffectionLevel(breedsInfo.optInt("affection_level", 0));
                                    cat.setChildFriendly(breedsInfo.optInt("child_friendly", 0));
                                    cat.setEnergyLevel(breedsInfo.optInt("energy_level", 0));
                                    cat.setIntelligence(breedsInfo.optInt("intelligence", 0));
                                    cat.setSocialNeeds(breedsInfo.optInt("social_needs", 0));
                                }
                            }
                        } catch (JSONException e) {
                            Log.e("ApiServices", "JSON parsing error: " + e.getMessage());
                        }
                    }, errorListener);

            queue.add(request);
        }

        // Vote  for a cat image
        public void voteOnCatImage(Context context, String imageId, int voteValue, Response.ErrorListener errorListener) {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = URL_API_VOTE;
            JSONObject voteData = new JSONObject();
            try {

                /*
                ** Note pour Nils —
                ** OnClick sur le button
                ** voteValue doit être 1 si cest un like
                ** et -1 si c'est un dislike
                */

                voteData.put("image_id", imageId);
                voteData.put("value", voteValue);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, voteData,
                    response -> Log.d("Vote", "Vote successful"),
                    errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("x-api-key", API_KEY);
                    return headers;
                }
            };
            queue.add(jsonObjectRequest);
        }

        //Fetch all votes
    public void getVotes(Context context, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = URL_API_VOTE;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("GetVotes", "Response: " + response);
                }, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-api-key", API_KEY);
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    //  Add an image to favourites
    public void addFavourite(Context context, String imageId, String subId, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = URL_API_FAVOURITE;
        JSONObject body = new JSONObject();
        try {
            /* Si on a le temps de faire l'authentification
             * le body de requête sera
             * body.put("image_id", imageId);
             * body.put("sub_id", subId);
             * avec subId = Id de l'utilisateur */

            body.put("image_id", imageId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, body, responseListener, errorListener) {



            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("x-api-key", "YOUR-API-KEY");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public void getFavourites(Context context, String subId, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = URL_API_FAVOURITE;
        /* Si on a le temps de faire l'authentification
        * l'url sera
        * String url = "URL_API_FAVOURITE + "?sub_id=" + subId;
        * avec subId = Id de l'utilisateur */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-api-key", API_KEY);
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    public void deleteFavourite(Context context, int favouriteId, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = URL_API_FAVOURITE + "/" favouriteId;

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, responseListener, errorListener) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-api-key", API_KEY);
                return headers;
            }
        };
        queue.add(stringRequest);
    }






}