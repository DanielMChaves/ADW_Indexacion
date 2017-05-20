package partition;

import java.sql.*;
import java.util.ArrayList;

public class Test {

    private static Connection connection_db0 = null;

    private static String host, passwd, port, url, usr;

    private static final String db0 = "pruebaE1";

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

    private static void test_1(int table){

        System.out.println(Database.getPartition(table) + " Table");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        ArrayList<Empleado> result = Database.getEmpleados(connection_db0,table);
        time_end = System.currentTimeMillis();
        System.out.println(result.size() + " results");
        System.out.println(( time_end - time_start ) + " milliseconds");
    }

    private static void test_2(int table){

        System.out.println(Database.getPartition(table) + " Table");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        ArrayList<Empleado> result = Database.getEmpleados90(connection_db0,table);
        time_end = System.currentTimeMillis();
        System.out.println(result.size() + " results");
        System.out.println(( time_end - time_start ) + " milliseconds");
    }

    private static void test_3(int table){

        System.out.println(Database.getPartition(table) + " Table");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        ArrayList<Empleado> result = Database.getEmpleados32_48(connection_db0,table);
        time_end = System.currentTimeMillis();
        System.out.println(result.size() + " results");
        System.out.println(( time_end - time_start ) + " milliseconds");
    }

    private static void test_4(int table){

        System.out.println(Database.getPartition(table) + " Table");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        ArrayList<Empleado> result = Database.getEmpleadosDepartment1_1000(connection_db0,table);
        time_end = System.currentTimeMillis();
        System.out.println(result.size() + " results");
        System.out.println(( time_end - time_start ) + " milliseconds");
    }

    public static void main(String[] args){

        System.out.println("**************************************************************");
        System.out.println("Test 1: Get All EMPLEADOS");
        System.out.println("**************************************************************");
        test_1(0);
        System.out.println("--------------------------------------------------------------");
        test_1(1);
        System.out.println("--------------------------------------------------------------");
        test_1(2);
        System.out.println("--------------------------------------------------------------");
        test_1(3);

        System.out.println("**************************************************************");
        System.out.println("Test 2: Get All EMPLEADOS in 90s");
        System.out.println("**************************************************************");
        test_2(0);
        System.out.println("--------------------------------------------------------------");
        test_2(1);


        System.out.println("**************************************************************");
        System.out.println("Test 3: Get All EMPLEADOS 32 to 48 years old");
        System.out.println("**************************************************************");
        test_3(0);
        System.out.println("--------------------------------------------------------------");
        test_3(2);


        System.out.println("**************************************************************");
        System.out.println("Test 4: Get All EMPLEADOS in Department 1 to 1000 ");
        System.out.println("**************************************************************");
        test_4(0);
        System.out.println("--------------------------------------------------------------");
        test_4(3);

    }
}
