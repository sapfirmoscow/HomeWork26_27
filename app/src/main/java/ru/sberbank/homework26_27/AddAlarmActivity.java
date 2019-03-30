package ru.sberbank.homework26_27;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddAlarmActivity extends AppCompatActivity {

    private FloatingActionButton mFloatingButton;
    private TimePicker mPicker;

    public static final Intent newIntent(Context context) {
        return new Intent(context, AddAlarmActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        initViews();
        initListeners();
        init();
    }

    private void initListeners() {
        mFloatingButton.setOnClickListener(v -> addAlarm());
    }

    private void init() {
        mPicker.setIs24HourView(true);
    }

    private void initViews() {
        mFloatingButton = findViewById(R.id.floatingActionButton_done);
        mPicker = findViewById(R.id.picker);
    }

    @SuppressLint("StaticFieldLeak")
    private void addAlarm() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Alarm alarm = getAlarmTime();
                AlarmApplication.getDBInstanse().getAlarmDAO().insert(alarm);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                setResult(1);
                finish();
            }
        }.execute();
    }

    private Alarm getAlarmTime() {
        Alarm alarm = new Alarm();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hour = mPicker.getHour();
            int minute = mPicker.getMinute();
            alarm.setHour(hour);
            alarm.setMinute(minute);
        } else {
            int hour = mPicker.getCurrentHour();
            int minute = mPicker.getCurrentMinute();
            alarm.setHour(hour);
            alarm.setMinute(minute);
        }
        return alarm;
    }
}
