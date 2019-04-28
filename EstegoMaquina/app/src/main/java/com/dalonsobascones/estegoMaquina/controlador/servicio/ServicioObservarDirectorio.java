package com.dalonsobascones.estegoMaquina.controlador.servicio;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.dalonsobascones.estegoMaquina.R;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class ServicioObservarDirectorio extends Service {

    ObservarDirectorio observer;
    final String pathToWatch = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/";


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        //Notificacion de servicio iniciado
        notificarServicioIniciado();

        //Iniciar servicio FileObserver
        observer = new ObservarDirectorio();
        observer.startWatching();

        return START_NOT_STICKY;
    }


    private void notificarServicioIniciado() {


        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        int icono = R.mipmap.ic_launcher_round;


        mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(icono)
                .setContentTitle("EstegoMaquina")
                .setContentText("Modo Automático: " + pathToWatch)
                .setVibrate(new long[]{100, 250, 100, 500})
                .setAutoCancel(true);


        mNotifyMgr.notify(1, mBuilder.build());


    }


}
