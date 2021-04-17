package com.app.freedom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Freedom {
    private final static String TAG = Freedom.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private volatile static Freedom instance = null;
    private final int DEFAULT_DAYS = 7;
    private final String DEFAULT_MESSAGE = "This APK has expired, please contact the developer to get a new APK.";
    private final Activity activity;
    private boolean isDebugOnlyMode = true;
    private boolean haveToShowMessage = true;
    private boolean haveToClearAllNotification = true;
    private int days = DEFAULT_DAYS;
    private String message = DEFAULT_MESSAGE;

    private Freedom(Activity activity) {
        this.activity = activity;
    }

    public static Freedom initialize(Activity activity) {
        if (instance == null) {
            synchronized (Freedom.class) {
                if (instance == null) {
                    instance = new Freedom(activity);
                }
            }
        }
        return instance;
    }


    private String getDate(long currentTimeMillis) {
        return new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS", Locale.getDefault())
                .format(currentTimeMillis);
    }

    private boolean isExpired() {
        long appBuildTimeStamp = BuildConfig.FreedomTime;
        long currentTimeStamp = System.currentTimeMillis();
        long apkExpireTimeStamp = appBuildTimeStamp + getMillisFromDays();

        // build date time
        String appBuildDate = getDate(appBuildTimeStamp);
        Log.i(TAG, "appBuildDate = " + appBuildTimeStamp + " : " + appBuildDate);

        // current date time
        String currentDate = getDate(currentTimeStamp);
        Log.i(TAG, "currentDate =  " + currentTimeStamp + " : " + currentDate);


        // APK expire date time
        String apkExpireDate = getDate(apkExpireTimeStamp);
        Log.i(TAG, "apkExpireTimeStamp =  " + apkExpireTimeStamp + " : " + apkExpireDate);

        if (currentTimeStamp >= apkExpireTimeStamp) {
            Log.i(TAG, "isExpired =  " + true);
            return true;
        }

        return false;

    }

    public void start() {
        Log.i(TAG, "isDebugOnlyMode =  " + isDebugOnlyMode);
        if (isDebugOnlyMode) {
            if (!BuildConfig.DEBUG) {
                Log.w(TAG, "This APK is not DEBUG and configuration have DEBUG only mode enable.");
                return;
            }
        }
        if (isExpired()) {

            Log.i(TAG, "haveToShowMessage =  " + haveToShowMessage);
            if (haveToShowMessage) {
                showMessage();
            }

            Log.i(TAG, "haveToClearAllNotification =  " + haveToClearAllNotification);
            if (haveToClearAllNotification) {
                clearAllNotifications();
            }

            exitApp();
        }
    }


    /**
     * 1 seconds = 1000 mills ->  </b>
     * 1 minutes = 1000 * 60 = 60000
     * 1 hour = 60000* 60 = 3600000
     * 1 day = 3600000 * 24 = 86400000L
     */
    private long getMillisFromDays() {
        long MILLS_IN_1_DAY = 86400000L;
        return days * MILLS_IN_1_DAY;
    }


    private void showMessage() {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    private void exitApp() {
        instance = null;
        activity.finishAndRemoveTask();
    }

    public Freedom setExpireAfterDays(int days) {
        if (days <= 0) {
            throw new RuntimeException("Days of expire APK should be grater than 0.");
        }
        this.days = days;
        return this;
    }

    public Freedom setMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            throw new RuntimeException("Message should not be empty or null.");
        }
        this.message = message;
        return this;
    }

    public Freedom setWorkForAllVariant(boolean forAllVariant) {
        isDebugOnlyMode = !forAllVariant;
        return this;
    }

    public Freedom setHaveToShowMessage(boolean haveToShowMessage) {
        this.haveToShowMessage = haveToShowMessage;
        return this;
    }

    public Freedom setHaveToClearNotifications(boolean haveToClearAllNotification) {
        this.haveToClearAllNotification = haveToClearAllNotification;
        return this;
    }

    private void clearAllNotifications() {
        NotificationManager notificationManager =
                (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null)
            notificationManager.cancelAll();
    }
}
