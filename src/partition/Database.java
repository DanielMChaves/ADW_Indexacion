package partition;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    // Inserts
    public static void insertEmpleado(Connection conn, String db, int table, Empleado e){

        String query = "INSERT INTO `" + db + "`.`EMPLEADO" + table + "` " +
                "(`id_empleado`, `nombre`, `apellido1`, `apellido2`, `dni`, `genero`, `edad`, " +
                "`id_puesto`, `id_departamento`, `horas_semanales`, `salario`, `fecha_contratacion`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setInt(1,e.getId_empleado());
            sentence.setString(2, e.getNombre());
            sentence.setString(3, e.getApellido1());
            sentence.setString(4, e.getApellido2());
            sentence.setString(5, e.getDni());
            sentence.setBoolean(6, e.isGenero());
            sentence.setInt(7, e.getEdad());
            sentence.setInt(8, e.getId_puesto());
            sentence.setInt(9, e.getId_departamento());
            sentence.setInt(10, e.getHoras_semanales());
            sentence.setInt(11, e.getSalario());
            sentence.setTimestamp(12, e.getFecha_contratacion());
            sentence.execute();
            sentence.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Queries
    public static ArrayList<Empleado> getEmpleados(Connection conn, int table){

        ArrayList<Empleado> result = new ArrayList<>();

        String query = "SELECT * FROM EMPLEADO" + table;

        if(table == 1)
            query += " PARTITION (p0,p1,p2)";
        if(table == 2 || table == 3)
            query += " PARTITION (p0,p1,p2,p3)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido1(rs.getString("apellido1"));
                e.setApellido2(rs.getString("apellido2"));
                e.setDni(rs.getString("dni"));
                e.setGenero(rs.getBoolean("genero"));
                e.setEdad(rs.getInt("edad"));
                e.setId_puesto(rs.getInt("id_puesto"));
                e.setId_departamento(rs.getInt("id_departamento"));
                e.setHoras_semanales(rs.getInt("horas_semanales"));
                e.setSalario(rs.getInt("salario"));
                e.setFecha_contratacion(rs.getTimestamp("fecha_contratacion"));
                result.add(e);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Empleado> getEmpleados90(Connection conn, int table){

        ArrayList<Empleado> result = new ArrayList<>();

        String query;

        if(table == 0)
            query = "SELECT * FROM EMPLEADO" + table + " " +
                    "WHERE fecha_contratacion BETWEEN '1990-01-01' AND '1999-12-31' ";
        else
            query = "SELECT * FROM EMPLEADO" + table + " PARTITION (p0,p1,p2)" +
                    "WHERE fecha_contratacion BETWEEN '1990-01-01' AND '1999-12-31' ";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido1(rs.getString("apellido1"));
                e.setApellido2(rs.getString("apellido2"));
                e.setDni(rs.getString("dni"));
                e.setGenero(rs.getBoolean("genero"));
                e.setEdad(rs.getInt("edad"));
                e.setId_puesto(rs.getInt("id_puesto"));
                e.setId_departamento(rs.getInt("id_departamento"));
                e.setHoras_semanales(rs.getInt("horas_semanales"));
                e.setSalario(rs.getInt("salario"));
                e.setFecha_contratacion(rs.getTimestamp("fecha_contratacion"));
                result.add(e);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Empleado> getEmpleados32_48(Connection conn, int table){

        ArrayList<Empleado> result = new ArrayList<>();

        String query;

        if(table == 0)
            query = "SELECT * FROM EMPLEADO" + table + " " +
                    "WHERE edad BETWEEN 32 AND 48";
        else
            query = "SELECT * FROM EMPLEADO" + table + " PARTITION (p0,p1,p2,p3)" +
                    "WHERE edad BETWEEN 32 AND 48";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido1(rs.getString("apellido1"));
                e.setApellido2(rs.getString("apellido2"));
                e.setDni(rs.getString("dni"));
                e.setGenero(rs.getBoolean("genero"));
                e.setEdad(rs.getInt("edad"));
                e.setId_puesto(rs.getInt("id_puesto"));
                e.setId_departamento(rs.getInt("id_departamento"));
                e.setHoras_semanales(rs.getInt("horas_semanales"));
                e.setSalario(rs.getInt("salario"));
                e.setFecha_contratacion(rs.getTimestamp("fecha_contratacion"));
                result.add(e);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Empleado> getEmpleadosDepartment1_1000(Connection conn, int table){

        ArrayList<Empleado> result = new ArrayList<>();

        String query;

        if(table == 0)
            query = "SELECT * FROM EMPLEADO" + table + " " +
                    "WHERE id_departamento BETWEEN 1 AND 1000";
        else
            query = "SELECT * FROM EMPLEADO" + table + " PARTITION (p0,p1,p2,p3)" +
                    "WHERE id_departamento BETWEEN 1 AND 1000";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido1(rs.getString("apellido1"));
                e.setApellido2(rs.getString("apellido2"));
                e.setDni(rs.getString("dni"));
                e.setGenero(rs.getBoolean("genero"));
                e.setEdad(rs.getInt("edad"));
                e.setId_puesto(rs.getInt("id_puesto"));
                e.setId_departamento(rs.getInt("id_departamento"));
                e.setHoras_semanales(rs.getInt("horas_semanales"));
                e.setSalario(rs.getInt("salario"));
                e.setFecha_contratacion(rs.getTimestamp("fecha_contratacion"));
                result.add(e);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Auxiliar
    public static String getPartition(int index){

        String type;

        switch (index){
            case 0:
                type = "Original";
                break;
            case 1:
                type = "Date";
                break;
            case 2:
                type = "Age";
                break;
            case 3:
                type = "Department";
                break;
            default:
                type = "Error";
        }

        return type;

    }
}
