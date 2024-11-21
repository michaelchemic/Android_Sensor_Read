package com.example.android_sensor_read;

public class SensorData {
    private String sensorName;
    private String wakeupStatus;
    private String sensorValues;

    public SensorData(String sensorName, String wakeupStatus, String sensorValues) {
        this.sensorName = sensorName;
        this.wakeupStatus = wakeupStatus;
        this.sensorValues = sensorValues;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getWakeupStatus() {
        return wakeupStatus;
    }

    public String getSensorValues() {
        return sensorValues;
    }

    public void setSensorValues(String sensorValues) {
        this.sensorValues = sensorValues;
    }
}
