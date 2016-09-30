package com.example.nguyendhoang.simpletodo.customize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyendhoang.simpletodo.R;

import java.util.ArrayList;

/**
 * Created by Nguyen.D.Hoang on 9/29/2016.
 */
public class ScheduleAdapter extends ArrayAdapter<Schdule> {
    public ScheduleAdapter(Context context, ArrayList<Schdule> schedules) {
        super(context,0, schedules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Schdule schedule = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.schedule_item, parent, false);
        }

        TextView tvTaskName = (TextView) convertView.findViewById(R.id.schedule_item_task_name);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.schedule_item_priority);

        tvTaskName.setText(schedule.getTaskName());
        tvPriority.setText(schedule.getPriorityLevel());

        return convertView;
    }
}