package case_1;

import common.*;

import java.sql.*;
import java.util.List;

public class Test_case_1 {

    private static Connection connection_db0 = null;

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
        List<Persona> result = Database.getPersonas(connection_db0,db0);
        time_end = System.currentTimeMillis();
        System.out.println("[Case 1] Tets 1: " + result.size() + " results");
        System.out.println("[Case 1] Test 1: " + ( time_end - time_start ) + " milliseconds");
    }

    private static void test_2(String msg){

        System.out.println("[Case 1] Test 2: " + msg + " cache");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        List<Proyecto> result = Database.getProyectos(connection_db0,db0);
        time_end = System.currentTimeMillis();
        System.out.println("[Case 1] Tets 2: " + result.size() + " results");
        System.out.println("[Case 1] Test 2: " + ( time_end - time_start ) + " milliseconds");
    }

    private static void test_3(String msg){

        System.out.println("[Case 1] Test 3: " + msg + " cache");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        List<Inventario> result = Database.getInventarios(connection_db0,db0);
        time_end = System.currentTimeMillis();
        System.out.println("[Case 1] Tets 3: " + result.size() + " results");
        System.out.println("[Case 1] Test 3: " + ( time_end - time_start ) + " milliseconds");
    }

    private static void test_4(String msg, int id_del){

        System.out.println("[Case 1] Test 4: " + msg + " cache");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        List<Empleado> result = Database.getEmpleadosFromDepartamento(connection_db0,db0,id_del);
        time_end = System.currentTimeMillis();
        System.out.println("[Case 1] Tets 4: " + result.size() + " results");
        System.out.println("[Case 1] Test 4: " + ( time_end - time_start ) + " milliseconds");
    }

    private static void test_5(String msg, int id_del){

        System.out.println("[Case 1] Test 5: " + msg + " cache");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        List<Proyecto> result = Database.getProyectosFromDepartamento(connection_db0,db0,id_del);
        time_end = System.currentTimeMillis();
        System.out.println("[Case 1] Tets 5: " + result.size() + " results");
        System.out.println("[Case 1] Test 5: " + ( time_end - time_start ) + " milliseconds");
    }

    private static void test_6(String msg, int id_del){

        System.out.println("[Case 1] Test 6: " + msg + " cache");
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        int result = Database.getHorasEstimadasProyectosFromDepartamento(connection_db0,db0, id_del);
        time_end = System.currentTimeMillis();
        System.out.println("[Case 1] Tets 6: " + result + " results");
        System.out.println("[Case 1] Test 6: " + ( time_end - time_start ) + " milliseconds");
    }

    public static void main(String[] args){

        System.out.println("**************************************************************");
        System.out.println("[Case 1] Test 1: Get All PERSONAs");
        System.out.println("**************************************************************");
        test_1("Without");
        System.out.println("--------------------------------------------------------------");
        test_1("With");

        System.out.println("**************************************************************");
        System.out.println("[Case 1] Test 2: Get All PROYECTOs");
        System.out.println("**************************************************************");
        test_2("Without");
        System.out.println("--------------------------------------------------------------");
        test_2("With");

        System.out.println("**************************************************************");
        System.out.println("[Case 1] Test 3: Get All INVENTARIOs");
        System.out.println("**************************************************************");
        test_3("Without");
        System.out.println("--------------------------------------------------------------");
        test_3("With");

        System.out.println("**************************************************************");
        System.out.println("[Case 1] Test 4: EMPLEADOs in a DELEGACION");
        System.out.println("**************************************************************");
        test_4("Without", 50);
        System.out.println("--------------------------------------------------------------");
        test_4("With", 50);

        System.out.println("**************************************************************");
        System.out.println("[Case 1] Test 5: PROYECTOs in a DELEGACION");
        System.out.println("**************************************************************");
        test_5("Without", 50);
        System.out.println("--------------------------------------------------------------");
        test_5("With", 50);

        System.out.println("**************************************************************");
        System.out.println("[Case 1] Test 6: HORASESTIMADAS from PROYECTOs in a DELEGACION");
        System.out.println("**************************************************************");
        test_6("Without", 50);
        System.out.println("--------------------------------------------------------------");
        test_6("With", 50);

    }
}
