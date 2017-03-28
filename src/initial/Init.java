package initial;

import java.sql.*;
import java.util.Scanner;

public class Init {

    private static Connection connection_master = null;
    private static Connection connection_db0 = null;
    private static Connection connection_db1 = null;
    private static Connection connection_db2 = null;
    private static Connection connection_db3 = null;
    private static Connection connection_db4 = null;

    static {
        String host = "localhost";
        String usr = "root";
        String passwd = "1qazxsw2";

        // Obtener conexion
        String driver = "com.mysql.jdbc.Driver";
        String port = "3306";
        String db0 = "ADW0";
        String db1 = "ADW1";
        String db2 = "ADW2";
        String db3 = "ADW3";
        String db4 = "ADW4";

        try {
            Class.forName(driver);
            String url = "jdbc:mysql://" + host + ":" + port + "/";
            connection_master = DriverManager.getConnection(url, usr, passwd);
            connection_db0 = DriverManager.getConnection(url + db0, usr, passwd);
            connection_db1 = DriverManager.getConnection(url + db1, usr, passwd);
            connection_db2 = DriverManager.getConnection(url + db2, usr, passwd);
            connection_db3 = DriverManager.getConnection(url + db3, usr, passwd);
            connection_db4 = DriverManager.getConnection(url + db4, usr, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
        }
    }

    // INSERT
    public static void insertUser() {

        String query = "INSERT INTO `chatrooms`.`USER` (`handle`, `password`) ";
        query += "VALUES (?,?)";

        try {
            PreparedStatement sentence = connection_db0.prepareStatement(query);
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initCase1(){

    }

    public static void initCase2(){

    }

    public static void main (String[] args){

        //System.out.println("ADW");
        System.out.println("SE");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
    }

}
