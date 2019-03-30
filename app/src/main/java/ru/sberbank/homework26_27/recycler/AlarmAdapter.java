package ru.sberbank.homework26_27.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.sberbank.homework26_27.Alarm;
import ru.sberbank.homework26_27.R;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    private List<Alarm> mData;

    public AlarmAdapter(List<Alarm> alarms) {
        mData = alarms;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        //
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
