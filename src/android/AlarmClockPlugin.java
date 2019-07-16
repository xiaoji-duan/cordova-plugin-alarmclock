package com.xj.cordova.alarmclock;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.provider.AlarmClock;
import android.net.Uri;

/**
 * This class echoes a string called from JavaScript.
 */
public class AlarmClockPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        if (action.equals("createAlarmClock")) {
            String message = args.getString(0);
            this.createAlarmClock(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void createAlarmClock(String message, CallbackContext callbackContext) {
      ArrayList<Integer> testDays = new ArrayList<>();
      testDays.add(Calendar.MONDAY);//周一
      testDays.add(Calendar.TUESDAY);//周二
      testDays.add(Calendar.FRIDAY);//周五

      String packageName = this.cordova.getActivity().getApplication().getPackageName();
      //Uri ringtoneUri = Uri.parse("android.resource://" + packageName + "/" + resId);

      int hour = 13, minutes = 0;

      //action为AlarmClock.ACTION_SET_ALARM
      Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                      //闹钟的小时
                      .putExtra(AlarmClock.EXTRA_HOUR, hour)
                      //闹钟的分钟
                      .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                      //响铃时提示的信息
                      .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                      //用于指定该闹铃触发时是否振动
                      .putExtra(AlarmClock.EXTRA_VIBRATE, true)
                      //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
                      //如需使用默认铃声，则无需指定此 extra。
                      //.putExtra(AlarmClock.EXTRA_RINGTONE, ringtoneUri)
                      //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
                      // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
                      //对于一次性闹铃，无需指定此 extra
                      //.putExtra(AlarmClock.EXTRA_DAYS, testDays)
                      //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
                      .putExtra(AlarmClock.EXTRA_SKIP_UI, true);

      if (intent.resolveActivity(this.cordova.getActivity().getPackageManager()) != null) {
          this.cordova.getActivity().startActivity(intent);
      }
    }
}
