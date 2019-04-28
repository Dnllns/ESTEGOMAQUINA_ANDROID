package com.dalonsobascones.estegoMaquina.controlador;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dalonsobascones.estegoMaquina.modelo.manipularExif.ExifControl;
import com.dalonsobascones.estegoMaquina.modelo.manipularExif.ExifData;

import java.util.ArrayList;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class HiloInsertar extends AsyncTask<Void, Integer, Boolean> {

    ArrayList<String> rutas;
    ProgressBar progressBar;
    TextView textView;

    public HiloInsertar(ArrayList<String> rutaImagen, ProgressBar progressBar, TextView textView) {
        this.rutas = rutaImagen;
        this.progressBar = progressBar;
        this.textView = textView;
    }


    @Override
    protected Boolean doInBackground(Void... voids) {

        Boolean procesoCompletado = false;

        try {

            for (String rutaImagen : rutas) {

                //Se obtiene el exif original ya que al procesar la imagen
                //se alteran algunos metadatos como Tag_Software, etc
                ExifData exif = ExifControl.extraerExif(rutaImagen);

                //Procesado de la imagen
                Controlador.insertarExifEnBmp(rutaImagen);

                //Se setea el exif original de nuevo
                ExifControl.modificarExif(rutaImagen, exif);

                procesoCompletado = true;

            }

        } catch (Exception x) {
        }

        return procesoCompletado;
    }

    //ANTES DE LA EJECUCION
    @Override
    protected void onPreExecute() {

        try {
            //Mostrar barra de progreso
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("Procesando...");

        } catch (Exception x) {


        }

    }

    //DESPUES DE LA EJECUCION
    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            try {
                //Actualizar la interface
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText("Completado");

            } catch (Exception x) {
            }
        }
    }

    @Override
    protected void onCancelled() {
    }

}
