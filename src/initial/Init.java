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

        tableList.add("CREATE TABLE PUESTO(\n" +
                "  id_puesto INT PRIMARY KEY,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  fecha_inserccion DATE NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE CATEGORIA(\n" +
                "  id_categoria INT PRIMARY KEY,\n" +
                "  categoria VARCHAR(20) NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE PROYECTO(\n" +
                "  id_proyecto INT  PRIMARY KEY,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  horas_estimadas INT NOT NULL,\n" +
                "  horas_reales INT NOT NULL,\n" +
                "  fecha_entrega DATE NOT NULL,\n" +
                "  fecha_inserccion DATE NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE PERSONA (\n" +
                "  dni VARCHAR(20) PRIMARY KEY ,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  apellido1 VARCHAR(20) NOT NULL,\n" +
                "  apellido2 VARCHAR(20),\n" +
                "  genero BOOLEAN NOT NULL,\n" +
                "  localidad VARCHAR(32) NOT NULL,\n" +
                "  fecha_insercion DATE NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE INVENTARIO(\n" +
                "  id_inv INT PRIMARY KEY,\n" +
                "  elemento VARCHAR(40) NOT NULL,\n" +
                "  descripcion VARCHAR(255) NOT NULL,\n" +
                "  id_categoria INT NOT NULL,\n" +
                " CONSTRAINT inven\n" +
                " FOREIGN KEY (id_categoria)\n" +
                " REFERENCES CATEGORIA(id_categoria)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                " precio DECIMAL(8,2) NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE EMPLEADO(\n" +
                "  id_emp INT PRIMARY KEY,\n" +
                "  dni VARCHAR(20) NOT NULL,\n" +
                " CONSTRAINT emple\n" +
                " FOREIGN KEY (dni)\n" +
                " REFERENCES PERSONA(dni)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  id_puesto INT NOT NULL,\n" +
                " CONSTRAINT emple2\n" +
                " FOREIGN KEY (id_puesto)\n" +
                " REFERENCES PUESTO(id_puesto)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  id_dep INT NOT NULL,\n" +
                "  horas_semanales INT NOT NULL,\n" +
                "  salario DECIMAL(8,4) NOT NULL,\n" +
                "  fecha_inserccion DATE NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE realiza(\n" +
                "  id_emp INT NOT NULL,\n" +
                "  id_proyecto INT NOT NULL,\n" +
                "  PRIMARY KEY(id_emp,id_proyecto),\n" +
                " CONSTRAINT reali\n" +
                " FOREIGN KEY (id_emp)\n" +
                " REFERENCES EMPLEADO(id_emp)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                " CONSTRAINT reali2\n" +
                " FOREIGN KEY (id_proyecto)\n" +
                " REFERENCES ADW0.PROYECTO(id_proyecto)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                ");");
        tableList.add("CREATE TABLE DELEGACION(\n" +
                "  id_del INT PRIMARY KEY,\n" +
                "  nombre VARCHAR(20)  NOT NULL,\n" +
                "  id_jefe INT,\n" +
                "  localizacion VARCHAR(40) NOT NULL,\n" +
                "  fecha_inserccion DATE NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE DEPARTAMENTO(\n" +
                "  id_dep INT PRIMARY KEY,\n" +
                "  nombre VARCHAR(40) NOT NULL,\n" +
                "  id_jefe INT NOT NULL,\n" +
                " CONSTRAINT dept\n" +
                " FOREIGN KEY (id_jefe)\n" +
                " REFERENCES EMPLEADO(id_emp)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  fecha_inserccion DATE NOT NULL\n" +
                ");\n");
        tableList.add("ALTER TABLE EMPLEADO ADD FOREIGN KEY (id_dep)  " +
                "REFERENCES DEPARTAMENTO(id_dep) ON UPDATE CASCADE;");
        tableList.add("CREATE TABLE pertenece(\n" +
                "  id_dep INT NOT NULL,\n" +
                "  id_del INT NOT NULL,\n" +
                "  PRIMARY KEY (id_dep, id_del),\n" +
                " CONSTRAINT pert\n" +
                " FOREIGN KEY (id_dep)\n" +
                " REFERENCES DEPARTAMENTO(id_dep)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                " CONSTRAINT pert2\n" +
                " FOREIGN KEY (id_del)\n" +
                " REFERENCES DELEGACION(id_del)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                ");");
        tableList.add("CREATE TABLE IF NOT EXISTS tiene(\n" +
                "  id_dep INT NOT NULL,\n" +
                "  id_inv INT NOT NULL,\n" +
                "  PRIMARY KEY (id_dep, id_inv),\n" +
                "  fecha_adquisicion DATE\n" +
                ");");

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
