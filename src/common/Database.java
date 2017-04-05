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
    public static List<Persona> getPersonas(Connection conn){

        List<Persona> result = new ArrayList<>();

        String query = "SELECT * " +
                "FROM PERSONA";

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
                result.add(p);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Proyecto> getProyectos(Connection conn){

        List<Proyecto> result = new ArrayList<>();

        String query = "SELECT * " +
                "FROM PROYECTO";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Proyecto p = new Proyecto();
                p.setId_proyecto(rs.getInt("id_proyecto"));
                p.setNombre(rs.getString("nombre"));
                p.setHoras_estimadas(rs.getInt("horas_estimadas"));
                p.setHoras_reales(rs.getInt("horas_reales"));
                p.setFecha_entrega(rs.getDate("fecha_entrega"));
                result.add(p);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Inventario> getInventarios(Connection conn){

        List<Inventario> result = new ArrayList<>();

        String query = "SELECT * " +
                "FROM INVENTARIO";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Inventario i = new Inventario();
                i.setId_inv(rs.getInt("id_inv"));
                i.setElemento(rs.getString("elemento"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setId_categoria(rs.getInt("id_categoria"));
                i.setPrecio(rs.getDouble("precio"));
                result.add(i);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Empleado> getEmpleadosFromDepartamento(Connection conn, int id_del){

        List<Empleado> result = new ArrayList<>();

        String query = "SELECT e.id_emp, e.dni, e.id_puesto, e.id_dep, e.horas_semanales, e.salario\n" +
                "FROM DELEGACION del, DEPARTAMENTO dep, EMPLEADO e, pertenece p\n" +
                "WHERE del.id_del = p.id_del\n" +
                "AND p.id_dep = dep.id_dep\n" +
                "AND dep.id_dep = e.id_dep\n" +
                "AND del.id_del = " + id_del;

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Empleado e = new Empleado();
                e.setId_emp(rs.getInt("id_emp"));
                e.setDni(rs.getString("dni"));
                e.setId_puesto(rs.getInt("id_puesto"));
                e.setId_dep(rs.getInt("id_dep"));
                e.setHoras_semanales(rs.getInt("horas_semanales"));
                e.setSalario(rs.getDouble("salario"));
                result.add(e);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Proyecto> getProyectosFromDepartamento(Connection conn, int id_del){

        List<Proyecto> result = new ArrayList<>();

        String query = "SELECT pro.id_proyecto, pro.nombre, pro.horas_estimadas, pro.horas_reales, pro.fecha_entrega\n" +
                "FROM DELEGACION del, DEPARTAMENTO dep, EMPLEADO e, pertenece p, PROYECTO pro, realiza r\n" +
                "WHERE del.id_del = p.id_del\n" +
                "AND p.id_dep = dep.id_dep\n" +
                "AND dep.id_dep = e.id_dep\n" +
                "AND e.id_emp = r.id_emp\n" +
                "AND r.id_proyecto = pro.id_proyecto\n" +
                "AND del.id_del = " + id_del;

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Proyecto p = new Proyecto();
                p.setId_proyecto(rs.getInt("id_proyecto"));
                p.setNombre(rs.getString("nombre"));
                p.setHoras_estimadas(rs.getInt("horas_estimadas"));
                p.setHoras_reales(rs.getInt("horas_reales"));
                p.setFecha_entrega(rs.getDate("fecha_entrega"));
                result.add(p);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int getHorasEstimadasProyectosFromDepartamento(Connection conn, int id_del){

        int horas_estimadas = 0;

        String query = "SELECT pro.horas_estimadas\n" +
                "FROM DELEGACION del, DEPARTAMENTO dep, EMPLEADO e, pertenece p, PROYECTO pro, realiza r\n" +
                "WHERE del.id_del = p.id_del\n" +
                "AND p.id_dep = dep.id_dep\n" +
                "AND dep.id_dep = e.id_dep\n" +
                "AND e.id_emp = r.id_emp\n" +
                "AND r.id_proyecto = pro.id_proyecto\n" +
                "AND del.id_del = " + id_del;

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next())
                horas_estimadas += rs.getInt("horas_estimadas");

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return horas_estimadas;
    }

    public static List<Inventario> getInventariosFromDelegacion(Connection conn, int id_del){

        List<Inventario> result = new ArrayList<>();

        String query = "SELECT i.id_inv, i.elemento, i.descripcion, i.id_categoria, i.precio\n" +
                "FROM DELEGACION del, pertenece p, DEPARTAMENTO dep, tiene t, INVENTARIO i\n" +
                "WHERE del.id_del = p.id_del\n" +
                "AND p.id_dep = dep.id_dep\n" +
                "AND dep.id_dep = t.id_dep\n" +
                "AND t.id_inv = i.id_inv\n" +
                "AND del.id_del = " + id_del;

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {

                Inventario i = new Inventario();
                i.setId_inv(rs.getInt("id_inv"));
                i.setElemento(rs.getString("elemento"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setId_categoria(rs.getInt("id_categoria"));
                i.setPrecio(rs.getDouble("precio"));
                result.add(i);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Puesto> getPuestosFromDelegacion(Connection conn, int id_del){

        List<Puesto> result = new ArrayList<>();

        String query = "SELECT pu.id_puesto, pu.nombre\n" +
                "FROM DELEGACION del, DEPARTAMENTO dep, EMPLEADO e, pertenece p, PUESTO pu\n" +
                "WHERE del.id_del = p.id_del\n" +
                "AND p.id_dep = dep.id_dep\n" +
                "AND dep.id_dep = e.id_dep\n" +
                "AND e.id_puesto = pu.id_puesto\n" +
                "AND del.id_del = " + id_del + "\n" +
                "GROUP BY pu.id_puesto";

        try {
            PreparedStatement sentence = conn.prepareStatement(query);
            ResultSet rs = sentence.executeQuery();

            while (rs.next()) {
                Puesto p = new Puesto();
                p.setId_puesto(rs.getInt("id_puesto"));
                p.setNombre(rs.getString("nombre"));
                result.add(p);
            }

            sentence.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
