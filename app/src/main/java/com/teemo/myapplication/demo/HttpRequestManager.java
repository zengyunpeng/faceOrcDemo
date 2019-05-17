package com.teemo.myapplication.demo;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangwenjun on 2017/8/31.
 */

public class HttpRequestManager {
    public final static int TIMEOUT = 10000;
    private static HttpRequestManager instance;

    public static HttpRequestManager getInstance() {
        if (instance == null) {
            instance = new HttpRequestManager();
        }
        return instance;
    }

    public void verify(Context context, String url, String sign, String signVersion, String bizToken, byte[] megLiveData, HttpRequestCallBack listener) {
        MultipartEntity entity = new MultipartEntity();
        entity.addStringPart("sign", sign);
        entity.addStringPart("sign_version", signVersion);
        entity.addStringPart("biz_token", bizToken);
        entity.addBinaryPart("meglive_data", megLiveData);

        sendMultipartRequest(context, url, entity, new HashMap<String, String>(), listener);

    }

    public void getBizToken(Context context, String url, String sign, String signVersoin, String livenessType, int comparisonType, String idcardName, String idcardNum, String uuid, byte[] image_ref1, HttpRequestCallBack listener) {
        MultipartEntity entity = new MultipartEntity();
        entity.addStringPart("sign", sign);
        entity.addStringPart("sign_version", signVersoin);
        entity.addStringPart("liveness_type", livenessType);
        entity.addStringPart("comparison_type", "" + comparisonType);
        if (comparisonType == 1) {
            entity.addStringPart("idcard_name", idcardName);
            entity.addStringPart("idcard_number", idcardNum);
        } else if (comparisonType == 0) {
            entity.addStringPart("uuid", uuid);
            entity.addBinaryPart("image_ref1", image_ref1);
        }
        sendMultipartRequest(context, url, entity, new HashMap<String, String>(), listener);
    }

    private void sendPostRequest(Context context, String url, final Map<String, String> params, final Map<String, String> header, final HttpRequestCallBack listener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null) {
                    if (listener != null) {
                        listener.onFailure(-1, "timeout exception".getBytes());
                    }
                } else if (error.networkResponse == null) {
                    if (listener != null) {
                        listener.onFailure(-1, "timeout exception".getBytes());
                    }
                } else {
                    if (listener != null) {
                        listener.onFailure(error.networkResponse.statusCode, error.networkResponse.data);
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        VolleyHelper.getInstance(context).addToRequestQueue(request);
    }

    private void sendGetRequest(Context context, String url, final Map<String, String> header, final HttpRequestCallBack listener) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null) {
                    listener.onFailure(-1, "timeout exception".getBytes());
                } else if (error.networkResponse == null) {
                    listener.onFailure(-1, "timeout exception".getBytes());
                } else {
                    listener.onFailure(error.networkResponse.statusCode, error.networkResponse.data);
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        VolleyHelper.getInstance(context).addToRequestQueue(request);
    }

    private void sendMultipartRequest(Context context, String url, MultipartEntity mult, final Map<String, String> header, final HttpRequestCallBack listener) {
        MultipartRequest multipartRequest = new MultipartRequest(
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null) {
                    listener.onFailure(-1, "timeout exception".getBytes());
                } else if (error.networkResponse == null) {
                    listener.onFailure(-1, "timeout exception".getBytes());
                } else {
                    listener.onFailure(error.networkResponse.statusCode, error.networkResponse.data);
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        // 通过MultipartEntity来设置参数
        multipartRequest.setmMultiPartEntity(mult);

        VolleyHelper.getInstance(context).addToRequestQueue(multipartRequest);
    }

    /**
     * 比对银行卡的方法
     */
    public void distinguishBankCard(Context context, String url, String appKey, String appSecret, byte[] megLiveData, HttpRequestCallBack listener) {
        MultipartEntity entity = new MultipartEntity();
        entity.addBinaryPart("image", megLiveData);
        sendMultipartRequest(context, url, entity, new HashMap<String, String>(), listener);
    }



}
