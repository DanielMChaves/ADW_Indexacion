package common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    // Inserts
    public static void insertPuesto(Connection conn, String db, Puesto p){

        String query = "INSERT INTO `" + db + "`.`PUESTO` (`nombre`) ";
        query += "VALUES (?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, p.getNombre());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCategoria(Connection conn, String db, Categoria c){

        String query = "INSERT INTO `" + db + "`.`CATEGORIA` (`categoria`) ";
        query += "VALUES (?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, c.getCategoria());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertProyecto(Connection conn, String db, Proyecto p){

        String query = "INSERT INTO `" + db + "`.`PROYECTO` (`nombre`, `horas_estimadas`, `horas_reales`) ";
        query += "VALUES (?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, p.getNombre());
            sentence.setInt(2, p.getHoras_estimadas());
            sentence.setInt(3, p.getHoras_reales());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPersona(Connection conn, String db, Persona p){

        String query = "INSERT INTO `" + db + "`.`PERSONA` (`dni`, `nombre`, `apellido1`, `apellido2`, `genero`, `localidad`) ";
        query += "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, p.getDni());
            sentence.setString(2, p.getNombre());
            sentence.setString(3, p.getApellido1());
            sentence.setString(4, p.getApellido2());
            sentence.setBoolean(5, p.isGenero());
            sentence.setString(6, p.getLocalidad());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertInventario(Connection conn, String db, Inventario i){

        String query = "INSERT INTO `" + db + "`.`INVENTARIO` (`elemento`, `descripcion`, `id_categoria`, `precio`) ";
        query += "VALUES (?,?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, i.getElemento());
            sentence.setString(2, i.getDescripcion());
            sentence.setInt(3, i.getId_categoria());
            sentence.setDouble(4, i.getPrecio());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertEmpleado(Connection conn, String db, Empleado e){

        String query = "INSERT INTO `" + db + "`.`EMPLEADO` (`dni`, `id_puesto`, `id_dep`, `horas_semanales`, `salario`) ";
        query += "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, e.getDni());
            sentence.setInt(2, e.getId_puesto());
            sentence.setInt(3, e.getId_dep());
            sentence.setDouble(4, e.getHoras_semanales());
            sentence.setDouble(5, e.getSalario());
            sentence.execute();
            sentence.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertEmpleadoWithDepartment(Connection conn, String db, Empleado e){

        String query = "UPDATE `" + db + "`.`EMPLEADO` ";
        query += "SET `id_dep` = ? ";
        query += "WHERE `id_emp` = ? ";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setInt(1, e.getId_dep());
            sentence.setInt(2, e.getId_emp());
            sentence.executeUpdate();
            sentence.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertEmpleadoWithoutDepartment(Connection conn, String db, Empleado e){

        String query = "INSERT INTO `" + db + "`.`EMPLEADO` (`dni`, `id_puesto`, `horas_semanales`, `salario`) ";
        query += "VALUES (?,?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, e.getDni());
            sentence.setInt(2, e.getId_puesto());
            sentence.setDouble(3, e.getHoras_semanales());
            sentence.setDouble(4, e.getSalario());
            sentence.execute();
            sentence.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertRealiza(Connection conn, String db, R_realiza r){

        String query = "INSERT INTO `" + db + "`.`realiza` (`id_emp`, `id_proyecto`) ";
        query += "VALUES (?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setInt(1, r.getId_emp());
            sentence.setInt(2, r.getId_proyecto());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void insertDelegacion(Connection conn, String db, Delegacion d){

        String query = "INSERT INTO `" + db + "`.`DELEGACION` (`id_del`, `nombre`, `id_jefe`, `localizacion`) ";
        query += "VALUES (?,?,?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setInt(1, d.getId_del());
            sentence.setString(2, d.getNombre());
            sentence.setInt(3, d.getId_jefe());
            sentence.setString(4, d.getLocalizacion());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDepartamento(Connection conn, String db, Departamento d){

        String query = "INSERT INTO `" + db + "`.`DEPARTAMENTO` (`nombre`, `id_jefe`) ";
        query += "VALUES (?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setString(1, d.getNombre());
            sentence.setInt(2, d.getId_jefe());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPertenece(Connection conn, String db, R_pertenece p){

        String query = "INSERT INTO `" + db + "`.`pertenece` (`id_dep`, `id_del`) ";
        query += "VALUES (?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setInt(1, p.getId_dep());
            sentence.setInt(2, p.getId_del());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void insertTiene(Connection conn, String db, R_tiene t){

        String query = "INSERT INTO `" + db + "`.`tiene` (`id_dep`, `id_inv`) ";
        query += "VALUES (?,?)";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            sentence.setInt(1, t.getId_dep());
            sentence.setInt(2, t.getId_inv());
            sentence.execute();
            sentence.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    // Queries
    public static List<Persona> getPersonas(Connection conn, String db){

        List<Persona> result = new ArrayList<>();

        String query = "SELECT * ";
        query += "FROM " + db + ".PERSONA";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Persona p = new Persona();
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido1(rs.getString("apellido1"));
                p.setApellido2(rs.getString("apellido2"));
                p.setGenero(rs.getBoolean("genero"));
                p.setLocalidad(rs.getString("localidad"));
                p.setFecha_insercion(rs.getDate("fecha_insercion"));
                result.add(p);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
    public static Chatroom[] getChatrooms() {

        Chatroom[] result = null;

        String query = "SELECT * ";
        query += "FROM chatrooms.CHATROOM ";

        try {
            PreparedStatement sentence = connection.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            result = new Chatroom[rs.last() ? rs.getRow() : 0];
            int i = 0;

            rs.beforeFirst();

            while (rs.next()) {

                result[i] = new Chatroom()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setHandle_creator(rs.getString("handle_creator"))
                        .setCreate_date(rs.getTimestamp("create_date"));

                i++;
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }*/


}
