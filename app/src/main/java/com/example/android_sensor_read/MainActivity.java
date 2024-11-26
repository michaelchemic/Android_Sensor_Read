package com.example.android_sensor_read;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private List<SensorData> sensorDataList;
    private SensorAdapter sensorAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.sensor_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sensorDataList = new ArrayList<>();
        sensorAdapter = new SensorAdapter(sensorDataList);
        recyclerView.setAdapter(sensorAdapter);

        // 获取传感器管理器
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // 获取所有传感器列表
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            // 初始化传感器数据
            sensorDataList.add(new SensorData(sensor.getName(), "Non-wakeup", "Waiting for data..."));

            // 注册监听器
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
        }

        // 通知适配器数据更新
        sensorAdapter.notifyDataSetChanged();
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // 更新传感器数据
            for (SensorData data : sensorDataList) {
                if (data.getSensorName().equals(event.sensor.getName())) {
                    StringBuilder values = new StringBuilder();
                    for (int i = 0; i < event.values.length; i++) {
                        values.append("Value[").append(i).append("]: ").append(event.values[i]).append(" ");
                    }
                    data.setSensorValues(values.toString());
                    break;
                }
            }
            // 刷新数据
            sensorAdapter.notifyDataSetChanged();

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销监听器
        sensorManager.unregisterListener(sensorEventListener);
    }
}
