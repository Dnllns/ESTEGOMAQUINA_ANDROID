package com.dalonsobascones.estegoMaquina.vista;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.dalonsobascones.estegoMaquina.R;
import java.io.File;
import static java.lang.Thread.sleep;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class Main extends AppCompatActivity {

    //REQUEST CODE DEL FORRESULT
    private final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    public static String almacenamientoExterno = Environment.getExternalStorageDirectory().getAbsolutePath() + "/EstegoMaquina/";
    public static String dirProcesado = almacenamientoExterno + "Procesado/";
    public static String dirReset = almacenamientoExterno + "Reset/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //SETEAR LAYOUT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtener permisos
        obtenerPermisos();

        //Control de permisos aceptados
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            //Se muestra la imagen de la app durante 3 segundos y se entra al menu
            new Thread(new Runnable() {
                public void run() {
                    try {
                        sleep(3000);
                        Intent a = new Intent(Main.this, MenuPrincipal.class);
                        startActivity(a);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        new Thread(new Runnable() {
            public void run() {
                try {
                    sleep(500);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * Inicializar los directorios que usará la aplicación
     */
    public void crearDirectorio() {

        File appFolder = new File(almacenamientoExterno);

        //Crear directorios
        if (!appFolder.exists()) {

            appFolder.mkdir();

            File fProcesado = new File(dirProcesado);
            File fReset = new File(dirReset);

            if (!fProcesado.exists()) {
                fProcesado.mkdir();
            }
            if (!fReset.exists()) {
                fReset.mkdir();
            }

        }

    }

    //------------------------PERMISOS--------------------------
    //----------------------------------------------------------


    /**
     * SOLICITA AL USUARIO CONFIRMACION PARA OBTENER LOS PERMISOS
     */
    private void obtenerPermisos() {

        //COMPROBAR SI SE HAN OBTENIDO PERMISOS
        int comprobacion = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (comprobacion != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                //PEDIR CONFIRMACION AL USUARIO
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_EXTERNAL_STORAGE
                );
            }
        }
    }

    /**
     * CONTROL DE PERMISOS DE LA ACTIVITY
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            //PERMISO DE ESCRITURA ALMACENAMIENTO EXTERNO
            case REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //PERMISOS OBTENIDOS

                    //Crear directorios de la app si no existen
                    crearDirectorio();

                    Intent a = new Intent(Main.this, MenuPrincipal.class);
                    startActivity(a);


                } else {
                    //PERMISOS DENEGADOS
                    finish();
                }
                return;
            }

        }
    }
}
