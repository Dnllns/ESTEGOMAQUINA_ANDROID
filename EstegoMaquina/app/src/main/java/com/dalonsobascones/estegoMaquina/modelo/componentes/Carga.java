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

public class Carga {

    private String carga;               //Carga en texto plano
    private final String binary;        //Carga binaria

    /**
     * Constructor
     * @param contenidoOriginal
     */
    public Carga(String contenidoOriginal) {
        carga = contenidoOriginal + " ";
        binary = binaryEncode(carga);
    }


    /**
     * Recibe un string, devuelbe el mismo string transformado a binario
     *
     * @param carga
     * @return un string equivalente al valor binario del recibido
     */
    public static String binaryEncode(String carga) {
        byte[] bytes = carga.getBytes();
        StringBuilder bin = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                bin.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return bin.toString();
    }

    /**
     * @param bin
     * @return
     */
    public static String binaryDecode(Boolean[] bin) {

        String stringExtraido = "";
        String byyte = "";
        int contadorBit = 0;
        for (Boolean bit : bin) {
            if (bit) {
                byyte = byyte + 0;
            } else {
                byyte = byyte + 1;
            }
            contadorBit++;
            if (contadorBit == 8) {

                stringExtraido = stringExtraido + (char) Integer.parseInt(byyte, 2);
                byyte = "";
                contadorBit = 0;

            }

        }
        return stringExtraido;
    }

    /**
     * Genera la carga que contiene la info del tamaño
     * @return
     */
    public String encapsularTam() {
        return binaryEncode(binary.length() + "|#||");
    }


    public String getBinary() {
        return binary;
    }

}
