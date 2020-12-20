package com.faizurazadri.submission2githubuser_faizurazadri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.faizurazadri.submission2githubuser_faizurazadri.receiver.MyReceiver;

import java.util.Calendar;
import java.util.Set;

public class SettingActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int notificationDaily =sharedPreferences.getInt("user_notification", 0);
        aSwitch = findViewById(R.id.switch_notification);
        if (notificationDaily == 1){
            aSwitch.setChecked(true);
        }else {
            aSwitch.setChecked(false);
        }

        klikSwitch();
    }

    private void klikSwitch() {
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                setReminder(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("user_notification", 1);
                editor.commit();
                Toast.makeText(SettingActivity.this, "Notification Active", Toast.LENGTH_LONG).show();
            }else{
                setReminderOff(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("user_notification",0);
                editor.commit();
                Toast.makeText(SettingActivity.this," Notification Not Active", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void setReminder(Context applicationContext) {
        Intent intent = new Intent(applicationContext, MyReceiver.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);

        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(applicationContext, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager !=  null){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    private void setReminderOff(Context context){
        Intent intent = new Intent(SettingActivity.this, MyReceiver.class);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(context, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}