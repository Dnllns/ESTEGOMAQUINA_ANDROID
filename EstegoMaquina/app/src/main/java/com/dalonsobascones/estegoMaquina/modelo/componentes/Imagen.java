/*

    (~ __|_ _  _  _ |\/| _  _    . _  _
    (__\ | (/_(_|(_)|  |(_|(_||_||| |(_|
               _|            |/

 */
package com.dalonsobascones.estegoMaquina.modelo.componentes;

import android.graphics.Bitmap;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */

public class Imagen {

    private Bitmap imagen;

    /**
     * Constructor
     */
    public Imagen(Bitmap input) {

        //Generar bitmap mutable
        try {
            imagen = toMutable(input);
        } catch (Exception x) {

        }
    }

    /**
     * Actualiza los pixeles de la imagen correspondientes a los Pixel pasados
     * por parámetro en el array
     *
     * @param pixelesMod
     */
    public void actualizarImagenRGB(ArrayList<Pixel> pixelesMod) {


        //v1 Funcional
        for (Pixel pixel : pixelesMod) {
            //Modificamos el valor del objeto BufferedImage
            imagen.setPixel(
                    pixel.getX(),
                    pixel.getY(),
                    pixel.getColorRGB().getColor() //Color en formato INT_ARGB
            );
        }

    }


    // <editor-fold defaultstate="collapsed" desc="Getters">

    /**
     * Devuelve el pixel pasado por parámetro
     *
     * @param x
     * @param y
     * @return
     */
    public Pixel getPixel(int x, int y) {

        int colorValue = imagen.getPixel(x, y);
        return new Pixel(x, y, new ColorRGB(colorValue));
    }

    public int getAncho() {
        return imagen.getWidth();
    }

    public int getAlto() {
        return imagen.getHeight();
    }

    // </editor-fold >


    // <editor-fold defaultstate="collapsed" desc="Metodos de Clase"> 

    /**
     * Guarda la imagen con el nombre pasado por parametro
     *
     * @param rutaGuardado
     */
    public void guardarImagenPNG(String rutaGuardado) {

        try (FileOutputStream out = new FileOutputStream(rutaGuardado)) {
            imagen.compress(Bitmap.CompressFormat.PNG, 100, out);
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Obtener bitmap mutable a partir de noMutable
     *
     * @param noMutable el bitmap no mutable original
     * @return
     */
    private Bitmap toMutable(Bitmap noMutable) {

        return noMutable.copy(Bitmap.Config.ARGB_8888, true);

    }

    // </editor-fold> 


}
