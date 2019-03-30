package ru.sberbank.homework26_27;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ru.sberbank.homework26_27.recycler.AlarmAdapter;

public class MainActivity extends AppCompatActivity {

    private AlarmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager workManager = WorkManager.getInstance();


    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        startActivity(AddAlarmActivity.newIntent(MainActivity.this));

        ArrayList arrayList = new ArrayList();
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        arrayList.add(new Alarm());
        mAdapter = new AlarmAdapter(arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
