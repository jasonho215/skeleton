package com.oursky.skeleton.helper;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Keep;

import java.util.Date;

import io.reactivex.annotations.NonNull;

public class Debounce {
    // TAG
    private static final String TAG = "DEBOUNCE";
    // Private members
    private Date mLastScheduledAt;
    private Looper mLooper;
    private int mDelay;

    @Keep
    private Debounce() {}
    @Keep
    public Debounce(final @NonNull Looper looper, int delay) {
        mLooper = looper;
        mDelay = delay;
    }

    public void post(@NonNull Runnable run) {
        final Date scheduledAt = new Date();
        if (mLastScheduledAt.equals(scheduledAt)) {
            Logger.d(TAG, "Debounce does not work, sequential calls are detected");
        }
        mLastScheduledAt = scheduledAt;
        new Handler(mLooper).postDelayed(() ->
        {
            if (mLastScheduledAt!=null && scheduledAt.equals(mLastScheduledAt)) {
                run.run();
            }
        }, mDelay);
    }
}
