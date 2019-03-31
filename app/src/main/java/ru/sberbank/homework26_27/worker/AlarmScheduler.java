package ru.sberbank.homework26_27.worker;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ru.sberbank.homework26_27.room.Alarm;

public class AlarmScheduler {

    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final long SECONDS_IN_DAY = SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY;

    private static long getLocalCurrentTime() {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        return ((Calendar.getInstance().getTime().getTime() + timeZone.getRawOffset()) / 1000) % SECONDS_IN_DAY;
    }

    private static long getAlarmTime(Alarm alarm) {
        return alarm.getHour() * 3600 + alarm.getMinute() * 60;
    }

    private static long getDelay(long currentTimeSeconds, long alarmTimeSeconds) {
        return (SECONDS_IN_DAY + alarmTimeSeconds - currentTimeSeconds) % SECONDS_IN_DAY;
    }

    public static void scheduleAlarm(Alarm alarm) {
        long delay = getDelay(getLocalCurrentTime(), getAlarmTime(alarm));
        String tag = String.valueOf(alarm.getId());
        WorkManager.getInstance().cancelAllWorkByTag(tag);
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(delay, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance().enqueue(request);

    }
}
