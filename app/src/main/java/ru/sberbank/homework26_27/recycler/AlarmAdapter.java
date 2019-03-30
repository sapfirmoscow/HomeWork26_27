package ru.sberbank.homework26_27.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.sberbank.homework26_27.Alarm;
import ru.sberbank.homework26_27.R;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    private List<Alarm> mData;

    public AlarmAdapter() {
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    public void setAlarms(List<Alarm> alarms) {
        mData.clear();
        mData.addAll(alarms);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
