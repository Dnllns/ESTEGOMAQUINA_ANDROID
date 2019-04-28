package com.dalonsobascones.estegoMaquina.controlador;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dalonsobascones.estegoMaquina.modelo.componentes.EstegoMaquina;
import com.dalonsobascones.estegoMaquina.modelo.manipularExif.ExifControl;
import com.dalonsobascones.estegoMaquina.modelo.manipularExif.ExifData;
import com.dalonsobascones.estegoMaquina.vista.Main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Daniel Alonso Báscones
 * @version Android 1
 */
public class Controlador {


    //---------------------------------------------------------------------------------
    //-----------------------------INSERTAR--------------------------------------------
    //---------------------------------------------------------------------------------


    /**
     * Crear EstegoImagen png
     */
    public static void insertarExifEnBmp(String rutaImagen) {

        File fOriginal = new File(rutaImagen);

        //Extraer el exif
        ExifData exifData = ExifControl.extraerExif(rutaImagen);

        //Serializar exif
        String base64 = exifData.toBase64String();

        //Inicializar insercion de EstegoExif
        Bitmap bitmap = BitmapFactory.decodeFile(rutaImagen);
        EstegoMaquina e = new EstegoMaquina(bitmap, base64);
        e.iniciarProcesado();

        //Guardar imagen procesada
        String nombreArchivo = fOriginal.getName().replaceAll(".jpg", ".png");
        e.getImagen().guardarImagenPNG(Main.dirProcesado + "/EstegoImagen_" + nombreArchivo);

    }


    /**
     * Procedimiento que se ejecuta tras recibirse el request de insetar imagen
     *
     * @param data
     * @param context
     * @param progressBar
     * @param textView
     */
    public static void rqInsertarImagen(Intent data, Context context, ProgressBar progressBar, TextView textView) {
        if (null != data) {

            ArrayList<String> rutas = new ArrayList();

            if (null != data.getClipData()) {
                //Seleccion múltiple
                for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                    //Obtener rutas seleccionadas
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    String rutaArchivo = Controlador.getPathFromUri(context, uri);
                    rutas.add(rutaArchivo);
                }

            } else {
                //Obtener ruta
                Uri uri = data.getData();
                String rutaArchivo = Controlador.getPathFromUri(context, uri);
                rutas.add(rutaArchivo);
            }


            //Inicaializar hilo
            new HiloInsertar(rutas, progressBar, textView).execute();


            //DEBUG (SIN HILO)

            /*try {

                for (String rutaImagen : rutas) {

                    //Se obtiene el exif original ya que al procesar la imagen
                    //se alteran algunos metadatos como Tag_Software, etc
                    ExifData exif = ExifControl.extraerExif(rutaImagen);

                    //Procesado de la imagen
                    Controlador.insertarExifEnBmp(rutaImagen);

                    //Se setea el exif original de nuevo
                    ExifControl.modificarExif(rutaImagen, exif);

                }

            } catch (Exception x) {

            }*/

        }
    }


    //---------------------------------------------------------------------------------
    //-----------------------------REESTABLECER----------------------------------------
    //---------------------------------------------------------------------------------


    /**
     * Hilo Reestablecer
     * ------------------
     * Reestablecer exif
     *
     * @param rutaFichero
     */
    public static void reestablecerExif(String rutaFichero) {
        //Obtener Fichero PNG con Esteganografia
        File fOriginal = new File(rutaFichero);
        Bitmap bitmap = BitmapFactory.decodeFile(rutaFichero);

        //Obtener EstegoExif
        EstegoMaquina e = new EstegoMaquina(bitmap);
        e.iniciarProcesado();
        String estegoExif = e.getExtraccion();

        //Obtener exif desde serial
        ExifData exif = new ExifData(estegoExif);

        //Crear fichero .jpg de salida
        String nombreArchivo = fOriginal.getName().replaceAll(".png", ".jpg");
        String rutaGuardado = Main.dirReset + nombreArchivo;

        try (FileOutputStream out = new FileOutputStream(rutaGuardado)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (IOException e2) {
        }

        //insertar exif
        ExifControl.modificarExif(rutaGuardado, exif);


    }


    /**
     * Procedimiento que se ejecuta en el request de Seleccionar archivo para reset
     *
     * @param data
     * @param context
     * @param progressBar
     * @param textView
     */
    public static void rqRestablecerImagen(Intent data, Context context, ProgressBar progressBar, TextView textView) {
        if (null != data) {
            ArrayList<String> rutas = new ArrayList();

            if (null != data.getClipData()) {
                //SELECCIÓN MÚLTIPLE
                for (int i = 0; i < data.getClipData().getItemCount(); i++) {

                    //OBTENER RUTAS SELECCIONADAS
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    String rutaArchivo = Controlador.getPathFromUri(context, uri);
                    rutas.add(rutaArchivo);
                }

            } else {
                //OBTENER RUTA
                Uri uri = data.getData();
                String uriPath = Controlador.getPathFromUri(context, uri);
                rutas.add(uriPath);
            }
            new HiloReestablecer(rutas, progressBar, textView).execute();
        }
    }


    //---------------------------------------------------------------------------------
    //-----------------------------VISUALIZAR------------------------------------------
    //---------------------------------------------------------------------------------


    /**
     * Procedimiento del request de visualizar metadatos
     * @param data
     * @param context
     * @param textView
     * @param editText
     */
    public static void rqVisualizarMetadatos(Intent data, Context context, TextView textView, EditText editText) {

        //SELECCION UNICA
        if (null != data) {

            //OBTENER RUTA
            Uri uri = data.getData();
            String uriPath = Controlador.getPathFromUri(context, uri);

            File fOriginal = new File(uriPath);

            Bitmap bitmap = BitmapFactory.decodeFile(uriPath);

            //Obtener EstegoExif
            EstegoMaquina e = new EstegoMaquina(bitmap);
            e.iniciarProcesado();
            String estegoExif = e.getExtraccion();

            //Obtener exif desde serial
            ExifData exif = new ExifData(estegoExif);

            textView.setText("Metadatos de " + uriPath);
            editText.setText(exif.getExifText());


        }

    }

    /**
     * Obtiene el path real a partir de un uri
     *
     * @param context
     * @param uri
     * @return
     */

    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}
