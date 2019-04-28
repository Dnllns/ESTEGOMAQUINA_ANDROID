package com.dalonsobascones.estegoMaquina.modelo.manipularExif;

import android.media.ExifInterface;

import java.io.IOException;

public class ExifControl {

    /**
     * Actualiza el exif de un archivo con la ruta pasada por parámetro
     * @param ruta
     * @param exifData
     */
    public static void modificarExif(String ruta, ExifData exifData) {

        //Exif interface
        try {

            //Obtener Exif
            ExifInterface exif = new ExifInterface(ruta);
            //Setear info
            exif.setAttribute(ExifInterface.TAG_MAKE, exifData.getTAG_MAKE());
            exif.setAttribute(ExifInterface.TAG_MODEL, exifData.getTAG_MODEL());
            exif.setAttribute(ExifInterface.TAG_SOFTWARE, exifData.getTAG_SOFTWARE());
            exif.setAttribute(ExifInterface.TAG_ORIENTATION, exifData.getTAG_ORIENTATION());
            exif.setAttribute(ExifInterface.TAG_X_RESOLUTION, exifData.getTAG_X_RESOLUTION());
            exif.setAttribute(ExifInterface.TAG_Y_RESOLUTION, exifData.getTAG_Y_RESOLUTION());
            exif.setAttribute(ExifInterface.TAG_RESOLUTION_UNIT, exifData.getTAG_RESOLUTION_UNIT());
            exif.setAttribute(ExifInterface.TAG_DATETIME, exifData.getTAG_DATETIME());
            exif.setAttribute(ExifInterface.TAG_EXPOSURE_TIME, exifData.getTAG_EXPOSURE_TIME());
            exif.setAttribute(ExifInterface.TAG_F_NUMBER, exifData.getTAG_F_NUMBER());
            exif.setAttribute(ExifInterface.TAG_ISO_SPEED_RATINGS, exifData.getTAG_ISO()); //iso
            exif.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL, exifData.getTAG_DATETIME_ORIGINAL());
            exif.setAttribute(ExifInterface.TAG_METERING_MODE, exifData.getTAG_METERING_MODE());
            exif.setAttribute(ExifInterface.TAG_FOCAL_LENGTH, exifData.getTAG_FOCAL_LENGTH());
            exif.setAttribute(ExifInterface.TAG_COLOR_SPACE, exifData.getTAG_COLOR_SPACE());
            exif.setAttribute(ExifInterface.TAG_IMAGE_WIDTH, exifData.getTAG_IMAGE_WIDTH());
            //exif.setAttribute(ExifInterface.TAG_IMAGE_HEIGHT, exifData.getTAG_IMAGE_HEIGHT());
            exif.setAttribute(ExifInterface.TAG_EXPOSURE_MODE, exifData.getTAG_EXPOSURE_MODE());
            exif.setAttribute(ExifInterface.TAG_WHITE_BALANCE, exifData.getTAG_WHITE_BALANCE());
            exif.setAttribute(ExifInterface.TAG_DIGITAL_ZOOM_RATIO, exifData.getTAG_DIGITAL_ZOOM_RATIO());
            exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, exifData.getTAG_GPS_LATITUDE_REF());
            exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, exifData.getTAG_GPS_LONGITUDE_REF());
            exif.setAttribute(ExifInterface.TAG_GPS_ALTITUDE_REF, exifData.getTAG_GPS_ALTITUDE_REF());
            exif.setAttribute(ExifInterface.TAG_GPS_TIMESTAMP, exifData.getTAG_GPS_TIMESTAMP());
            exif.setAttribute(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, exifData.getTAG_GPS_IMG_DIRECTION_REF());
            exif.setAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD, exifData.getTAG_GPS_PROCESSING_METHOD());
            exif.setAttribute(ExifInterface.TAG_GPS_DATESTAMP, exifData.getTAG_GPS_DATESTAMP());
            //exif.setAttribute(ExifInterface.TAG_APERTURE, exifData.getTAG_APERTURE());
            exif.setAttribute(ExifInterface.TAG_FLASH, exifData.getTAG_FLASH());
            exif.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, exifData.getTAG_GPS_ALTITUDE());
            exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, exifData.getTAG_GPS_LATITUDE());
            exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, exifData.getTAG_GPS_LONGITUDE());
            exif.setAttribute(ExifInterface.TAG_SHUTTER_SPEED_VALUE, exifData.getTAG_SHUTTER_SPEED_VALUE());
            //Guardar exif
            exif.saveAttributes();

        } catch (IOException ex) {
        }

    }


    /**
     * Extrae el exif del archivo con lo ruta pasada por parametro
     * @param ruta
     * @return
     */
    public static ExifData extraerExif(String ruta) {


        ExifData data = new ExifData();
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(ruta);
        } catch (IOException e) {

        }
        if (exif != null) {

            data.setTAG_MAKE(exif.getAttribute(ExifInterface.TAG_MAKE));
            data.setTAG_MODEL(exif.getAttribute(ExifInterface.TAG_MODEL));
            data.setTAG_SOFTWARE(exif.getAttribute(ExifInterface.TAG_SOFTWARE));
            data.setTAG_ORIENTATION(exif.getAttribute(ExifInterface.TAG_ORIENTATION));
            data.setTAG_X_RESOLUTION(exif.getAttribute(ExifInterface.TAG_X_RESOLUTION));
            data.setTAG_Y_RESOLUTION(exif.getAttribute(ExifInterface.TAG_Y_RESOLUTION));
            data.setTAG_RESOLUTION_UNIT(exif.getAttribute(ExifInterface.TAG_RESOLUTION_UNIT));
            data.setTAG_DATETIME(exif.getAttribute(ExifInterface.TAG_DATETIME));
            data.setTAG_EXPOSURE_TIME(exif.getAttribute(ExifInterface.TAG_EXPOSURE_TIME));
            data.setTAG_F_NUMBER(exif.getAttribute(ExifInterface.TAG_F_NUMBER));
            data.setTAG_ISO(exif.getAttribute(ExifInterface.TAG_ISO_SPEED_RATINGS)); //ISO
            data.setTAG_DATETIME_ORIGINAL(exif.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL));
            data.setTAG_METERING_MODE(exif.getAttribute(ExifInterface.TAG_METERING_MODE));
            data.setTAG_FOCAL_LENGTH(exif.getAttribute(ExifInterface.TAG_FOCAL_LENGTH));
            data.setTAG_COLOR_SPACE(exif.getAttribute(ExifInterface.TAG_COLOR_SPACE));
            data.setTAG_IMAGE_WIDTH(exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH));
            data.setTAG_EXPOSURE_MODE(exif.getAttribute(ExifInterface.TAG_EXPOSURE_MODE));
            data.setTAG_WHITE_BALANCE(exif.getAttribute(ExifInterface.TAG_WHITE_BALANCE));
            data.setTAG_DIGITAL_ZOOM_RATIO(exif.getAttribute(ExifInterface.TAG_DIGITAL_ZOOM_RATIO));
            data.setTAG_GPS_LATITUDE_REF(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF));
            data.setTAG_GPS_LONGITUDE_REF(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF));
            data.setTAG_GPS_ALTITUDE_REF(exif.getAttribute(ExifInterface.TAG_GPS_ALTITUDE_REF));
            data.setTAG_GPS_TIMESTAMP(exif.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP));
            data.setTAG_GPS_IMG_DIRECTION_REF(exif.getAttribute(ExifInterface.TAG_GPS_IMG_DIRECTION_REF));
            data.setTAG_GPS_PROCESSING_METHOD(exif.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD));
            data.setTAG_GPS_DATESTAMP(exif.getAttribute(ExifInterface.TAG_GPS_DATESTAMP));
            data.setTAG_FLASH(exif.getAttribute(ExifInterface.TAG_FLASH));
            data.setTAG_GPS_ALTITUDE(exif.getAttribute(ExifInterface.TAG_GPS_ALTITUDE));
            data.setTAG_GPS_LATITUDE(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
            data.setTAG_GPS_LONGITUDE(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
            data.setTAG_SHUTTER_SPEED_VALUE(exif.getAttribute(ExifInterface.TAG_SHUTTER_SPEED_VALUE));


        }

        return data;

    }

}
