package ru.sberbank.homework26_27.recycler;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.sberbank.homework26_27.Alarm;
import ru.sberbank.homework26_27.AlarmApplication;
import ru.sberbank.homework26_27.R;

public class AlarmViewHolder extends RecyclerView.ViewHolder {

    private Alarm mAlarm;
    private TextView mTime;
    private Switch mSwitch;

    public AlarmViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
        initListeners();
    }

    private void initListeners() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAlarm.setEnabled(isChecked);
                updateAlarm(mAlarm);
            }
        });
    }

    private void initViews(View itemView) {
        mTime = itemView.findViewById(R.id.timeTextView);
        mSwitch = itemView.findViewById(R.id.switch1);
    }

    public void bind(Alarm alarm) {
        mAlarm = alarm;
        String hour = String.valueOf(mAlarm.getHour());
        String minute = "";
        if (mAlarm.getMinute() <= 9) minute = "0" + mAlarm.getMinute();
        else minute = String.valueOf(mAlarm.getMinute());

        mTime.setText(hour + ":" + minute);
        mSwitch.setChecked(mAlarm.isEnabled());
    }

    private void updateAlarm(Alarm alarm) {
        AlarmApplication.getDBInstanse().getAlarmDAO().update(alarm);
    }
}
