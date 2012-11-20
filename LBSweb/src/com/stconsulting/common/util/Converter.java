package com.stconsulting.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import javax.servlet.*;
/** 
 * User: tcdata
 * Date: 29/07/2003
 * Time: 06:37:24 PM
 */
public class Converter {
    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    
    public static String dateToString(java.util.Date date) throws ConversionException {
        return dateToString(date, Converter.DEFAULT_DATE_PATTERN);
    }
    
    public static String dateToString(java.util.Date date, String pattern) throws ConversionException {
        String result = null;
        if (date == null) {
            throw new ConversionException("Parameter date is null");
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        result = formatter.format(date);
        return result;
    }
    
    public static java.util.Date stringToDate(String dateStr) throws ConversionException {
        return stringToDate(dateStr, Converter.DEFAULT_DATE_PATTERN);
    }
    
    public static java.util.Date stringToDate(String dateStr, String pattern) throws ConversionException {
        java.util.Date date = null;
        if (dateStr == null || dateStr.trim().equals("")) {
            throw new ConversionException("Invalid value for parameter dateStr");
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            date = formatter.parse(dateStr);
        }
        catch (ParseException pe) {
            throw new ConversionException(pe);
        }
        return date;
    }
    
    public static java.sql.Date stringToSqlDate(String dateStr) throws ConversionException {
        java.sql.Date sqlDate = null;
        java.util.Date date = stringToDate(dateStr);
        sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
    
    public static java.sql.Date stringToSqlDate(String dateStr, String pattern) throws ConversionException {
        java.sql.Date sqlDate = null;
        java.util.Date date = stringToDate(dateStr, pattern);
        sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
    
    /*public static byte[] imageToJpgByteArray(Image image) throws ConversionException {
        ByteArrayOutputStream baos = null;
        byte[] bytes = null;
        try {
            BufferedImage bimg = new BufferedImage(image.getWidth(null) , image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            bimg.createGraphics().drawImage(image, 0, 0, null);
            baos = new ByteArrayOutputStream();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimg);
            param.setQuality(1.0f, false);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(bimg);
            baos.close();
            bytes = baos.toByteArray();
        }
        catch(Exception e) {
            throw new ConversionException(e);
        }
        
        return bytes;
    }*/
    
    /*public static Image jpgByteArrayToImage(byte[] bytes) throws ConversionException {
        BufferedImage image = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(bais);
            image = decoder.decodeAsBufferedImage();
        }
        catch(Exception e) {
            throw new ConversionException(e);
        }
        return image;
    }*/
    
    
}
