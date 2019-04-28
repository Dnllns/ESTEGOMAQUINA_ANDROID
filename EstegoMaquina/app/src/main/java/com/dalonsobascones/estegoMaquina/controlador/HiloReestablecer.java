package com.dalonsobascones.estegoMaquina.controlador;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class HiloReestablecer extends AsyncTask<Void, Integer, Boolean> {


    ArrayList<String> rutas;
    ProgressBar progressBar;
    TextView textView;


    public HiloReestablecer(ArrayList<String> rutaImagen, ProgressBar progressBar, TextView textView) {
        this.rutas = rutaImagen;
        this.progressBar = progressBar;
        this.textView = textView;
    }

    //SEGUNDO PLANO
    @Override
    protected Boolean doInBackground(Void... voids) {
        //Proceso de inserccion
        try {
            for (String ruta : rutas) {
                Controlador.reestablecerExif(ruta);
            }
            return true;
        } catch (Exception x) {
            return false;
        }
    }

    //ANTES DE LA EJECUCION
    @Override
    protected void onPreExecute() {
        //Mostrar barra de progreso
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView.setText("Procesando...");
    }

    //DESPUES DE LA EJECUCION
    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            progressBar.setVisibility(View.INVISIBLE);
            textView.setText("Completado");
            //new File(rutaUltimaFotoSacada).delete();
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            textView.setText("Se ha producido un Error\nLa imagen no se puede Reestablecer");
        }
    }

    @Override
    protected void onCancelled() {
    }

    //ACTUALIZAR PROGRESO
    @Override
    protected void onProgressUpdate(Integer... values) {
    }


}
