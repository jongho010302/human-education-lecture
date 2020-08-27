package com.myapp.myworkspace;

// 파일업로드 참조 : https://gogorchg.tistory.com/entry/Android-WebView-File-Upload

// 안드로이드 5.0(롤리팝) 미만 호환성 제거
// 권한 제어 및 비디오 처리 부분 추가

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String URL_LOAD = "http://ec2-15-164-212-224.ap-northeast-2.compute.amazonaws.com";
    private static final int INPUT_FILE_REQUEST_CODE = 1;

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPath;

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		
		// 내용 추가 - 강의중
		// 다른 사람이 추가
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 파일 읽기/쓰기/카메라 권한 요청
        requestPermission();

        myWebView  = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);

        // Enable pinch to zoom without the zoom buttons
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);

        myWebView.setWebChromeClient(new WebChromeClient(){
            // 웹뷰가 종료될 때 처리
            @Override
            public void onCloseWindow(WebView w) {
                super.onCloseWindow(w);
                finish();   // MainActivity를 종료함
            }

            // 웹뷰가 생성될 떄 옵션 지정
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
                final WebSettings settings = view.getSettings();
                settings.setDomStorageEnabled(true);
                settings.setJavaScriptEnabled(true);
                settings.setAllowFileAccess(true);
                settings.setAllowContentAccess(true);
                view.setWebChromeClient(this);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(view);
                resultMsg.sendToTarget();
                return false;
            }

            // 웹뷰에서 파일을 선택할 때 실행되는 메소드
            // For Android Version 5.0+
            // Ref: https://github.com/GoogleChrome/chromium-webview-samples/blob/master/input-file-example/app/src/main/java/inputfilesample/android/chrome/google/com/inputfilesample/MainFragment.java
            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                // ValueCallback<Uri[]> filePathCallback - html 파일 선택자 객체가 uri 형식으로 넘어옴
                // FileChooserParams fileChooserParams - 파일 선택 옵션 ex) accept="video/mp4"
                System.out.println("WebViewActivity A>5, OS Version : " + Build.VERSION.SDK_INT + "\t onSFC(WV,VCUB,FCP), n=3");

                // html의 파일 선택자 객체의 uri를 클래스 멤버 변수에 저장
                // 이후에 카메라/파일 액티비티에서 onActivityResult 넘어올 때 처리하기 위함
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePathCallback;

                // 임시파일을 생성하고 카메라와 파일 액티비티 선택자 액티비티를 만들어줌
                fileChooser(TextUtils.join(",", fileChooserParams.getAcceptTypes()));

                return true;
            }

            private void fileChooser(String acceptType) {

                Intent captureIntent;

                if(acceptType.startsWith("video")){
                    captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                } else {
                    captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                }

                if (captureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go

					/// comments
                    File photoFile = null;
                    try {
						// 파일명
						String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
						String fileName = acceptType.startsWith("video") ? "cam_" :"photo_" + timeStamp + "_";

						// 임시파일 경로
						File storageDir = Environment.getExternalStoragePublicDirectory(
								Environment.DIRECTORY_DCIM);					
						
                        photoFile = File.createTempFile(
								fileName,  /* prefix */
								acceptType.startsWith("video") ? ".mp4" :".jpg",         /* suffix */
								storageDir      /* directory */
						);		
						
                        Log.e(getClass().getName(), "file:");
                        Log.e(getClass().getName(), file.getAbsoluteFile().toString());
                        captureIntent.putExtra("CameraPath", mCameraPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        Log.e(getClass().getName(), "Unable to create Image File", ex);
                    }

                    // Continue only if the File was successfully created
                    if (file != null) {
                        mCameraPath = "file:" + file.getAbsolutePath();
                        //Log.e(getClass().getName(), "file:");
                        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(file));
                    } else {
                        captureIntent = null;
                    }
                }

                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType(acceptType);

                Intent[] intentArray;
                if(captureIntent != null) {
                    intentArray = new Intent[]{captureIntent};
                } else {
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "File Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

                startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
            }
        });
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.loadUrl(URL_LOAD);
    }


    // 뒤로가기 버튼 제어
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            //Log.e(getClass().getName(), "current url: " + myWebView.getOriginalUrl());
            //Log.e(getClass().getName(), "current url: " + myWebView.getUrl());

            String[] urlArray = myWebView.getUrl().split("/", -1);
            //Log.e(getClass().getName(), "path: " + urlArray[urlArray.length - 1]);

            // 홈(/) 경로에서 뒤로가기 눌렀을 시에 액티비티 종료
            if(urlArray[urlArray.length - 1].equals("")){
                finish();
            } else {
                myWebView.goBack();
            }
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


    // 파일 선택 또는 카메라 촬영 후 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(getClass().getName(), "requestCode : " + requestCode);
        Log.d(getClass().getName(), "resultCode : " + resultCode);

        // 코드를 비교후에 결과 데이터 처리
        if (requestCode == INPUT_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }

            // 결과 데이터의 uri 형식을 변환후 html의 파일 선택자객체에 결과데이터를 넣어줌
            Uri[] results = new Uri[]{getResultUri(data)};
            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;

        } else {
            if (mFilePathCallback != null) mFilePathCallback.onReceiveValue(null);
            if (mUploadMessage != null) mUploadMessage.onReceiveValue(null);
            mFilePathCallback = null;
            mUploadMessage = null;
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    private Uri getResultUri(Intent data) {
        Uri result = null;
        if(data == null || TextUtils.isEmpty(data.getDataString())) {
            // If there is not data, then we may have taken a photo
            if(mCameraPath != null) {
                result = Uri.parse(mCameraPath);
            }
        } else {
            result = Uri.parse(data.getDataString());
        }

        Log.d(getClass().getName(), "result uri : " +  result.toString());

        return result;
    }

    // 권한 요청
    private  void requestPermission(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA},
                        1);
        }
    }
}
