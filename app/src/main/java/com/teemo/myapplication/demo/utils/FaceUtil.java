package com.teemo.myapplication.demo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.megvii.meglive_sdk.listener.DetectCallback;
import com.megvii.meglive_sdk.listener.PreCallback;
import com.megvii.meglive_sdk.manager.MegLiveManager;
import com.teemo.myapplication.demo.GenerateSign;
import com.teemo.myapplication.demo.HttpRequestCallBack;
import com.teemo.myapplication.demo.HttpRequestManager;

import org.json.JSONObject;

/**
 * description ： TODO:类的作用
 * author : teemo
 * email :
 * date : 2019/5/16 16:24
 */
public class FaceUtil {
    private static String sign = "";
    private MegLiveManager megLiveManager;
    private static FaceUtil instance;


    public static FaceUtil getInstance() {
        if (instance == null) {
            synchronized (FaceUtil.class) {
                if (instance == null) {
                    instance = new FaceUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 调用鉴权接口
     */
    public void startAuthentication(@NonNull final Context context, @NonNull String livenessType, @NonNull String idcardName, @NonNull String idcardNum, @NonNull final onAuthListener callBack) {
        preAuthentication();
        HttpRequestManager.getInstance().getBizToken(context, Constants.GET_BIZTOKEN_URL, sign, Constants.SIGN_VERSION, livenessType, 1, idcardName, idcardNum, "", null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String responseBody) {
                try {
                    JSONObject json = new JSONObject(responseBody);
                    String bizToken = json.optString("biz_token");
                    callBack.onAuthSuccess(bizToken);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "鉴权失败请检查配置", Toast.LENGTH_SHORT).show();
                    callBack.onAuthFail(0, null);
                }
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {
                callBack.onAuthFail(statusCode, responseBody);
            }
        });
    }


    /**
     * 检测人脸前的一些准备工作
     * 当CallBack返回1000的时候表示环境初始化成功
     *
     * @param context
     */
    public void prepareDect(Context context, String bizToken, PreCallback callback) {
        if (megLiveManager == null) {
            megLiveManager = MegLiveManager.getInstance();
        }
        megLiveManager.preDetect(context, bizToken, "zh", "https://api.megvii.com", callback);
    }

    /**
     * 开始人脸检测
     * 当CallBack返回1000的时候表示人脸检测成功
     */
    public void startDetect(DetectCallback callback) {
        megLiveManager.setVerticalDetectionType(MegLiveManager.DETECT_VERITICAL_FRONT);
        megLiveManager.startDetect(callback);
    }

    /**
     * 开始进行服务器端人脸比对,
     * 当callBack返回 result_code为1000时表示人脸比对成功是同一个人
     */
    public void verify(final Context context, String token, byte[] data, HttpRequestCallBack callBack) {
        HttpRequestManager.getInstance().verify(context, Constants.VERIFY_URL, sign, Constants.SIGN_VERSION, token, data, callBack);
    }


    /**
     * 初始化一些认证的基本数据
     */
    private void preAuthentication() {
        if (TextUtils.isEmpty(sign)) {
            long currtTime = System.currentTimeMillis() / 1000;
            long expireTime = (System.currentTimeMillis() + 60 * 60 * 100) / 1000;
            sign = GenerateSign.appSign(Constants.API_KEY, Constants.SECRET, currtTime, expireTime);
        }
    }


    interface onAuthListener {
        void onAuthSuccess(String bizToken);

        void onAuthFail(int statusCode, byte[] responseBody);
    }
}
