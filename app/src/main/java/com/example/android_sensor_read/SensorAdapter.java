package com.example.android_sensor_read;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {

    private final List<SensorData> sensorDataList;

    public SensorAdapter(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_item, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        SensorData sensorData = sensorDataList.get(position);
        holder.sensorName.setText(sensorData.getSensorName());
        holder.wakeupStatus.setText(sensorData.getWakeupStatus());
        holder.sensorValues.setText(sensorData.getSensorValues());
    }

    @Override
    public int getItemCount() {
        return sensorDataList.size();
    }

    static class SensorViewHolder extends RecyclerView.ViewHolder {
        TextView sensorName, wakeupStatus, sensorValues;

        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);
            sensorName = itemView.findViewById(R.id.sensor_name);
            wakeupStatus = itemView.findViewById(R.id.wakeup_status);
            sensorValues = itemView.findViewById(R.id.sensor_values);
        }
    }
}
