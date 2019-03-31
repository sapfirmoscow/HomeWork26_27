package ru.sberbank.homework26_27.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    private int mId;
    private int mHour;
    private int mMinute;
    private boolean mEnabled = true;

    public Alarm() {
    }

    public Alarm(int hour, int minute) {
        mHour = hour;
        mMinute = minute;
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        mHour = hour;
    }

    public int getMinute() {
        return mMinute;
    }

    public void setMinute(int minute) {
        mMinute = minute;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        mEnabled = isEnabled;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
