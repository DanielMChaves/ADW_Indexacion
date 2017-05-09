package partition;

import java.sql.*;

public class Database {

    // Inserts
    public static void insertEmpleado(Connection conn, String db, int table, Empleado e){

        String query = "INSERT INTO `" + db + "`.`EMPLEADO" + table + "` " +
                "(`nombre`, `apellido1`, `apellido2`, `dni`, `genero`, `edad`, " +
                "`id_puesto`, `id_departamento`, `horas_semanales`, `salario`, `fecha_contratacion`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, e.getNombre());
            sentence.setString(2, e.getApellido1());
            sentence.setString(3, e.getApellido2());
            sentence.setString(4, e.getDni());
            sentence.setBoolean(5, e.isGenero());
            sentence.setInt(6, e.getEdad());
            sentence.setInt(7, e.getId_puesto());
            sentence.setInt(8, e.getId_departamento());
            sentence.setInt(9, e.getHoras_semanales());
            sentence.setInt(10, e.getSalario());
            sentence.setTimestamp(11, e.getFecha_contratacion());
            sentence.execute();
            sentence.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
