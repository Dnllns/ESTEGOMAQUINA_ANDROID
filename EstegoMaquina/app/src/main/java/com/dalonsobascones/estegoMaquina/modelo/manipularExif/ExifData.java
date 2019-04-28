package com.dalonsobascones.estegoMaquina.modelo.manipularExif;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Daniel Alonso
 */
public class ExifData implements Serializable {

    private String TAG_MAKE;
    private String TAG_MODEL;
    private String TAG_SOFTWARE;
    private String TAG_ORIENTATION;
    private String TAG_X_RESOLUTION;
    private String TAG_Y_RESOLUTION;
    private String TAG_RESOLUTION_UNIT;
    private String TAG_DATETIME;
    private String TAG_EXPOSURE_TIME;
    private String TAG_F_NUMBER;
    private String TAG_ISO;
    private String TAG_DATETIME_ORIGINAL;
    private String TAG_METERING_MODE;
    private String TAG_FOCAL_LENGTH;
    private String TAG_COLOR_SPACE;
    private String TAG_IMAGE_WIDTH;
    //private String TAG_IMAGE_HEIGHT;
    private String TAG_EXPOSURE_MODE;
    private String TAG_WHITE_BALANCE;
    private String TAG_DIGITAL_ZOOM_RATIO;
    private String TAG_GPS_LATITUDE_REF;
    private String TAG_GPS_LONGITUDE_REF;
    private String TAG_GPS_ALTITUDE_REF;
    private String TAG_GPS_TIMESTAMP;
    private String TAG_GPS_IMG_DIRECTION_REF;
    private String TAG_GPS_PROCESSING_METHOD;
    private String TAG_GPS_DATESTAMP;
    //private String TAG_APERTURE;
    private String TAG_FLASH;
    private String TAG_GPS_ALTITUDE;
    private String TAG_GPS_LATITUDE;
    private String TAG_GPS_LONGITUDE;
    private String TAG_SHUTTER_SPEED_VALUE;


    public ExifData() {

    }

    /**
     * Constructor serializado
     *
     * @param base64_object
     */
    public ExifData(String base64_object) {

        fromString(base64_object);

    }

    /**
     * Obtiene la representacion del objeto como un String en base64
     *
     * @return
     */
    public String toBase64String() {

        ObjectOutputStream oos = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();
            return new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));

        } catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getLocalizedMessage());
            return null;

        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                System.out.println("Excepcion: " + ex.getLocalizedMessage());

            }
        }
    }

    /**
     * @param s
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void fromString(String s) {
        ObjectInputStream ois = null;
        try {
            byte[] data = Base64.decode(s, Base64.DEFAULT);
            ois = new ObjectInputStream(
                    new ByteArrayInputStream(data)
            );
            ExifData exif = null;
            try {
                exif = (ExifData) ois.readObject();
            } catch (ClassNotFoundException ex) {
                System.out.println("Excepcion: " + ex.getLocalizedMessage());
            }

            if (exif != null) {

                setTAG_MAKE(exif.TAG_MAKE);
                setTAG_MODEL(exif.TAG_MODEL);
                setTAG_SOFTWARE(exif.TAG_SOFTWARE);
                setTAG_ORIENTATION(exif.TAG_ORIENTATION);
                setTAG_X_RESOLUTION(exif.TAG_X_RESOLUTION);
                setTAG_Y_RESOLUTION(exif.TAG_Y_RESOLUTION);
                setTAG_RESOLUTION_UNIT(exif.TAG_RESOLUTION_UNIT);
                setTAG_DATETIME(exif.TAG_DATETIME);
                setTAG_EXPOSURE_TIME(exif.TAG_EXPOSURE_TIME);
                setTAG_F_NUMBER(exif.TAG_F_NUMBER);
                setTAG_ISO(exif.TAG_ISO);
                setTAG_DATETIME_ORIGINAL(exif.TAG_DATETIME_ORIGINAL);
                setTAG_METERING_MODE(exif.TAG_METERING_MODE);
                setTAG_FOCAL_LENGTH(exif.TAG_FOCAL_LENGTH);
                setTAG_COLOR_SPACE(exif.TAG_COLOR_SPACE);
                setTAG_IMAGE_WIDTH(exif.TAG_IMAGE_WIDTH);
                //setTAG_IMAGE_HEIGHT(exif.TAG_IMAGE_HEIGHT);
                setTAG_METERING_MODE(exif.TAG_METERING_MODE);
                setTAG_WHITE_BALANCE(exif.TAG_WHITE_BALANCE);
                setTAG_DIGITAL_ZOOM_RATIO(exif.TAG_DIGITAL_ZOOM_RATIO);
                setTAG_GPS_LATITUDE_REF(exif.TAG_GPS_LATITUDE_REF);
                setTAG_GPS_LONGITUDE_REF(exif.TAG_GPS_LONGITUDE_REF);
                setTAG_GPS_ALTITUDE_REF(exif.TAG_GPS_ALTITUDE_REF);
                setTAG_GPS_TIMESTAMP(exif.TAG_GPS_TIMESTAMP);
                setTAG_GPS_IMG_DIRECTION_REF(exif.TAG_GPS_IMG_DIRECTION_REF);
                setTAG_GPS_PROCESSING_METHOD(exif.TAG_GPS_PROCESSING_METHOD);
                setTAG_GPS_DATESTAMP(exif.TAG_GPS_DATESTAMP);
                //setTAG_APERTURE(exif.TAG_APERTURE);
                setTAG_FLASH(exif.TAG_FLASH);
                setTAG_GPS_ALTITUDE(exif.TAG_GPS_ALTITUDE);
                setTAG_GPS_LATITUDE(exif.TAG_GPS_LATITUDE);
                setTAG_GPS_LONGITUDE(exif.TAG_GPS_LONGITUDE);
                setTAG_SHUTTER_SPEED_VALUE(exif.TAG_SHUTTER_SPEED_VALUE);

            }
        } catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getLocalizedMessage());
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
            }
        }
    }

    //Getters y setters


    public String getExifText() {

        String text = "";

        text = text + "TAG_MAKE: " + TAG_MAKE;
        text = text + "\n" + "TAG_MODEL: " + TAG_MODEL;
        text = text + "\n" + "TAG_SOFTWARE: " + TAG_SOFTWARE;
        text = text + "\n" + "TAG_ORIENTATION: " + TAG_ORIENTATION;
        text = text + "\n" + "TAG_X_RESOLUTION: " + TAG_X_RESOLUTION;
        text = text + "\n" + "TAG_Y_RESOLUTION: " + TAG_Y_RESOLUTION;
        text = text + "\n" + "TAG_RESOLUTION_UNIT: " + TAG_RESOLUTION_UNIT;
        text = text + "\n" + "TAG_DATETIME: " + TAG_DATETIME;
        text = text + "\n" + "TAG_DATETIME_ORIGINAL: " + TAG_DATETIME_ORIGINAL;
        text = text + "\n" + "TAG_ISO: " + TAG_ISO;
        text = text + "\n" + "TAG_EXPOSURE_TIME: " + TAG_EXPOSURE_TIME;
        text = text + "\n" + "TAG_F_NUMBER: " + TAG_F_NUMBER;
        text = text + "\n" + "TAG_METERING_MODE: " + TAG_METERING_MODE;
        text = text + "\n" + "TAG_FOCAL_LENGTH: " + TAG_FOCAL_LENGTH;
        text = text + "\n" + "TAG_COLOR_SPACE: " + TAG_COLOR_SPACE;
        text = text + "\n" + "TAG_IMAGE_WIDTH: " + TAG_IMAGE_WIDTH;
        //private String TA+ "IMAGE_HEIGHT;              G_IMAGE_HEIGHT;
        text = text + "\n" + "TAG_EXPOSURE_MODE: " + TAG_EXPOSURE_MODE;
        text = text + "\n" + "TAG_WHITE_BALANCE: " + TAG_WHITE_BALANCE;
        text = text + "\n" + "TAG_DIGITAL_ZOOM_RATIO: " + TAG_DIGITAL_ZOOM_RATIO;
        text = text + "\n" + "TAG_FLASH: " + TAG_FLASH;
        text = text + "\n" + "TAG_SHUTTER_SPEED_VALUE: " + TAG_SHUTTER_SPEED_VALUE;
        text = text + "\n" + "TAG_GPS_LATITUDE_REF: " + TAG_GPS_LATITUDE_REF;
        text = text + "\n" + "TAG_GPS_LONGITUDE_REF: " + TAG_GPS_LONGITUDE_REF;
        text = text + "\n" + "TAG_GPS_ALTITUDE_REF: " + TAG_GPS_ALTITUDE_REF;
        text = text + "\n" + "TAG_GPS_TIMESTAMP: " + TAG_GPS_TIMESTAMP;
        text = text + "\n" + "TAG_GPS_IMG_DIRECTION_REF: " + TAG_GPS_IMG_DIRECTION_REF;
        text = text + "\n" + "TAG_GPS_PROCESSING_METHOD: " + TAG_GPS_PROCESSING_METHOD;
        text = text + "\n" + "TAG_GPS_DATESTAMP: " + TAG_GPS_DATESTAMP;
        //private String TA+ "APERTURE;                  G_APERTURE;

        text = text + "\n" + "TAG_GPS_ALTITUDE: " + TAG_GPS_ALTITUDE;
        text = text + "\n" + "TAG_GPS_LATITUDE: " + TAG_GPS_LATITUDE;
        text = text + "\n" + "TAG_GPS_LONGITUDE: " + TAG_GPS_LONGITUDE;


        return text;
    }


    public String getTAG_MAKE() {
        return TAG_MAKE;
    }

    public void setTAG_MAKE(String TAG_MAKE) {
        this.TAG_MAKE = TAG_MAKE;
    }

    public String getTAG_MODEL() {
        return TAG_MODEL;
    }

    public void setTAG_MODEL(String TAG_MODEL) {
        this.TAG_MODEL = TAG_MODEL;
    }

    public String getTAG_SOFTWARE() {
        return TAG_SOFTWARE;
    }

    public void setTAG_SOFTWARE(String TAG_SOFTWARE) {
        this.TAG_SOFTWARE = TAG_SOFTWARE;
    }

    public String getTAG_ORIENTATION() {
        return TAG_ORIENTATION;
    }

    public void setTAG_ORIENTATION(String TAG_ORIENTATION) {
        this.TAG_ORIENTATION = TAG_ORIENTATION;
    }

    public String getTAG_X_RESOLUTION() {
        return TAG_X_RESOLUTION;
    }

    public void setTAG_X_RESOLUTION(String TAG_X_RESOLUTION) {
        this.TAG_X_RESOLUTION = TAG_X_RESOLUTION;
    }

    public String getTAG_Y_RESOLUTION() {
        return TAG_Y_RESOLUTION;
    }

    public void setTAG_Y_RESOLUTION(String TAG_Y_RESOLUTION) {
        this.TAG_Y_RESOLUTION = TAG_Y_RESOLUTION;
    }

    public String getTAG_RESOLUTION_UNIT() {
        return TAG_RESOLUTION_UNIT;
    }

    public void setTAG_RESOLUTION_UNIT(String TAG_RESOLUTION_UNIT) {
        this.TAG_RESOLUTION_UNIT = TAG_RESOLUTION_UNIT;
    }

    public String getTAG_DATETIME() {
        return TAG_DATETIME;
    }

    public void setTAG_DATETIME(String TAG_DATETIME) {
        this.TAG_DATETIME = TAG_DATETIME;
    }

    public String getTAG_EXPOSURE_TIME() {
        return TAG_EXPOSURE_TIME;
    }

    public void setTAG_EXPOSURE_TIME(String TAG_EXPOSURE_TIME) {
        this.TAG_EXPOSURE_TIME = TAG_EXPOSURE_TIME;
    }

    public String getTAG_F_NUMBER() {
        return TAG_F_NUMBER;
    }

    public void setTAG_F_NUMBER(String TAG_F_NUMBER) {
        this.TAG_F_NUMBER = TAG_F_NUMBER;
    }

    public String getTAG_ISO() {
        return TAG_ISO;
    }

    public void setTAG_ISO(String TAG_ISO) {
        this.TAG_ISO = TAG_ISO;
    }

    public String getTAG_DATETIME_ORIGINAL() {
        return TAG_DATETIME_ORIGINAL;
    }

    public void setTAG_DATETIME_ORIGINAL(String TAG_DATETIME_ORIGINAL) {
        this.TAG_DATETIME_ORIGINAL = TAG_DATETIME_ORIGINAL;
    }

    public String getTAG_METERING_MODE() {
        return TAG_METERING_MODE;
    }

    public void setTAG_METERING_MODE(String TAG_METERING_MODE) {
        this.TAG_METERING_MODE = TAG_METERING_MODE;
    }

    public String getTAG_FOCAL_LENGTH() {
        return TAG_FOCAL_LENGTH;
    }

    public void setTAG_FOCAL_LENGTH(String TAG_FOCAL_LENGTH) {
        this.TAG_FOCAL_LENGTH = TAG_FOCAL_LENGTH;
    }

    public String getTAG_COLOR_SPACE() {
        return TAG_COLOR_SPACE;
    }

    public void setTAG_COLOR_SPACE(String TAG_COLOR_SPACE) {
        this.TAG_COLOR_SPACE = TAG_COLOR_SPACE;
    }

    public String getTAG_IMAGE_WIDTH() {
        return TAG_IMAGE_WIDTH;
    }

    public void setTAG_IMAGE_WIDTH(String TAG_IMAGE_WIDTH) {
        this.TAG_IMAGE_WIDTH = TAG_IMAGE_WIDTH;
    }


/*
    public String getTAG_IMAGE_HEIGHT() {
        return TAG_IMAGE_HEIGHT;
    }

    public void setTAG_IMAGE_HEIGHT(String TAG_IMAGE_HEIGHT) {
        this.TAG_IMAGE_HEIGHT = TAG_IMAGE_HEIGHT;
    }
*/

    public String getTAG_EXPOSURE_MODE() {
        return TAG_EXPOSURE_MODE;
    }

    public void setTAG_EXPOSURE_MODE(String TAG_EXPOSURE_MODE) {
        this.TAG_EXPOSURE_MODE = TAG_EXPOSURE_MODE;
    }

    public String getTAG_WHITE_BALANCE() {
        return TAG_WHITE_BALANCE;
    }

    public void setTAG_WHITE_BALANCE(String TAG_WHITE_BALANCE) {
        this.TAG_WHITE_BALANCE = TAG_WHITE_BALANCE;
    }

    public String getTAG_DIGITAL_ZOOM_RATIO() {
        return TAG_DIGITAL_ZOOM_RATIO;
    }

    public void setTAG_DIGITAL_ZOOM_RATIO(String TAG_DIGITAL_ZOOM_RATIO) {
        this.TAG_DIGITAL_ZOOM_RATIO = TAG_DIGITAL_ZOOM_RATIO;
    }

    public String getTAG_GPS_LATITUDE_REF() {
        return TAG_GPS_LATITUDE_REF;
    }

    public void setTAG_GPS_LATITUDE_REF(String TAG_GPS_LATITUDE_REF) {
        this.TAG_GPS_LATITUDE_REF = TAG_GPS_LATITUDE_REF;
    }

    public String getTAG_GPS_LONGITUDE_REF() {
        return TAG_GPS_LONGITUDE_REF;
    }

    public void setTAG_GPS_LONGITUDE_REF(String TAG_GPS_LONGITUDE_REF) {
        this.TAG_GPS_LONGITUDE_REF = TAG_GPS_LONGITUDE_REF;
    }

    public String getTAG_GPS_ALTITUDE_REF() {
        return TAG_GPS_ALTITUDE_REF;
    }

    public void setTAG_GPS_ALTITUDE_REF(String TAG_GPS_ALTITUDE_REF) {
        this.TAG_GPS_ALTITUDE_REF = TAG_GPS_ALTITUDE_REF;
    }

    public String getTAG_GPS_TIMESTAMP() {
        return TAG_GPS_TIMESTAMP;
    }

    public void setTAG_GPS_TIMESTAMP(String TAG_GPS_TIMESTAMP) {
        this.TAG_GPS_TIMESTAMP = TAG_GPS_TIMESTAMP;
    }

    public String getTAG_GPS_IMG_DIRECTION_REF() {
        return TAG_GPS_IMG_DIRECTION_REF;
    }

    public void setTAG_GPS_IMG_DIRECTION_REF(String TAG_GPS_IMG_DIRECTION_REF) {
        this.TAG_GPS_IMG_DIRECTION_REF = TAG_GPS_IMG_DIRECTION_REF;
    }

    public String getTAG_GPS_PROCESSING_METHOD() {
        return TAG_GPS_PROCESSING_METHOD;
    }

    public void setTAG_GPS_PROCESSING_METHOD(String TAG_GPS_PROCESSING_METHOD) {
        this.TAG_GPS_PROCESSING_METHOD = TAG_GPS_PROCESSING_METHOD;
    }

    public String getTAG_GPS_DATESTAMP() {
        return TAG_GPS_DATESTAMP;
    }

    public void setTAG_GPS_DATESTAMP(String TAG_GPS_DATESTAMP) {
        this.TAG_GPS_DATESTAMP = TAG_GPS_DATESTAMP;
    }

    /*
        public String getTAG_APERTURE() {
            return TAG_APERTURE;
        }

        public void setTAG_APERTURE(String TAG_APERTURE) {
            this.TAG_APERTURE = TAG_APERTURE;
        }
    */
    public String getTAG_FLASH() {
        return TAG_FLASH;
    }

    public void setTAG_FLASH(String TAG_FLASH) {
        this.TAG_FLASH = TAG_FLASH;
    }

    public String getTAG_GPS_ALTITUDE() {
        return TAG_GPS_ALTITUDE;
    }

    public void setTAG_GPS_ALTITUDE(String TAG_GPS_ALTITUDE) {
        this.TAG_GPS_ALTITUDE = TAG_GPS_ALTITUDE;
    }

    public String getTAG_GPS_LATITUDE() {
        return TAG_GPS_LATITUDE;
    }

    public void setTAG_GPS_LATITUDE(String TAG_GPS_LATITUDE) {
        this.TAG_GPS_LATITUDE = TAG_GPS_LATITUDE;
    }

    public String getTAG_GPS_LONGITUDE() {
        return TAG_GPS_LONGITUDE;
    }

    public void setTAG_GPS_LONGITUDE(String TAG_GPS_LONGITUDE) {
        this.TAG_GPS_LONGITUDE = TAG_GPS_LONGITUDE;
    }

    public String getTAG_SHUTTER_SPEED_VALUE() {
        return TAG_SHUTTER_SPEED_VALUE;
    }

    public void setTAG_SHUTTER_SPEED_VALUE(String TAG_SHUTTER_SPEED_VALUE) {
        this.TAG_SHUTTER_SPEED_VALUE = TAG_SHUTTER_SPEED_VALUE;
    }
}
