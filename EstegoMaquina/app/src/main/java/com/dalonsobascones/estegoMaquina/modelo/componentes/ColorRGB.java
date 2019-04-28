/*

    (~ __|_ _  _  _ |\/| _  _    . _  _
    (__\ | (/_(_|(_)|  |(_|(_||_||| |(_|
               _|            |/

 */
package com.dalonsobascones.estegoMaquina.modelo.componentes;

import android.graphics.Color;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */

public class ColorRGB {

    int colorValue;     //INT ARGB VALUE


    /**
     * Constructor
     * @param colorValue
     */
    public ColorRGB(int colorValue) {

        this.colorValue = colorValue;

    }

    /**
     * Constructor
     * @param R canal red
     * @param G canal green
     * @param B canal blue
     */
    public ColorRGB(int R, int G, int B) {

        colorValue = Color.rgb(R, G, B);

    }


    /**
     * Crea un array con los valores de los componentes RGB
     * Optimizado para versiones Android < 8
     *
     * @return
     */
    public int[] getArrayRGB() {

        int[] colorArray = new int[3];
        colorArray[0] = (colorValue >> 16) & 0xff;  //red
        colorArray[1] = (colorValue >> 8) & 0xff;   //green
        colorArray[2] = (colorValue) & 0xff;        //blue
        return colorArray;

    }


    /**
     *
     * @return
     */
    public int getColor() {

        return colorValue;
    }

    /**
     *
     * @param colorValue
     */
    public void setColor(int colorValue) {

        this.colorValue = colorValue;

    }


}
