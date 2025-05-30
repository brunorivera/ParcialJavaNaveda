package dao;

import model.Alumno;
import util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public void insertar(Alumno alumno) {
        String sql = "INSERT INTO alumno (nombre, edad) VALUES (?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, alumno.nombre);
            stmt.setInt(2, alumno.edad);
            stmt.executeUpdate();
            System.out.println("Alumno insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Alumno buscarPorId(int id) {
        String sql = "SELECT * FROM alumno WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Alumno> listarTodos() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alumno a = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Alumno alumno) {
        String sql = "UPDATE alumno SET nombre = ?, edad = ? WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, alumno.nombre);
            stmt.setInt(2, alumno.edad);
            stmt.setInt(3, alumno.id);
            stmt.executeUpdate();
            System.out.println("Alumno actualizado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM alumno WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Alumno eliminado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
