package dao;

import model.Inscripcion;
import util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAOImpl implements InscripcionDAO {

    @Override
    public void insertar(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (idAlumno, idCurso) VALUES (?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.idAlumno);
            stmt.setInt(2, inscripcion.idCurso);
            stmt.executeUpdate();
            System.out.println("Inscripción realizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Inscripcion> listarTodas() {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";
        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Inscripcion(
                        rs.getInt("id"),
                        rs.getInt("idAlumno"),
                        rs.getInt("idCurso")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void mostrarInscripcionesConNombres() {
        String sql = """
            SELECT i.id, a.nombre AS nombreAlumno, c.nombre AS nombreCurso
            FROM inscripcion i
            JOIN alumno a ON i.idAlumno = a.id
            JOIN curso c ON i.idCurso = c.id
            """;

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String alumno = rs.getString("nombreAlumno");
                String curso = rs.getString("nombreCurso");
                System.out.println("Inscripción ID: " + id + " | Alumno: " + alumno + " | Curso: " + curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}