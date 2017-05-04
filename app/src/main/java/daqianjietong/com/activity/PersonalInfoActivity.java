package daqianjietong.com.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.daqianjietong.R;
import daqianjietong.com.utils.HaveSdCard;
import daqianjietong.com.utils.Image;

/**
 * Created by Administrator on 2017/4/28 0028.
 * 个人信息页
 */

@ContentView(R.layout.activity_personal_info)
public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_user_photo)
    private ImageView user_photo;
    @ViewInject(R.id.tv_user_name)
    private TextView tv_user_name;
    @ViewInject(R.id.tv_user_phone)
    private TextView tv_user_phone;
    @ViewInject(R.id.tv_user_car)
    private TextView tv_user_car;
    @ViewInject(R.id.tv_login_out)
    private TextView tv_login_out;

    @ViewInject(R.id.ll_user_photo)
    private LinearLayout ll_user_photo;
    @ViewInject(R.id.ll_user_name)
    private LinearLayout ll_user_name;
    @ViewInject(R.id.ll_user_phone)
    private LinearLayout ll_user_phone;
    @ViewInject(R.id.ll_user_car)
    private LinearLayout ll_user_car;


    private LayoutInflater mInflater;
    private Dialog mDialog;
    private View openCamera;
    private View openPhones;
    private View cancel;
    private static final String IMAGE_FILE_NAME_TEMP = "temp_user_photo";
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;
    private File mTempFile;
    private Activity act;
    private View photo_choose_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        this.mInflater = getLayoutInflater();
        initData();
    }

    private void initData() {
        tv_title.setText("个人信息");
        iv_back.setOnClickListener(this);
        ll_user_photo.setOnClickListener(this);
        ll_user_name.setOnClickListener(this);
        ll_user_car.setOnClickListener(this);
        tv_login_out.setOnClickListener(this);
        //设置头像一些信息
        File photo = new File(act.getFilesDir() + "/daqianjietong/user_photo.jpg");
        Bitmap bitmap;
        if (photo.exists()) {
            bitmap = BitmapFactory.decodeFile(act.getFilesDir()
                    + "/daqianjietong/user_photo.jpg");
        } else {
            bitmap = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher);
        }
        user_photo.setImageBitmap(bitmap);
    }
    //打开摄像头监听
    View.OnClickListener openCameraListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            openCamera();

        }
    };
    //打开本地相册监听
    View.OnClickListener openPhonesListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            openPhones();

        }
    };
    //取消按钮监听
    View.OnClickListener cancelListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mDialog.cancel();
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                PersonalInfoActivity.this.finish();
                break;
            case R.id.ll_user_photo:
                //设置头像
                showDialog();

                break;
            case R.id.ll_user_name:
                //设置昵称
                break;
            case R.id.ll_user_car:
                //设置车牌
                break;
            case R.id.tv_login_out:
                //退出登录
                break;
        }
    }


    private void showDialog() {
        photo_choose_dialog = mInflater.inflate(
                R.layout.photo_choose_dialog, null);

        openCamera = photo_choose_dialog.findViewById(R.id.openCamera);
        openPhones = photo_choose_dialog.findViewById(R.id.openPhones);
        cancel = photo_choose_dialog.findViewById(R.id.cancel);
        mDialog = new Dialog(act, R.style.transparentFrameWindowStyle);
        mDialog.setContentView(photo_choose_dialog, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = mDialog.getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;

        wl.y = act.getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        mDialog.onWindowAttributesChanged(wl);
        mDialog.setCanceledOnTouchOutside(true);

        openCamera.setOnClickListener(openCameraListener);
        openPhones.setOnClickListener(openPhonesListener);
        cancel.setOnClickListener(cancelListener);
        mDialog.show();
    }

    //调用照相机
    private void openCamera() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (HaveSdCard.hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory()
                            + "/"
                            + IMAGE_FILE_NAME_TEMP)));
        }
        startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
    }
    //打开本地相册
    private void openPhones() {
        Intent intentFromGallery = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        mTempFile = new File(
                                Environment.getExternalStorageDirectory() + "/"
                                        + IMAGE_FILE_NAME_TEMP);
                        startPhotoZoom(Uri.fromFile(mTempFile));
                    } else {
                        Toast.makeText(act.getApplicationContext(),
                                "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                    }
                    break;
                case RESULT_REQUEST_CODE:
                    mTempFile = new File(Environment.getExternalStorageDirectory()
                            + "/" + IMAGE_FILE_NAME_TEMP);
                    if (mTempFile.exists()) {
                        new Thread() {
                            public void run() {
                                mTempFile.delete();
                            };
                        }.start();
                    }
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap photo = extras.getParcelable("data");
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            Image image = new Image();
                            try {
                                File f = new File(act.getFilesDir()
                                        + "/daqianjietong/user_photo.jpg");
                                if (f.exists()) {
                                    f.delete();
                                }
                                f.createNewFile();
                                FileOutputStream fOut = new FileOutputStream(f);
                                photo.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                fOut.flush();
                                fOut.close();
                                //上传头像
//                                RequestParams params = new RequestParams();
//                                params.addBodyParameter("pic", f);
//                                params.addBodyParameter("name", "pic");
//                                HttpUtils http = new HttpUtils();
//                                System.out
//                                        .println("http://www.viniu.com.cn/upload.php?m=app&token="
//                                                + Data.getToken());
//                                http.send(HttpRequest.HttpMethod.POST,
//                                        "http://www.viniu.com.cn/upload.php?m=app&token="
//                                                + Data.getToken(), params,
//                                        uploadCallBack);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            photo = image.toRoundBitmap(photo);
                            user_photo.setImageBitmap(photo);
                        }
                    }
                    mDialog.cancel();
                    break;
            }
        }
    }

}
