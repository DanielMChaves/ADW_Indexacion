package case_1;

import common.Database;
import common.Persona;

import java.sql.*;
import java.util.List;

public class test_case_1 {

    private static Connection connection_db0 = null;

    private static Statement sentence = null;

    private static String host, passwd, port, url, usr;

    private static final String db0 = "ADW0";

    static {
        host = "localhost";
        usr = "root";
        passwd = "1qazxsw2";

        String driver = "com.mysql.jdbc.Driver";
        port = "3306";

        try {
            Class.forName(driver);
            url = "jdbc:mysql://" + host + ":" + port + "/";
            connection_db0 = DriverManager.getConnection(url + db0, usr, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void test_1(String msg){

        System.out.println("[Case 1] Test 1: " + msg + " cache");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        List<Persona> peoples = Database.getPersonas(connection_db0,db0);
        time_end = System.currentTimeMillis();
        System.out.println("[Case 1] Tets 1: " + peoples.size() + " results");
        System.out.println("[Case 1] Test 1: " + ( time_end - time_start ) + " milliseconds");
    }

    public static void main(String[] args){

        System.out.println("**********************************************");
        System.out.println("[Case 1] Test 1: Get All PERSONAS");
        System.out.println("----------------------------------------------");
        test_1("Without");
        System.out.println("----------------------------------------------");
        test_1("With");
        System.out.println("**********************************************");

    }
}
