package ru.sberbank.homework26_27.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ru.sberbank.homework26_27.R;

public class MyWorker extends Worker {


    private static final String CHANEL_ID = "1";
    private static final CharSequence CHANEL_NAME = "alarm";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        NotificationManager notificationManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("description");
            channel.enableLights(true);
            long[] pattern = {0, 100, 100, 200, 100};
            channel.setVibrationPattern(pattern);
            channel.setShowBadge(true);

            notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setContentTitle("HomeWork26_27")
                .setContentText("Time to wake up!")
                .setAutoCancel(true);

        if (notificationManager != null) {
            notificationManager.notify(1, mBuilder.build());
            return Result.success();
        } else {
            return Result.retry();
        }

    }
}
