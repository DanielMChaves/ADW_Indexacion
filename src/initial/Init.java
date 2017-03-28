package initial;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Init {

    private static Connection connection_master = null;
    private static Connection connection_db0 = null;
    private static Connection connection_db1 = null;
    private static Connection connection_db2 = null;
    private static Connection connection_db3 = null;
    private static Connection connection_db4 = null;
    private static Statement sentence = null;

    private static String host, passwd, port, query, url, usr;

    private static final String db0 = "ADW0";
    private static final String db1 = "ADW1";
    private static final String db2 = "ADW2";
    private static final String db3 = "ADW3";
    private static final String db4 = "ADW4";

    private static final List<String> dbList = Arrays.asList(db0, db1, db2, db3, db4);
    private static final List<String> tableList = new ArrayList<>();

    static {

        tableList.add("");

        host = "localhost";
        usr = "root";
        passwd = "1qazxsw2";

        String driver = "com.mysql.jdbc.Driver";
        port = "3306";

        try {
            Class.forName(driver);
            url = "jdbc:mysql://" + host + ":" + port + "/";
            connection_master = DriverManager.getConnection(url, usr, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initCase1(){

        try {
            sentence = connection_master.createStatement();
            query = "DROP DATABASE IF EXISTS " + db0;
            sentence.execute(query);
            query = "CREATE DATABASE " + db0;
            sentence.executeUpdate(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        try {
            // Database 0
            connection_db0 = DriverManager.getConnection(url + db0, usr, passwd);
            sentence = connection_db0.createStatement();
            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));
        } catch (SQLException e) {
            //e.printStackTrace();
        }finally{
            try{
                if(sentence != null)
                    sentence.close();
                if(connection_db0 != null)
                    connection_db0.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initCase2(){

        try {
            sentence = connection_master.createStatement();
            for(int i = 1; i < dbList.size(); i++){
                query = "DROP DATABASE IF EXISTS " + dbList.get(i);
                sentence.execute(query);
                query = "CREATE DATABASE " + dbList.get(i);
                sentence.executeUpdate(query);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        try {
            // Database 1
            connection_db1 = DriverManager.getConnection(url + db1, usr, passwd);
            sentence = connection_db1.createStatement();

            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

            // Database 2
            connection_db2 = DriverManager.getConnection(url + db2, usr, passwd);
            sentence = connection_db2.createStatement();

            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

            // Database 3
            connection_db3 = DriverManager.getConnection(url + db3, usr, passwd);
            sentence = connection_db3.createStatement();

            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

            // Database 4
            connection_db4 = DriverManager.getConnection(url + db4, usr, passwd);
            sentence = connection_db4.createStatement();

            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

        } catch (SQLException e) {
            //e.printStackTrace();
        }finally{
            try{
                if(sentence != null)
                    sentence.close();
                if(connection_db1 != null)
                    connection_db1.close();
                if(connection_db2 != null)
                    connection_db2.close();
                if(connection_db3 != null)
                    connection_db3.close();
                if(connection_db4 != null)
                    connection_db4.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main (String[] args){

        System.out.println("SELECT ONE OPTION:");
        System.out.println("    (1) - CASE 1");
        System.out.println("    (2) - CASE 2");
        System.out.println("    (3) - CASE 1 & 2\n");
        System.out.print("SELECT OPTION: ");

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        if(option == 1){
            initCase1();
        }
        else if(option == 2){
            initCase2();
        }
        else if(option == 3){
            initCase1();
            initCase2();
        }
        else {

        }

        System.out.println("\nINIT FINISH!!!");
    }
}
