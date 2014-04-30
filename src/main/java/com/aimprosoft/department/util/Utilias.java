package com.aimprosoft.department.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utilias {
    public static java.sql.Date transferDate(Date date) {

        java.sql.Date result = new java.sql.Date(date.getTime());
        return result;
    }

    public static Date getDateFromStr(String str) {
        Date result = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (str.equals("")) {
            result = null;
        } else {
            try {
                result = format.parse(str);
            } catch (ParseException e) {
                e.getMessage();
            }

        }


        return result;
    }


    public static Integer getIntFromStr(String str) {
        Integer integer;

        if (str == null) {
            integer = null;
        } else {
            integer = Integer.parseInt(str);
        }


        return integer;
    }

    public static Float getFloatFromStr(String str) {
        Float flo;
        if (str.equals("")) {

            flo = null;

        } else {
            try {
                flo = Float.parseFloat(str);
            } catch (Exception e) {
                flo = null;
            }

        }
        return flo;
    }

    public static String getStringFromString(String str) {
        String result = null;
        if (str.equals("")) {
            result = null;

        } else result = str;

        return result;

    }

}
