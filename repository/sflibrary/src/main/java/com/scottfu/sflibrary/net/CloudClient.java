package com.scottfu.sflibrary.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.NetworkState;
import com.scottfu.sflibrary.util.ToastManager;

import org.json.JSONArray;
import org.json.JSONException;
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


    public static void getRequest(Context context,String httpUrl) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("id", "0");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtil.e("jjjjj",jsonObject.toString());

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,httpUrl, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "response -> " + response.toString());
                        LogUtil.e("1111111",response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, error.getMessage(), error);
                LogUtil.e("222222",error.toString());
            }
        })
        {
            //注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
            //必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
            //所以这个方法在此处是不需要的
    @Override
    protected Map<String, String> getParams() {
          Map<String, String> map = new HashMap<String, String>();
            map.put("id", "1");

        return map;
    }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Accept", "application/json");
//                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
//        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonRequest);
    }


}
