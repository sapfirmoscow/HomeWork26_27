package ru.sberbank.homework26_27;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddAlarmActivity extends AppCompatActivity {

    public static final Intent newIntent(Context context) {
        return new Intent(context, AddAlarmActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
    }
}
