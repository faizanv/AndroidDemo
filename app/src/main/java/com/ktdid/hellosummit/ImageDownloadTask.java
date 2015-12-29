package com.ktdid.hellosummit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Simple async task for downloading an image
 */
public class ImageDownloadTask extends AsyncTask<Void, Void, Bitmap> {

    private static final String TAG = ImageDownloadTask.class.getSimpleName();

    private WeakReference<Context> mWeakRefContext;

    private WeakReference<TaskCallbacks<Bitmap>> mWeakRefTaskCallbacks;

    private String mUri;


    public ImageDownloadTask(Context context, TaskCallbacks<Bitmap> taskCallbacks, String uri) {
        mWeakRefContext = new WeakReference<>(context);
        mWeakRefTaskCallbacks = new WeakReference<>(taskCallbacks);
        mUri = uri;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Context context = getContext();

        if (context != null) {
            try {
                URL url = new URL(mUri);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(2 * 1000); //Give up after 2 min
                conn.setReadTimeout(2 * 1000); //Give up if no data is received after 2 min

                InputStream is = conn.getInputStream();
                return BitmapFactory.decodeStream(is);

            } catch (MalformedURLException e) {
                Log.e(TAG, "Malformed Url", e);
            } catch (IOException e) {
                Log.e(TAG, "IO connection", e);
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            TaskCallbacks<Bitmap> callbacks = getCallbacks();
            if (callbacks != null) {
                callbacks.onTaskComplete(bitmap);
            }
        }
    }

    private TaskCallbacks<Bitmap> getCallbacks() {
        if (mWeakRefTaskCallbacks != null) {
            return mWeakRefTaskCallbacks.get();
        }
        return null;
    }

    private Context getContext() {
        if (mWeakRefContext != null) {
            return mWeakRefContext.get();
        }
        return null;
    }
}
