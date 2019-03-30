package ru.sberbank.homework26_27.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.sberbank.homework26_27.Alarm;

@Database(entities = Alarm.class, version = 2)
public abstract class AlarmDB extends RoomDatabase {
    public abstract AlarmDAO getAlarmDAO();
}
