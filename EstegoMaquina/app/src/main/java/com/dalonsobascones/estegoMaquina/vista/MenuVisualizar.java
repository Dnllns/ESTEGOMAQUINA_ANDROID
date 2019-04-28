package com.dalonsobascones.estegoMaquina.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.dalonsobascones.estegoMaquina.R;
import com.dalonsobascones.estegoMaquina.controlador.Controlador;
import static com.dalonsobascones.estegoMaquina.vista.MenuPrincipal.getContext;


/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class MenuVisualizar extends AppCompatActivity {

    private TextView tvTitular;
    private EditText edMetadatos;
    private ImageButton btnSeleccionarimagen;

    //Request
    private final int REQUEST_SELECCIONAR_IMAGEN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_visualizar);

        //Descripción Path imagen...
        tvTitular = findViewById(R.id.TV_MenuVisualizar_Titular);

        //Visualizar metadatos
        edMetadatos = findViewById(R.id.ET_MenuVisualizar_Metadatos);
        edMetadatos.setVisibility(View.INVISIBLE);

        //Botones
        btnSeleccionarimagen = findViewById(R.id.BTN_MenuVisualizar_SeleccionarImagen);
        btnSeleccionarimagen.setOnClickListener(listenerBtnSeleccionarimagen);

    }



    //Listener Button

    View.OnClickListener listenerBtnSeleccionarimagen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            seleccionarFicheroActivity(REQUEST_SELECCIONAR_IMAGEN);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //REQUEST REESTABLECER SELECCIONAR IMAGEN
        if (requestCode == REQUEST_SELECCIONAR_IMAGEN && resultCode == Activity.RESULT_OK) {

            try {
                edMetadatos.setVisibility(View.VISIBLE);
                Controlador.rqVisualizarMetadatos(data, getContext(), tvTitular, edMetadatos);

            } catch (Exception x) {

                tvTitular.setText("Se ha producido un error, la imagen no contiene Estegometadatos");
                edMetadatos.setVisibility(View.INVISIBLE);


            }
        }


    }


    //-----------------------------


    /**
     * Procedimiento que abre una activity para elegir un archivo del almacenamiento
     * @param request
     */
    private void seleccionarFicheroActivity(int request) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), request);
    }

}
