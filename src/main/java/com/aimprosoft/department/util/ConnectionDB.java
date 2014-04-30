package com.aimprosoft.department.util;

import com.aimprosoft.department.models.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConnectionDB {
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1/test_mashine";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1";

    public static Connection getConnection()throws Exception{

    Connection con = null;


            Class.forName(DRIVER_CLASS_NAME);


            con = DriverManager.getConnection(URL,USER_NAME,PASSWORD);

        return con;


    }

    public static void closeConnection(Connection con)throws Exception{


            if(con!=null){


                    con.close();

            }
    }


}
