package com.megvii.demo.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.megvii.demo.utils.CommonUtils;
import com.megvii.demo.utils.Configuration;
import com.megvii.demo.utils.ICamera;
import com.megvii.demo.utils.ModelUtil;
import com.megvii.demo.utils.RotaterUtil;
import com.megvii.demo.utils.StatusBarCompat;
import com.megvii.demo.view.IDCardGuide;
import com.megvii.demo.view.IDCardGuideH;
import com.megvii.idcard.quality.R;
import com.megvii.idcardquality.IDCardQualityAssessment;
import com.megvii.idcardquality.IDCardQualityLicenseManager;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.idcardquality.bean.IDCardAttr;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static android.os.Build.VERSION_CODES.M;


public class IDCardDetectActivity extends Activity implements TextureView.SurfaceTextureListener, Camera.PreviewCallback, View.OnClickListener {
    private TextureView mTextureView;
    private RelativeLayout mRlMegviiTips, mTitleBar, mRlMegviiGoBack, mRlMegviiGoBackH, mTitleBarH;
    private TextView mTvMegviiTips, mTvMegviiTipsH;
    private RelativeLayout mRelativeLayout;
    IDCardQualityLicenseManager m;

    private IDCardGuide mIdcardGuide;
    private IDCardGuideH mIdcardGuideH;
    private boolean mIsVertical;
    private int mCardType = 0;
    private IDCardAttr.IDCardSide mSide;
    private ICamera mICamera;
    private BlockingQueue<byte[]> mFrameDataQueue;
    private boolean mHasSurface = false;
    private DecodeThread mDecoder = null;
    private IDCardQualityAssessment mIdCardQualityAssessment = null;
    private Rect mRoi;
    private ImageView mImageView, mIvBottomLogoH;
    private boolean isCanDetected = false;//是否可以检测，5s超时后停止检测 TODO
    private boolean isDetectFinished = false;

    private IDCardQualityResult mQualityResult;

    private ImageView mIbAnimalBreathView;
    private ImageView mIbAnimalOneView;
    private ImageView mIvPeopleIconlightView;
    private ImageView mIvChinaIconLightView;
    private RectF mRectScreen;
    private TextView mTvTipText;
    private ObjectAnimator mIDcardAlphaAnimation;
    private ObjectAnimator mPeopleIconAlphaAnimation;
    private ObjectAnimator mChinaIconAlphaAnimation;
    private ImageView mIvIconTickAll;


    private TextView mToastTitle;
    private ImageView mToastTip;
    private RelativeLayout mLayoutToast;

    private int FIRST_RECT = 1;
    private int SENCOND_RECT = 2;
    private int rectType = FIRST_RECT;//第几面

    private byte[] mImgData = null;
    private int mImageWidth;
    private int mImageHeight;
    private long mBeginTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsVertical = Configuration.getIsVertical(this);
        if (!mIsVertical) {
            setTheme(R.style.idcard_cn_FullScreenTheme);
            CommonUtils.toggleHideyBar(this);
        } else {
            StatusBarCompat.compat(this, getResources().getColor(R.color.idcard_cn_title_bar_bg_color));
        }
        setContentView(R.layout.activity_detect);
        initView();
    }

    private void initView() {
        mCardType = Configuration.getCardType(this);//1代表人像面，2代表国徽面检测
        mIsVertical = Configuration.getIsVertical(this);//横竖屏

        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_idcard_cn_root_view);
        mRlMegviiGoBack = (RelativeLayout) findViewById(R.id.rl_megvii_idcard_cn_goback);
        mRlMegviiGoBackH = (RelativeLayout) findViewById(R.id.rl_megvii_idcard_cn_goback_h);
        mImageView = (ImageView) findViewById(R.id.iv_idcard_cn_image);
        mTextureView = (TextureView) findViewById(R.id.idcardscan_cn_layout_surface);
        mIvBottomLogoH = (ImageView) findViewById(R.id.iv_idcard_cn_bottom_logo_h);
        mRlMegviiTips = (RelativeLayout) findViewById(R.id.rl_megvii_idcard_cn_tips);
        mTitleBar = (RelativeLayout) findViewById(R.id.in_idcard_cn_title_bar);
        mTitleBarH = (RelativeLayout) findViewById(R.id.rl_megvii_idcard_cn_title_bar_h);
        mTvMegviiTips = (TextView) findViewById(R.id.tv_megvii_idcard_cn_tips);
        mTvMegviiTipsH = (TextView) findViewById(R.id.tv_megvii_idcard_cn_tips_h);

        mIvPeopleIconlightView = (ImageView) findViewById(R.id.iv_people_light_icon);
        mIvChinaIconLightView = (ImageView) findViewById(R.id.iv_china_light_icon);

        mIbAnimalBreathView = (ImageView) findViewById(R.id.ib_animal_breath_view);
        mIbAnimalOneView = (ImageView) findViewById(R.id.ib_animal_one_view);

        mToastTitle = (TextView) findViewById(R.id.toast_tv);
        mToastTip = (ImageView) findViewById(R.id.iv_auth_toast_tip);
        mLayoutToast = (RelativeLayout) findViewById(R.id.layout_toast);

        mTvTipText = (TextView) findViewById(R.id.tv_tip_text);
        mIvIconTickAll = (ImageView) findViewById(R.id.iv_icon_tick_all);
        mIdcardGuide = (IDCardGuide) findViewById(R.id.idcardscan_cn_layout_guide);
        mIdcardGuideH = (IDCardGuideH) findViewById(R.id.idcardscan_cn_layout_guide_h);

        mIdcardGuide.setOnClickListener(this);
        mIdcardGuideH.setOnClickListener(this);
        mRlMegviiGoBack.setOnClickListener(this);
        mRlMegviiGoBackH.setOnClickListener(this);
        mTextureView.setOnClickListener(this);
        mRelativeLayout.setOnClickListener(this);
        mTextureView.setSurfaceTextureListener(this);

        mIbAnimalBreathView.setBackgroundResource(R.mipmap.bg_sfz_light);
        mIbAnimalOneView.setVisibility(View.VISIBLE);


        if (mCardType == 1) {
            mSide = IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT;
            mIbAnimalOneView.setBackgroundResource(R.mipmap.bg_people_icon);

            if (mIsVertical) {
                mTvMegviiTips.setText(getResources().getString(R.string.idcard_cn_tips_face));
            } else {
                mTvMegviiTipsH.setText(getResources().getString(R.string.idcard_cn_tips_face));
            }

        } else if (mCardType == 2) {
            mSide = IDCardAttr.IDCardSide.IDCARD_SIDE_BACK;
            mIbAnimalOneView.setBackgroundResource(R.mipmap.bg_china_icon);
            if (mIsVertical) {
                mTvMegviiTips.setText(getResources().getString(R.string.idcard_cn_tips_emblem));
            } else {
                mTvMegviiTipsH.setText(getResources().getString(R.string.idcard_cn_tips_emblem));
            }
        }
//        else if (mCardType == 3) {
//            mSide = IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT;
//            //截取银行卡  TODO
//            if (mIsVertical) {
//                mTvMegviiTips.setText("银行卡正面拍摄");
//            } else {
//                mTvMegviiTipsH.setText("银行卡正面拍摄");
//            }
//
//        }


        if (mIsVertical) {
            mRelativeLayout.setBackgroundColor(Color.WHITE);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mIdcardGuide.setVisibility(View.VISIBLE);
            mIdcardGuideH.setVisibility(View.GONE);
            mTitleBar.setVisibility(View.VISIBLE);
            mTitleBarH.setVisibility(View.GONE);
            mRlMegviiTips.setVisibility(View.VISIBLE);
            mIdcardGuide.setCardSide(mSide);
        } else {//横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mRelativeLayout.setBackgroundColor(Color.BLACK);

            mIvBottomLogoH.setVisibility(View.VISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mIdcardGuide.setVisibility(View.GONE);
            mIdcardGuideH.setVisibility(View.VISIBLE);
            mTitleBar.setVisibility(View.GONE);
            mTitleBarH.setVisibility(View.VISIBLE);
            mRlMegviiTips.setVisibility(View.GONE);
            mIdcardGuideH.setCardSide(mSide);
        }


        if (Build.VERSION.SDK_INT >= M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "没有摄像机权限", Toast.LENGTH_SHORT).show();
                doFinish();
            }
        }

        initSdk();
    }

    private void initSdk() {
        mICamera = new ICamera(mIsVertical);

        mFrameDataQueue = new LinkedBlockingDeque<byte[]>(1);

        //初始化
        mIdCardQualityAssessment = new IDCardQualityAssessment.Builder()
                .setIsIgnoreShadow(false)// 不忽略阴影
                .setIsIgnoreHighlight(false)//不忽略光斑
                .build();


        //必须先做授权判断

        //加载模型，加载该方法之前，一定要先调用授权！！否则会crash！！！注意
        boolean initSuccess = mIdCardQualityAssessment.init(this, ModelUtil.readModel(this, R.raw.meg_idcard));
        if (!initSuccess) {
            Toast.makeText(this, "检测器初始化失败", Toast.LENGTH_SHORT).show();
            doFinish();
        }

    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (isCanDetected) {
            mFrameDataQueue.offer(data);
        }
    }

    private void doPreview() {
        if (!mHasSurface) {
            return;
        } else {
            mICamera.startPreview(mTextureView.getSurfaceTexture());
        }
    }


    /**
     * 加载完毕后取得身份证的坐标，计算动画的位置
     */
    private void initIdCardRect() {
        int imageWidth = mICamera.cameraWidth;
        int imageHeight = mICamera.cameraHeight;
        if (mIsVertical) {
            imageWidth = mICamera.cameraHeight;
            imageHeight = mICamera.cameraWidth;
        }
        RectF rectF;
        if (mIsVertical) {
            rectF = mIdcardGuide.getPosition(rectType);
        } else {
            rectF = mIdcardGuideH.getPosition(rectType);
        }
        mRoi = new Rect();
        mRoi.left = (int) (rectF.left * imageWidth);
        mRoi.top = (int) (rectF.top * imageHeight);
        mRoi.right = (int) (rectF.right * imageWidth);
        mRoi.bottom = (int) (rectF.bottom * imageHeight);

        if (!isEven01(mRoi.left)) {
            mRoi.left = mRoi.left + 1;
        }
        if (!isEven01(mRoi.top)) {
            mRoi.top = mRoi.top + 1;
        }
        if (!isEven01(mRoi.right)) {
            mRoi.right = mRoi.right - 1;
        }
        if (!isEven01(mRoi.bottom)) {
            mRoi.bottom = mRoi.bottom - 1;
        }

        //=========animation
        if (mIsVertical) {
            mRectScreen = mIdcardGuide.getScreenPosition(rectType);
        } else {
            mRectScreen = mIdcardGuideH.getScreenPosition(rectType);
        }

        int width = (int) (mRectScreen.right - mRectScreen.left);
        int height = (int) (mRectScreen.bottom - mRectScreen.top);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mIbAnimalOneView.getLayoutParams());
        int offset = (int) getResources().getDimension(R.dimen.idcard_checktrue_rect_big_offset);

        layoutParams.width = width + offset;
        layoutParams.height = height + offset;
        layoutParams.topMargin = (int) mRectScreen.top - offset / 2;

        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mIbAnimalBreathView.setLayoutParams(layoutParams);
        mIbAnimalOneView.setLayoutParams(layoutParams);

        mIvChinaIconLightView.setLayoutParams(layoutParams);
        mIvPeopleIconlightView.setLayoutParams(layoutParams);

        //--tip textview
        RelativeLayout.LayoutParams textlayoutParams = new RelativeLayout.LayoutParams(mTvTipText.getLayoutParams());
        textlayoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        textlayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        textlayoutParams.topMargin = (int) mRectScreen.bottom + 2 * offset;
        textlayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mTvTipText.setLayoutParams(textlayoutParams);

        //toast相对居中，减去框的高度/2， 再减去自身的高度/2
        RelativeLayout.LayoutParams myToastParams = new RelativeLayout.LayoutParams(mLayoutToast.getLayoutParams());
        myToastParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        myToastParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        myToastParams.topMargin = layoutParams.topMargin + layoutParams.height / 2 - (int) getResources().getDimension(R.dimen.checktrue_toast_text_padding_height);
        myToastParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mLayoutToast.setLayoutParams(myToastParams);

        //完整对勾的位置
        RelativeLayout.LayoutParams ivIconTick = new RelativeLayout.LayoutParams(mIvIconTickAll.getLayoutParams());
        ivIconTick.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        ivIconTick.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        ivIconTick.topMargin = layoutParams.topMargin + layoutParams.height / 2 - (int) getResources().getDimension(R.dimen.checktrue_bg_tick_bg_height);
        ivIconTick.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mIvIconTickAll.setLayoutParams(ivIconTick);


    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Camera camera = mICamera.openCamera(this);
        if (camera != null) {
            initIdcardGuide();

            mHasSurface = true;

            doPreview();
            mICamera.setPreviewCallback(this);

            lastFaileType = IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_UNKNOWN;
            mBeginTime = System.currentTimeMillis();
            isCanDetected = true;


            mTextureView.post(new Runnable() {
                @Override
                public void run() {
                    initIdCardRect();
                }
            });

            if (mDecoder == null) {
                mDecoder = new DecodeThread();
            }
            if (mDecoder != null && !mDecoder.isAlive()) {
                mDecoder.start();
            }

            startBreatheAlphaAnimation(mIbAnimalBreathView);
            startBreatheAlphaAnimation(mTvTipText);
        } else {
            Toast.makeText(this, "打开摄像头失败", Toast.LENGTH_SHORT).show();
            doFinish();
            return;
        }
    }

    private void initIdcardGuide() {
        RelativeLayout.LayoutParams layout_params = mICamera.getLayoutParam(this);
        if (mIsVertical) {
            mIdcardGuide.setLayoutParams(layout_params);
        } else {
            layout_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mIdcardGuideH.setLayoutParams(layout_params);
        }
        mTextureView.setLayoutParams(layout_params);
        if (!mIsVertical) {
            layout_params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            mTitleBarH.setLayoutParams(layout_params);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!isDetectFinished) {
            doFinish();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        mICamera.closeCamera();
        mHasSurface = false;
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    private class DecodeThread extends Thread {
        boolean mHasFinished = false;

        public void setmHasSuccess(boolean mHasFinished) {
            this.mHasFinished = mHasFinished;
        }

        @Override
        public void run() {
            Log.i("tag", "run方法执行");
            try {
                byte[] image = null;
                while ((image = mFrameDataQueue.take()) != null) {
                    if (mHasFinished) {//两面全部检测通过
                        return;
                    }
                    if (!isCanDetected) {
                        continue;
                    }
                    mImageWidth = mICamera.cameraWidth;
                    mImageHeight = mICamera.cameraHeight;
                    Log.i("tag", "image : " + image);
                    if (image != null) {
                        Log.i("tag", "image.length : " + image.length);
                    }

                    mImgData = RotaterUtil.rotate(image, mImageWidth, mImageHeight,
                            mICamera.getCameraAngle(IDCardDetectActivity.this));
                    Log.i("tag", "mImgData : " + mImgData);

                    if (mIsVertical) {
                        mImageWidth = mICamera.cameraHeight;
                        mImageHeight = mICamera.cameraWidth;
                    }
                    mQualityResult = mIdCardQualityAssessment.getQuality(mImgData, mImageWidth,
                            mImageHeight, mSide, mRoi);
//                    这里mQualityResult 对象里的 attrs cornerPoint没赋值导致下一句取出图片为空
//                    mQualityResult.attr.cornerPoints
                    Log.i("tag", "mQualityResult.croppedImageOfIDCard() : " + mQualityResult.croppedImageOfIDCard());

                    final boolean isSuccess = mQualityResult.isValid();
                    Log.i("tag", "isSuccess:" + isSuccess);

                    mDecoder.setmHasSuccess(isSuccess);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isSuccess) {
                                handleSuccessResult();
                            } else {
                                IDCardQualityResult.IDCardResultType resultType = mQualityResult.idCardResultType;
                                //如果不是身份证也就是银行卡 TODO 这里进行特殊处理
//                                if (resultType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NOTIDCARD) {
//                                    Log.i("tag", "检测到不是银行卡");
//                                    //返回图片
//                                    handleSuccessResult();
//                                    return;
//                                }


                                //以下几种情况隐藏身份证人像面或者国徽面图标icon
                                if (resultType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NONE
                                        || resultType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NOTINBOUND
                                        || resultType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NOTCLEAR
                                        || resultType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_HAVEHIGHLIGHT
                                        || resultType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_HAVESHADOW
                                        || resultType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_CONVERT) {
                                    dismissIcon();
                                    showFaileToast(resultType);
                                } else {
                                    showIcon();
                                    showFaileToast(resultType);
                                }

                            }
                        }
                    });
                }
            } catch (
                    Throwable e) {
                e.printStackTrace();
            }
        }

    }

    private Bitmap iDCardImg = null;
    private Bitmap portraitImg = null;

    private void handleSuccessResult() {
        cancelMyToast();
        setBlueLine();

        mIbAnimalBreathView.setVisibility(View.GONE);
        mIvIconTickAll.setVisibility(View.VISIBLE);


        //不同手机，intent传值限制的大小不同，实际应用需要注意！此处仅做示例。实际应用可选择其它方式进行传递bitmap  TODO
        Intent intent = new Intent();
        iDCardImg = mQualityResult.croppedImageOfIDCard();
        Log.i("tag", "iDCardImg : " + iDCardImg);
        intent.putExtra("idcardimg_bitmap", CommonUtils.bmp2byteArr(iDCardImg));
        if (mQualityResult.attr.side == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
            portraitImg = mQualityResult.croppedImageOfPortrait();
            intent.putExtra("portraitimg_bitmap", CommonUtils.bmp2byteArr(portraitImg));
        }
        setResult(RESULT_OK, intent);
        doFinish();
    }

    private long toastTime = 0;
    private IDCardQualityResult.IDCardResultType lastFaileType;
    private long lastTime = 0;
    private long firstTime = 0;
    private boolean isFirstToast = true;
    private boolean canShowAnimal = true;
    private boolean isTheSameError = true;

    private void showFaileToast(IDCardQualityResult.IDCardResultType faileType) {
        Log.i("tag", "showFaileToast : " + faileType);
        try {
            //检测5s后开始弹toast
            if (System.currentTimeMillis() - mBeginTime <= 10000) {
                return;
            }

            if (System.currentTimeMillis() - toastTime < 500) {
                return;
            }

            //计算同一个错误出现的时间
            if (faileType == lastFaileType) {
                isTheSameError = true;
                if (isFirstToast) {
                    firstTime = System.currentTimeMillis();
                    lastTime = firstTime;
                    isFirstToast = false;
                }
                lastTime += (System.currentTimeMillis() - lastTime);
            } else {
                isTheSameError = false;
                firstTime = System.currentTimeMillis();
                lastTime = firstTime;

                //两个toast，间隔500毫秒
                if (isMyToastShown()) {
                    lastFaileType = faileType;
                    cancelMyToast();
                    toastTime = System.currentTimeMillis();
                    return;
                }
            }

            long continueTime = lastTime - firstTime;
            //超过2秒后，弹出动画
            if (continueTime > 2000) {
                if (canShowAnimal) {//闪烁动画只出现一次
                    canShowAnimal = false;
                    startToastTipAnimal();
                    if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NEEDFRONT) {//需要人像面
                        startPeopleIconAlphaAnimation();
                    } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NEEDBACK) {
                        startChinaIconAlphaAnimation();
                    }
                    autoFocus();
                }
            } else {
                canShowAnimal = true;
                cancelToastTipAnimal();
                cancelPeopleIconAlphaAnimation();
                cancelChinaIconAlphaAnimation();
            }


            lastFaileType = faileType;
            String toastStr = "";
            if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NOTIDCARD) {//没有检测到身份证
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_1);
//                if (mCardType == 3) {
//                    Toast.makeText(this, "检测到银行卡", Toast.LENGTH_LONG).show();
////                    handleSuccessResult();
////                    return;
//                }
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NOTINBOUND) {//身份证不在引导框内
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_2);
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NOTCLEAR) {//身份证清晰度太低
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_3);
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_HAVEHIGHLIGHT) {//存在光斑
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_4);
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_HAVESHADOW) {//存在阴影
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_5);
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NEEDFRONT) {//需要检测身份人像面
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_6);
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NEEDBACK) {//需要检测身份证国徽面
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_7);
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_NEEDBACK) {//需要检测身份证国徽面
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_7);
            } else if (faileType == IDCardQualityResult.IDCardResultType.IDCARD_QUALITY_FAILED_CONVERT) {
                toastStr = getResources().getString(R.string.remind_idcard_quality_failed_8);
            }

            if (!"".equals(toastStr)) {
                final String finalToastStr = toastStr;
                if (isTheSameError) {
                    showMyToast(finalToastStr, false);
                } else {
                    showMyToast(finalToastStr, true);
                }
                toastTime = System.currentTimeMillis();
            }


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void showIcon() {
        mIbAnimalOneView.setVisibility(View.VISIBLE);
    }

    private void dismissIcon() {
        mIbAnimalOneView.setVisibility(View.GONE);
    }

    public void autoFocus() {
        try {
            if (null != mICamera) {
                mICamera.autoFocus();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    /**
     * 展示toast前面的tip动画
     */
    ObjectAnimator tipAnimation;

    public void startToastTipAnimal() {
        mToastTip.setVisibility(View.VISIBLE);
        tipAnimation = ObjectAnimator.ofFloat(mToastTip, "alpha", 0.3f, 0.8f, 0.3f);
        tipAnimation.setDuration(500);
        tipAnimation.setRepeatCount(300);
        tipAnimation.setInterpolator(new LinearInterpolator());
        tipAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        tipAnimation.start();
    }

    /**
     * 展示自定义toast
     *
     * @param msg
     */
    private void showMyToast(final String msg, final boolean useCustomTime) {
        if (!isMyToastShown()) {
            mLayoutToast.setVisibility(View.VISIBLE);
            mToastTitle.setText(msg);
            if (useCustomTime) {
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cancelMyToast();
                            }
                        });
                    }
                }, 1000);
            }
        }
    }


    /**
     * 展示自定义toast
     */
    private void cancelMyToast() {
        mLayoutToast.setVisibility(View.GONE);
        cancelToastTipAnimal();
    }

    /**
     * 自定义toast是否展示
     */
    private boolean isMyToastShown() {
        if (View.VISIBLE == mLayoutToast.getVisibility()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 隐藏toast前面的tip动画
     */
    public void cancelToastTipAnimal() {
        if (tipAnimation != null) {
            tipAnimation.cancel();
            tipAnimation.end();
            tipAnimation.removeAllListeners();
            tipAnimation.removeAllUpdateListeners();
            tipAnimation = null;
        }
        mToastTip.setVisibility(View.GONE);
    }

    /**
     * 取消人像面icon呼吸渐变动画
     */
    private void cancelPeopleIconAlphaAnimation() {
        if (null != mPeopleIconAlphaAnimation) {
            mPeopleIconAlphaAnimation.cancel();
            mPeopleIconAlphaAnimation.end();
            mPeopleIconAlphaAnimation = null;
        }
        mIvPeopleIconlightView.setVisibility(View.GONE);
    }

    /**
     * 取消人像面icon呼吸渐变动画
     */
    private void cancelChinaIconAlphaAnimation() {
        if (null != mChinaIconAlphaAnimation) {
            mChinaIconAlphaAnimation.cancel();
            mChinaIconAlphaAnimation.end();
            mChinaIconAlphaAnimation = null;
        }
        mIvChinaIconLightView.setVisibility(View.GONE);
    }

    /**
     * 国徽面icon呼吸渐变动画
     */
    private void startChinaIconAlphaAnimation() {
        mIvChinaIconLightView.setVisibility(View.VISIBLE);
        mChinaIconAlphaAnimation = ObjectAnimator.ofFloat(mIvChinaIconLightView, "alpha", 0.2f, 0.8f, 0.2f);
        mChinaIconAlphaAnimation.setDuration(500);
        mChinaIconAlphaAnimation.setRepeatCount(100);
        mChinaIconAlphaAnimation.setInterpolator(new LinearInterpolator());
        mChinaIconAlphaAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        mChinaIconAlphaAnimation.start();
    }

    /**
     * 人像面icon呼吸渐变动画
     */
    private void startPeopleIconAlphaAnimation() {
        mIvPeopleIconlightView.setVisibility(View.VISIBLE);
        mPeopleIconAlphaAnimation = ObjectAnimator.ofFloat(mIvPeopleIconlightView, "alpha", 0.2f, 0.8f, 0.2f);
        mPeopleIconAlphaAnimation.setDuration(500);
        mPeopleIconAlphaAnimation.setRepeatCount(100);
        mPeopleIconAlphaAnimation.setInterpolator(new LinearInterpolator());
        mPeopleIconAlphaAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        mPeopleIconAlphaAnimation.start();
    }


    private void removeAllAnimation() {
        if (mChinaIconAlphaAnimation != null) {
            mChinaIconAlphaAnimation.cancel();
            mChinaIconAlphaAnimation.end();
            mChinaIconAlphaAnimation.removeAllListeners();
            mChinaIconAlphaAnimation.removeAllUpdateListeners();
            mChinaIconAlphaAnimation = null;
        }
        if (mPeopleIconAlphaAnimation != null) {
            mPeopleIconAlphaAnimation.cancel();
            mPeopleIconAlphaAnimation.end();
            mPeopleIconAlphaAnimation.removeAllListeners();
            mPeopleIconAlphaAnimation.removeAllUpdateListeners();
            mPeopleIconAlphaAnimation = null;
        }
        if (mIDcardAlphaAnimation != null) {
            mIDcardAlphaAnimation.cancel();
            mIDcardAlphaAnimation.end();
            mIDcardAlphaAnimation.removeAllListeners();
            mIDcardAlphaAnimation.removeAllUpdateListeners();
            mIDcardAlphaAnimation = null;
        }

        cancelChinaIconAlphaAnimation();
        cancelPeopleIconAlphaAnimation();

    }

    /**
     * 画蓝色的线
     */
    private void setBlueLine() {
        if (mIsVertical) {
            mIdcardGuide.setDrawLine(false);
        } else {
            mIdcardGuideH.setDrawLine(false);
        }
    }

    /**
     * 常驻呼吸框渐变动画
     *
     * @param view
     */
    private void startBreatheAlphaAnimation(final View view) {
        view.setVisibility(View.VISIBLE);
        if (view == mTvTipText) {
            mIDcardAlphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.3f, 1f);
        } else {
            mIDcardAlphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        }
        mIDcardAlphaAnimation.setDuration(1500);
        mIDcardAlphaAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        mIDcardAlphaAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        mIDcardAlphaAnimation.start();
    }


    // 用取余运算
    public boolean isEven01(int num) {
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_megvii_idcard_cn_goback || v.getId() == R.id.rl_megvii_idcard_cn_goback_h) {
            doFinish();
        } else if (v.getId() == R.id.idcardscan_cn_layout_guide || v.getId() == R.id.idcardscan_cn_layout_guide_h
                || v.getId() == R.id.idcardscan_cn_layout_surface) {
            if (mICamera != null) {
                mICamera.autoFocus();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doFinish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


    private void doFinish() {
        try {
            if (mDecoder != null) {
                mDecoder.setmHasSuccess(true);
                mDecoder.interrupt();
                mDecoder.join();
                mDecoder = null;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (mFrameDataQueue != null) {
            mFrameDataQueue.clear();
        }
        mIdCardQualityAssessment.release();
        removeAllAnimation();

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
