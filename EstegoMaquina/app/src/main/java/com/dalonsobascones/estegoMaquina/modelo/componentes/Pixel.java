/*

    (~ __|_ _  _  _ |\/| _  _    . _  _
    (__\ | (/_(_|(_)|  |(_|(_||_||| |(_|
               _|            |/

 */
package com.dalonsobascones.estegoMaquina.modelo.componentes;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */

public class Pixel {

    private int x;
    private int y;
    private ColorRGB color;

    /**
     * Constructor del objeto Pixel
     *
     * @param x coordenada x
     * @param y coordenada y
     * @param color color del pixel
     */
    public Pixel(int x, int y, ColorRGB color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }


    /**
     * ------------------------- SETTERS ---------------------------------------
     * -------------------------------------------------------------------------
     */


    /**
     * @param color
     */
    public void setColor(ColorRGB color) {
        this.color = color;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * ------------------------- GETTERS ---------------------------------------
     * -------------------------------------------------------------------------
     */

    /**
     * @return
     */
    public ColorRGB getColorRGB() {
        return this.color;
    }

    /**
     * Obtiene la coordenada x del pixel
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada y del pixel
     *
     * @return
     */
    public int getY() {
        return y;
    }



}
