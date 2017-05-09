package partition;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Init {

    private static Connection connection_master = null;
    private static Connection connection_db0 = null;

    private static Statement sentence = null;

    private static String host, passwd, port, query, url, usr;

    private static final String db0 = "adw0";

    private static final int num_Puestos = 100;
    private static final int num_Empleado = 100;
    private static final int num_Departamento = 100;

    private static List<String> dniList = new ArrayList<>();
    private static List<String> tableList = new ArrayList<>();

    static {

        tableList.add("CREATE TABLE EMPLEADO0(\n" +
                "  id_empleado INT PRIMARY KEY auto_increment,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  apellido1 VARCHAR(20) NOT NULL,\n" +
                "  apellido2 VARCHAR(20),\n" +
                "  dni VARCHAR(20) NOT NULL,\n" +
                "  genero BOOLEAN NOT NULL,\n" +
                "  edad INT NOT NULL,\n" +
                "  id_puesto INT NOT NULL,\n" +
                "  id_departamento INT NOT NULL,\n" +
                "  horas_semanales INT NOT NULL,\n" +
                "  salario INT NOT NULL,\n" +
                "  fecha_contratacion DATE NOT NULL\n" +
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
        }
    }

    public static Timestamp randomDate(){

        long fecha1 = ThreadLocalRandom.current().nextLong(1000000, 1000000000);
        long fecha2 = ThreadLocalRandom.current().nextLong(1000000, 1000000000);
        long a = fecha1 * 6 + fecha2 * 7 + fecha1 * 8 + fecha2 * 9 + fecha1 * 6 + fecha2 * 7 + fecha1 * 8 + fecha2 * 9;
        // random date between the range

        return new Timestamp(a^512);
    }

    public static void main (String[] args){
        initCase1();
        System.out.println("\nInit Finish!!!");
    }
}