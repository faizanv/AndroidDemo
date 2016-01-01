package com.ktdid.hellosummit;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ImageDownloadActivity extends AppCompatActivity implements View.OnClickListener, TaskCallbacks<Bitmap> {

    private Button mBtnDownload;

    private EditText mEtImageUri;

    private ImageView mIvImage;

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_download);

        mProgress = (ProgressBar) findViewById(R.id.pb_progress);
        mEtImageUri = (EditText) findViewById(R.id.et_image_url);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mBtnDownload = (Button) findViewById(R.id.btn_start_download);
        mBtnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_download:
                startDownload();
                break;
        }
    }

    private void startDownload() {
        String uri = TextUtils.isEmpty(mEtImageUri.getText()) ? mEtImageUri.getHint().toString() : mEtImageUri.getText().toString();

        if (!TextUtils.isEmpty(uri)) {
            showProgress();
            mBtnDownload.setEnabled(false);
            ImageDownloadTask task = new ImageDownloadTask(this, this, uri);
            task.execute();
        } else {
            Toast.makeText(this, "Please enter a valid url", Toast.LENGTH_SHORT).show();

        }
    }

    private void showProgress() {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgress() {
        if (mProgress != null) {
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTaskComplete(Bitmap bitmap) {
        mBtnDownload.setEnabled(true);
        hideProgress();
        mIvImage.setImageBitmap(bitmap);
    }
}
