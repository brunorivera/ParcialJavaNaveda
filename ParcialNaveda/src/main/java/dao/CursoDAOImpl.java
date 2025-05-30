package dao;

import model.Curso;
import util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImpl implements CursoDAO {

    @Override
    public void insertar(Curso curso) {
        String sql = "INSERT INTO curso (nombre, creditos) VALUES (?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.nombre);
            stmt.setInt(2, curso.creditos);
            stmt.executeUpdate();
            System.out.println("Curso insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Curso buscarPorId(int id) {
        String sql = "SELECT * FROM curso WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getInt("creditos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Curso> listarTodos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getInt("creditos")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Curso curso) {
        String sql = "UPDATE curso SET nombre = ?, creditos = ? WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.nombre);
            stmt.setInt(2, curso.creditos);
            stmt.setInt(3, curso.id);
            stmt.executeUpdate();
            System.out.println("Curso actualizado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Curso eliminado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

