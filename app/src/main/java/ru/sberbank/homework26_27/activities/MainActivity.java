package ru.sberbank.homework26_27.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ru.sberbank.homework26_27.AlarmApplication;
import ru.sberbank.homework26_27.R;
import ru.sberbank.homework26_27.recycler.AlarmAdapter;
import ru.sberbank.homework26_27.room.Alarm;
import ru.sberbank.homework26_27.worker.AlarmScheduler;
import ru.sberbank.homework26_27.worker.MyWorker;

public class MainActivity extends AppCompatActivity {

    private AlarmAdapter mAdapter;
    private FloatingActionButton mFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initViews();
        initListeners();
        long time = System.currentTimeMillis();
        Log.d("Dede", " " + time);
    }

    private void setWorker(Alarm alarm) {
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build();
        WorkManager workManager = WorkManager.getInstance();
        workManager.enqueue(request);
    }

    private void initListeners() {
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(AddAlarmActivity.newIntent(MainActivity.this), 1);
            }
        });
    }

    private void initViews() {
        mFloatingButton = findViewById(R.id.floatingActionButton_add);
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new AlarmAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
        updateAlarmList();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFloatingButton.show();
    }

    private List<Alarm> updateAlarmList() {
        @SuppressLint("StaticFieldLeak") AsyncTask task = new AsyncTask<Void, Void, List<Alarm>>() {

            @Override
            protected List<Alarm> doInBackground(Void... voids) {
                List<Alarm> alarms = AlarmApplication
                        .getDBInstanse()
                        .getAlarmDAO()
                        .getAlarms();
                return alarms;
            }

            @Override
            protected void onPostExecute(List<Alarm> alarms) {
                mAdapter.setAlarms(alarms);
            }
        }.execute();

        try {
            return (List<Alarm>) task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        updateAlarmList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        List<Alarm> activeAlarms = AlarmApplication
                .getDBInstanse()
                .getAlarmDAO()
                .getEnabledAlarms();
        for (int i = 0; i < activeAlarms.size(); i++) {
            AlarmScheduler.scheduleAlarm(activeAlarms.get(i));
        }
    }
}
