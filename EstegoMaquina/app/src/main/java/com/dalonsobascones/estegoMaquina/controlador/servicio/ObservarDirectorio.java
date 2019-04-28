package com.dalonsobascones.estegoMaquina.controlador.servicio;

import android.app.NotificationManager;
import android.os.Environment;
import android.os.FileObserver;
import android.support.v4.app.NotificationCompat;

import com.dalonsobascones.estegoMaquina.R;
import com.dalonsobascones.estegoMaquina.controlador.HiloInsertar;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.dalonsobascones.estegoMaquina.vista.MenuPrincipal.getContext;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class ObservarDirectorio extends FileObserver {


    public ObservarDirectorio() {
        super(
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/",
                FileObserver.CREATE
        );
    }

    @Override
    public void onEvent(int event, String path) {

        if (path != null) {

            //Inicializar procesado
            ArrayList<String> lista = new ArrayList<>();
            lista.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/" + path);
            new HiloInsertar(lista, null, null).execute();

            //Notificacion de imagen procesandose
            mostrarNotificacion(path);
            //Toast.makeText(getContext(), "Procesando imagen " + path , Toast.LENGTH_LONG).show();

        }
    }


    /**
     * Muestra una notificacion
     *
     * @param nombreImagen
     */
    private void mostrarNotificacion(String nombreImagen) {

        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
        int icono = R.mipmap.ic_launcher_round;

        mBuilder = new NotificationCompat.Builder(getContext())
                .setSmallIcon(icono)
                .setContentTitle("EstegoMaquina")
                .setContentText("Procesando imagen: " + nombreImagen)
                .setVibrate(new long[]{100, 250, 100, 500})
                .setAutoCancel(true);


        mNotifyMgr.notify(1, mBuilder.build());


    }
}