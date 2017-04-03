package initial;

import common.*;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    // Valores de prueba
    private static final int num_Puestos = 100;
    private static final int num_Categoria = 100;
    private static final int num_Proyecto = 100;
    private static final int num_Persona = 100;
    private static final int num_Inventario = 100;
    private static final int num_Empleado = 100;
    private static final int num_Delegacion = 100;
    private static final int num_Departamento = 100;

    // Valores Reales
    /*private static final int num_Puestos = 100;
    private static final int num_Categoria = 10;
    private static final int num_Proyecto = 500000;
    private static final int num_Persona = 200000;
    private static final int num_Inventario = 100000;
    private static final int num_Empleado = 100000;
    private static final int num_Delegacion = 100;
    private static final int num_Departamento = 1000;*/

    private static List<String> dniList = new ArrayList<>();
    private static List<Integer> empleadoList = new ArrayList<Integer>();

    static {

        tableList.add("CREATE TABLE PUESTO(\n" +
                "  id_puesto INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  nombre VARCHAR(20) NOT NULL" +
                ");");
        tableList.add("CREATE TABLE CATEGORIA(\n" +
                "  id_categoria INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  categoria VARCHAR(20) NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE PROYECTO(\n" +
                "  id_proyecto INT  PRIMARY KEY AUTO_INCREMENT,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  horas_estimadas INT NOT NULL,\n" +
                "  horas_reales INT NOT NULL,\n" +
                "  fecha_entrega TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                ");");
        tableList.add("CREATE TABLE PERSONA (\n" +
                "  dni VARCHAR(20) PRIMARY KEY,\n" +
                "  nombre VARCHAR(20) NOT NULL,\n" +
                "  apellido1 VARCHAR(20) NOT NULL,\n" +
                "  apellido2 VARCHAR(20),\n" +
                "  genero BOOLEAN NOT NULL,\n" +
                "  localidad VARCHAR(32) NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE INVENTARIO(\n" +
                "  id_inv INT PRIMARY KEY AUTO_INCREMENT,\n" +
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
                "  id_emp INT PRIMARY KEY AUTO_INCREMENT,\n" +
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
                "  id_dep INT,\n" +
                "  horas_semanales INT NOT NULL,\n" +
                "  salario DECIMAL(8,4) NOT NULL\n" +
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
                " REFERENCES PROYECTO(id_proyecto)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                ");");
        tableList.add("CREATE TABLE DELEGACION(\n" +
                "  id_del INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  nombre VARCHAR(20)  NOT NULL,\n" +
                "  id_jefe INT NOT NULL,\n" +
                " CONSTRAINT del\n" +
                " FOREIGN KEY (id_jefe)\n" +
                " REFERENCES EMPLEADO(id_emp)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  localizacion VARCHAR(40) NOT NULL\n" +
                ");");
        tableList.add("CREATE TABLE DEPARTAMENTO(\n" +
                "  id_dep INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  nombre VARCHAR(40) NOT NULL,\n" +
                "  id_jefe INT NOT NULL,\n" +
                " CONSTRAINT dept\n" +
                " FOREIGN KEY (id_jefe)\n" +
                " REFERENCES EMPLEADO(id_emp)\n" +
                " ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
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
        tableList.add("CREATE TABLE tiene(\n" +
                "  id_dep INT NOT NULL,\n" +
                "  id_inv INT NOT NULL,\n" +
                "  PRIMARY KEY (id_dep, id_inv)\n" +
                ");");
        tableList.add("ALTER TABLE tiene ADD FOREIGN KEY (id_dep)  " +
                "REFERENCES DEPARTAMENTO(id_dep) ON UPDATE CASCADE;");
        tableList.add("ALTER TABLE tiene ADD FOREIGN KEY (id_inv)  " +
                "REFERENCES INVENTARIO(id_inv) ON UPDATE CASCADE;");

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
            //e.printStackTrace();
        }

        try {
            // Database 0
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

    public static void initCase2(){

        try {
            sentence = connection_master.createStatement();
            for(int i = 1; i < dbList.size(); i++){
                System.out.println("Creating Database " + dbList.get(i) + " ...");
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

            System.out.println("[" + db1 + "] Creating tables ...");
            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

            // Database 2
            connection_db2 = DriverManager.getConnection(url + db2, usr, passwd);
            sentence = connection_db2.createStatement();

            System.out.println("[" + db2 + "] Creating tables ...");
            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

            // Database 3
            connection_db3 = DriverManager.getConnection(url + db3, usr, passwd);
            sentence = connection_db3.createStatement();

            System.out.println("[" + db3 + "] Creating tables ...");
            for(int i = 0; i < tableList.size(); i++)
                sentence.executeUpdate(tableList.get(i));

            // Database 4
            connection_db4 = DriverManager.getConnection(url + db4, usr, passwd);
            sentence = connection_db4.createStatement();

            System.out.println("[" + db4 + "] Creating tables in ...");
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

    public static void initContentCase1(){

        System.out.println("[" + db0 + "] Creating PUESTOs ...");

        for(int i = 0; i < num_Puestos; i++){
            Puesto p = new Puesto();
            p.setNombre("puesto_" + (i+1));
            Database.insertPuesto(connection_db0,db0,p);
        }

        System.out.println("[" + db0 + "] Creating CATEGORIAs in ...");

        for(int i = 0; i < num_Categoria; i++){
            Categoria c = new Categoria();
            c.setCategoria("categoria_" + (i+1));
            Database.insertCategoria(connection_db0,db0,c);
        }

        System.out.println("[" + db0 + "] Creating PROYECTOs in ...");

        for(int i = 0; i < num_Proyecto; i++){
            Proyecto p = new Proyecto();
            p.setNombre("proyecto_" + (i+1));
            p.setHoras_estimadas(100);
            p.setHoras_reales(100);
            Database.insertProyecto(connection_db0,db0,p);
        }

        System.out.println("[" + db0 + "] Creating PERSONAs in ...");

        for(int i = 0; i < num_Persona; i++){
            Persona p = new Persona();
            p.setDni("dni_" + (i+1));
            p.setNombre("nombre_" + (i+1));
            p.setApellido1("apellido1_" + (i+1));
            p.setApellido2("apellido2_" + (i+1));
            p.setGenero(true);
            p.setLocalidad("localidad_persona_" + (i+1));
            Database.insertPersona(connection_db0,db0,p);
        }

        System.out.println("[" + db0 + "] Creating INVENTARIOs in ...");

        for(int i = 0; i < num_Inventario; i++){
            Inventario in = new Inventario();
            in.setElemento("elemento_" + (i+1));
            in.setDescripcion("descripcion_elemento_" + (i+1));
            in.setId_categoria(ThreadLocalRandom.current().nextInt(1, num_Categoria));
            in.setPrecio(100);
            Database.insertInventario(connection_db0,db0,in);
        }

        System.out.println("[" + db0 + "] Creating EMPLEADOs without id_dep in ...");

        dniList.clear();
        for(int i = 0; i < num_Persona; i++){
            dniList.add("dni_" + (i+1));
        }

        for(int i = 0; i < num_Empleado; i++){

            int random = ThreadLocalRandom.current().nextInt(0, dniList.size());

            Empleado e = new Empleado();
            e.setDni(dniList.get(random));
            dniList.remove(random);
            e.setId_puesto(ThreadLocalRandom.current().nextInt(1, num_Puestos));
            e.setHoras_semanales(100);
            e.setSalario(100);
            Database.insertEmpleadoWithoutDepartment(connection_db0,db0,e);
        }

        System.out.println("[" + db0 + "] Creating realiza in ...");

        for(int i = 0; i < num_Proyecto; i++){
            R_realiza r = new R_realiza();
            r.setId_emp(ThreadLocalRandom.current().nextInt(1, num_Empleado));
            r.setId_proyecto(ThreadLocalRandom.current().nextInt(1, num_Proyecto));
            Database.insertRealiza(connection_db0,db0,r);
        }

        System.out.println("[" + db0 + "] Creating DELEGACIONes in ...");

        empleadoList.clear();
        for(int i = 0; i < num_Empleado; i++){
            empleadoList.add(i+1);
        }

        for(int i = 0; i < num_Delegacion; i++){

            int random = ThreadLocalRandom.current().nextInt(0, empleadoList.size());

            Delegacion d = new Delegacion();
            d.setNombre("delegacion_" + (i+1));
            d.setId_jefe(empleadoList.get(random));
            empleadoList.remove(random);
            d.setLocalizacion("localidad_delegacion_" + (i+1));
            Database.insertDelegacion(connection_db0,db0,d);
        }

        System.out.println("[" + db0 + "] Creating DEPARTAMENTos in ...");

        empleadoList.clear();
        for(int i = 0; i < num_Empleado; i++){
            empleadoList.add(i+1);
        }

        for(int i = 0; i < num_Departamento; i++){

            int random = ThreadLocalRandom.current().nextInt(0, empleadoList.size());

            Departamento d = new Departamento();
            d.setNombre("departamento_" + (i+1));
            d.setId_jefe(empleadoList.get(random));
            empleadoList.remove(random);
            Database.insertDepartamento(connection_db0,db0,d);
        }

        System.out.println("[" + db0 + "] Updating EMPLEADOs in ...");

        for(int i = 0; i < num_Empleado; i++){
            Empleado e = new Empleado();
            e.setId_emp(i+1);
            e.setId_dep(ThreadLocalRandom.current().nextInt(1, num_Departamento));
            Database.insertEmpleadoWithDepartment(connection_db0,db0,e);
        }

        System.out.println("[" + db0 + "] Creating pertenece in ...");

        for(int i = 0; i < num_Departamento; i++){
            R_pertenece r = new R_pertenece();
            r.setId_dep(ThreadLocalRandom.current().nextInt(1, num_Departamento));
            r.setId_del(ThreadLocalRandom.current().nextInt(1, num_Delegacion));
            Database.insertPertenece(connection_db0,db0,r);
        }

        System.out.println("[" + db0 + "] Creating tiene in ...");

        for(int i = 0; i < num_Proyecto; i++){
            R_tiene r = new R_tiene();
            r.setId_dep(ThreadLocalRandom.current().nextInt(1, num_Departamento));
            r.setId_inv(ThreadLocalRandom.current().nextInt(1, num_Inventario));
            Database.insertTiene(connection_db0,db0,r);
        }

    }

    public static void main (String[] args){

        /*
        System.out.println("SELECT ONE OPTION:");
        System.out.println("    (1) - CASE 1");
        System.out.println("    (2) - CASE 2");
        System.out.println("    (3) - CASE 1 & 2\n");
        System.out.print("SELECT OPTION: ");

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        System.out.println("");

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
        */

        initCase1();

        System.out.println("\nInit Finish!!!");
    }
}