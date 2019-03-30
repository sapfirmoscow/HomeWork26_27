package ru.sberbank.homework26_27;

import android.app.Application;

import androidx.room.Room;
import ru.sberbank.homework26_27.room.AlarmDB;

public class AlarmApplication extends Application {

    private static AlarmDB mAlarmDB;

    public static AlarmDB getDBInstanse() {
        return mAlarmDB;
    }

    @Override
    public void onCreate() {
        mAlarmDB = Room.databaseBuilder(getApplicationContext(), AlarmDB.class, "alarms").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        super.onCreate();
    }
}
