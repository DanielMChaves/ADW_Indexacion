package partition;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Init {

    private static Connection connection_master = null;
    private static Connection connection_db0 = null;

    private static Statement sentence = null;

    private static String host, passwd, port, query, url, usr;

    private static final String db0 = "pruebaE1";

    private static final int num_Puestos = 1000000;
    private static final int num_Empleado = 1000000;
    private static final int num_Departamento = 1000000;

    private static List<String> dniList = new ArrayList<>();
    private static List<String> tableList = new ArrayList<>();

    static {

        // TABLA ORIGINAL
        tableList.add("CREATE TABLE EMPLEADO0(\n" +
                "  id_empleado INT,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  apellido1 VARCHAR(20) NOT NULL,\n" +
                "  apellido2 VARCHAR(20) ,\n" +
                "  dni VARCHAR(20) NOT NULL DEFAULT '0000',\n" +
                "  genero BOOLEAN NOT NULL DEFAULT TRUE,\n" +
                "  edad INT NOT NULL DEFAULT 0,\n" +
                "  id_puesto INT NOT NULL DEFAULT 0,\n" +
                "  id_departamento INT NOT NULL DEFAULT 0,\n" +
                "  horas_semanales INT NOT NULL DEFAULT 8,\n" +
                "  salario INT NOT NULL DEFAULT 0,\n" +
                "  fecha_contratacion DATE NOT NULL DEFAULT '9999-12-31'\n" +
                ");");

        // PARTICION POR FECHA
        tableList.add("CREATE TABLE EMPLEADO1(\n" +
                "  id_empleado INT,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  apellido1 VARCHAR(20) NOT NULL,\n" +
                "  apellido2 VARCHAR(20) ,\n" +
                "  dni VARCHAR(20) NOT NULL DEFAULT '0000',\n" +
                "  genero BOOLEAN NOT NULL DEFAULT TRUE,\n" +
                "  edad INT NOT NULL DEFAULT 0,\n" +
                "  id_puesto INT NOT NULL DEFAULT 0,\n" +
                "  id_departamento INT NOT NULL DEFAULT 0,\n" +
                "  horas_semanales INT NOT NULL DEFAULT 8,\n" +
                "  salario INT NOT NULL DEFAULT 0,\n" +
                "  fecha_contratacion DATE NOT NULL DEFAULT '9999-12-31'\n" +
                ");");

        tableList.add("ALTER TABLE EMPLEADO1\n" +
                " PARTITION BY RANGE(YEAR(fecha_contratacion))(\n" +
                " PARTITION p0 VALUES LESS THAN (1970),  \n" +
                " PARTITION p1 VALUES LESS THAN (1980),  \n" +
                " PARTITION p2 VALUES LESS THAN MAXVALUE \n" +
                "); ");

        // PARTICION POR EDAD
        tableList.add("CREATE TABLE EMPLEADO2(\n" +
                "  id_empleado INT,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  apellido1 VARCHAR(20) NOT NULL,\n" +
                "  apellido2 VARCHAR(20) ,\n" +
                "  dni VARCHAR(20) NOT NULL DEFAULT '0000',\n" +
                "  genero BOOLEAN NOT NULL DEFAULT TRUE,\n" +
                "  edad INT NOT NULL DEFAULT 0,\n" +
                "  id_puesto INT NOT NULL DEFAULT 0,\n" +
                "  id_departamento INT NOT NULL DEFAULT 0,\n" +
                "  horas_semanales INT NOT NULL DEFAULT 8,\n" +
                "  salario INT NOT NULL DEFAULT 0,\n" +
                "  fecha_contratacion DATE NOT NULL DEFAULT '9999-12-31'\n" +
                ");");

        tableList.add("ALTER TABLE EMPLEADO2\n" +
                "PARTITION BY RANGE (edad) (\n" +
                "\tPARTITION p0 VALUES LESS THAN (18),\n" +
                "\tPARTITION p1 VALUES LESS THAN (30),\n" +
                "\tPARTITION p2 VALUES LESS THAN (50),\n" +
                "\tPARTITION p3 VALUES LESS THAN MAXVALUE\n" +
                ");");

        // PARTICION POR DEPARTMENT
        tableList.add("CREATE TABLE EMPLEADO3(\n" +
                "  id_empleado INT,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  apellido1 VARCHAR(20) NOT NULL,\n" +
                "  apellido2 VARCHAR(20) ,\n" +
                "  dni VARCHAR(20) NOT NULL DEFAULT '0000',\n" +
                "  genero BOOLEAN NOT NULL DEFAULT TRUE,\n" +
                "  edad INT NOT NULL DEFAULT 0,\n" +
                "  id_puesto INT NOT NULL DEFAULT 0,\n" +
                "  id_departamento INT NOT NULL DEFAULT 0,\n" +
                "  horas_semanales INT NOT NULL DEFAULT 8,\n" +
                "  salario INT NOT NULL DEFAULT 0,\n" +
                "  fecha_contratacion DATE NOT NULL DEFAULT '9999-12-31'\n" +
                ");");

        tableList.add("ALTER TABLE EMPLEADO3\n" +
                "PARTITION BY RANGE (id_departamento)\n" +
                "(PARTITION p0 VALUES LESS THAN (" + num_Departamento/4 + "),\n" +
                "PARTITION p1 VALUES LESS THAN (" + num_Departamento/3 + "),\n" +
                "PARTITION p2 VALUES LESS THAN (" + num_Departamento/2 + "),\n" +
                "PARTITION p3 VALUES LESS THAN MAXVALUE\n" +
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

    public static void init(){

        try {
            System.out.println("[" + db0 + "] Creating Database ...");
            sentence = connection_master.createStatement();
            query = "DROP DATABASE IF EXISTS " + db0;
            sentence.execute(query);
            query = "CREATE DATABASE " + db0;
            sentence.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection_db0 = DriverManager.getConnection(url + db0, usr, passwd);
            sentence = connection_db0.createStatement();

            System.out.println("[" + db0 + "] Creating tables ...");
            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

            System.out.println("[" + db0 + "] Creating Inicial Content ...");
            initContentCase1();

        } catch (SQLException e) {
            e.printStackTrace();
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

    public static void initContentCase1(){

        System.out.println("[" + db0 + "] Creating EMPLEADOs");

        dniList.clear();
        for(int i = 0; i < num_Empleado; i++){
            dniList.add("dni_" + (i+1));
        }

        for(int i = 0; i < num_Empleado; i++){

            int random = ThreadLocalRandom.current().nextInt(0, dniList.size());

            Empleado e = new Empleado();

            e.setId_empleado(i+1);
            e.setNombre("nombre_" + (i+1));
            e.setApellido1("apellido1_" + (i+1));
            e.setApellido2("apellido2_" + (i+1));
            e.setDni(dniList.get(random));
            dniList.remove(random);

            if(random == 0)
                e.setGenero(false);
            else
                e.setGenero(true);

            e.setEdad(ThreadLocalRandom.current().nextInt(18, 86));
            e.setId_puesto(ThreadLocalRandom.current().nextInt(1, num_Puestos));
            e.setId_departamento(ThreadLocalRandom.current().nextInt(1, num_Departamento));
            e.setHoras_semanales(100);
            e.setSalario(100);
            e.setFecha_contratacion(randomDate());

            Database.insertEmpleado(connection_db0,db0,0,e);
            Database.insertEmpleado(connection_db0,db0,1,e);
            Database.insertEmpleado(connection_db0,db0,2,e);
            Database.insertEmpleado(connection_db0,db0,3,e);
        }
    }

    public static Timestamp randomDate(){

        long long_fecha1 = ThreadLocalRandom.current().nextLong(1000, 1000000);
        long long_fecha2 = ThreadLocalRandom.current().nextLong(1000, 1000000);
        String fecha = Long.toString(long_fecha1) + Long.toString(long_fecha2);

        return new Timestamp(Long.parseLong(fecha));
    }

    public static void main (String[] args){
        init();
        System.out.println("\nInit Finish!!!");
    }
}