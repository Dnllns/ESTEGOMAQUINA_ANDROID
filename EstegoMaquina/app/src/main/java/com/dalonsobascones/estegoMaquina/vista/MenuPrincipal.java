package com.dalonsobascones.estegoMaquina.vista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dalonsobascones.estegoMaquina.R;
import com.dalonsobascones.estegoMaquina.controlador.Controlador;
import com.dalonsobascones.estegoMaquina.controlador.HiloInsertar;
import com.dalonsobascones.estegoMaquina.controlador.servicio.ServicioObservarDirectorio;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class MenuPrincipal extends AppCompatActivity {


    //Request
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private final int REQUEST_INSERTAR_SELECCIONAR_IMAGEN = 2;
    private final int REQUEST_REESTABLECER_SELECCIONAR_IMAGEN = 3;

    //Interface
    private Button btnSacarFoto;
    private Button btnSeleccionarFoto;
    private Button btnReestablecer;
    private Button btnAuto;
    private Button btnVisualizar;
    private Button btnInformacion;
    private ProgressBar progressBar;
    private TextView tvEstadoProcesado;

    //Control
    private String almacenamientoExterno = Environment.getExternalStorageDirectory().getAbsolutePath();
    private String rutaUltimaFotoSacada;
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //Botones
        btnSacarFoto = findViewById(R.id.BTN_MenuPrincipal_SacarFoto);
        btnSeleccionarFoto = findViewById(R.id.BTN_MenuPrincipal_SeleccionarFoto);
        btnReestablecer = findViewById(R.id.BTN_MenuPrincipal_Reestablecer);
        btnAuto = findViewById(R.id.BTN_MenuPrincipal_LanzarServicio);
        btnVisualizar = findViewById(R.id.BTN_MenuPrincipal_MenuVisualizar);
        btnInformacion = findViewById(R.id.BTN_MenuPrincipal_MenuInformacion);

        //Progress bar
        progressBar = findViewById(R.id.PBAR_MenuPrincipal_Progreso);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        //Text View Info
        tvEstadoProcesado = findViewById(R.id.TV__MenuPrincipal_EstadoProcesado);
        tvEstadoProcesado.setVisibility(View.INVISIBLE);

        //Set listeners
        btnSacarFoto.setOnClickListener(listenerBtnSacarFoto);
        btnSeleccionarFoto.setOnClickListener(listenerSeleccionarFoto);
        btnReestablecer.setOnClickListener(listenerBtnReestablecer);
        btnAuto.setOnClickListener(listenerBtnAuto);
        btnVisualizar.setOnClickListener(listenerBtnMenuVisualizar);
        btnInformacion.setOnClickListener(listenerBtnMenuInformacion);

        //Estructural
        context = getApplicationContext();

    }


    //------------------------------EVENTOS------------------------------------------------
    //-------------------------------------------------------------------------------------

    //Click en btnReestablecer

    View.OnClickListener listenerBtnMenuVisualizar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MenuPrincipal.this, MenuVisualizar.class);
            startActivity(i);
        }
    };


    View.OnClickListener listenerBtnMenuInformacion = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MenuPrincipal.this, MenuInformacion.class);
            startActivity(i);
        }
    };


    View.OnClickListener listenerBtnReestablecer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            seleccionarFicheroActivity(REQUEST_REESTABLECER_SELECCIONAR_IMAGEN);
        }
    };

    //Click en sacar foto
    View.OnClickListener listenerBtnSacarFoto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tomarFotoActivity();
        }
    };

    //Click en seleccionar imagenes insertar
    View.OnClickListener listenerSeleccionarFoto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            seleccionarFicheroActivity(REQUEST_INSERTAR_SELECCIONAR_IMAGEN);
        }
    };

    //Click en seleccionar imagenes insertar
    View.OnClickListener listenerBtnAuto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent s = new Intent(context, ServicioObservarDirectorio.class);
            startService(s);

        }
    };

    //---------------------------------------------------------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //INSERTAR SELECCIONAR IMAGEN
        if (requestCode == REQUEST_INSERTAR_SELECCIONAR_IMAGEN && resultCode == Activity.RESULT_OK) {
            Controlador.rqInsertarImagen(data, getContext(), progressBar, tvEstadoProcesado);
        }

        //REESTABLECER SELECCIONAR IMAGEN
        if (requestCode == REQUEST_REESTABLECER_SELECCIONAR_IMAGEN && resultCode == Activity.RESULT_OK) {
            Controlador.rqRestablecerImagen(data, getContext(), progressBar, tvEstadoProcesado);
        }

        //SACAR FOTO
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            ArrayList<String> ruta = new ArrayList<>();
            ruta.add(rutaUltimaFotoSacada);
            new HiloInsertar(ruta, progressBar, tvEstadoProcesado).execute();
        }
    }


    //---------------------------------------------------------------------------------------------------
    //-------------------------------Metodos de Clase----------------------------------------------------
    //---------------------------------------------------------------------------------------------------


    /**
     * Lanza una activity que permite tomar una foto
     */

    private void tomarFotoActivity() {


        //Ruta de guardado
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
        String fileName = "IMG_" + df.format(date) + ".jpg";
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/" + fileName;

        //Fichero
        File file = new File(filePath);
        rutaUltimaFotoSacada = file.toString();

        //Configurar intent activity
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);      //ACTION IMMAGE CAPTURE
        Uri imagenNueva = Uri.fromFile(file);                   //URI DEL FICHERO
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imagenNueva);  //AÑADIR EL URI COMO EXTRA

        //Lanzar activity
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    /**
     * Abre un activity  que permite seleccionar achivos imagen
     *
     * @param request
     */
    private void seleccionarFicheroActivity(int request) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), request);
    }


    public static Context getContext() {
        return context;
    }


}
