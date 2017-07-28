package com.scottfu.sflibrary.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.scottfu.sflibrary.util.NetworkState;
import com.scottfu.sflibrary.util.ToastManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fujindong on 2017/3/6.
 * 网络访问
 */

public class CloudClient {


    public static void getHttpRequest(Context context, String url, final JSONResultHandler jsonResultHandler){



        if (!NetworkState.networkConnected(context)) {
            ToastManager.repeatToast(context,"无法连接到网络，请检查网络连接");
            return;
        }


        StringRequest request = new StringRequest(url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                jsonResultHandler.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jsonResultHandler.onError(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
    }
//
//
//    public static void doHttpRequestV2(Context context, String url, final JSONObject jsonObject, final JSONResultHandler jsonResultHandler) {
//        String sss = jsonObject.toString();
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                    jsonResultHandler.onSuccess(response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                jsonResultHandler.onError(error);
//            }
//        });
//        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
//
//    }
//
//    public static void doHttpRequestv3(final HashMap<String,String> params, Context context, String url, final JSONResultHandler jsonResultHandler) {
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                jsonResultHandler.onSuccess(response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                jsonResultHandler.onError(error);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//
//                return params;
//            }
//        };
//        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
//
//    }


    public static void doHttpRequest(Context context, final String url,
                                       final HashMap<String,String> postMap, final String loading,
                                       final JSONResultHandler resultHandler) {



        if (!NetworkState.networkConnected(context)) {
            ToastManager.repeatToast(context,"无法连接到网络，请检查网络连接");
            return;
        }


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                resultHandler.onSuccess(response.toString());
//
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultHandler.onError(error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return postMap;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
    }


}
