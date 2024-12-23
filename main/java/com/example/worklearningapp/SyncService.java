package com.example.worklearningapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SyncService extends Service {
    // Конструктор сервиса
    public SyncService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Логирование начала работы сервиса
        Log.d("SyncService", "Синхронизация запускается");

        // Здесь добавьте код для синхронизации с удаленным сервером
        // Например, можно использовать AsyncTask или другие механизмы для выполнения сетевых операций

        return START_STICKY; // Возвращает флаг, указывающий системе, чтобы перезапустить сервис, если он был убран
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Метод onBind не используется в данном сервисе, поэтому возвращаем null
        return null;
    }
}
