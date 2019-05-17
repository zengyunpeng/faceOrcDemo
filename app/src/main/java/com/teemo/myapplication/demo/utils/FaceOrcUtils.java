package com.teemo.myapplication.demo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.megvii.meglive_sdk.listener.DetectCallback;
import com.megvii.meglive_sdk.listener.PreCallback;
import com.megvii.meglive_sdk.manager.MegLiveManager;
import com.teemo.myapplication.demo.GenerateSign;
import com.teemo.myapplication.demo.HttpRequestCallBack;
import com.teemo.myapplication.demo.HttpRequestManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * description ： 封装的工具类
 * author : teemo
 * email :
 * date : 2019/5/16 11:40
 */
public class FaceOrcUtils implements DetectCallback, PreCallback {
    private static volatile FaceOrcUtils instance;
    private String sign = "";
    private MegLiveManager megLiveManager;
    Context c;
    OnDetectListener l;

    private FaceOrcUtils() {

    }

    public static FaceOrcUtils getInstance() {
        if (instance == null) {
            synchronized (FaceOrcUtils.class) {
                if (instance == null) {
                    instance = new FaceOrcUtils();
                }
            }
        }
        return instance;
    }


    public void startFaceDetect(@NonNull final Context context, @NonNull String livenessType, @NonNull String idcardName, @NonNull String idcardNum, @NonNull final OnDetectListener callBack) {
        if (TextUtils.isEmpty(sign)) {
            long currtTime = System.currentTimeMillis() / 1000;
            long expireTime = (System.currentTimeMillis() + 60 * 60 * 100) / 1000;
            sign = GenerateSign.appSign(Constants.API_KEY, Constants.SECRET, currtTime, expireTime);
        }
        if (megLiveManager == null) {
            megLiveManager = MegLiveManager.getInstance();
        }
        instance.l = callBack;
        instance.c = context;
        HttpRequestManager.getInstance().getBizToken(context, Constants.GET_BIZTOKEN_URL, sign, Constants.SIGN_VERSION, livenessType, 1, idcardName, idcardNum, "", null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String responseBody) {
                Log.i("tag", "鉴权结果返回:" + responseBody);
                try {
                    JSONObject json = new JSONObject(responseBody);
                    String bizToken = json.optString("biz_token");
                    megLiveManager.preDetect(context, bizToken, "zh", "https://api.megvii.com", FaceOrcUtils.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "鉴权失败请检查配置", Toast.LENGTH_SHORT).show();
                    callBack.onDetectFail();
                }
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {
                Toast.makeText(context, "鉴权失败请检查配置" + statusCode, Toast.LENGTH_SHORT).show();
                callBack.onDetectFail();
            }
        });
    }

    @Override
    public void onDetectFinish(String token, int errorCode, String errorMessage, String data) {
        if (errorCode == 1000) {
            verify(token, data.getBytes());
        }
    }

    @Override
    public void onPreStart() {

    }

    @Override
    public void onPreFinish(String token, int errorCode, String errorMessage) {
        if (errorCode == 1000) {
            megLiveManager.setVerticalDetectionType(MegLiveManager.DETECT_VERITICAL_FRONT);
            megLiveManager.startDetect(this);
        }
    }


    private void verify(String token, byte[] data) {
        HttpRequestManager.getInstance().verify(c, Constants.VERIFY_URL, sign, Constants.SIGN_VERSION, token, data, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String responseBody) {
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 1000) {
                        //同一个人
                        Toast.makeText(c, "身份验证成功", Toast.LENGTH_SHORT).show();
                        if (l != null) {
                            l.onDetectSuccess();
                        }
                    } else {
                        if (l != null) {
                            l.onDetectFail();
                        }
                        Toast.makeText(c, "经对比不是同一个人", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (l != null) {
                        l.onDetectFail();
                    }
                    Toast.makeText(c, "对比失败请重试" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {
                if (l != null) {
                    l.onDetectFail();
                }
                Toast.makeText(c, "对比失败请重试:" + statusCode, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "对比失败请重试:" + statusCode, Toast.LENGTH_SHORT).show();
                Log.w("result", new String(responseBody));
            }
        });
    }

    public interface OnDetectListener {
        void onDetectSuccess();

        void onDetectFail();
    }
}
