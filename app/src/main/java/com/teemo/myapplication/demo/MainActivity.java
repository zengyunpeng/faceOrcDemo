package com.teemo.myapplication.demo;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.megvii.demo.activity.IDCardDetectActivity;
import com.megvii.demo.utils.Configuration;
import com.megvii.idcardquality.IDCardQualityLicenseManager;
import com.megvii.licensemanager.Manager;
import com.megvii.meglive_sdk.listener.DetectCallback;
import com.megvii.meglive_sdk.listener.PreCallback;
import com.megvii.meglive_sdk.manager.MegLiveManager;
import com.teemo.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static android.os.Build.VERSION_CODES.M;

/**
 * 使用说明
 * 使用前请商家申请自己的apiKey 和 secret
 * 在使用无源对比是需要补全比对所需要的底库图片
 * 在使用有源比对时需要补全身份号和姓名
 * demo中所包含的两个网络请求和签名建议商家放在自己服务器端去做，确保自己的apikey和secret安全
 * 网络请求中只包含了必选字段 商家需要根据自己需要完善 详细说明参考API文档
 */

public class MainActivity extends Activity implements View.OnClickListener, DetectCallback, PreCallback {
    private static final int ACTION_YY = 1;
    private static final int ACTION_WY = 2;
    private static final int FMP_YY = 3;
    private static final int FMP_WY = 4;
    private Button bt_action_yy, bt_action_wy, bt_fmp_yy, bt_fmp_wy, idCardQuality, idCardQualityBack, bankCard;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    /**
     * 身份证认证请求码
     */
    private static final int INTO_IDCARDSCAN_PAGE = 102;
    /**
     * 银行卡认证请求码
     */
    private static final int BANK_CARD_REQUEST_CODE = 102;
    private static final int EXTERNAL_STORAGE_REQ_WRITE_EXTERNAL_STORAGE_CODE = 101;
    private ProgressDialog mProgressDialog;
    private MegLiveManager megLiveManager;
    private static final String GET_BIZTOKEN_URL = "https://api.megvii.com/faceid/v3/sdk/get_biz_token";
    private static final String VERIFY_URL = "https://api.megvii.com/faceid/v3/sdk/verify";
    private static final String API_KEY = "nfihZtxQviTdqDnRxf_uSCURAY0XUhyy";
    private static final String SECRET = "Fuhh9fp7v6OgSGzftwkXQTIvSQaBZnrd";
    private String sign = "";
    private static final String SIGN_VERSION = "hmac_sha1";
    private byte[] imageRef;//底库图片 TODO
    private int buttonType;
    private static final String LANGUAGE = "zh";//en
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        megLiveManager = MegLiveManager.getInstance();
        /**
         * 如果build.gradle中的applicationId 与 manifest中package不一致，必须将manifest中package通过
         * 下面方法传入，如果一致可以不调用此方法
         */
        //megLiveManager.setManifestPack(this, "com.megvii.meglive_still.demo");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);


        bt_action_yy = findViewById(R.id.bt_action_yy);
        bt_action_yy.setOnClickListener(this);
        bt_action_wy = findViewById(R.id.bt_action_wy);
        bt_action_wy.setOnClickListener(this);
        bt_fmp_yy = findViewById(R.id.bt_fmp_yy);
        bt_fmp_yy.setOnClickListener(this);
        bt_fmp_wy = findViewById(R.id.bt_fmp_wy);
        bt_fmp_wy.setOnClickListener(this);
        idCardQuality = findViewById(R.id.idCardQuality);
        idCardQuality.setOnClickListener(this);
        idCardQualityBack = findViewById(R.id.idCardQualityBack);
        idCardQualityBack.setOnClickListener(this);
        bankCard = findViewById(R.id.bankCard);
        bankCard.setOnClickListener(this);
        img = findViewById(R.id.img);

        long currtTime = System.currentTimeMillis() / 1000;
        long expireTime = (System.currentTimeMillis() + 60 * 60 * 100) / 1000;
        sign = GenerateSign.appSign(API_KEY, SECRET, currtTime, expireTime);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_action_yy:
                buttonType = ACTION_YY;
                requestCameraPerm();
                break;
            case R.id.bt_action_wy:
                buttonType = ACTION_WY;
                requestCameraPerm();
                break;
            case R.id.bt_fmp_yy:
                buttonType = FMP_YY;
                requestCameraPerm();
                break;
            case R.id.bt_fmp_wy:
                buttonType = FMP_WY;
                requestCameraPerm();
                break;
            case R.id.idCardQuality:
                //初始化一些基本参数
                Configuration.setIsVertical(this, true);
                Configuration.setCardType(this, 1);
                //开始鉴权
                startGetLicense(0, INTO_IDCARDSCAN_PAGE);
                break;
            case R.id.idCardQualityBack:
                Configuration.setIsVertical(this, true);
                Configuration.setCardType(this, 2);
                //开始鉴权
                startGetLicense(0, INTO_IDCARDSCAN_PAGE);
                break;
            case R.id.bankCard:
                Configuration.setIsVertical(this, true);
                Configuration.setCardType(this, 3);
                //开始鉴权
                startGetLicense(0, BANK_CARD_REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    private void requestCameraPerm() {
        if (android.os.Build.VERSION.SDK_INT >= M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                //进行权限请求
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION_REQUEST_CODE);
            } else {
                requestWriteExternalPerm();
            }
        } else {
            beginDetect(buttonType);
        }
    }

    private IDCardQualityLicenseManager mIdCardLicenseManager;

    /**
     * 0表示身份证识别
     * 1表示人脸识别
     *
     * @param type
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void startGetLicense(final int type, final int requestCode) {
        mIdCardLicenseManager = new IDCardQualityLicenseManager(
                MainActivity.this);

        long status = 0;
        try {
            status = mIdCardLicenseManager.checkCachedLicense();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        //大于0，已授权或者授权未过期
        if (status > 0) {
            Intent intent = new Intent(MainActivity.this, IDCardDetectActivity.class);
            MainActivity.this.startActivityForResult(intent, INTO_IDCARDSCAN_PAGE);
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();

        } else { //需要重新授权
            Toast.makeText(MainActivity.this, "没有缓存的授权信息，开始授权", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        getLicense(type, requestCode);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }

    }


    private void getLicense(int type, final int requestCode) {
        Manager manager = new Manager(MainActivity.this);
        manager.registerLicenseManager(mIdCardLicenseManager);

        String uuid = Configuration.getUUID(MainActivity.this);
        String authMsg = mIdCardLicenseManager.getContext(uuid);
        manager.takeLicenseFromNetwork(authMsg);
        //大于0，已授权或者授权未过期
        if (mIdCardLicenseManager.checkCachedLicense() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, IDCardDetectActivity.class);
                    MainActivity.this.startActivityForResult(intent, requestCode);
                    Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    private void requestWriteExternalPerm() {
        if (android.os.Build.VERSION.SDK_INT >= M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //进行权限请求
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        EXTERNAL_STORAGE_REQ_WRITE_EXTERNAL_STORAGE_CODE);
            } else {
                beginDetect(buttonType);
            }
        } else {
            beginDetect(buttonType);
        }
    }


    /**
     * 开始检测
     *
     * @param type
     */
    private void beginDetect(int type) {
        if (type == ACTION_YY) {
            getBizToken("meglive", 1, "曾云鹏", "360781199301294217", "", null);
        } else if (type == ACTION_WY) {
            imageRef = bitmapToByte(MainActivity.this);
            getBizToken("meglive", 0, "", "", UUID.randomUUID().toString(), imageRef);
        } else if (type == FMP_YY) {
            getBizToken("still", 1, "曾云鹏", "360781199301294217", "", null);
        } else if (type == FMP_WY) {
            imageRef = bitmapToByte(MainActivity.this);
            getBizToken("still", 0, "", "", UUID.randomUUID().toString(), imageRef);
        }
    }


    private static byte[] bitmapToByte(Context c) {
        Bitmap bitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.a);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imgBytes = baos.toByteArray();
        return imgBytes;
    }

    /**
     * 此接口用于配置人脸比对的身份核实功能，支持有源比对（调用者提供姓名、身份证号、和待核实人脸图）和无源比对（直接比对待核实人脸图和参照人脸图）。
     * 客户通过服务器将本次活体相关的配置传到FaceID服务器，在验证无误后，返回本次业务的biz_token，用FaceID MegLiveStill SDK的初始化。
     */
    private void getBizToken(String livenessType, int comparisonType, String idcardName, String idcardNum, String uuid, byte[] image) {
        mProgressDialog.show();
        HttpRequestManager.getInstance().getBizToken(this, GET_BIZTOKEN_URL, sign, SIGN_VERSION, livenessType, comparisonType, idcardName, idcardNum, uuid, image, new HttpRequestCallBack() {

            @Override
            public void onSuccess(String responseBody) {
                Toast.makeText(MainActivity.this, "鉴权结果返回:" + responseBody, Toast.LENGTH_SHORT).show();
                Log.i("tag", "人脸对比结果返回:" + responseBody);
                try {
                    JSONObject json = new JSONObject(responseBody);
                    String bizToken = json.optString("biz_token");
                    megLiveManager.preDetect(MainActivity.this, bizToken, "zh", "https://api.megvii.com", MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {
                mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, "人脸比对失败请重试", Toast.LENGTH_LONG).show();
                Log.w("onFailure", "statusCode=" + statusCode + ",responseBody" + new String(responseBody));
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
        showDialogDismiss();
    }

    @Override
    public void onPreFinish(String token, int errorCode, String errorMessage) {
        progressDialogDismiss();
        if (errorCode == 1000) {
            megLiveManager.setVerticalDetectionType(MegLiveManager.DETECT_VERITICAL_FRONT);
            megLiveManager.startDetect(this);
        }
    }

    private void verify(String token, byte[] data) {
        showDialogDismiss();
        HttpRequestManager.getInstance().verify(this, VERIFY_URL, sign, SIGN_VERSION, token, data, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String responseBody) {
                progressDialogDismiss();
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 1000) {
                        //同一个人
                        Toast.makeText(MainActivity.this, "经对比是同一个人", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "经对比不是同一个人", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "对比失败请重试" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {
                Toast.makeText(MainActivity.this, "对比失败请重试:" + statusCode, Toast.LENGTH_SHORT).show();
                Log.w("result", new String(responseBody));
                progressDialogDismiss();
            }
        });
    }

    private void progressDialogDismiss() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    private void showDialogDismiss() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null) {
                    mProgressDialog.show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length < 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                //拒绝了权限申请
            } else {
                requestWriteExternalPerm();
            }
        } else if (requestCode == EXTERNAL_STORAGE_REQ_WRITE_EXTERNAL_STORAGE_CODE) {
            if (grantResults.length < 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                //拒绝了权限申请
            } else {
                beginDetect(buttonType);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTO_IDCARDSCAN_PAGE && resultCode == RESULT_OK) {
            //身份证识别返回
//            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//            intent.putExtra("portraitimg_bitmap", data.getByteArrayExtra("portraitimg_bitmap"));
//            intent.putExtra("idcardimg_bitmap", data.getByteArrayExtra("idcardimg_bitmap"));
//            startActivity(intent);
            //获取身份证图片,提取有用信息
            Log.i("tag", "身份证扫描结果返回扫描结果返回");
            byte[] portraitimg_bitmaps = data.getByteArrayExtra("idcardimg_bitmap");
            byte[] portraitimg_bitmap = data.getByteArrayExtra("portraitimg_bitmap");//抠出的头像
            if (portraitimg_bitmap != null) {
                Log.i("tag", "身份证头像节码图片不为空" + portraitimg_bitmaps.length);

            }

            if (portraitimg_bitmaps != null) {
                Log.i("tag", "身份证字节码图片不为空" + portraitimg_bitmaps.length);
                Bitmap bitmap = BitmapFactory.decodeByteArray(portraitimg_bitmaps, 0, portraitimg_bitmaps.length);
                img.setImageBitmap(bitmap);
            }


        } else if (requestCode == BANK_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.i("tag", "银行卡扫描结果返回");
            //获取银行卡图片,提取有用信息
            byte[] portraitimg_bitmaps = data.getByteArrayExtra("idcardimg_bitmap");
            if (portraitimg_bitmaps != null) {
                Log.i("tag", "银行卡字节码图片不为空" + portraitimg_bitmaps.length);
                Bitmap bitmap = BitmapFactory.decodeByteArray(portraitimg_bitmaps, 0, portraitimg_bitmaps.length);
                img.setImageBitmap(bitmap);
            }
            //进行比对

        }
    }
}
